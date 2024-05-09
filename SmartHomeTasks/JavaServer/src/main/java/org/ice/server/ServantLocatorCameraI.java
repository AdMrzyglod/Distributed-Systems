package org.ice.server;

import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;
import org.ice.implementations.CameraI;

public class ServantLocatorCameraI implements ServantLocator {

    private ServerICE serverICE;

    public ServantLocatorCameraI(ServerICE serverICE){
        this.serverICE=serverICE;
    }

    @Override
    public LocateResult locate(Current curr) throws UserException {
        if(curr.id.category.equals("camera")){
            CameraI cameraI = new CameraI();
            curr.adapter.add(cameraI,new Identity(curr.id.name,curr.id.category));
            this.serverICE.addIdentifierToList("camera",curr.id.name);
            return new LocateResult(cameraI,null);
        }
        else{
            return new LocateResult(null,null);
        }
    }

    @Override
    public void finished(Current curr, Object servant, java.lang.Object cookie) throws UserException {

    }

    @Override
    public void deactivate(String category) {

    }
}
