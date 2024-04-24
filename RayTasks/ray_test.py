from time import sleep

import ray
from name_node import NameNode

if ray.is_initialized:
    ray.shutdown()
ray.init(ignore_reinit_error=True)

node = NameNode.remote()

print("Add:")
node.add_artifact.remote("data","njgsfndsgfsuoisfgubiguasodnagonfbsnfnsdanfsabuyfsaiufdfdfdisfbdbdasbidsfibda")
node.list_all_nodes.remote()

sleep(2)

print("Update:")
node.update_artifact.remote("data","sentenceisfdonfdsnfdsanfdsifndsofasnidnfod")
node.list_all_nodes.remote()

sleep(2)

print("Get:")
print(ray.get(node.get_artifact.remote("data")))
node.list_all_nodes.remote()

sleep(2)

print("Delete:")
node.delete_artifact.remote("data")
node.list_all_nodes.remote()

sleep(2)

ray.shutdown()


