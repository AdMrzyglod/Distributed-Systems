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

public class InvalidPercentageValue extends com.zeroc.Ice.UserException
{
    public InvalidPercentageValue()
    {
        this.data = "Percentage out of range.";
    }

    public InvalidPercentageValue(Throwable cause)
    {
        super(cause);
        this.data = "Percentage out of range.";
    }

    public InvalidPercentageValue(String data)
    {
        this.data = data;
    }

    public InvalidPercentageValue(String data, Throwable cause)
    {
        super(cause);
        this.data = data;
    }

    public String ice_id()
    {
        return "::SmartHome::InvalidPercentageValue";
    }

    public String data;

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::SmartHome::InvalidPercentageValue", -1, true);
        ostr_.writeString(data);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        data = istr_.readString();
        istr_.endSlice();
    }

    /** @hidden */
    public static final long serialVersionUID = -489797848L;
}
