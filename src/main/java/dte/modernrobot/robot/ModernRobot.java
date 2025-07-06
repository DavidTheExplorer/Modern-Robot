package dte.modernrobot.robot;

import dte.modernrobot.utils.KeyEventUtils;

import java.awt.Robot;
import java.time.Duration;

public class ModernRobot
{
    private final Robot delegate;

    public ModernRobot(Robot delegate)
    {
        this.delegate = delegate;
    }

    /**
     * Presses and then releases every key in the provided {@code array}, with a delay between each release.
     *
     * @param releaseDelay The delay to wait after releasing.
     * @param virtualKeys The keys.
     */
    public void press(Duration releaseDelay, int... virtualKeys)
    {
        int msDelay = (int) releaseDelay.toMillis();

        for(int key : virtualKeys)
        {
            press(key);
            release(key);
            this.delegate.delay(msDelay);
        }
    }

    /**
     * Moves the cursor to the specificed {@code x, y} coordinates on the screen.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public void moveCursorTo(int x, int y)
    {
        this.delegate.mouseMove(x, y);
    }

    private void press(int key)
    {
        if(KeyEventUtils.isMouseKey(key))
            this.delegate.mousePress(key);
        else
            this.delegate.keyPress(key);
    }

    private void release(int key)
    {
        if(KeyEventUtils.isMouseKey(key))
            this.delegate.mouseRelease(key);
        else
            this.delegate.keyRelease(key);
    }
}
