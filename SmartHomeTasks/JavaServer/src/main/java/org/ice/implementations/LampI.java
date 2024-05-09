package org.ice.implementations;

import SmartHome.PowerStatus;
import SmartHome.Lamp;
import SmartHome.InvalidPercentageValue;
import SmartHome.PowerStatusError;
import com.zeroc.Ice.Current;


public class LampI implements Lamp {

    private PowerStatus currentPowerStatus = PowerStatus.ON;
    private short brightness = 100;


    @Override
    public PowerStatus getStatus(Current current) {
        return this.currentPowerStatus;
    }

    @Override
    public void setPowerStatus(PowerStatus status, Current current) throws PowerStatusError {
        if(status == currentPowerStatus){
            throw new PowerStatusError();
        }
        this.currentPowerStatus = status;
    }

    @Override
    public void setBrightnessPercentage(short percent, Current current) throws InvalidPercentageValue,PowerStatusError {
        if(currentPowerStatus==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        if(percent<0 || percent>100){
            throw new InvalidPercentageValue();
        }
        this.brightness = percent;
    }

    @Override
    public short getBrightnessPercentage(Current current) throws PowerStatusError{
        if(currentPowerStatus==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        return this.brightness;
    }
}
