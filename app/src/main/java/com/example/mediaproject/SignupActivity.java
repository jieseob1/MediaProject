package com.example.mediaproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mediaproject.Home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";


    EditText editTextSignUpEmail;
    EditText editTextSignUpPassword, editTextSignUpPasswordConfirm;
    Button buttonSignup;
    TextView textviewMessage;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //initializig firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();//get instance to firebaseAuth

        /* ********if already logged in,finish this job********* */

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

        }
        //initializing views
        editTextSignUpEmail = (EditText) findViewById(R.id.editTextSignUpEmail);
        editTextSignUpPassword = (EditText) findViewById(R.id.editTextSignUpPassword);
        editTextSignUpPasswordConfirm = (EditText) findViewById(R.id.editTextSignUpPasswordConfirm);
        textviewMessage = (TextView) findViewById(R.id.textviewMessage);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(this);

        buttonSignup.setOnClickListener(this);

    }



    private void registerUser() {
        //Get the email & password that the user enters.
        String email = editTextSignUpEmail.getText().toString().trim();
        String password = editTextSignUpPassword.getText().toString().trim();
        String confirmPassword = editTextSignUpPasswordConfirm.getText().toString().trim();


        //Check whether the email and password are empty or not.
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "이메일을 입력해달라구미!", Toast.LENGTH_SHORT).show();
            editTextSignUpEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "비밀번호를 입력해달라구미!", Toast.LENGTH_SHORT).show();
            editTextSignUpPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "비밀번호 확인을 입력해달라구미!", Toast.LENGTH_SHORT).show();
            editTextSignUpPasswordConfirm.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "이메일 형식에 맞지 않는다구미", Toast.LENGTH_SHORT).show();
            editTextSignUpEmail.requestFocus();
            return;
        }
        if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password)) {
            Toast.makeText(this, "비밀번호 형식에 맞춰달라구미!", Toast.LENGTH_SHORT).show();
            editTextSignUpPassword.requestFocus();
            return;
        }
        if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", confirmPassword)) {
            Toast.makeText(this, "비밀번호 형식에 맞춰달라구미!", Toast.LENGTH_SHORT).show();
            editTextSignUpPasswordConfirm.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)){
            Toast.makeText(this,"비밀번호를 다시 확인해달라구미!",Toast.LENGTH_SHORT).show();
            editTextSignUpPassword.setText("");
            editTextSignUpPasswordConfirm.setText("");
            editTextSignUpPassword.requestFocus();
            return;
        }

        //If the email and password are entered correctly, continue.
        progressDialog.setMessage("등록중이다구미. 잠시만 기다려달라구미...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            //If error occured
                            textviewMessage.setText("에러 타입\n - 이메일이 이미 등록되어 있다구미\n -비밀번호는 최소 8자리 이상의 숫자로 이루어져야한다구미! " +
                                    "\n - 서버에러");
                            Toast.makeText(SignupActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    /* *******Register Button ******** */
    @Override
    public void onClick(View view) {
        if (view == buttonSignup) {
            registerUser();

        }

        /* *******go to Login page ******** */

    }
}