package cs301.rafanan.cuscolor;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.graphics.Color;
import android.widget.TextView;

public class ColorTouch implements View.OnTouchListener{
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private TextView showElement;

    public ColorTouch(SeekBar redSeekBar, SeekBar greenSeekBar, SeekBar blueSeekBar, TextView showElement){
        this.redSeekBar = redSeekBar;
        this.greenSeekBar = greenSeekBar;
        this.blueSeekBar = blueSeekBar;
        this.showElement = showElement;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        //Coordinates for Triangle
        float a1 = 500; // Bottom-left point
        float b1 = 550;
        float a2 = 825; // Bottom-right point
        float b2 = 550;
        float a3 = 825; // Top-right point
        float b3 = 300;

        //Trapezoid coordinates
        float x1 = 475;  // Bottom-left corner
        float y1 = 600;
        float x2 = 1225; // Bottom-right corner
        float y2 = 600;
        float x3 = 1075; // Top-right corner
        float y3 = 700;
        float x4 = 625;  // Top-left corner
        float y4 = 700;

        // Mast rectangle bounds
        float mastLeft = 825;
        float mastTop = 300;
        float mastRight = 850;
        float mastBottom = 600;

        // Sea rectangle bounds
        float seaLeft = 0;
        float seaTop = 700;
        float seaRight = 1600;
        float seaBottom = 1100;

        // Detect if the touch is within certain shapes (e.g., triangle, rectangle, trapezoid)
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP) {
            if (isPointInTriangle(x, y, a1, b1, a2, b2, a3, b3)) {
                setSeekBarFromColor(0xA5FF0000);  // Light Red
                setTextView("Sail");
            } else if (isPointInRectangle(x, y, mastLeft, mastTop, mastRight, mastBottom)) {
                setSeekBarFromColor(0xFF000000);
                setTextView("Mast");// Black color for mast
            } else if (isPointInRectangle(x, y, seaLeft, seaTop, seaRight, seaBottom)) {
                setSeekBarFromColor(0xFF0000FF);
                setTextView("Ocean");// Blue color for sea
            } else if(isPointInTrapezoid(x, y, x1, y1, x2, y2, x3, y3, x4, y4)) {
                setSeekBarFromColor(0xFFC4A484);
                setTextView("Ship Hull");
            } else if (isPointInYellowSun(x, y)) {
                setSeekBarFromColor(0xFFFFFF00);
                setTextView("Sun");// Yellow color for the oval
            } else if(isPointInIsland(x, y)) {
                setSeekBarFromColor(0xFF228B22);
                setTextView("Island");// Forest Green color for the island
            }
            else{
                setTextView("");
            }
        }

        return false;
    }

    private void setSeekBarFromColor(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);
    }

    private void setTextView(String TV) {
        showElement.setText(TV);
    }

    public boolean isPointInTriangle(float px, float py, float ax, float ay, float bx, float by, float cx, float cy) {
        // Calculate the vectors
        float v0x = cx - ax;
        float v0y = cy - ay;
        float v1x = bx - ax;
        float v1y = by - ay;
        float v2x = px - ax;
        float v2y = py - ay;

        // Compute dot products
        float dot00 = v0x * v0x + v0y * v0y;
        float dot01 = v0x * v1x + v0y * v1y;
        float dot02 = v0x * v2x + v0y * v2y;
        float dot11 = v1x * v1x + v1y * v1y;
        float dot12 = v1x * v2x + v1y * v2y;

        // Compute barycentric coordinates
        float denom = (dot00 * dot11 - dot01 * dot01);
        if (denom == 0) return false;  // Avoid division by zero
        float invDenom = 1 / denom;
        float u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        float v = (dot00 * dot12 - dot01 * dot02) * invDenom;

        // Check if point is in triangle
        return (u >= 0) && (v >= 0) && (u + v < 1);
    }

    // Helper method to check if a point is inside a rectangle
    public boolean isPointInRectangle(float px, float py, float left, float top, float right, float bottom) {
        return (px >= left && px <= right && py >= top && py <= bottom);
    }

    // Check if the point (px, py) is inside a trapezoid formed by (x1, y1), (x2, y2), (x3, y3), (x4, y4)
    public boolean isPointInTrapezoid(float px, float py, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        // Split the trapezoid into two triangles
        boolean inFirstTriangle = isPointInTriangle(px, py, x1, y1, x2, y2, x3, y3);
        boolean inSecondTriangle = isPointInTriangle(px, py, x1, y1, x3, y3, x4, y4);

        // If the point is in either triangle, it's in the trapezoid
        return inFirstTriangle || inSecondTriangle;
    }

    public boolean isPointInCircle(float px, float py) {
        return true;
    }

    // Check if a point is inside the yellow oval
    public boolean isPointInYellowSun(float px, float py) {
        // Center of the oval
        float h = 200; // Center X
        float k = 200; // Center Y

        // Semi-major and semi-minor axes (radius since it's a circle in this case)
        float a = 100; // Radius along the X-axis
        float b = 100; // Radius along the Y-axis (same as X-axis because it's a circle)

        // Apply the ellipse formula
        return Math.pow((px - h) / a, 2) + Math.pow((py - k) / b, 2) <= 1;
    }

    // Helper method to check if a point is inside the semicircle (island)
    public boolean isPointInIsland(float px, float py) {
        // Center of the circle (semicircle)
        float h = 225;  // Center X (midpoint of 100 and 350)
        float k = 700;  // Center Y (midpoint of 650 and 750)

        // Radius of the circle
        float r = 125;  // Radius (half of 350 - 100)

        // Check if the point is inside the bounding circle
        boolean isInCircle = Math.pow(px - h, 2) + Math.pow(py - k, 2) <= Math.pow(r, 2);

        // Check if the point is in the bottom half of the circle (y >= centerY)
        boolean isInBottomHalf = py >= k;

        // Return true if both conditions are satisfied
        return isInCircle && isInBottomHalf;
    }


}
