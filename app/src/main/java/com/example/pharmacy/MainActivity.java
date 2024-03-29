package com.example.pharmacy;

import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance():

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (textView) findViewById(R.id.textViewSignin)

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim() ;
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email" , Toast.LENGTH_SHORT).show();
            //stopping the execution of the function
            return ;
        }
        if (TextUtils.isEmpty(password)){
            // password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            //stopping the execution of the function
            return;



        }

        progressDialog.setMessage("Registering User ...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password);
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                @Override
                public void OnComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccesful()){
                        // user is succesfuly created
                        //we will start the profile activity here
                        Toast.makeText(MainActivity.this, "Registered succesfuly", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Could not register , try again", Toast.LENGTH_SHORT).show();

                    }
                }
                });_
    }



    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();

        }
        if (view == textViewSignin) {
            // will open login activity
        }
    }
}
