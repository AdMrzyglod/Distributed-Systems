package org.ice.implementations;

import SmartHome.*;
import com.zeroc.Ice.Current;

import java.util.ArrayList;
import java.util.List;


public class LampRGBI extends LampI implements LampRGB {

    private short MAX_SIZE = 10;
    private RGB[] color = new RGB[]{new RGB((short) 255, (short) 255, (short) 255)};
    private List<RGB> colors = new ArrayList();
    private long timestamp = System.currentTimeMillis();
    private int colorTime = 10;
    private int currentIndex = 0;


    @Override
    public RGB[] getColorList(Current current) throws PowerStatusError{
        if(super.getStatus(current)==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        if(colors.size()>0){
            return colors.toArray(new RGB[0]);
        }
        return color;
    }

    @Override
    public void addColorToList(RGB color, Current current) throws RGBValueError, RGBListMaxSizeError,PowerStatusError {
        if(super.getStatus(current)==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        if(color.red<0 || color.red>255 || color.green<0 || color.green>255 || color.blue<0 || color.blue>255){
            throw new RGBValueError();
        }
        if(this.colors.size()>=this.MAX_SIZE){
            throw new RGBListMaxSizeError();
        }
        this.colors.add(color);
    }

    @Override
    public void deleteColorFromList(int index, Current current) throws ListColorsIndexOutOfRange,PowerStatusError {
        if(super.getStatus(current)==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        if(index<0 || index>=colors.size()){
            throw new ListColorsIndexOutOfRange();
        }
        this.colors.remove(index);
    }

    @Override
    public RGB getCurrentColor(Current current) throws PowerStatusError{
        if(super.getStatus(current)==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        this.currentIndex += (int)Math.ceil(((double) ((System.currentTimeMillis() - this.timestamp) / 1000) /this.colorTime));
        this.currentIndex %= Math.max(this.colors.size(),1);
        this.timestamp = System.currentTimeMillis();

        if(this.colors.size()>0){
            return this.colors.get(this.currentIndex);
        }
        return color[0];
    }
}
