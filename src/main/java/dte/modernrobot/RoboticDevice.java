package dte.modernrobot;

import dte.modernrobot.robot.ModernRobot;

public abstract class RoboticDevice
{
    private final ModernRobot robot;

    protected RoboticDevice(ModernRobot robot)
    {
        this.robot = robot;
    }

    protected ModernRobot robot()
    {
        return this.robot;
    }
}
