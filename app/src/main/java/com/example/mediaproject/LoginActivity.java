package com.example.mediaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaproject.Home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    EditText editTextLoginEmail;
    EditText editTextLoginPassword;
    Button buttonSignin;
    TextView textviewSignup;
    TextView textviewMessage;
    TextView textviewFindPassword;
    ProgressDialog progressDialog;
    //define firebase object
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();//use firebase get instance

        if (firebaseAuth.getCurrentUser() != null) {
            //if already logged in
            finish();//finish and go to Main activity

            startActivity(new Intent(getApplicationContext(), HomeActivity.class)); //
        }
        editTextLoginEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        editTextLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        textviewSignup = (TextView) findViewById(R.id.textViewSignUp);
        textviewMessage = (TextView) findViewById(R.id.textviewMessage);
        textviewFindPassword = (TextView) findViewById(R.id.textViewFindPassword);

        textviewFindPassword.setText("비밀번호를 잊으셨다면 클릭");
        buttonSignin = (Button) findViewById(R.id.buttonLogIn);
        progressDialog = new ProgressDialog(this); //conncect Function


//        button click event
        buttonSignin.setOnClickListener(this);
        textviewSignup.setOnClickListener(this);
        textviewFindPassword.setOnClickListener(this);
    }

    private void userLogin() {
        String email = editTextLoginEmail.getText().toString().trim();
        String password = editTextLoginPassword.getText().toString().trim();
        //get identification

        /* *******Validation ref:http://blog.naver.com/suda552/220813122485******** */


        /* *******Validation of email(is empty& validation)******** */
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "이메일을 입력해 달라구미!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "이메일 형식에 맞지 않다구미!\n", Toast.LENGTH_SHORT).show();
            return;
        }
        /* *******Validation of email(is empty& validation)******** */

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "비밀번호를 넣어달라구미!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password))
        {
            Toast.makeText(this,"비밀번호 형식에 맞지 않는다구미!",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("로그인 중이다구미! 잠시만 기다려달라구미...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "로그인에 실패하였다구미! 다시시도하여 달라구미!", Toast.LENGTH_LONG).show();
                            textviewMessage.setText("비밀번호는 반드시 숫자와 알파벳, \n그리고 특수기호를 사용하여야 한다구미\n비밀번호는 최소 \n8자리 이상으로 만들어달라구미\n");
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {

        /* *******if click Login button , can login ******** */
        if (view == buttonSignin) {
            userLogin();
        }
        /* *******if click Signup button , can Signup ******** */

        if (view == textviewSignup) {
            startActivity(new Intent(this, SignupActivity.class));
        }
        /* *******go to find password ******** */

        if (view == textviewFindPassword) {
            startActivity(new Intent(this, FindActivity.class));
        }
    }
}