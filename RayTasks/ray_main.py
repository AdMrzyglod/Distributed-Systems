import ray
from name_node import NameNode

if ray.is_initialized:
    ray.shutdown()
ray.init(ignore_reinit_error=True)

node = NameNode.remote()

while True:
    print()
    command = input(">>> Operation: ")
    if " " in command:
        operation, arguments = command.split(" ", 1)
    else:
        operation, arguments = command, ""
    match operation:
        case "upload":
            if " " in arguments:
                artifact_name, artifact_sentence = arguments.split(" ", 1)
                artifact_sentence = artifact_sentence.strip()
                if artifact_name and artifact_sentence:
                    node.add_artifact.remote(artifact_name, artifact_sentence)
            else:
                print("Niepoprawne argumenty")
        case "update":
            if " " in arguments:
                artifact_name, artifact_sentence = arguments.split(" ", 1)
                artifact_sentence = artifact_sentence.strip()
                if artifact_name and artifact_sentence:
                    node.update_artifact.remote(artifact_name, artifact_sentence)
            else:
                print("Niepoprawne argumenty")
        case "delete":
            artifact_name = arguments.strip()
            if artifact_name:
                node.delete_artifact.remote(artifact_name)
            else:
                print("Niepoprawny argument")
        case "get":
            artifact_name = arguments.strip()
            if artifact_name:
                artifact_sentence = ray.get(node.get_artifact.remote(artifact_name))
                print(artifact_sentence)
            else:
                print("Niepoprawny argument")
        case "list":
            if arguments.startswith("artifact") and " " in arguments:
                type, artifact_name = arguments.split(" ", 1)
                artifact_name = artifact_name.strip()
                if artifact_name:
                    node.list_artifact_nodes.remote(artifact_name)
                else:
                    print("Niepoprawny argument")
            elif arguments.startswith("node") and " " in arguments:
                type, node_number = arguments.split(" ", 1)
                if node_number.isdigit():
                    node_number = int(node_number)
                    node.list_node.remote(node_number)
                else:
                    print("Niepoprawny argument")
            elif arguments.startswith("all"):
                node.list_all_nodes.remote()
            elif arguments.startswith("status"):
                node.list_nodes_statuses.remote()
        case "status":
            if " " in arguments:
                node_number, new_status = arguments.split(" ", 1)
                if node_number.isdigit():
                    node_number = int(node_number)
                    if new_status == "True":
                        new_status = True
                    elif new_status == "False":
                        new_status = False
                    else:
                        print("Niepoprawny argument")
                        continue
                    node.set_status.remote(node_number, new_status)
                else:
                    print("Niepoprawny argument")
            else:
                print("Niepoprawne argumenty")
        case "exit":
            break
        case _:
            print("Nie znaleziono operacji")

ray.shutdown()
