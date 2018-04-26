package com.beanzhou.login.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 *  微信：
 *  AppID: wx38684064dbf9ddca
 *  AppSecret: 77f4c7ee709f2199ab27e176aa1726bf
 *
 *  QQ:
 *  APP ID: 1106786249APP
 *  KEY: JZWlfoQaWI1DtVQn
 */
public class MainActivity extends AppCompatActivity implements IWXAPIEventHandler{


    final static String WX_APP_ID = "wx38684064dbf9ddca";
    final static String WX_APP_SECRET = "77f4c7ee709f2199ab27e176aa1726bf";

    final static String QQ_APP_ID = "1106786249";
    final static String QQ_APP_SECRET = "JZWlfoQaWI1DtVQn";

    private Button      buttonQQLogin, buttonWXLogin;
    private TextView    text1, text2;

    // QQ
    private Tencent     mTencent;
    private IUiListener QQLoginListener = new IUiListener() {
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

    public void qqLogin() {
        mTencent.login(this, "all", QQLoginListener);
    }


    // wx
    private IWXAPI mWxApi;

    private void regToWx() {
        mWxApi = WXAPIFactory.createWXAPI(this, MainActivity.WX_APP_ID, true);
        mWxApi.registerApp(MainActivity.WX_APP_ID);
    }

    public void wxLogin() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wx_state_login";
        mWxApi.sendReq(req);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTencent = Tencent.createInstance(MainActivity.QQ_APP_ID, this.getApplicationContext());

        text1 = (TextView) findViewById(R.id.textView2);
        text2 = (TextView) findViewById(R.id.textView4);

        buttonQQLogin = (Button)findViewById(R.id.button_qq_login);
        buttonQQLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.qqLogin();
                Toast.makeText(getApplicationContext(), "things happened", Toast.LENGTH_SHORT).show();
                String s = "hhhhhhhh";
                char[] chars = s.toCharArray();
                text1.setText(chars, 0, chars.length);
            }
        });

        regToWx();
        buttonWXLogin = (Button) findViewById(R.id.button_wx_login);
        buttonWXLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.this.wxLogin();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, QQLoginListener);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

    }
}

