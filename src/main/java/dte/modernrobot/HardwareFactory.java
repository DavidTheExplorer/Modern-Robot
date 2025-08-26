package dte.modernrobot;

import dte.modernrobot.keyboard.Keyboard;
import dte.modernrobot.keyboard.RoboticKeyboard;
import dte.modernrobot.mouse.Mouse;
import dte.modernrobot.mouse.RoboticMouse;
import dte.modernrobot.robot.ModernRobot;

import java.awt.AWTException;
import java.awt.Robot;

public class HardwareFactory
{
    private static final ModernRobot ROBOT = new ModernRobot(createRobot());

    public static Mouse createMouse()
    {
        return new RoboticMouse(ROBOT);
    }

    public static Keyboard createKeyboard()
    {
        return new RoboticKeyboard(ROBOT);
    }

    private static Robot createRobot()
    {
        try
        {
            return new Robot();
        }
        catch(AWTException exception)
        {
            throw new RuntimeException("Couldn't create a delegate Robot", exception);
        }
    }
}
