# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.10
#
# <auto-generated>
#
# Generated from file `smarthome.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module SmartHome
_M_SmartHome = Ice.openModule('SmartHome')
__name__ = 'SmartHome'

if 'InvalidDegreeValue' not in _M_SmartHome.__dict__:
    _M_SmartHome.InvalidDegreeValue = Ice.createTempClass()
    class InvalidDegreeValue(Ice.UserException):
        def __init__(self, data="Degrees out of range."):
            self.data = data

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHome::InvalidDegreeValue'

    _M_SmartHome._t_InvalidDegreeValue = IcePy.defineException('::SmartHome::InvalidDegreeValue', InvalidDegreeValue, (), False, None, (('data', (), IcePy._t_string, False, 0),))
    InvalidDegreeValue._ice_type = _M_SmartHome._t_InvalidDegreeValue

    _M_SmartHome.InvalidDegreeValue = InvalidDegreeValue
    del InvalidDegreeValue

if '_t_degrees' not in _M_SmartHome.__dict__:
    _M_SmartHome._t_degrees = IcePy.defineSequence('::SmartHome::degrees', (), IcePy._t_short)

_M_SmartHome._t_Camera = IcePy.defineValue('::SmartHome::Camera', Ice.Value, -1, (), False, True, None, ())

if 'CameraPrx' not in _M_SmartHome.__dict__:
    _M_SmartHome.CameraPrx = Ice.createTempClass()
    class CameraPrx(Ice.ObjectPrx):

        def getMaxDegreesHorizontalVertical(self, context=None):
            return _M_SmartHome.Camera._op_getMaxDegreesHorizontalVertical.invoke(self, ((), context))

        def getMaxDegreesHorizontalVerticalAsync(self, context=None):
            return _M_SmartHome.Camera._op_getMaxDegreesHorizontalVertical.invokeAsync(self, ((), context))

        def begin_getMaxDegreesHorizontalVertical(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.Camera._op_getMaxDegreesHorizontalVertical.begin(self, ((), _response, _ex, _sent, context))

        def end_getMaxDegreesHorizontalVertical(self, _r):
            return _M_SmartHome.Camera._op_getMaxDegreesHorizontalVertical.end(self, _r)

        def setDegrees(self, horizontalDegrees, verticalDegrees, context=None):
            return _M_SmartHome.Camera._op_setDegrees.invoke(self, ((horizontalDegrees, verticalDegrees), context))

        def setDegreesAsync(self, horizontalDegrees, verticalDegrees, context=None):
            return _M_SmartHome.Camera._op_setDegrees.invokeAsync(self, ((horizontalDegrees, verticalDegrees), context))

        def begin_setDegrees(self, horizontalDegrees, verticalDegrees, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.Camera._op_setDegrees.begin(self, ((horizontalDegrees, verticalDegrees), _response, _ex, _sent, context))

        def end_setDegrees(self, _r):
            return _M_SmartHome.Camera._op_setDegrees.end(self, _r)

        def getCurrentDegreesHorizontalVertical(self, context=None):
            return _M_SmartHome.Camera._op_getCurrentDegreesHorizontalVertical.invoke(self, ((), context))

        def getCurrentDegreesHorizontalVerticalAsync(self, context=None):
            return _M_SmartHome.Camera._op_getCurrentDegreesHorizontalVertical.invokeAsync(self, ((), context))

        def begin_getCurrentDegreesHorizontalVertical(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.Camera._op_getCurrentDegreesHorizontalVertical.begin(self, ((), _response, _ex, _sent, context))

        def end_getCurrentDegreesHorizontalVertical(self, _r):
            return _M_SmartHome.Camera._op_getCurrentDegreesHorizontalVertical.end(self, _r)

        def getBatteryStatus(self, context=None):
            return _M_SmartHome.Camera._op_getBatteryStatus.invoke(self, ((), context))

        def getBatteryStatusAsync(self, context=None):
            return _M_SmartHome.Camera._op_getBatteryStatus.invokeAsync(self, ((), context))

        def begin_getBatteryStatus(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.Camera._op_getBatteryStatus.begin(self, ((), _response, _ex, _sent, context))

        def end_getBatteryStatus(self, _r):
            return _M_SmartHome.Camera._op_getBatteryStatus.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHome.CameraPrx.ice_checkedCast(proxy, '::SmartHome::Camera', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHome.CameraPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHome::Camera'
    _M_SmartHome._t_CameraPrx = IcePy.defineProxy('::SmartHome::Camera', CameraPrx)

    _M_SmartHome.CameraPrx = CameraPrx
    del CameraPrx

    _M_SmartHome.Camera = Ice.createTempClass()
    class Camera(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHome::Camera')

        def ice_id(self, current=None):
            return '::SmartHome::Camera'

        @staticmethod
        def ice_staticId():
            return '::SmartHome::Camera'

        def getMaxDegreesHorizontalVertical(self, current=None):
            raise NotImplementedError("servant method 'getMaxDegreesHorizontalVertical' not implemented")

        def setDegrees(self, horizontalDegrees, verticalDegrees, current=None):
            raise NotImplementedError("servant method 'setDegrees' not implemented")

        def getCurrentDegreesHorizontalVertical(self, current=None):
            raise NotImplementedError("servant method 'getCurrentDegreesHorizontalVertical' not implemented")

        def getBatteryStatus(self, current=None):
            raise NotImplementedError("servant method 'getBatteryStatus' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHome._t_CameraDisp)

        __repr__ = __str__

    _M_SmartHome._t_CameraDisp = IcePy.defineClass('::SmartHome::Camera', Camera, (), None, ())
    Camera._ice_type = _M_SmartHome._t_CameraDisp

    Camera._op_getMaxDegreesHorizontalVertical = IcePy.Operation('getMaxDegreesHorizontalVertical', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHome._t_degrees, False, 0), ())
    Camera._op_setDegrees = IcePy.Operation('setDegrees', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_short, False, 0), ((), IcePy._t_short, False, 0)), (), None, (_M_SmartHome._t_InvalidDegreeValue,))
    Camera._op_getCurrentDegreesHorizontalVertical = IcePy.Operation('getCurrentDegreesHorizontalVertical', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHome._t_degrees, False, 0), ())
    Camera._op_getBatteryStatus = IcePy.Operation('getBatteryStatus', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), ((), IcePy._t_short, False, 0), ())

    _M_SmartHome.Camera = Camera
    del Camera

if 'PowerStatus' not in _M_SmartHome.__dict__:
    _M_SmartHome.PowerStatus = Ice.createTempClass()
    class PowerStatus(Ice.EnumBase):

        def __init__(self, _n, _v):
            Ice.EnumBase.__init__(self, _n, _v)

        def valueOf(self, _n):
            if _n in self._enumerators:
                return self._enumerators[_n]
            return None
        valueOf = classmethod(valueOf)

    PowerStatus.ON = PowerStatus("ON", 0)
    PowerStatus.OFF = PowerStatus("OFF", 1)
    PowerStatus._enumerators = { 0:PowerStatus.ON, 1:PowerStatus.OFF }

    _M_SmartHome._t_PowerStatus = IcePy.defineEnum('::SmartHome::PowerStatus', PowerStatus, (), PowerStatus._enumerators)

    _M_SmartHome.PowerStatus = PowerStatus
    del PowerStatus

if 'PowerStatusError' not in _M_SmartHome.__dict__:
    _M_SmartHome.PowerStatusError = Ice.createTempClass()
    class PowerStatusError(Ice.UserException):
        def __init__(self, data="Power status cannot be changed."):
            self.data = data

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHome::PowerStatusError'

    _M_SmartHome._t_PowerStatusError = IcePy.defineException('::SmartHome::PowerStatusError', PowerStatusError, (), False, None, (('data', (), IcePy._t_string, False, 0),))
    PowerStatusError._ice_type = _M_SmartHome._t_PowerStatusError

    _M_SmartHome.PowerStatusError = PowerStatusError
    del PowerStatusError

if 'InvalidPercentageValue' not in _M_SmartHome.__dict__:
    _M_SmartHome.InvalidPercentageValue = Ice.createTempClass()
    class InvalidPercentageValue(Ice.UserException):
        def __init__(self, data="Percentage out of range."):
            self.data = data

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHome::InvalidPercentageValue'

    _M_SmartHome._t_InvalidPercentageValue = IcePy.defineException('::SmartHome::InvalidPercentageValue', InvalidPercentageValue, (), False, None, (('data', (), IcePy._t_string, False, 0),))
    InvalidPercentageValue._ice_type = _M_SmartHome._t_InvalidPercentageValue

    _M_SmartHome.InvalidPercentageValue = InvalidPercentageValue
    del InvalidPercentageValue

_M_SmartHome._t_Lamp = IcePy.defineValue('::SmartHome::Lamp', Ice.Value, -1, (), False, True, None, ())

if 'LampPrx' not in _M_SmartHome.__dict__:
    _M_SmartHome.LampPrx = Ice.createTempClass()
    class LampPrx(Ice.ObjectPrx):

        def getStatus(self, context=None):
            return _M_SmartHome.Lamp._op_getStatus.invoke(self, ((), context))

        def getStatusAsync(self, context=None):
            return _M_SmartHome.Lamp._op_getStatus.invokeAsync(self, ((), context))

        def begin_getStatus(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.Lamp._op_getStatus.begin(self, ((), _response, _ex, _sent, context))

        def end_getStatus(self, _r):
            return _M_SmartHome.Lamp._op_getStatus.end(self, _r)

        def setPowerStatus(self, status, context=None):
            return _M_SmartHome.Lamp._op_setPowerStatus.invoke(self, ((status, ), context))

        def setPowerStatusAsync(self, status, context=None):
            return _M_SmartHome.Lamp._op_setPowerStatus.invokeAsync(self, ((status, ), context))

        def begin_setPowerStatus(self, status, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.Lamp._op_setPowerStatus.begin(self, ((status, ), _response, _ex, _sent, context))

        def end_setPowerStatus(self, _r):
            return _M_SmartHome.Lamp._op_setPowerStatus.end(self, _r)

        def setBrightnessPercentage(self, percent, context=None):
            return _M_SmartHome.Lamp._op_setBrightnessPercentage.invoke(self, ((percent, ), context))

        def setBrightnessPercentageAsync(self, percent, context=None):
            return _M_SmartHome.Lamp._op_setBrightnessPercentage.invokeAsync(self, ((percent, ), context))

        def begin_setBrightnessPercentage(self, percent, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.Lamp._op_setBrightnessPercentage.begin(self, ((percent, ), _response, _ex, _sent, context))

        def end_setBrightnessPercentage(self, _r):
            return _M_SmartHome.Lamp._op_setBrightnessPercentage.end(self, _r)

        def getBrightnessPercentage(self, context=None):
            return _M_SmartHome.Lamp._op_getBrightnessPercentage.invoke(self, ((), context))

        def getBrightnessPercentageAsync(self, context=None):
            return _M_SmartHome.Lamp._op_getBrightnessPercentage.invokeAsync(self, ((), context))

        def begin_getBrightnessPercentage(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.Lamp._op_getBrightnessPercentage.begin(self, ((), _response, _ex, _sent, context))

        def end_getBrightnessPercentage(self, _r):
            return _M_SmartHome.Lamp._op_getBrightnessPercentage.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHome.LampPrx.ice_checkedCast(proxy, '::SmartHome::Lamp', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHome.LampPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHome::Lamp'
    _M_SmartHome._t_LampPrx = IcePy.defineProxy('::SmartHome::Lamp', LampPrx)

    _M_SmartHome.LampPrx = LampPrx
    del LampPrx

    _M_SmartHome.Lamp = Ice.createTempClass()
    class Lamp(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHome::Lamp')

        def ice_id(self, current=None):
            return '::SmartHome::Lamp'

        @staticmethod
        def ice_staticId():
            return '::SmartHome::Lamp'

        def getStatus(self, current=None):
            raise NotImplementedError("servant method 'getStatus' not implemented")

        def setPowerStatus(self, status, current=None):
            raise NotImplementedError("servant method 'setPowerStatus' not implemented")

        def setBrightnessPercentage(self, percent, current=None):
            raise NotImplementedError("servant method 'setBrightnessPercentage' not implemented")

        def getBrightnessPercentage(self, current=None):
            raise NotImplementedError("servant method 'getBrightnessPercentage' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHome._t_LampDisp)

        __repr__ = __str__

    _M_SmartHome._t_LampDisp = IcePy.defineClass('::SmartHome::Lamp', Lamp, (), None, ())
    Lamp._ice_type = _M_SmartHome._t_LampDisp

    Lamp._op_getStatus = IcePy.Operation('getStatus', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHome._t_PowerStatus, False, 0), ())
    Lamp._op_setPowerStatus = IcePy.Operation('setPowerStatus', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_SmartHome._t_PowerStatus, False, 0),), (), None, (_M_SmartHome._t_PowerStatusError,))
    Lamp._op_setBrightnessPercentage = IcePy.Operation('setBrightnessPercentage', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_short, False, 0),), (), None, (_M_SmartHome._t_InvalidPercentageValue, _M_SmartHome._t_PowerStatusError))
    Lamp._op_getBrightnessPercentage = IcePy.Operation('getBrightnessPercentage', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), IcePy._t_short, False, 0), (_M_SmartHome._t_PowerStatusError,))

    _M_SmartHome.Lamp = Lamp
    del Lamp

if 'RGB' not in _M_SmartHome.__dict__:
    _M_SmartHome.RGB = Ice.createTempClass()
    class RGB(object):
        def __init__(self, red=0, green=0, blue=0):
            self.red = red
            self.green = green
            self.blue = blue

        def __hash__(self):
            _h = 0
            _h = 5 * _h + Ice.getHash(self.red)
            _h = 5 * _h + Ice.getHash(self.green)
            _h = 5 * _h + Ice.getHash(self.blue)
            return _h % 0x7fffffff

        def __compare(self, other):
            if other is None:
                return 1
            elif not isinstance(other, _M_SmartHome.RGB):
                return NotImplemented
            else:
                if self.red is None or other.red is None:
                    if self.red != other.red:
                        return (-1 if self.red is None else 1)
                else:
                    if self.red < other.red:
                        return -1
                    elif self.red > other.red:
                        return 1
                if self.green is None or other.green is None:
                    if self.green != other.green:
                        return (-1 if self.green is None else 1)
                else:
                    if self.green < other.green:
                        return -1
                    elif self.green > other.green:
                        return 1
                if self.blue is None or other.blue is None:
                    if self.blue != other.blue:
                        return (-1 if self.blue is None else 1)
                else:
                    if self.blue < other.blue:
                        return -1
                    elif self.blue > other.blue:
                        return 1
                return 0

        def __lt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r < 0

        def __le__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r <= 0

        def __gt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r > 0

        def __ge__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r >= 0

        def __eq__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r == 0

        def __ne__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r != 0

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHome._t_RGB)

        __repr__ = __str__

    _M_SmartHome._t_RGB = IcePy.defineStruct('::SmartHome::RGB', RGB, (), (
        ('red', (), IcePy._t_short),
        ('green', (), IcePy._t_short),
        ('blue', (), IcePy._t_short)
    ))

    _M_SmartHome.RGB = RGB
    del RGB

if 'RGBValueError' not in _M_SmartHome.__dict__:
    _M_SmartHome.RGBValueError = Ice.createTempClass()
    class RGBValueError(Ice.UserException):
        def __init__(self):
            pass

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHome::RGBValueError'

    _M_SmartHome._t_RGBValueError = IcePy.defineException('::SmartHome::RGBValueError', RGBValueError, (), False, None, ())
    RGBValueError._ice_type = _M_SmartHome._t_RGBValueError

    _M_SmartHome.RGBValueError = RGBValueError
    del RGBValueError

if 'RGBListMaxSizeError' not in _M_SmartHome.__dict__:
    _M_SmartHome.RGBListMaxSizeError = Ice.createTempClass()
    class RGBListMaxSizeError(Ice.UserException):
        def __init__(self):
            pass

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHome::RGBListMaxSizeError'

    _M_SmartHome._t_RGBListMaxSizeError = IcePy.defineException('::SmartHome::RGBListMaxSizeError', RGBListMaxSizeError, (), False, None, ())
    RGBListMaxSizeError._ice_type = _M_SmartHome._t_RGBListMaxSizeError

    _M_SmartHome.RGBListMaxSizeError = RGBListMaxSizeError
    del RGBListMaxSizeError

if '_t_colors' not in _M_SmartHome.__dict__:
    _M_SmartHome._t_colors = IcePy.defineSequence('::SmartHome::colors', (), _M_SmartHome._t_RGB)

if 'ListColorsIndexOutOfRange' not in _M_SmartHome.__dict__:
    _M_SmartHome.ListColorsIndexOutOfRange = Ice.createTempClass()
    class ListColorsIndexOutOfRange(Ice.UserException):
        def __init__(self):
            pass

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHome::ListColorsIndexOutOfRange'

    _M_SmartHome._t_ListColorsIndexOutOfRange = IcePy.defineException('::SmartHome::ListColorsIndexOutOfRange', ListColorsIndexOutOfRange, (), False, None, ())
    ListColorsIndexOutOfRange._ice_type = _M_SmartHome._t_ListColorsIndexOutOfRange

    _M_SmartHome.ListColorsIndexOutOfRange = ListColorsIndexOutOfRange
    del ListColorsIndexOutOfRange

_M_SmartHome._t_LampRGB = IcePy.defineValue('::SmartHome::LampRGB', Ice.Value, -1, (), False, True, None, ())

if 'LampRGBPrx' not in _M_SmartHome.__dict__:
    _M_SmartHome.LampRGBPrx = Ice.createTempClass()
    class LampRGBPrx(_M_SmartHome.LampPrx):

        def getColorList(self, context=None):
            return _M_SmartHome.LampRGB._op_getColorList.invoke(self, ((), context))

        def getColorListAsync(self, context=None):
            return _M_SmartHome.LampRGB._op_getColorList.invokeAsync(self, ((), context))

        def begin_getColorList(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.LampRGB._op_getColorList.begin(self, ((), _response, _ex, _sent, context))

        def end_getColorList(self, _r):
            return _M_SmartHome.LampRGB._op_getColorList.end(self, _r)

        def addColorToList(self, color, context=None):
            return _M_SmartHome.LampRGB._op_addColorToList.invoke(self, ((color, ), context))

        def addColorToListAsync(self, color, context=None):
            return _M_SmartHome.LampRGB._op_addColorToList.invokeAsync(self, ((color, ), context))

        def begin_addColorToList(self, color, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.LampRGB._op_addColorToList.begin(self, ((color, ), _response, _ex, _sent, context))

        def end_addColorToList(self, _r):
            return _M_SmartHome.LampRGB._op_addColorToList.end(self, _r)

        def deleteColorFromList(self, index, context=None):
            return _M_SmartHome.LampRGB._op_deleteColorFromList.invoke(self, ((index, ), context))

        def deleteColorFromListAsync(self, index, context=None):
            return _M_SmartHome.LampRGB._op_deleteColorFromList.invokeAsync(self, ((index, ), context))

        def begin_deleteColorFromList(self, index, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.LampRGB._op_deleteColorFromList.begin(self, ((index, ), _response, _ex, _sent, context))

        def end_deleteColorFromList(self, _r):
            return _M_SmartHome.LampRGB._op_deleteColorFromList.end(self, _r)

        def getCurrentColor(self, context=None):
            return _M_SmartHome.LampRGB._op_getCurrentColor.invoke(self, ((), context))

        def getCurrentColorAsync(self, context=None):
            return _M_SmartHome.LampRGB._op_getCurrentColor.invokeAsync(self, ((), context))

        def begin_getCurrentColor(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.LampRGB._op_getCurrentColor.begin(self, ((), _response, _ex, _sent, context))

        def end_getCurrentColor(self, _r):
            return _M_SmartHome.LampRGB._op_getCurrentColor.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHome.LampRGBPrx.ice_checkedCast(proxy, '::SmartHome::LampRGB', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHome.LampRGBPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHome::LampRGB'
    _M_SmartHome._t_LampRGBPrx = IcePy.defineProxy('::SmartHome::LampRGB', LampRGBPrx)

    _M_SmartHome.LampRGBPrx = LampRGBPrx
    del LampRGBPrx

    _M_SmartHome.LampRGB = Ice.createTempClass()
    class LampRGB(_M_SmartHome.Lamp):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHome::Lamp', '::SmartHome::LampRGB')

        def ice_id(self, current=None):
            return '::SmartHome::LampRGB'

        @staticmethod
        def ice_staticId():
            return '::SmartHome::LampRGB'

        def getColorList(self, current=None):
            raise NotImplementedError("servant method 'getColorList' not implemented")

        def addColorToList(self, color, current=None):
            raise NotImplementedError("servant method 'addColorToList' not implemented")

        def deleteColorFromList(self, index, current=None):
            raise NotImplementedError("servant method 'deleteColorFromList' not implemented")

        def getCurrentColor(self, current=None):
            raise NotImplementedError("servant method 'getCurrentColor' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHome._t_LampRGBDisp)

        __repr__ = __str__

    _M_SmartHome._t_LampRGBDisp = IcePy.defineClass('::SmartHome::LampRGB', LampRGB, (), None, (_M_SmartHome._t_LampDisp,))
    LampRGB._ice_type = _M_SmartHome._t_LampRGBDisp

    LampRGB._op_getColorList = IcePy.Operation('getColorList', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHome._t_colors, False, 0), (_M_SmartHome._t_PowerStatusError,))
    LampRGB._op_addColorToList = IcePy.Operation('addColorToList', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_SmartHome._t_RGB, False, 0),), (), None, (_M_SmartHome._t_RGBValueError, _M_SmartHome._t_RGBListMaxSizeError, _M_SmartHome._t_PowerStatusError))
    LampRGB._op_deleteColorFromList = IcePy.Operation('deleteColorFromList', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_int, False, 0),), (), None, (_M_SmartHome._t_ListColorsIndexOutOfRange, _M_SmartHome._t_PowerStatusError))
    LampRGB._op_getCurrentColor = IcePy.Operation('getCurrentColor', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHome._t_RGB, False, 0), (_M_SmartHome._t_PowerStatusError,))

    _M_SmartHome.LampRGB = LampRGB
    del LampRGB

if 'Day' not in _M_SmartHome.__dict__:
    _M_SmartHome.Day = Ice.createTempClass()
    class Day(Ice.EnumBase):

        def __init__(self, _n, _v):
            Ice.EnumBase.__init__(self, _n, _v)

        def valueOf(self, _n):
            if _n in self._enumerators:
                return self._enumerators[_n]
            return None
        valueOf = classmethod(valueOf)

    Day.MONDAY = Day("MONDAY", 0)
    Day.TUESDAY = Day("TUESDAY", 1)
    Day.WEDNESDAY = Day("WEDNESDAY", 2)
    Day.THURSDAY = Day("THURSDAY", 3)
    Day.FRIDAY = Day("FRIDAY", 4)
    Day.SATURDAY = Day("SATURDAY", 5)
    Day.SUNDAY = Day("SUNDAY", 6)
    Day._enumerators = { 0:Day.MONDAY, 1:Day.TUESDAY, 2:Day.WEDNESDAY, 3:Day.THURSDAY, 4:Day.FRIDAY, 5:Day.SATURDAY, 6:Day.SUNDAY }

    _M_SmartHome._t_Day = IcePy.defineEnum('::SmartHome::Day', Day, (), Day._enumerators)

    _M_SmartHome.Day = Day
    del Day

if 'Hours' not in _M_SmartHome.__dict__:
    _M_SmartHome.Hours = Ice.createTempClass()
    class Hours(object):
        def __init__(self, fromHour=0, toHour=0):
            self.fromHour = fromHour
            self.toHour = toHour

        def __hash__(self):
            _h = 0
            _h = 5 * _h + Ice.getHash(self.fromHour)
            _h = 5 * _h + Ice.getHash(self.toHour)
            return _h % 0x7fffffff

        def __compare(self, other):
            if other is None:
                return 1
            elif not isinstance(other, _M_SmartHome.Hours):
                return NotImplemented
            else:
                if self.fromHour is None or other.fromHour is None:
                    if self.fromHour != other.fromHour:
                        return (-1 if self.fromHour is None else 1)
                else:
                    if self.fromHour < other.fromHour:
                        return -1
                    elif self.fromHour > other.fromHour:
                        return 1
                if self.toHour is None or other.toHour is None:
                    if self.toHour != other.toHour:
                        return (-1 if self.toHour is None else 1)
                else:
                    if self.toHour < other.toHour:
                        return -1
                    elif self.toHour > other.toHour:
                        return 1
                return 0

        def __lt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r < 0

        def __le__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r <= 0

        def __gt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r > 0

        def __ge__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r >= 0

        def __eq__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r == 0

        def __ne__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r != 0

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHome._t_Hours)

        __repr__ = __str__

    _M_SmartHome._t_Hours = IcePy.defineStruct('::SmartHome::Hours', Hours, (), (
        ('fromHour', (), IcePy._t_short),
        ('toHour', (), IcePy._t_short)
    ))

    _M_SmartHome.Hours = Hours
    del Hours

