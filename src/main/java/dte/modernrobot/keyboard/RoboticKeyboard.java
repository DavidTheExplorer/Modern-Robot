package dte.modernrobot.keyboard;

import dte.modernrobot.RoboticDevice;
import dte.modernrobot.robot.ModernRobot;
import dte.modernrobot.robot.VirtualKeyProvider;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class RoboticKeyboard extends RoboticDevice implements Keyboard
{
    private static final Duration RELEASE_DELAY = Duration.ofMillis(50);

    public RoboticKeyboard(ModernRobot robot)
    {
        super(robot);
    }

    @Override
    public void type(String text)
    {
        Objects.requireNonNull(text, "The text to type cannot be null.");

        int[] virtualKeys = text.chars()
                .flatMap(RoboticKeyboard::toVirtualKeys)
                .toArray();

        press(virtualKeys);
    }

    @Override
    public void press(KeyboardFunction... keys)
    {
        ensureValidKeys(keys);

        int[] virtualKeys = Arrays.stream(keys)
                .mapToInt(VirtualKeyProvider::getFor)
                .toArray();

        press(virtualKeys);
    }

    private void press(int... virtualKeys)
    {
        robot().press(RELEASE_DELAY, virtualKeys);
    }

    private static IntStream toVirtualKeys(int asciiValue)
    {
        int virtualKey = KeyEvent.getExtendedKeyCodeForChar(asciiValue);

        //insert caps lock if the letter is uppercase
        if(Character.isUpperCase(asciiValue))
            return IntStream.of(KeyEvent.VK_CAPS_LOCK, virtualKey, KeyEvent.VK_CAPS_LOCK);

        return IntStream.of(virtualKey);
    }

    private static void ensureValidKeys(KeyboardFunction... keys)
    {
        Objects.requireNonNull(keys, "The keys to tap cannot be null.");

        boolean containsNull = Arrays.stream(keys).anyMatch(Objects::isNull);

        if(containsNull)
            throw new IllegalArgumentException("The provided function array cannot contain any nulls.");
    }
}