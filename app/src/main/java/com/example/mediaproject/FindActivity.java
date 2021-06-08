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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FindActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FindActivity";
    private EditText editTextUserEmail;
    private Button buttonFind;
    private ImageView findBackArrow;
    private TextView findPassword;
    private ProgressDialog progressDialog;
    //define firebase object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        editTextUserEmail = (EditText) findViewById(R.id.editTextUserEmail);
        buttonFind = (Button) findViewById(R.id.buttonFind);
        findBackArrow = (ImageView) findViewById(R.id.findBackArrow);
        findPassword = (TextView) findViewById(R.id.findPassword);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        findPassword.setText("비밀번호를 잊으셨나요?");
        buttonFind.setOnClickListener(this);
        findBackArrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonFind) {
            progressDialog.setMessage("진행중이다구미! 잠시만 기다려 달라구미...");
            progressDialog.show();
            /* ************Send password reset email************ */
            String emailAddress = editTextUserEmail.getText().toString().trim();

            if (TextUtils.isEmpty(emailAddress)) {
                Toast.makeText(this, "이메일을 넣어달라구미!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }

            firebaseAuth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(FindActivity.this, "이메일을 보냈다구미!", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            } else {
                                Toast.makeText(FindActivity.this, "이메일을 보내는데 실패하였다구미!", Toast.LENGTH_LONG).show();


                            }
                            progressDialog.dismiss();

                        }
                    });
            return;
        }

        if (view == findBackArrow) {
            finish();
        }
    }
}