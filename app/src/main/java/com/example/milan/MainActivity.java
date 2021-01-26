package com.example.milan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.milan.inscription.InscriptionActivity;
import com.example.milan.inscription.NomUtilisateurFragment;
import com.example.milan.objects.User;
import com.example.milan.utils.Constants;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Pattern;

import static com.example.milan.utils.Constants.mAuth;
import static com.example.milan.utils.Constants.mStoreBase;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText mailEditText;
    private EditText passwordEditText;
    private Button buttonInscription;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_ICON_ONLY);
        signInButton.setColorScheme(SignInButton.COLOR_AUTO);

        initialiserWidjets();


    }

    private void initialiserWidjets() {
        buttonInscription = findViewById(R.id.buttonInscription);
        login = findViewById(R.id.buttonLogin);

        mailEditText = findViewById(R.id.editTextLoginMail);
        passwordEditText = findViewById(R.id.editTextLoginPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentMail = mailEditText.getText().toString();
                String contentPassword = passwordEditText.getText().toString();

                if(!contentMail.equalsIgnoreCase("") && !contentPassword.equalsIgnoreCase("")){
                    if(isValidEmail(contentMail)){
                    mAuth.signInWithEmailAndPassword(contentMail, contentPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                if (mAuth.getCurrentUser().isEmailVerified()) {
                                    mStoreBase.collection("users").whereEqualTo("mail", contentMail).get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if(task.isSuccessful() && task.getResult().size() == 1) {
                                                        for(QueryDocumentSnapshot doc : task.getResult()) {
                                                            try {
                                                                Constants.currentUser = doc.toObject(User.class);
                                                                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                                                            }catch(Exception e){
                                                                Log.w("Connection", e.getMessage());
                                                                Toast.makeText(MainActivity.this, "Une erreur est survenue !", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                } else {
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Votre adresse mail n'a pas été vérifiée")
                                            .setPositiveButton("Renvoyer", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    sendMailVerification();
                                                }
                                            })
                                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    builder.create();
                                    builder.show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    }else {
                        mailEditText.setError("Entrez une adresse mail valide !");
                    }
                }
            }
        });

        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), InscriptionActivity.class));
            }
        });
    }

    private void sendMailVerification(){
        mAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Un mail vient de vous être renvoyé !", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("MAIL", "sendEmailVerification", task.getException());
                            Toast.makeText(MainActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}

