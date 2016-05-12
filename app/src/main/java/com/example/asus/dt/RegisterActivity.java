package com.example.asus.dt;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editText_phone;
    private EditText editText_num;
    private EditText editText_pwd;
    private Button button_getNum;
    private String sjNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_phone = (EditText) findViewById(R.id.editText_phone);
        editText_num = (EditText) findViewById(R.id.editText_num);
        editText_pwd = (EditText) findViewById(R.id.editText_pwd);
        button_getNum = (Button) findViewById(R.id.button_getNum);

        button_getNum.setOnClickListener(onClickListener);
    }

    /**
     * 验证按钮点击事件
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String str = String.valueOf(button_getNum.getText());
            if ("获取验证码".equals(str)) {
                String phone = String.valueOf(editText_phone.getText());
                String pwd = String.valueOf(editText_pwd);
//                判断手机号码和密码是否合法
                if (VerifyPhone(phone) && VerifyPwd(pwd)) {
                    button_getNum.setText("验证");
                    Asyn asyn = new Asyn();
                    asyn.execute();
                } else {
                    Toast.makeText(RegisterActivity.this, "手机号或密码不合法", Toast.LENGTH_SHORT).show();
                }
            } else {
                String num = String.valueOf(editText_num.getText());
                if (sjNum.equals(num)) {
                    Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                } else {
                    button_getNum.setText("获取验证码");
                }
            }
        }
    };

    /**
     * 发送短信验证码
     *
     * @param phoneNumber
     * @param content
     */
    public String sendSmsCode(String phoneNumber, String content) {
        if (phoneNumber == null || content == null) {
            return null;
        }
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        String httpUrl = "http://apis.baidu.com/baidu_communication/sms_verification_code/smsverifycode";
        String httpArg = "phone=" + phoneNumber + "&content=" + content;
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // ????apikey??HTTP header
            connection.setRequestProperty("apikey",
                    "db642b2fac4fafe26849179ad8883592 ");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            Log.i("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    class Asyn extends AsyncTask<Void, Void, Void> {

        String phone = String.valueOf(editText_phone.getText());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //获取随机验证码
            SJNum.getNum();
            sjNum = SJNum.num;
        }

        @Override
        protected Void doInBackground(Void... params) {
            sendSmsCode(phone, sjNum);
            return null;
        }
    }

    public boolean VerifyPhone(String phone) {
        Pattern p = Pattern.compile("1\\d{10}");
        Matcher m = p.matcher(phone);
        boolean b = m.matches();
        return b;
    }

    public boolean VerifyPwd(String pwd) {
        Pattern p = Pattern.compile("(?![a-zA-Z]+$)(?![0-9]+$)[a-zA-Z0-9\\W]{6,20}");
        Matcher m = p.matcher(pwd);
        boolean b = m.matches();
        return b;
    }
}
