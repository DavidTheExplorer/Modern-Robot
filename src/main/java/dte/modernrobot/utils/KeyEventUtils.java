package dte.modernrobot.utils;

import java.awt.event.KeyEvent;
import java.util.Set;

public class KeyEventUtils
{
    private static final Set<Integer> MOUSE_KEYS = Set.of(
            KeyEvent.BUTTON1_DOWN_MASK,
            KeyEvent.BUTTON2_DOWN_MASK,
            KeyEvent.BUTTON3_DOWN_MASK
    );

    public static boolean isMouseKey(int virtualKey)
    {
        return MOUSE_KEYS.contains(virtualKey);
    }
}
