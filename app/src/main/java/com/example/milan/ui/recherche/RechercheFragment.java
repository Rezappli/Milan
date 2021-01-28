package com.example.milan.ui.recherche;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milan.R;
import com.example.milan.bdd.BddPost;
import com.example.milan.objects.Post;
import com.example.milan.objects.User;
import com.example.milan.ui.roomchat.SubRoomFragment;
import com.example.milan.ui.roomchat.enums.TypePost;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import static com.example.milan.utils.Constants.USER_ID;
import static com.example.milan.utils.Constants.mStoreBase;

public class RechercheFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FirestoreRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recherche, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerRecherche);

        Query query = mStoreBase.collection("users").whereEqualTo("uid", USER_ID);

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>().setQuery(query, User.class).build();

        adapter = new FirestoreRecyclerAdapter<User, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder userViewHolder, int i, @NonNull final User u) {
                /*userViewHolder.nbComment.setText(p.getNbComment()+"");
                userViewHolder.nbLike.setText(p.getNbLike()+"");
                userViewHolder.nbShare.setText(p.getNbShare()+"");
                userViewHolder.author.setText(p.getOwnerName());*/

            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();

        return root;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView author;
        private TextView nbLike;
        private TextView nbComment;
        private TextView nbShare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author_post);
            nbShare = itemView.findViewById(R.id.nb_share_post);
            nbLike = itemView.findViewById(R.id.nb_like_post);
            nbComment = itemView.findViewById(R.id.nb_comment_post);
        }
    }

}