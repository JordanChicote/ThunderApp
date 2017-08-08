package com.thunder.jordanchicote.thunder;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static android.R.id.edit;
import static com.thunder.jordanchicote.thunder.R.id.editText;

public class MainActivity extends Activity implements View.OnClickListener {

    private FirebaseAuth mFirebaseAuth;
    EditText editTextEmail1, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //view inicial


        mFirebaseAuth = FirebaseAuth.getInstance();

        editTextEmail1 = (EditText) findViewById(R.id.userLogin);
        editTextSenha = (EditText) findViewById(R.id.userSenha);
        findViewById(R.id.buttonLogin).setOnClickListener(this);


        Button novaConta = (Button) findViewById(R.id.buttonCreateAC);
        novaConta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CrieSuaConta.class)); //estamos saindo da main e indo pro crie sua conta
            }

        });

    }

    @Override
    public void onClick(View v) {
        String Email = editTextEmail1.getText().toString();  //guardo em uma string o email digitado
        String Senha = editTextSenha.getText().toString();   //guardo em uma string a senha digitada

        if (TextUtils.isEmpty(Email)) {
            editTextEmail1.setError("Informe o E-mail!");
            getCurrentFocus();
            return;
        }

        if (TextUtils.isEmpty(Senha)) {
            editTextSenha.setError("Informe a Senha!!");
            getCurrentFocus();
            return;
        }

        mFirebaseAuth.signInWithEmailAndPassword(Email, Senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this, Menu.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Usuário e/ou senha incorreto(s)", Toast.LENGTH_LONG).show();

                        }


                    }
                });
    }
}



