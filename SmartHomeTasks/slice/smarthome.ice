
#ifndef SMARTHOME_ICE
#define SMARTHOME_ICE

module SmartHome
{

    exception InvalidDegreeValue{
        string data = "Degrees out of range.";
    };

    sequence<short> degrees;

    interface Camera{

        idempotent degrees getMaxDegreesHorizontalVertical();
        void setDegrees(short horizontalDegrees, short verticalDegrees) throws InvalidDegreeValue;
        idempotent degrees getCurrentDegreesHorizontalVertical();
        short getBatteryStatus();
    };

    enum PowerStatus { ON, OFF}

    exception PowerStatusError{
        string data = "Power status cannot be changed.";
    }

    exception InvalidPercentageValue{
        string data = "Percentage out of range.";
    }

    interface Lamp{

        idempotent PowerStatus getStatus();
        void setPowerStatus(PowerStatus status) throws PowerStatusError;

        void setBrightnessPercentage(short percent) throws InvalidPercentageValue,PowerStatusError;
        idempotent short getBrightnessPercentage() throws PowerStatusError;
    }


    struct RGB{
        short red;
        short green;
        short blue;
    }

    exception RGBValueError {};

    exception RGBListMaxSizeError {};

    sequence<RGB> colors;

    exception ListColorsIndexOutOfRange {};

    interface LampRGB extends Lamp{

        idempotent colors getColorList() throws PowerStatusError;
        void addColorToList(RGB color) throws RGBValueError,RGBListMaxSizeError,PowerStatusError;
        void deleteColorFromList(int index) throws ListColorsIndexOutOfRange,PowerStatusError;

        idempotent RGB getCurrentColor() throws PowerStatusError;
    }

    enum Day{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    struct Hours{
        short fromHour;
        short toHour;
    }

    dictionary<Day,Hours> plan;

    exception InvalidHoursError {};

    interface LampWithWorkSchedule extends Lamp{

        idempotent plan getPlan() throws PowerStatusError;
        idempotent Hours getDayHours(Day day) throws PowerStatusError;
        void updateDayHours(Day day, Hours hours) throws InvalidHoursError,PowerStatusError;
    }

};

#endif
