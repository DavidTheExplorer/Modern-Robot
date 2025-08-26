package dte.modernrobot.keyboard;

import dte.modernrobot.RoboticDevice;
import dte.modernrobot.robot.ModernRobot;
import dte.modernrobot.robot.VirtualKeyProvider;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class RoboticKeyboard extends RoboticDevice implements Keyboard
{
    public RoboticKeyboard(ModernRobot robot)
    {
        super(robot);
    }

    @Override
    public void type(String text, TypingSpeed speed)
    {
        Objects.requireNonNull(text, "The text to type cannot be null.");

        int[] virtualKeys = text.chars()
                .flatMap(RoboticKeyboard::toVirtualKeys)
                .toArray();

        press(speed, virtualKeys);
    }

    @Override
    public void press(KeyboardFunction... keys)
    {
        ensureValidKeys(keys);

        int[] virtualKeys = Arrays.stream(keys)
                .mapToInt(VirtualKeyProvider::getFor)
                .toArray();

        press(TypingSpeed.FASTEST, virtualKeys);
    }

    private void press(TypingSpeed speed, int... virtualKeys)
    {
        for(int key : virtualKeys)
            robot().press(speed.createNextLetterDelay(), key);
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