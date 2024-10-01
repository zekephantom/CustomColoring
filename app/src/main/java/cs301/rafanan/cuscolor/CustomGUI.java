package cs301.rafanan.cuscolor;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Author: Ezekiel Rafanan
 * Date: 22 September 2024
 * Description: MainActivity class but renamed to CustomGUI
 */
public class CustomGUI extends AppCompatActivity {

    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private drawObject drawingSurface;
    private TextView currentElem, redNum, greenNum, blueNum;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.custom_gui);

        //Seekbar widgets
        redSeekBar = findViewById(R.id.redSeek);
        greenSeekBar = findViewById(R.id.greenSeek);
        blueSeekBar = findViewById(R.id.blueSeek);

        //Sets the range from 0 to 255
        redSeekBar.setMax(255);
        greenSeekBar.setMax(255);
        blueSeekBar.setMax(255);

        //TextView widgets
        currentElem = findViewById(R.id.dispElem);
        redNum = findViewById(R.id.redSeekNum);
        greenNum = findViewById(R.id.greenSeekNum);
        blueNum = findViewById(R.id.blueSeekNum);

        drawingSurface = findViewById(R.id.surfaceView);

        ColorTouch touchListen = new ColorTouch(redSeekBar, greenSeekBar,blueSeekBar, currentElem, redNum, greenNum, blueNum);

        drawingSurface.setOnTouchListener(touchListen);

        /*
        * External Citation
        * Date: 29 September 2024
        * Problem: Could not combine the functionality of showing the RGB values in TextView while
        *           changing the color of the element. Also, I do not know much about onProgressChanged() method.
        * Resource: ChatGPT, https://developer.android.com/reference/android/widget/SeekBar.OnSeekBarChangeListener
        * Solution: I used the if-statement approach to combining the functionality.
        * */

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // Get the last tapped element from ColorTouch
                    String lastTappedElement = touchListen.getLastTappedElement();
                    redNum.setText(String.valueOf(redSeekBar.getProgress()));
                    greenNum.setText(String.valueOf(greenSeekBar.getProgress()));
                    blueNum.setText(String.valueOf(blueSeekBar.getProgress()));

                    if (lastTappedElement != null) {
                        // Get the RGB values from SeekBars
                        int red = redSeekBar.getProgress();
                        int green = greenSeekBar.getProgress();
                        int blue = blueSeekBar.getProgress();
                        int color = Color.rgb(red, green, blue);

                        // Update the drawing surface with the new color
                        drawingSurface.updateElementColor(lastTappedElement, color);
                    }

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.custom), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
