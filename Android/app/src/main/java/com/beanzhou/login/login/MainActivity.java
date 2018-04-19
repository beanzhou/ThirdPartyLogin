package com.beanzhou.login.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView text1, text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView) findViewById(R.id.textView2);
        text2 = (TextView) findViewById(R.id.textView4);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "things happened", Toast.LENGTH_SHORT).show();
                String s = "hhhhhhhh";
                char[] chars = s.toCharArray();
                text1.setText(chars, 0, chars.length);
            }
        });

    }
}
