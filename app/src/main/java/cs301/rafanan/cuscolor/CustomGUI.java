package cs301.rafanan.cuscolor;

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
 *
 * MainActivity class but renamed to CustomGUI
 *
 */
public class CustomGUI extends AppCompatActivity {

    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private drawObject drawingSurface;
    private TextView showElement, redNum, greenNum, blueNum;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.custom_gui);

        redSeekBar = findViewById(R.id.redSeek);
        greenSeekBar = findViewById(R.id.greenSeek);
        blueSeekBar = findViewById(R.id.blueSeek);

        redSeekBar.setMax(255);
        greenSeekBar.setMax(255);
        blueSeekBar.setMax(255);

        showElement = findViewById(R.id.dispElem);
        redNum = findViewById(R.id.redSeekNum);
        greenNum = findViewById(R.id.greenSeekNum);
        blueNum = findViewById(R.id.blueSeekNum);

        drawingSurface = findViewById(R.id.surfaceView);

        ColorTouch touchListen = new ColorTouch(redSeekBar, greenSeekBar,blueSeekBar, showElement, redNum, greenNum, blueNum);

        drawingSurface.setOnTouchListener(touchListen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.custom), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
