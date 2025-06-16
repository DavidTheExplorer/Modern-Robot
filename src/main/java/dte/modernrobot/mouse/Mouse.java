package dte.modernrobot.mouse;

public interface Mouse
{
    /**
     * Performs the provided {@code action}.
     *
     * @param action The action.
     */
    void perform(MouseAction action);

    /**
     * Moves the cursor to the provided {@code point} on the screen.
     *
     * @param point The point.
     */
    void moveCursorTo(Point2D point);

    /**
     * Moves the cursor to the provided {@code point} on the screen, and then performs the provided {@code action}.
     *
     * @param action The action.
     * @param point The point.
     */
    default void performAt(MouseAction action, Point2D point)
    {
        moveCursorTo(point);
        perform(action);
    }
}
