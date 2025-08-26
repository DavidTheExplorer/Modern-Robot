package dte.modernrobot.keyboard;

import java.time.Duration;

/**
 * Represents the speed at which a {@link Keyboard} types letters by controlling the delay after each one.
 */
public interface TypingSpeed
{
    Duration createNextLetterDelay();



    TypingSpeed FASTEST = () -> Duration.ZERO;

    static TypingSpeed atDelay(Duration delay)
    {
        return () -> delay;
    }
}
