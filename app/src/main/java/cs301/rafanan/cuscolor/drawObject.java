package cs301.rafanan.cuscolor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.graphics.*;

public class drawObject extends SurfaceView {
    private final Paint paintRed, paintBlue, paintWhite, paintYellow;

    public drawObject(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        paintRed = new Paint();
        paintRed.setColor(0xFFFF0000);
        paintRed.setStyle(Paint.Style.FILL);

        paintBlue = new Paint();
        paintBlue.setColor(0xFF0000FF);
        paintBlue.setStyle(Paint.Style.FILL);

        paintWhite = new Paint();
        paintWhite.setColor(0xFFFFFFFF);
        paintWhite.setStyle(Paint.Style.FILL);

        paintYellow = new Paint();
        paintYellow.setColor(0xFFFFFF00);
        paintYellow.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Path trapezoid = new Path();

        /*
         *External Citation
         * Date: 19 September 2024
         * Problem: Could not figure out how to draw a trapezoid and do translation shifts
         * Resource: https://stackoverflow.com/questions/25768037/how-to-build-a-trapezoid-shape-in-android
         * Solution:
         *
         */
        int shiftY = -100;  // Vertical shift
        float scaleX = 1.5f;  // Horizontal scaling
        float centerX = (600 + 1100) / 2.0f;  // Center point for scaling reference

        // Calculate new x coordinates after scaling
        float x1 = centerX + (600 - centerX) * scaleX;
        float x2 = centerX + (1100 - centerX) * scaleX;
        float x3 = centerX + (1000 - centerX) * scaleX;
        float x4 = centerX + (700 - centerX) * scaleX;

        // Flips the trapezoid upside down by swapping the y-values of top and bottom points
        trapezoid.moveTo(x1, 700 + shiftY); //bottom-left corner
        trapezoid.lineTo(x2, 700 + shiftY); //bottom-right corner
        trapezoid.lineTo(x3, 800 + shiftY); //top-right corner
        trapezoid.lineTo(x4, 800 + shiftY); //top-left corner
        trapezoid.close();  // Close the path

        // Draw the upside-down trapezoid
        canvas.drawPath(trapezoid, paintWhite);

        canvas.drawRect(0, 700, 1600, 1100, paintBlue);
        canvas.drawOval(100, 150, 300, 350, paintYellow);
    }
}


