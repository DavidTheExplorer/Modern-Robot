package dte.modernrobot.keyboard;

import dte.modernrobot.robot.ModernRobot;
import dte.modernrobot.robot.VirtualKeyProvider;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class RobotKeyboard implements Keyboard
{
    private final ModernRobot robot;

    private static final Duration RELEASE_DELAY = Duration.ofMillis(50);

    public RobotKeyboard(ModernRobot robot)
    {
        this.robot = robot;
    }

    @Override
    public void tap(String text)
    {
        Objects.requireNonNull(text, "The text to type cannot be null.");

        int[] virtualKeys = text.chars()
                .flatMap(RobotKeyboard::toVirtualKeys)
                .toArray();

        press(virtualKeys);
    }

    @Override
    public void tap(FunctionKey... keys)
    {
        ensureValidKeys(keys);

        int[] virtualKeys = Arrays.stream(keys)
                .mapToInt(VirtualKeyProvider::getFor)
                .toArray();

        press(virtualKeys);
    }

    private void press(int... virtualKeys)
    {
        this.robot.press(RELEASE_DELAY, virtualKeys);
    }

    private static IntStream toVirtualKeys(int asciiValue)
    {
        int virtualKey = KeyEvent.getExtendedKeyCodeForChar(asciiValue);

        //insert caps lock if the letter is uppercase
        if(Character.isUpperCase(asciiValue))
            return IntStream.of(KeyEvent.VK_CAPS_LOCK, virtualKey, KeyEvent.VK_CAPS_LOCK);

        return IntStream.of(virtualKey);
    }

    private static void ensureValidKeys(FunctionKey... keys)
    {
        Objects.requireNonNull(keys, "The keys to tap cannot be null.");

        boolean containsNull = Arrays.stream(keys).anyMatch(Objects::isNull);

        if(containsNull)
            throw new IllegalArgumentException("The provided function array cannot contain any nulls.");
    }
}