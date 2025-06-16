package dte.modernrobot.mouse;

public record Point2D(int x, int y)
{
    /**
     * Creates a new point from a string in the format of "<i>(x, y)</i>".
     *
     * @param text The string representation.
     * @return The created point.
     */
    public static Point2D fromString(String text)
    {
        //remove enclosing parentheses
        if(text.startsWith("(") || text.endsWith(")"))
            text = text.substring(1, text.length()-1);

        //removes the space in "X, Y"
        text = text.replace(" ", "");

        //at this point, we're left with "X,Y"
        String[] data = text.split(",");
        int x = Integer.parseInt(data[0]);
        int y = Integer.parseInt(data[1]);

        return new Point2D(x, y);
    }
}
