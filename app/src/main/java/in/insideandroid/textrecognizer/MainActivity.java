package in.insideandroid.textrecognizer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView detectedText;
    Button btn_detect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img);
        detectedText = findViewById(R.id.detectedText);
        btn_detect = findViewById(R.id.button_detect);

        btn_detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detect();
            }
        });
    }

    public void detect()
    {
        TextRecognizer recognizer = new TextRecognizer.Builder(MainActivity.this).build();
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<TextBlock> sparseArray =  recognizer.detect(frame);
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i < sparseArray.size(); i++)
        {
            TextBlock tx = sparseArray.get(i);
            String str = tx.getValue();

            stringBuilder.append(str);
        }

        detectedText.setText(stringBuilder);
    }
}
