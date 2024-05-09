//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `smarthome.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package SmartHome;
/**
 * Helper class for marshaling/unmarshaling plan.
 **/

public final class planHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, java.util.Map<Day, Hours> v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.size());
            for(java.util.Map.Entry<Day, Hours> e : v.entrySet())
            {
                Day.ice_write(ostr, e.getKey());
                Hours.ice_write(ostr, e.getValue());
            }
        }
    }

    public static java.util.Map<Day, Hours> read(com.zeroc.Ice.InputStream istr)
    {
        java.util.Map<Day, Hours> v;
        v = new java.util.HashMap<SmartHome.Day, SmartHome.Hours>();
        int sz0 = istr.readSize();
        for(int i0 = 0; i0 < sz0; i0++)
        {
            Day key;
            key = Day.ice_read(istr);
            Hours value;
            value = Hours.ice_read(istr);
            v.put(key, value);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<java.util.Map<Day, Hours>> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Map<Day, Hours> v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            planHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    public static java.util.Optional<java.util.Map<Day, Hours>> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            java.util.Map<Day, Hours> v;
            v = planHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}
