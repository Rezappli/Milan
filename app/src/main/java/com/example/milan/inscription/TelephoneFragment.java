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

public class TelephoneFragment extends Fragment {

    private Button buttonNext;
    private NavController navController;
    private EditText editTextPhoneNumber;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inscription_telephone, container, false);

        editTextPhoneNumber = root.findViewById(R.id.editTextPhoneNumber);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_inscription);

        buttonNext = root.findViewById(R.id.buttonNext3);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentPhoneNumber = editTextPhoneNumber.getText().toString();
                if(!contentPhoneNumber.equalsIgnoreCase("")) {
                    if(contentPhoneNumber.length() == 10) {
                        mStoreBase.collection("users").whereEqualTo("phone", contentPhoneNumber).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.getResult().size() > 0) {
                                    editTextPhoneNumber.setError("Ce numéro est déjà associé à un compte !");
                                }else{
                                    InscriptionActivity.registerUser.setPhone(contentPhoneNumber);
                                    navController.navigate(R.id.action_nav_tel_to_nav_mdp);
                                }
                            }
                        });
                    }else{
                        editTextPhoneNumber.setError("Entrer un numéro de téléphone valide !");
                    }
                }else{
                    editTextPhoneNumber.setError("Entrer un numéro de téléphone !");
                }
            }
        });

        return root;
    }
}