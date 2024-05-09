package org.ice.implementations;

import SmartHome.*;

import com.zeroc.Ice.Current;

import java.util.HashMap;
import java.util.Map;

public class LampWithWorkScheduleI extends LampI implements LampWithWorkSchedule{

    private Map<Day,Hours> plan = new HashMap<>();

    public LampWithWorkScheduleI(){
        for(Day day: Day.values()){
            plan.put(day, new Hours((short) 22, (short) 6));
        }
    }

    @Override
    public Map<Day, Hours> getPlan(Current current) throws PowerStatusError {
        if(super.getStatus(current)==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        return this.plan;
    }

    @Override
    public Hours getDayHours(Day day, Current current) throws PowerStatusError{
        if(super.getStatus(current)==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        return this.plan.get(day);
    }

    @Override
    public void updateDayHours(Day day, Hours hours, Current current) throws InvalidHoursError,PowerStatusError {
        if(super.getStatus(current)==PowerStatus.OFF){
            throw new PowerStatusError("POWER STATUS OFF");
        }
        if(hours.toHour<0 || hours.toHour>23 || hours.fromHour<0 || hours.fromHour>23){
            throw new InvalidHoursError();
        }
        this.plan.put(day,hours);
    }
}
