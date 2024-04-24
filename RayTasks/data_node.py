import ray


@ray.remote
class DataNode:
    def __init__(self, number: int):
        self.data_db = dict()
        self.ready = True
        self.number = number

    def add_artifact_block(self, name: str, block_index: int, block: str):
        if name not in self.data_db:
            self.data_db[name] = dict()

        if block_index in self.data_db[name]:
            print("Blok o id {} już istnieje.".format(block_index))
            return

        self.data_db[name][block_index] = block

    def update_artifact_block(self, name: str, block_index: int, block: str):
        if name not in self.data_db:
            print("Artefakt nie istnieje")
            return

        if block_index not in self.data_db[name]:
            print("Indeks artefaktu nie istnieje")
            return

        self.data_db[name][block_index] = block

    def delete_artifact_block(self, name: str, block_index: int):
        if name not in self.data_db:
            print("Artefakt nie istnieje")
            return

        if block_index not in self.data_db[name]:
            print("Blok nie istnieje")
            return

        self.data_db[name].pop(block_index)

        if len(self.data_db[name]) == 0:
            del self.data_db[name]

    def get_artifact_block(self, name: str, block_index: int):
        if name not in self.data_db:
            print("Artefakt nie istnieje")
            return ""
        if block_index not in self.data_db[name]:
            print("Indeks artefaktu nie istnieje")
            return ""

        return self.data_db[name][block_index]

    def list_all_artifact_block(self):
        details = []
        for name in self.data_db:
            details.append("DataNode {} ,Artifact: {} , Blocks: {}.".format(self.number, name, self.data_db[name]))
        return details

    def list_artifact_blocks(self, name: str):
        if name not in self.data_db:
            return ""
        details = "DataNode {} ,Artifact: {} , Blocks: {}.".format(self.number, name, self.data_db[name])
        return details

    def is_ready(self):
        return self.ready

    def set_ready_status(self, ready_status: bool):
        self.ready = ready_status

    def get_number(self):
        return self.number

    def get_data_db(self):
        return self.data_db

    def clear_data_db(self):
        self.data_db.clear()
        return
