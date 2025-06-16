package dte.modernrobot.keyboard;

public interface Keyboard
{
    /**
     * Types all the characters in the provided {@code text}, in their appearance order.
     *
     * @param text The text.
     */
    void type(String text);

    /**
     * Presses all the function keys in the provided {@code array}, in their appearance order.
     *
     * @param keys The keys.
     */
    void press(KeyboardFunction... keys);
}