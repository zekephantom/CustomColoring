package cs301.rafanan.cuscolor;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.graphics.Color;

public class ColorTouch implements View.OnTouchListener{
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;

    public ColorTouch(SeekBar redSeekBar, SeekBar greenSeekBar, SeekBar blueSeekBar){
        this.redSeekBar = redSeekBar;
        this.greenSeekBar = greenSeekBar;
        this.blueSeekBar = blueSeekBar;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        //Coordinates for triangle
        float a1 = 500; // Bottom-left point
        float b1 = 550;
        float a2 = 825; // Bottom-right point
        float b2 = 550;
        float a3 = 825; // Top-right point
        float b3 = 300;

        // Detect if the touch is within certain shapes (e.g., triangle, rectangle, trapezoid)
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP) {
            if (isPointInTriangle(x, y, a1, b1, a2, b2, a3, b3)) {
                setSeekBarFromColor(0xA5FF0000);  // Light Red
            } else if (isPointInRectangle(x, y)) {
                setSeekBarFromColor(0xFF0000FF);
            } else if (isPointInTrapezoid(x, y)) {
                setSeekBarFromColor(0xFFC4A484);  // Mahogany
            }
        }
        return true;  // Return true to indicate the event was handled
    }

    private void setSeekBarFromColor(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);
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


    private boolean isPointInRectangle(float x, float y) {
        // Sea region (replace with real region logic)
        return (x >= 0 && x <= 1600 && y >= 700 && y <= 1100);
    }

    private boolean isPointInTrapezoid(float x, float y) {
        // Trapezoid region (replace with real trapezoid boundary logic)
        return (x >= 600 && x <= 1100 && y >= 700 && y <= 800);
    }
}
