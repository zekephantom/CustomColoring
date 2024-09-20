package cs301.rafanan.cuscolor;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*
* This class is the new MainActivity
* */
public class CustomGUI extends AppCompatActivity {

    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private drawObject drawingSurface;
    private TextView showElement;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.custom_gui);

        redSeekBar = findViewById(R.id.redSeek);
        greenSeekBar = findViewById(R.id.greenSeek);
        blueSeekBar = findViewById(R.id.blueSeek);
        showElement = findViewById(R.id.textView2);
        drawingSurface = findViewById(R.id.surfaceView);

        ColorTouch touchListen = new ColorTouch(redSeekBar, greenSeekBar,blueSeekBar, showElement);
        drawingSurface.setOnTouchListener(touchListen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.custom), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
