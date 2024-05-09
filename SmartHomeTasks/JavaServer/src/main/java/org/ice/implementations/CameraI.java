package org.ice.implementations;


import com.zeroc.Ice.Current;
import SmartHome.Camera;
import SmartHome.InvalidDegreeValue;

public class CameraI implements Camera {

    private short[] maxDegrees = {300,90};

    private short[] currentDegrees = {0,0};

    private short maxBatteryStatus = 100;

    private short secondsPerBatteryPercent = 20;

    private long timestamp = System.currentTimeMillis();

    @Override
    public short[] getMaxDegreesHorizontalVertical(Current current) {
        return this.maxDegrees;
    }

    @Override
    public void setDegrees(short horizontalDegrees, short verticalDegrees, Current current) throws InvalidDegreeValue {

        if(horizontalDegrees<0 || horizontalDegrees>maxDegrees[0] || verticalDegrees<0 || verticalDegrees>maxDegrees[1]){
            throw new InvalidDegreeValue();
        }

        this.currentDegrees[0]=horizontalDegrees;
        this.currentDegrees[1]=verticalDegrees;
    }

    @Override
    public short[] getCurrentDegreesHorizontalVertical(Current current) {
        return this.currentDegrees;
    }

    @Override
    public short getBatteryStatus(Current current) {
        return (short) Math.max(0,(short)Math.floor(this.maxBatteryStatus- (double) ((System.currentTimeMillis() - this.timestamp) / 1000) /this.secondsPerBatteryPercent));
    }
}
