package org.ice.server;

import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;
import org.ice.implementations.CameraI;
import org.ice.implementations.LampRGBI;
import org.ice.implementations.LampWithWorkScheduleI;

public class ServantLocatorLampI implements ServantLocator {

    private ServerICE serverICE;

    public ServantLocatorLampI(ServerICE serverICE){
        this.serverICE=serverICE;
    }

    @Override
    public LocateResult locate(Current curr) throws UserException {

        if(curr.id.category.equals("lamp")){
            String name = curr.id.name;
            if(!name.contains("_")){
                return new LocateResult(null,null);
            }
            String[] data = name.split("_",2);
            String type = data[1];

            if(type.equals("RGB")){
                LampRGBI lampRGBI = new LampRGBI();
                curr.adapter.add(lampRGBI,new Identity(name,"lamp"));
                this.serverICE.addIdentifierToList("lamp",name);
                return new LocateResult(lampRGBI,null);
            }
            else if(type.equals("SCHEDULE")){
                LampWithWorkScheduleI lampWithWorkScheduleI = new LampWithWorkScheduleI();
                curr.adapter.add(lampWithWorkScheduleI,new Identity(name,"lamp"));
                this.serverICE.addIdentifierToList("lamp",name);
                return new LocateResult(lampWithWorkScheduleI,null);
            }

        }
        return new LocateResult(null,null);
    }

    @Override
    public void finished(Current curr, Object servant, java.lang.Object cookie) throws UserException {

    }

    @Override
    public void deactivate(String category) {

    }
}
