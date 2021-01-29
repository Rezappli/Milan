package com.example.milan.ui.recherche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milan.R;
import com.example.milan.objects.User;
import com.example.milan.ui.actualite.enums.TypePost;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

import static com.example.milan.utils.Constants.currentUser;
import static com.example.milan.utils.Constants.mStoreBase;

public class RechercheFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FirestoreRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recherche, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerRecherche);

        Query query = mStoreBase.collection("users").whereNotEqualTo("uid", currentUser.getUid());

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>().setQuery(query, User.class).build();

        adapter = new FirestoreRecyclerAdapter<User, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friends_list, parent, false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder userViewHolder, int i, @NonNull final User u) {
                userViewHolder.nbLike.setText(u.getNbLike()+"");
                userViewHolder.nbPost.setText(u.getNbPost()+"");
                userViewHolder.name.setText(u.getUsername());
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();

        return root;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView nbLike;
        private TextView nbPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userName);
            nbLike = itemView.findViewById(R.id.userNbLike);
            nbPost = itemView.findViewById(R.id.userNbPost);
        }
    }

}