package com.karan.smartchatapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karan.smartchatapp.R;
import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity {
     Button main_login;
     Button main_signup;
     EditText etUserName;
     EditText etUserPassword;


    static final String APP_ID="60056";
    static final String AUTH_KEY="vXX-KdhBvRq7pBJ";
    static final String AUTH_SECRET="WeNZVaeHr4mfFex";
    static final String APP_KEY="5xVx6xrb5CvopUL7tj2L";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_login=(Button) findViewById(R.id.main_btnLogin);
        main_signup=(Button) findViewById(R.id.main_btnSignUp);
        etUserName=(EditText)findViewById(R.id.etUsernName);
        etUserPassword=(EditText)findViewById(R.id.etUserPassword);


        initializeFramework();
       main_signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
           }
       });
        main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=etUserName.getText().toString();
                String pass=etUserPassword.getText().toString();
                QBUser qbUser=new QBUser(user,pass);
                QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(QBResponseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }

    private void initializeFramework()
    {
        QBSettings.getInstance().init(getApplicationContext(),APP_ID,AUTH_KEY,AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(APP_KEY);
    }
}
