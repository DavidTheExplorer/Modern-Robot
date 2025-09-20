package dte.modernrobot.keyboard;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTypingSpeed implements TypingSpeed
{
    private final Duration min;
    private final long differenceMillis;

    public RandomTypingSpeed(Duration min, Duration max)
    {
        if(min.compareTo(max) > 0)
            throw new IllegalArgumentException("The minimum duration must be less than the maximum duration!");

        this.min = min;
        this.differenceMillis = max.minus(min).toMillis();
    }

    @Override
    public Duration createNextLetterDelay()
    {
        return this.min.plusMillis(ThreadLocalRandom.current().nextLong(1 + this.differenceMillis));
    }
}
