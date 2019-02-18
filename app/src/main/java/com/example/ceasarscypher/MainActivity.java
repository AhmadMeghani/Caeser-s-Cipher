package com.example.ceasarscypher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText ogMessage;
    TextView encMessage;
    String message;
    Button encryptButton;
    String encrMessage = "";
    float x1, x2, y1, y2;

    public void encryptButtonMethod(View view) {
        message = "";
        encrMessage = "";
        encMessage.setText("");
        message = ogMessage.getText().toString();
        encrypt(message, 3);
    }

    public void encrypt(String message, int shiftKey) {

        for (int i = 0; i < message.length(); i++) {

            int asci = message.charAt(i);
            if (Character.isUpperCase(asci)) {
                asci = asci + (shiftKey % 26);
                if (asci > 'Z')
                    asci = asci - 26;
            } else if (Character.isLowerCase(asci)) {
                asci = asci + (shiftKey % 26);
                if (asci > 'z')
                    asci = asci - 26;
            }

            encrMessage = encrMessage + (char) asci;
        }

        encMessage.setText(encrMessage);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ogMessage = findViewById(R.id.ogMessage);
        encryptButton = findViewById(R.id.decryptButton);
        encMessage = findViewById(R.id.orgMessage);

    }


    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 > x2) {
                    Intent intent = new Intent(MainActivity.this, DecryptActivity.class);
                    intent.putExtra("encryptedMsg", encrMessage);
                    startActivity(intent);
                }
                break;
        }
        return false;
    }
}
