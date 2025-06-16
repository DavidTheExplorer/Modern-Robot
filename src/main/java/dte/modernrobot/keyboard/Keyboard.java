package dte.modernrobot.keyboard;

public interface Keyboard
{
    /**
     * Taps all the characters in the provided {@code text}, in their appearance order.
     *
     * @param text The text.
     */
    void tap(String text);

    /**
     * Taps all the function keys in the provided {@code array}, in their appearance order.
     *
     * @param keys The keys.
     */
    void tap(FunctionKey... keys);
}