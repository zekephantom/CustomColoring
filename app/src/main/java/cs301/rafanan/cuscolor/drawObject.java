package cs301.rafanan.cuscolor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.graphics.*;

/**
 * Author: Ezekiel Rafanan
 * Date: 22 September 2024
 * This Java file basically draws the elements onto SurfaceView.
 */
public class drawObject extends SurfaceView {
    private Paint sailPaint, oceanPaint, sunPaint,hullPaint, mastPaint, islandPaint;

    public drawObject(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);

        sailPaint = new Paint();
        sailPaint.setColor(0xA5FF0000);
        sailPaint.setStyle(Paint.Style.FILL);

        oceanPaint = new Paint();
        oceanPaint.setColor(0xFF0000FF);
        oceanPaint.setStyle(Paint.Style.FILL);

        sunPaint = new Paint();
        sunPaint.setColor(0xFFFFFF00);
        sunPaint.setStyle(Paint.Style.FILL);

        hullPaint = new Paint();
        hullPaint.setColor(0xFFC4A484);
        hullPaint.setStyle(Paint.Style.FILL);

        mastPaint = new Paint();
        mastPaint.setColor(0xFF000000);
        mastPaint.setStyle(Paint.Style.FILL);

        islandPaint = new Paint();
        islandPaint.setColor(0xFF228B22);
        islandPaint.setStyle(Paint.Style.FILL);
    }

    /*
    * External Citation
    * Date: 28 September 2024
    * Problem: I could not change the color of the element after touch
    * Resource: ChatGPT
    * Solution: Provided me the invalidate() method which redraws or recolors the element. I also followed the switch(case) suggestion.
    * */
    public void updateElementColor(String element, int color) {
        switch (element) {
            case "Sail":
                sailPaint.setColor(color);
                break;
            case "Mast":
                mastPaint.setColor(color);
                break;
            case "Ocean":
                oceanPaint.setColor(color);
                break;
            case "Hull":
                hullPaint.setColor(color);
                break;
            case "Sun":
                sunPaint.setColor(color);
                break;
            case "Island":
                islandPaint.setColor(color);
                break;
        }
        invalidate();  // Redraw the view with the updated color
    }

    @Override
    protected void onDraw(Canvas canvas){
        /*
        * External Citation
        * Date: 30 September 2024
        * Problem: I could not figure out how to draw trapezoids and shapes with sides on Android Studio
        * Resource: https://developer.android.com/reference/android/graphics/Path
        * Solution: Read up the documentation about the Path class
        * */
        Path shipHull = new Path();
        Path shipSail = new Path();
        Path island = new Path();
        
        /*
         *External Citation
         * Date: 19 September 2024
         * Problem: Could not figure out how to draw a trapezoid and do translation shifts
         * Resource: https://stackoverflow.com/questions/25768037/how-to-build-a-trapezoid-shape-in-android, ChatGPT
         * Solution: Used their example code, asked AI on how to improved my code - AI gave me suggestions
         */
        int shiftY = -100;

        //Trapezoid (for ship hull) coordinates
        float x1 = 475;  // Bottom-left corner
        float y1 = 600;
        float x2 = 1225; // Bottom-right corner
        float y2 = 600;
        float x3 = 1075; // Top-right corner
        float y3 = 700;
        float x4 = 625;  // Top-left corner
        float y4 = 700;
        
        // Flips the ship hull upside down by swapping the y-values of top and bottom points
        shipHull.moveTo(x1, y1); // Bottom-left corner
        shipHull.lineTo(x2, y2); // Bottom-right corner
        shipHull.lineTo(x3, y3); // Top-right corner
        shipHull.lineTo(x4, y4); // Top-left corner
        shipHull.close();

        // Define the three points of the triangle (sail)
        float a1 = 500; // Bottom-left point
        float b1 = 550;
        float a2 = 825; // Bottom-right point
        float b2 = 550;
        float a3 = 825; // Top-right point
        float b3 = 300;

        shipSail.moveTo(a1, b1); // Move to the first point (bottom-left corner)
        shipSail.lineTo(a2, b2); // Draw line to the bottom-right corner
        shipSail.lineTo(a3, b3); // Draw line to the top-right corner
        shipSail.close();

        
        //Island coordinates
        float leftBottomX = 100;  // Bottom-left corner
        float bottomY = 700;  // Bottom of the trapezoid (same Y as the bottom of the semicircle)
        float rightBottomX = 350;  // Bottom-right corner
        float leftTopX = 150;  // Top-left corner (narrower top)
        float topY = 650;  // Top of the trapezoid (same Y as the top of the semicircle)
        float rightTopX = 300;  // Top-right corner (narrower top)


        //Creates the outline for drawing the trapezoid which will then be labeled as island
        island.moveTo(leftBottomX, bottomY);
        island.lineTo(rightBottomX, bottomY);
        island.lineTo(rightTopX, topY);
        island.lineTo(leftTopX, topY);
        island.close();


        canvas.drawPath(shipSail, sailPaint); //draws the ship's sail
        canvas.drawPath(shipHull, hullPaint); //draws the ship's hull
        canvas.drawRect(0, 700, 1600, 1100, oceanPaint); //draws the ocean
        canvas.drawRect(825, 300, 850, 600, mastPaint); //draws the mast
        canvas.drawOval(100, 100, 300, 300, sunPaint);
        canvas.drawPath(island, islandPaint); //draws the island

        
        /*
        * Adding the text labels identifying the elements. Note: I am not sure if I needed to do this for the homework assignment
        * */
        Paint sun, mast, sail, hull, ocean, new_Island;
        sun = new Paint(); mast = new Paint(); sail = new Paint(); hull = new Paint(); ocean = new Paint(); new_Island = new Paint();
        sun.setColor(Color.BLACK); mast.setColor(Color.BLACK); sail.setColor(Color.BLACK); hull.setColor(Color.BLACK); ocean.setColor(Color.BLACK); new_Island.setColor(Color.BLACK);

        sun.setTextSize(30);
        mast.setTextSize(30);
        sail.setTextSize(30);
        hull.setTextSize(30);
        ocean.setTextSize(30);
        new_Island.setTextSize(30);

        // Define the text to be drawn
        String sunText = "Sun";
        String mastText = "Mast";
        String sailText = "Sail";
        String shipHullText = "Hull";
        String oceanText = "Ocean";
        String islandText = "Island";

        // Define the coordinates for multiple texts
        float sunX = 305; float sunY = 200;
        float mastX, mastY; mastX = 850; mastY = 500;
        float sailX, sailY; sailX = 475; sailY = 500;
        float hullX, hullY; hullX = 1225; hullY = 600;
        float oceanX, oceanY; oceanX = 1300; oceanY = 685;
        float islandX, islandY; islandX = 160; islandY = 640;

        // Draw the text labels on canvas
        canvas.drawText(sunText, sunX, sunY, sun);
        canvas.drawText(mastText, mastX, mastY, mast);
        canvas.drawText(sailText, sailX, sailY, sail);
        canvas.drawText(shipHullText, hullX, hullY, sail);
        canvas.drawText(oceanText, oceanX, oceanY, sail);
        canvas.drawText(islandText, islandX, islandY, new_Island);
    }
}


