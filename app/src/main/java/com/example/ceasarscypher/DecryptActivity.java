package com.example.ceasarscypher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class DecryptActivity extends AppCompatActivity {
    EditText encrpMessage;
    TextView orgMessage;
    String message;
    Button decryptButton;
    String encrMessage = "";

    public void decryptButtonMethod(View view) {
        message = "";
        encrMessage = "";
        message = encrpMessage.getText().toString();
        decrypt(message, 3);
    }

    public void decrypt(String message, int shiftKey) {

        for (int i = 0; i < message.length(); i++) {

            int asci = message.charAt(i);
            if (Character.isUpperCase(asci)) {
                asci = asci - (shiftKey % 26);
                if (asci < 'A')
                    asci = asci + 26;
            } else if (Character.isLowerCase(asci)) {
                asci = asci - (shiftKey % 26);
                if (asci < 'a')
                    asci = asci + 26;
            }

            encrMessage = encrMessage + (char) asci;
        }

        orgMessage.setText(encrMessage);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        encrpMessage = findViewById(R.id.encrpMessage);
        decryptButton = findViewById(R.id.decryptButton);
        orgMessage = findViewById(R.id.orgMessage);

        Intent intent = getIntent();
        String encyptedMsg = intent.getStringExtra("encryptedMsg");

        encrpMessage.setText(encyptedMsg);


    }


}
