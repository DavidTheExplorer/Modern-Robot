package dte.modernrobot.mouse;

import dte.modernrobot.RoboticDevice;
import dte.modernrobot.robot.ModernRobot;
import dte.modernrobot.robot.VirtualKeyProvider;

import java.time.Duration;
import java.util.Objects;

public class RoboticMouse extends RoboticDevice implements Mouse
{
    private static final Duration RELEASE_DELAY = Duration.ofMillis(50);

    public RoboticMouse(ModernRobot robot)
    {
        super(robot);
    }

    @Override
    public void perform(MouseAction action)
    {
        Objects.requireNonNull(action, "The provided action cannot be null");

        robot().press(RELEASE_DELAY, VirtualKeyProvider.getFor(action));
    }

    @Override
    public void moveCursorTo(Point2D point)
    {
        Objects.requireNonNull(point, "The provided point cannot be null");

        robot().moveCursorTo(point.x(), point.y());
    }
}
