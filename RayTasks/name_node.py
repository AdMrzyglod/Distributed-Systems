import random
import ray
from data_node import DataNode

NUMBER_OF_NODES = 7
NUMBER_OF_BLOCKS = 4
NUMBER_OF_DUPLICATE_BLOCKS = 3


@ray.remote
class NameNode:
    def __init__(self):
        self.nodes = [DataNode.remote(i) for i in range(NUMBER_OF_NODES)]
        self.artifacts_db = dict()

    def add_artifact(self, name: str, sentence: str):
        if name in self.artifacts_db:
            print("Nazwa artefaktu już istnieje")
            return

        if len(sentence) < NUMBER_OF_BLOCKS:
            print("Długość napisu musi być większa lub równa {}".format(NUMBER_OF_BLOCKS))
            return

        self.artifacts_db[name] = dict()

        for block_index, block in enumerate(self.split_sentence(sentence)):
            self.artifacts_db[name][block_index] = []
            for node_index in self.random_nodes_indexes():
                self.nodes[node_index].add_artifact_block.remote(name, block_index, block)
                self.artifacts_db[name][block_index].append(node_index)

        print("Dodano artefakt: {}".format(name))
        return

    def update_artifact(self, name: str, sentence: str):
        if name not in self.artifacts_db:
            print("Artefakt nie istnieje")
            return

        if len(sentence) < NUMBER_OF_BLOCKS:
            print("Długość napisu musi być większa lub równa {}".format(NUMBER_OF_BLOCKS))
            return

        for block_index, block in enumerate(self.split_sentence(sentence)):
            for node_index in self.artifacts_db[name][block_index]:
                self.nodes[node_index].update_artifact_block.remote(name, block_index, block)

        print("Zaktualizowano artefakt: {}".format(name))
        return

    def delete_artifact(self, name: str):
        if name not in self.artifacts_db:
            print("Artefakt nie isnieje")
            return

        for block_index in self.artifacts_db[name].keys():
            for node_index in self.artifacts_db[name][block_index]:
                self.nodes[node_index].delete_artifact_block.remote(name, block_index)

        self.artifacts_db.pop(name)

        print("Usunięto artefakt {}".format(name))
        return

    def get_artifact(self, name: str):
        if name not in self.artifacts_db:
            print("Artefakt nie istnieje")
            return
        artifact_sentence = []

        for block_index in sorted(self.artifacts_db[name].keys()):
            if len(self.artifacts_db[name][block_index]) > 0:
                node_index = self.artifacts_db[name][block_index][0]
                artifact_sentence.append(self.nodes[node_index].get_artifact_block.remote(name, block_index))

        artifact_sentence = [ray.get(block) for block in artifact_sentence]

        return "".join(artifact_sentence)

    def split_sentence(self, sentence: str):
        block_len = len(sentence) // NUMBER_OF_BLOCKS
        data_split = [sentence[i:i + block_len] for i in range(0, len(sentence), block_len)]
        if len(data_split) > NUMBER_OF_BLOCKS:
            value = data_split[-1]
            data_split = data_split[:-1]
            data_split[-1] += value
        return data_split

    def random_nodes_indexes(self):
        active_nodes = [node_index for node_index in range(NUMBER_OF_NODES) if
                        ray.get(self.nodes[node_index].is_ready.remote())]
        return random.sample(active_nodes, NUMBER_OF_DUPLICATE_BLOCKS) if len(
            active_nodes) >= NUMBER_OF_DUPLICATE_BLOCKS else active_nodes

    def active_node_not_in_table(self, nodes_table: list):

        active_nodes = [node_index for node_index in range(NUMBER_OF_NODES) if
                        node_index not in nodes_table and ray.get(self.nodes[node_index].is_ready.remote())]

        new_node = random.sample(active_nodes, 1)[0] if len(active_nodes) >= 1 else None

        return new_node

    def list_node(self, node_number: int):
        if node_number >= NUMBER_OF_NODES:
            print("DataNode {} nie istnieje".format(node_number))
            return

        if not ray.get(self.nodes[node_number].is_ready.remote()):
            print("DataNode {} jest nieaktywny".format(node_number))
            return

        details = ray.get(self.nodes[node_number].list_all_artifact_block.remote())
        for detail in details:
            print(detail)
        return

    def list_all_nodes(self):
        for node in self.nodes:
            for details in ray.get(node.list_all_artifact_block.remote()):
                print(details)
        return

    def list_artifact_nodes(self, name: str):
        if name not in self.artifacts_db:
            print("Artefakt nie istnieje")
            return

        for node in self.nodes:
            details = ray.get(node.list_artifact_blocks.remote(name))
            if details:
                print(details)
        return

    def list_nodes_statuses(self):
        for i, node in enumerate(self.nodes):
            print("DataNode {} status: {}".format(i, ray.get(node.is_ready.remote())))
        return

    def set_status(self, node_index: int, status: bool):
        node_status = ray.get(self.nodes[node_index].is_ready.remote())
        if node_status == status:
            print("DataNode {} ma już status: {} ".format(node_index, node_status))

        else:
            self.nodes[node_index].set_ready_status.remote(status)

            if node_status:
                data_db = ray.get(self.nodes[node_index].get_data_db.remote())

                for name in data_db:
                    for block_index in data_db[name]:
                        self.artifacts_db[name][block_index].remove(node_index)
                        new_node_number = self.active_node_not_in_table(self.artifacts_db[name][block_index])

                        if new_node_number is None:
                            if len(self.artifacts_db[name][block_index]) == 0:
                                self.artifacts_db[name].pop(block_index)
                                if len(self.artifacts_db[name]) == 0:
                                    self.artifacts_db.pop(name)
                            continue
                        else:
                            self.nodes[new_node_number].add_artifact_block.remote(name, block_index,
                                                                                  data_db[name][block_index])
                            self.artifacts_db[name][block_index].append(new_node_number)

                self.nodes[node_index].clear_data_db.remote()

            else:
                for name in self.artifacts_db:
                    for block_index in self.artifacts_db[name]:
                        if 0 < len(self.artifacts_db[name][block_index]) < NUMBER_OF_DUPLICATE_BLOCKS:
                            self.nodes[node_index].add_artifact_block.remote(name, block_index, ray.get(
                                self.nodes[self.artifacts_db[name][block_index][0]].get_artifact_block.remote(name,
                                                                                                              block_index)))
                            self.artifacts_db[name][block_index].append(node_index)

