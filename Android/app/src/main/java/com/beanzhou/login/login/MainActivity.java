package com.beanzhou.login.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    final static String WXAPP_SECRET = "77f4c7ee709f2199ab27e176aa1726bf";

    private Button button;
    private TextView text1, text2;
    private Tencent mTencent;

    private IUiListener Listener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            String ss = response.toString();
            char[] s = ss.toCharArray();
            text2.setText(s, 0, ss.length());
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getApplicationContext(), uiError.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTencent = Tencent.createInstance("1106786249", this.getApplicationContext());

        text1 = (TextView) findViewById(R.id.textView2);
        text2 = (TextView) findViewById(R.id.textView4);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                MainActivity.this.qqLogin();
                Toast.makeText(getApplicationContext(), "things happened", Toast.LENGTH_SHORT).show();
                String s = "hhhhhhhh";
                char[] chars = s.toCharArray();
                text1.setText(chars, 0, chars.length);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data,Listener);
    }

    public void qqLogin() {
        mTencent.login(this, "all", Listener);
    }
}

