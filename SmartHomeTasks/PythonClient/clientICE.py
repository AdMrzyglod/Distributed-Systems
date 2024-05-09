import sys, Ice
import SmartHome

CATEGORIES_TYPES = dict(camera=["CAMERA"], lamp=["RGB", "SCHEDULE"])

try:
    if len(sys.argv) < 2:
        raise IndexError("Podaj numer serwera")
    server_number = int(sys.argv[1])
    address = "127.0.0." + str(server_number)
    port = str(10000 + 20 * server_number)
    proxy_arguments = ":tcp -h " + address + " -p " + port + " : udp -h " + address + " -p " + port

    with Ice.initialize(sys.argv) as communicator:

        current_instance_name = ""
        instance = None

        while True:
            try:
                command = input(">>> Operation: ")
                if " " in command:
                    operation, arguments = command.split(" ", 1)
                else:
                    operation, arguments = command, ""

                match operation:
                    case "categories":
                        print(CATEGORIES_TYPES)
                    case "format":
                        print("[category]/[name]_[type]")
                    case "set":
                        if arguments.count("/") != 1:
                            print("Invalid format identifier")
                            continue
                        else:
                            category, name = arguments.split("/", 1)
                            if category not in CATEGORIES_TYPES:
                                print("Invalid category")
                                continue
                            elif name.count("_") != 1:
                                print("Invalid name format")
                                continue
                            else:
                                instance_name, type = name.split("_", 1)
                                instance_name = instance_name.strip()
                                if not instance_name or type not in CATEGORIES_TYPES[category]:
                                    print("Invalid name format")
                                    continue
                                else:
                                    current_instance_name = arguments
                                    base = communicator.stringToProxy(current_instance_name + proxy_arguments)

                                    if category == "camera":
                                        if type == "CAMERA":
                                            instance = SmartHome.CameraPrx.checkedCast(base)
                                    elif category == "lamp":
                                        if type == "RGB":
                                            instance = SmartHome.LampRGBPrx.checkedCast(base)
                                        elif type == "SCHEDULE":
                                            instance = SmartHome.LampWithWorkSchedulePrx.checkedCast(base)

                    case "camera":
                        if not current_instance_name.startswith("camera"):
                            print("Invalid category")
                        else:
                            if current_instance_name.endswith("CAMERA"):
                                if arguments.startswith("max_degrees"):
                                    print(instance.getMaxDegreesHorizontalVertical())
                                elif arguments.startswith("current_degrees"):
                                    print(instance.getCurrentDegreesHorizontalVertical())
                                elif arguments.startswith("battery_status"):
                                    print(instance.getBatteryStatus())
                                elif arguments.startswith("set_degrees"):
                                    if arguments.count(" ") != 2:
                                        print("Invalid arguments")
                                    else:
                                        function_name, horizontal_degrees, vertical_degrees = arguments.split(" ", 2)
                                        horizontal_degrees, vertical_degrees = int(horizontal_degrees), int(vertical_degrees)
                                        instance.setDegrees(horizontal_degrees, vertical_degrees)
                    case "lamp":
                        if not current_instance_name.startswith("lamp"):
                            print("Invalid category")
                        else:
                            if arguments.startswith("power_status"):
                                print(instance.getStatus())
                            elif arguments.startswith("set_status"):
                                if arguments.count(" ") != 1:
                                    print("Invalid arguments")
                                else:
                                    function_name, status = arguments.split(" ", 1)
                                    status = status.strip()
                                    if status not in ["ON", "OFF"]:
                                        print("Invalid status")
                                    else:
                                        if status=="ON":
                                            final_status = SmartHome.PowerStatus.ON
                                        else:
                                            final_status = SmartHome.PowerStatus.OFF
                                        instance.setPowerStatus(final_status)
                            elif arguments.startswith("brightness"):
                                print(instance.getBrightnessPercentage())
                            elif arguments.startswith("set_brightness"):
                                if arguments.count(" ") != 1:
                                    print("Invalid arguments")
                                else:
                                    function_name, percentage = arguments.split(" ", 1)
                                    percentage = int(percentage)
                                    instance.setBrightnessPercentage(percentage)
                            elif current_instance_name.endswith("RGB"):
                                if arguments.startswith("color_list"):
                                    print(instance.getColorList())
                                elif arguments.startswith("current_color"):
                                    print(instance.getCurrentColor())
                                elif arguments.startswith("add_color"):
                                    if arguments.count(" ") != 3:
                                        print("Invalid arguments")
                                    else:
                                        function_name, red, green, blue = arguments.split(" ", 3)
                                        red, green, blue = int(red), int(green), int(blue)
                                        instance.addColorToList(SmartHome.RGB(red, green, blue))
                                elif arguments.startswith("delete_color"):
                                    if arguments.count(" ") != 1:
                                        print("Invalid arguments")
                                    else:
                                        function_name, index = arguments.split(" ", 1)
                                        index = int(index)
                                        instance.deleteColorFromList(index)

                            elif current_instance_name.endswith("SCHEDULE"):
                                if arguments.startswith("plan"):
                                    print(instance.getPlan())
                                elif arguments.startswith("get_day_hours"):
                                    if arguments.count(" ") != 1:
                                        print("Invalid arguments")
                                    else:
                                        function_name, day_number = arguments.split(" ", 1)
                                        day_number = int(day_number)
                                        if day_number < 0 or day_number > 6:
                                            print("Invalid day number")
                                        else:
                                            print(instance.getDayHours(SmartHome.Day.valueOf(day_number)))
                                elif arguments.startswith("update_day_hours"):
                                    if arguments.count(" ") != 3:
                                        print("Invalid arguments")
                                    else:
                                        function_name, day_number, start_hour, end_hour = arguments.split(" ", 3)
                                        day_number = int(day_number)
                                        if day_number < 0 or day_number > 6:
                                            print("Invalid day number")
                                        start_hour, end_hour = int(start_hour), int(end_hour)
                                        instance.updateDayHours(SmartHome.Day.valueOf(day_number), SmartHome.Hours(start_hour, end_hour))
                    case "exit":
                        break
                    case _:
                        print("Invalid operation")
                print(command)
                print(current_instance_name)
            except Exception as e:
                print(e)
except Exception as e:
    print(e)
