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

import static com.example.milan.utils.Constants.mStoreBase;

public class NomUtilisateurFragment extends Fragment {

    private Button next;
    private NavController navController;
    private EditText editTextUserName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inscription_nomutilisateur, container, false);

        editTextUserName = root.findViewById(R.id.editTextUserName);

        next = root.findViewById(R.id.buttonNext1);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_inscription);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentEditTextUserName = editTextUserName.getText().toString();
                if(!contentEditTextUserName.equalsIgnoreCase("")) {
                    if(contentEditTextUserName.length() >= 6) {
                        mStoreBase.collection("users").whereEqualTo("username", contentEditTextUserName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.getResult().size() > 0) {
                                    editTextUserName.setError("Ce nombre d'utilisateur est déjà pris !");
                                }else{
                                    InscriptionActivity.registerUser.setUsername(contentEditTextUserName);
                                    navController.navigate(R.id.action_nav_nomutilisateur_to_nav_mail);
                                }
                            }
                        });
                    }else{
                        editTextUserName.setError("Le nom d'utilisateur doit contenir au moins 6 caractères");
                    }
                }else{
                    editTextUserName.setError("Le nom d'utilisateur est obligatoire");
                }
            }
        });



        return root;
    }
}
