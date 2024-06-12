package org.watches;

import org.apache.zookeeper.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ZNodeWatcher implements Watcher {

    private ZooKeeper zooKeeper;
    private String znode;
    private String[] exec;
    private Process process;
    private ZNodeGUI zNodeGUI;


    public ZNodeWatcher(String hostPort, String znode, String[] exec) throws IOException {
        this.zooKeeper = new ZooKeeper(hostPort, 3000, this);
        this.znode = znode;
        this.exec = exec;
        this.zNodeGUI = new ZNodeGUI(this);
    }


    public void run(){

        try {
            this.zooKeeper.addWatch(this.znode, AddWatchMode.PERSISTENT_RECURSIVE);
            this.zNodeGUI.updateFrame(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.println("Enter data:");
                String message = br.readLine();

                if("exit".equals(message)){
                    if(this.process!=null && this.process.isAlive()){
                        this.process.destroy();
                    }
                    this.zNodeGUI.updateFrame(false);
                    System.exit(0);
                }
                else{
                    System.out.println("Incorrect data");
                }
            }


        } catch (KeeperException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        String path = event.getPath();
        try {
            if (event.getType() == Event.EventType.NodeCreated) {
                if(this.zNodeGUI!=null){
                    this.zNodeGUI.updateFrame(true);
                }
                if(path.equals(this.znode)){
                    this.process = new ProcessBuilder(this.exec).start();
                }
                if(!path.equals(this.znode) && path.startsWith(this.znode)){
                    int nodesNumber = this.zooKeeper.getAllChildrenNumber(this.znode);
                    this.zNodeGUI.updateNodeNumber(nodesNumber);
                }

            } else if (event.getType() == Event.EventType.NodeDeleted) {
                if(this.process!=null && this.process.isAlive() && path.equals(this.znode)){
                    this.process.destroy();
                    if(this.zNodeGUI!=null){
                        this.zNodeGUI.updateFrame(false);
                        this.zNodeGUI.updateNodeNumber(0);
                    }
                }
                if(!path.equals(this.znode) && path.startsWith(this.znode) && this.zooKeeper.exists(this.znode,false)!=null){
                    int nodesNumber = this.zooKeeper.getAllChildrenNumber(this.znode);
                    this.zNodeGUI.updateNodeNumber(nodesNumber);
                }
            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }


    public String printZNodeTree(){
        try {
            if(this.zooKeeper.exists(this.znode,false)==null){
                return "";
            }
            List<String> tree = new ArrayList<>(List.of(this.znode));
            List<String> nodes = new ArrayList<>(List.of(this.znode));

            while(!nodes.isEmpty()){
                String node = nodes.remove(0);
                List<String> currentNodes = this.zooKeeper.getChildren(node,false);
                if(currentNodes.size()==0){
                    continue;
                }
                for(String nextNode: currentNodes){
                    tree.add(node + "/" + nextNode);
                    nodes.add(node + "/" + nextNode);
                }
            }
            tree.sort(String::compareTo);
            StringBuilder stringBuilder = new StringBuilder();

            for (String treeNode : tree) {
                int index = treeNode.lastIndexOf("/");
                if (index - 2 >= 0) {
                    stringBuilder.append(" ".repeat(index+4)).append("|-- ").append(treeNode.substring(index + 1)).append("\n");
                } else {
                    stringBuilder.append(" ".repeat(6)).append(treeNode).append("\n");
                }
            }

            return stringBuilder.toString();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args){
        if (args.length < 3) {
            System.out.println("USAGE: ZNodeWatcher hostPort znode program [args ...]");
            System.exit(2);
        }
        String hostPort = args[0];
        String znode = args[1];
        String[] exec = new String[args.length - 2];
        System.arraycopy(args, 2, exec, 0, exec.length);
        try {
            new ZNodeWatcher(hostPort, znode, exec).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
