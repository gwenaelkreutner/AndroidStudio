package com.example.td1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.td1.R;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editPwd;
    private String mail = "gwen";
    private String pwd = "1234";
    private static final int APPEL_LISTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("cycle","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.editEmail = this.findViewById(R.id.edit_email);
        this.editPwd = this.findViewById(R.id.edit_pwd);

        Log.i("cycle","onStart");
    }

    public void connexion (View view) {
        //String email = new String("Tentative de connxion de \n" + this.editEmail.getText().toString());
        String erreur = "Saisir un E-mail";


        if (this.editEmail.getText().toString().isEmpty())
            Toast.makeText(this, erreur, Toast.LENGTH_SHORT).show();
        else
            accesSaisie();
            //Toast.makeText(this, email,Toast.LENGTH_LONG).show();
    }

    public void accesSaisie(){
        if (this.editEmail.getText().toString().equals(mail) && this.editPwd.getText().toString().equals(pwd)){
            Toast.makeText(this, "Connexion reussi", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ListeActivity.class);
            intent.putExtra("mail",this.editEmail.getText().toString());
            Log.i("test", this.editEmail.getText().toString());
            startActivityForResult(intent, APPEL_LISTE);
        }
        else
            Toast.makeText(this, "Vous n'etes pas enregistre",Toast.LENGTH_LONG).show();
    }

    protected void onResume(){
        super.onResume();
        Log.i("cycle","onResume");
    }

    protected void onPause(){
        super.onPause();
        Log.i("cycle","onPause");
    }
}
