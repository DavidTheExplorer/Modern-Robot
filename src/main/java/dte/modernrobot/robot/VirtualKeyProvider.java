package dte.modernrobot.robot;

import dte.modernrobot.keyboard.FunctionKey;
import dte.modernrobot.mouse.MouseAction;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Map;

public class VirtualKeyProvider
{
    private static final Map<MouseAction, Integer> MOUSE_VIRTUAL_KEYS = Map.of(
            MouseAction.LEFT_CLICK, InputEvent.BUTTON1_DOWN_MASK,
            MouseAction.RIGHT_CLICK, InputEvent.BUTTON3_DOWN_MASK,
            MouseAction.WHEEL_CLICK, InputEvent.BUTTON2_DOWN_MASK
    );

    private static final Map<FunctionKey, Integer> KEYBOARD_FUNCTION_KEYS = Map.of(
            FunctionKey.ENTER, KeyEvent.VK_ENTER,
            FunctionKey.CAPS_LOCK, KeyEvent.VK_CAPS_LOCK
    );

    public static int getFor(MouseAction action)
    {
        return getKey(MOUSE_VIRTUAL_KEYS, action);
    }

    public static int getFor(FunctionKey action)
    {
        return getKey(KEYBOARD_FUNCTION_KEYS, action);
    }

    private static <E extends Enum<?>> int getKey(Map<E, Integer> map, E action)
    {
        Integer key = map.get(action);

        if(key == null)
            throw new IllegalStateException("The provided action(%s) doesn't have a corresponding robot action!".formatted(action));

        return key;
    }
}

