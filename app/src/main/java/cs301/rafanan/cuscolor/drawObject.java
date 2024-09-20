package cs301.rafanan.cuscolor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.graphics.*;


public class drawObject extends SurfaceView {
    private Paint paintLightRed, paintBlue, paintWhite, paintYellow
            ,paintMahogany, paintBlack, paintForestGreen;

    public drawObject(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);

        paintLightRed = new Paint();
        paintLightRed.setColor(0xA5FF0000);
        paintLightRed.setStyle(Paint.Style.FILL);

        paintBlue = new Paint();
        paintBlue.setColor(0xFF0000FF);
        paintBlue.setStyle(Paint.Style.FILL);

        paintWhite = new Paint();
        paintWhite.setColor(0xFFFFFFFF);
        paintWhite.setStyle(Paint.Style.FILL);

        paintYellow = new Paint();
        paintYellow.setColor(0xFFFFFF00);
        paintYellow.setStyle(Paint.Style.FILL);

        paintMahogany = new Paint();
        paintMahogany.setColor(0xFFC4A484);
        paintMahogany.setStyle(Paint.Style.FILL);

        paintBlack = new Paint();
        paintBlack.setColor(0xFF000000);
        paintBlack.setStyle(Paint.Style.FILL);

        paintForestGreen = new Paint();
        paintForestGreen.setColor(0xFF228B22);
        paintForestGreen.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Path trapezoid = new Path();
        Path trianglePath = new Path();

        /*
         *External Citation
         * Date: 19 September 2024
         * Problem: Could not figure out how to draw a trapezoid and do translation shifts
         * Resource: https://stackoverflow.com/questions/25768037/how-to-build-a-trapezoid-shape-in-android
         * Solution:
         *
         */
        int shiftY = -100;
        float scaleX = 1.5f;
        float centerX = (600 + 1100) / 2.0f;

        //new x coordinates after scaling
        float x1 = centerX + (600 - centerX) * scaleX;
        float x2 = centerX + (1100 - centerX) * scaleX;
        float x3 = centerX + (1000 - centerX) * scaleX;
        float x4 = centerX + (700 - centerX) * scaleX;

        // Flips the trapezoid upside down by swapping the y-values of top and bottom points
        trapezoid.moveTo(x1, 700 + shiftY); //bottom-left corner
        trapezoid.lineTo(x2, 700 + shiftY); //bottom-right corner
        trapezoid.lineTo(x3, 800 + shiftY); //top-right corner
        trapezoid.lineTo(x4, 800 + shiftY); //top-left corner
        trapezoid.close();

        // Define the three points of the triangle
        float a1 = 500; // Bottom-left point
        float b1 = 550;
        float a2 = 825; // Bottom-right point
        float b2 = 550;
        float a3 = 825; // Top-right point
        float b3 = 300;

        trianglePath.moveTo(a1, b1); // Move to the first point (bottom-left corner)
        trianglePath.lineTo(a2, b2); // Draw line to the bottom-right corner
        trianglePath.lineTo(a3, b3); // Draw line to the top-right corner
        trianglePath.close();

        // Define the rectangle bounds for the semicircle
        RectF semicircleRect = new RectF(100, 650, 350, 750); // Adjust as needed

        // Draw the arc (concave downward)
        canvas.drawArc(semicircleRect, 0, -180, true, paintForestGreen);

        canvas.drawPath(trianglePath, paintLightRed);
        canvas.drawPath(trapezoid, paintMahogany);

        canvas.drawRect(0, 700, 1600, 1100, paintBlue);
        canvas.drawRect(825, 300, 850, 600, paintBlack);
        canvas.drawOval(100, 100, 300, 300, paintYellow);
    }
}


