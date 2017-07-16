package com.karan.smartchatapp.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karan.smartchatapp.R;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import butterknife.BindView;
public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.btnSignUp) Button btnSignUp;
    @BindView(R.id.btnCancel) Button btnCancel;
    @BindView(R.id.signup_etUsernName) EditText etUserName;
    @BindView(R.id.signup_etUserPassword) EditText etPassword;
    @BindView(R.id.signup_etEmail) EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        registerSession();
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        etUserName = (EditText) findViewById(R.id.signup_etUsernName);
        etPassword = (EditText) findViewById(R.id.signup_etUserPassword);
        etEmail=(EditText)findViewById(R.id.signup_etEmail);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String username=etUserName.getText().toString();
              String password=etPassword.getText().toString();
                QBUser qbUser=new QBUser(username,password);
                qbUser.setEmail(etEmail.getText().toString());
                QBUsers.signUp(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG);
                        finish();
                    }
                    @Override
                    public void onError(QBResponseException e) {
                        Log.d("ERROR",e.getMessage());
                       Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    private void registerSession()
    {
        QBAuth.createSession().performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {
            }

            @Override
            public void onError(QBResponseException e) {
                Log.e("ERROR",e.getMessage());
            }
        });
    }
}
