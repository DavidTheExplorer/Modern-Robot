package dte.modernrobot.keyboard;

public interface Keyboard
{
    /**
     * Types all the characters in the provided {@code text}, in their appearance order.
     * <p>
     * This method works at maximum speed, so it's not meant to mimic a human.
     * To control the typing speed, use {@link #type(String, TypingSpeed)}.
     *
     * @param text The text.
     */
    default void type(String text)
    {
        type(text, TypingSpeed.FASTEST);
    }

    /**
     * Types all the characters in the provided {@code text}, in their appearance order, at the provided {@code speed}.
     *
     * @param text The text.
     * @param speed The speed.
     */
    void type(String text, TypingSpeed speed);

    /**
     * Presses all the function keys in the provided {@code array}, in their appearance order.
     *
     * @param keys The keys.
     */
    void press(KeyboardFunction... keys);
}