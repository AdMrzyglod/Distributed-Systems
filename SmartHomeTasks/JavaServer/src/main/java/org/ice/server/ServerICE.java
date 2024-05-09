package org.ice.server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

import java.util.*;


public class ServerICE {

    private Communicator communicator;
    private ObjectAdapter adapter;

    private Map<String, List<String>> identifiersForCategory;

    public ServerICE(){
        this.identifiersForCategory = new HashMap<>();
    }


    public void startServer(String[] args, int serverNumber) {
        int status = 0;
        try {

            this.communicator = Util.initialize(args);
            String address = "127.0.0."+serverNumber;
            String port = String.valueOf((10000+20*serverNumber));
            this.adapter = communicator.createObjectAdapterWithEndpoints("Adapter", "tcp -h "+address+" -p "+port+" : udp -h "+address+" -p "+port);
            System.out.println("tcp -h "+address+" -p "+port+" : udp -h "+address+" -p "+port);
            this.adapter.addServantLocator(new ServantLocatorCameraI(this),"camera");
            this.adapter.addServantLocator(new ServantLocatorLampI(this),"lamp");

            this.identifiersForCategory.put("lamp",new ArrayList<>());
            this.identifiersForCategory.put("camera",new ArrayList<>());

            adapter.activate();
            System.out.println("Entering event processing loop...");

            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.print(">>> ");

                if(scanner.hasNextLine()){
                    String command = scanner.nextLine();

                    if(!command.isBlank()){
                        if(command.equals("show")){
                            for(String category: this.identifiersForCategory.keySet()){
                                System.out.println(">>> Category: "+category);
                                for(String id: this.identifiersForCategory.get(category)){
                                    System.out.println(">>> "+id);
                                }
                            }
                        }
                        else if(command.equals("exit")){
                            break;
                        }
                        else{
                            System.out.println(">>> Niepoprawna komenda");
                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
            status = 1;
        }

        if (communicator != null) {
            try {
                communicator.shutdown();
                communicator.destroy();
            } catch (Exception e) {
                e.printStackTrace(System.err);
                status = 1;
            }
        }
        System.exit(status);
    }

    public void addIdentifierToList(String category,String name){
        this.identifiersForCategory.get(category).add(name);
    }

}
