package dte.modernrobot.mouse;

import dte.modernrobot.robot.ModernRobot;
import dte.modernrobot.robot.VirtualKeyProvider;

import java.time.Duration;
import java.util.Objects;

public class RobotMouse implements Mouse
{
    private final ModernRobot robot;

    private static final Duration RELEASE_DELAY = Duration.ofMillis(50);

    public RobotMouse(ModernRobot robot)
    {
        this.robot = robot;
    }

    @Override
    public void perform(MouseAction action)
    {
        Objects.requireNonNull(action, "The provided action cannot be null");

        this.robot.press(RELEASE_DELAY, VirtualKeyProvider.getFor(action));
    }

    @Override
    public void moveCursorTo(Point2D point)
    {
        Objects.requireNonNull(point, "The provided point cannot be null");

        this.robot.moveCursorTo(point.x(), point.y());
    }
}