if '_t_plan' not in _M_SmartHome.__dict__:
    _M_SmartHome._t_plan = IcePy.defineDictionary('::SmartHome::plan', (), _M_SmartHome._t_Day, _M_SmartHome._t_Hours)

if 'InvalidHoursError' not in _M_SmartHome.__dict__:
    _M_SmartHome.InvalidHoursError = Ice.createTempClass()
    class InvalidHoursError(Ice.UserException):
        def __init__(self):
            pass

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHome::InvalidHoursError'

    _M_SmartHome._t_InvalidHoursError = IcePy.defineException('::SmartHome::InvalidHoursError', InvalidHoursError, (), False, None, ())
    InvalidHoursError._ice_type = _M_SmartHome._t_InvalidHoursError

    _M_SmartHome.InvalidHoursError = InvalidHoursError
    del InvalidHoursError

_M_SmartHome._t_LampWithWorkSchedule = IcePy.defineValue('::SmartHome::LampWithWorkSchedule', Ice.Value, -1, (), False, True, None, ())

if 'LampWithWorkSchedulePrx' not in _M_SmartHome.__dict__:
    _M_SmartHome.LampWithWorkSchedulePrx = Ice.createTempClass()
    class LampWithWorkSchedulePrx(_M_SmartHome.LampPrx):

        def getPlan(self, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_getPlan.invoke(self, ((), context))

        def getPlanAsync(self, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_getPlan.invokeAsync(self, ((), context))

        def begin_getPlan(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_getPlan.begin(self, ((), _response, _ex, _sent, context))

        def end_getPlan(self, _r):
            return _M_SmartHome.LampWithWorkSchedule._op_getPlan.end(self, _r)

        def getDayHours(self, day, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_getDayHours.invoke(self, ((day, ), context))

        def getDayHoursAsync(self, day, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_getDayHours.invokeAsync(self, ((day, ), context))

        def begin_getDayHours(self, day, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_getDayHours.begin(self, ((day, ), _response, _ex, _sent, context))

        def end_getDayHours(self, _r):
            return _M_SmartHome.LampWithWorkSchedule._op_getDayHours.end(self, _r)

        def updateDayHours(self, day, hours, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_updateDayHours.invoke(self, ((day, hours), context))

        def updateDayHoursAsync(self, day, hours, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_updateDayHours.invokeAsync(self, ((day, hours), context))

        def begin_updateDayHours(self, day, hours, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHome.LampWithWorkSchedule._op_updateDayHours.begin(self, ((day, hours), _response, _ex, _sent, context))

        def end_updateDayHours(self, _r):
            return _M_SmartHome.LampWithWorkSchedule._op_updateDayHours.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHome.LampWithWorkSchedulePrx.ice_checkedCast(proxy, '::SmartHome::LampWithWorkSchedule', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHome.LampWithWorkSchedulePrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHome::LampWithWorkSchedule'
    _M_SmartHome._t_LampWithWorkSchedulePrx = IcePy.defineProxy('::SmartHome::LampWithWorkSchedule', LampWithWorkSchedulePrx)

    _M_SmartHome.LampWithWorkSchedulePrx = LampWithWorkSchedulePrx
    del LampWithWorkSchedulePrx

    _M_SmartHome.LampWithWorkSchedule = Ice.createTempClass()
    class LampWithWorkSchedule(_M_SmartHome.Lamp):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHome::Lamp', '::SmartHome::LampWithWorkSchedule')

        def ice_id(self, current=None):
            return '::SmartHome::LampWithWorkSchedule'

        @staticmethod
        def ice_staticId():
            return '::SmartHome::LampWithWorkSchedule'

        def getPlan(self, current=None):
            raise NotImplementedError("servant method 'getPlan' not implemented")

        def getDayHours(self, day, current=None):
            raise NotImplementedError("servant method 'getDayHours' not implemented")

        def updateDayHours(self, day, hours, current=None):
            raise NotImplementedError("servant method 'updateDayHours' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHome._t_LampWithWorkScheduleDisp)

        __repr__ = __str__

    _M_SmartHome._t_LampWithWorkScheduleDisp = IcePy.defineClass('::SmartHome::LampWithWorkSchedule', LampWithWorkSchedule, (), None, (_M_SmartHome._t_LampDisp,))
    LampWithWorkSchedule._ice_type = _M_SmartHome._t_LampWithWorkScheduleDisp

    LampWithWorkSchedule._op_getPlan = IcePy.Operation('getPlan', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHome._t_plan, False, 0), (_M_SmartHome._t_PowerStatusError,))
    LampWithWorkSchedule._op_getDayHours = IcePy.Operation('getDayHours', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (((), _M_SmartHome._t_Day, False, 0),), (), ((), _M_SmartHome._t_Hours, False, 0), (_M_SmartHome._t_PowerStatusError,))
    LampWithWorkSchedule._op_updateDayHours = IcePy.Operation('updateDayHours', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_SmartHome._t_Day, False, 0), ((), _M_SmartHome._t_Hours, False, 0)), (), None, (_M_SmartHome._t_InvalidHoursError, _M_SmartHome._t_PowerStatusError))

    _M_SmartHome.LampWithWorkSchedule = LampWithWorkSchedule
    del LampWithWorkSchedule

# End of module SmartHome
