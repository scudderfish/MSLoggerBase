package uk.org.smithfamily.mslogger.widgets;

import android.graphics.Color;

/**
 * Helper class that take a minimum and maximum value and return the corresponding ROYGB (Red-Orange-Yellow-Green-Blue) color in between
 */
public class ZAxisGradient
{
    private double min;
    private double max;
    
    /**
     * Set the minimum and maximum values
     * 
     * @param min Minimum value of the gradient
     * @param max Maximum value of the gradient
     */
    public ZAxisGradient(double min, double max)
    {
        this.min = min;
        this.max = max;
    }
    
    /**
     * From a value between min and max, get an ROYGB color
     * 
     * @param value The value to get the color for
     * @return An integer representing the color of the value
     */
    public int getColorForValue(double value)
    {
        double percent = 1 - ((value - min) / (max - min));
        int r = 0, g = 0, b = 0, x = 0, y = (int) (1020 * percent);
        
        if (y >= 0 && y <= 255)
        {
            r = (int) Math.floor(255 - 128 / 255 * x);
            g = (int) Math.floor(y + (127 - y) / 255 * x);
            b = (int) Math.floor(127 / 255 * x);
        }
        else if (y >= 256 && y <= 510)
        {
            r = (int) Math.floor((255 - (y - 255)) + (127 - (255 - (y - 255))) / 255 * x);
            g = (int) Math.floor(255 - 128 / 255 * x);
            b = (int) Math.floor(127 / 255 * x); 
        }
        else if (y >= 511 && y <= 765)
        {
            r = (int) Math.floor(127 / 255 * x);
            g = (int) Math.floor(255 - 128 / 255 * x);
            b = (int) Math.floor((y - 510) + (127 - (y - 510)) / 255 * x); 
        }
        else if (y >= 766 && y <= 1020)
        {
            r = (int) Math.floor(127 / 255 * x);
            g = (int) Math.floor((255 - (y - 765)) + (127 - (255 - (y - 765))) / 255 * x);
            b = (int) Math.floor(255 - 128 / 255 * x); 
        }

        return Color.rgb(r, g, b);
    }
}
