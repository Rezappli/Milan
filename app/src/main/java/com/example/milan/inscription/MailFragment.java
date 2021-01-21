package com.example.milan.inscription;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.milan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Pattern;

import static com.example.milan.utils.Constants.mStoreBase;

public class MailFragment extends Fragment {

    private Button buttonNext;
    private NavController navController;

    private EditText editTextEmailAddress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_inscription_mail, container, false);

        editTextEmailAddress = root.findViewById(R.id.editTextEmailAddress);

        buttonNext = root.findViewById(R.id.buttonNext2);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_inscription);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentMail = editTextEmailAddress.getText().toString();

                if(!contentMail.equalsIgnoreCase("")){
                    if(isValidEmail(contentMail)){
                        mStoreBase.collection("users").whereEqualTo("mail", contentMail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.getResult().size() > 0){
                                    editTextEmailAddress.setError("Cette adresse mail est déjà utilisée");
                                }else{
                                    InscriptionActivity.registerUser.setMail(contentMail);
                                    navController.navigate(R.id.action_nav_mail_to_nav_tel);
                                }
                            }
                        });
                    }else{
                        editTextEmailAddress.setError("Entrer une adresse mail valide !");
                    }
                }else{
                    editTextEmailAddress.setError("Entrer une adresse mail !");
                }
            }
        });

        return root;


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