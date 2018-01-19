package com.example.zverek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Main3SignInActivity extends AppCompatActivity {
    EditText name = null;
    EditText password = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.sign_in_layout);
        name = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

    }


    public void signIn(View view){
        String nam = name.getText().toString();
        String pass = password.getText().toString();
        /*if(nam.equals("123")&&pass.equals("123")){*/
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        /*}else{
            Toast.makeText(Main3SignInActivity.this,"Введенные данные не верны",Toast.LENGTH_SHORT).show();
        }*/
    }
    public void registration(View view){
        Intent intent = new Intent(Main3SignInActivity.this, Main4RegistrationActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }




}
