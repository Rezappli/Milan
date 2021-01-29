package com.example.milan.ui.actualite;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milan.R;
import com.example.milan.objects.Post;
import com.example.milan.ui.actualite.enums.SubRoom;
import com.example.milan.ui.actualite.enums.TypePost;
import com.example.milan.bdd.BddPost;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import static com.example.milan.utils.Constants.currentUser;
import static com.example.milan.utils.Constants.mStoreBase;

public class SubActualiteFragment extends Fragment {

    private Scene scene1;
    private Scene scene2;
    private Scene currentScene;
    private RecyclerView mRecyclerView;
    private FirestoreRecyclerAdapter adapter;
    private ImageView addPostImg;
    public static SubRoom currentRoom;

    public SubActualiteFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sub_actualite, container, false);
        addPostImg = root.findViewById(R.id.add_post_img);
        mRecyclerView = root.findViewById(R.id.recyclerPost);
        // Click on add post
        addPostImg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               currentRoom = currentRoom;
               startActivity(new Intent(getContext(),AddPostActivity.class));
           }
       });

        // Found the room choise by the user
        switch (currentRoom){
            case IT:launchPosts(SubRoom.IT);
                break;
            case CAR:launchPosts(SubRoom.CAR);
                break;
            case MEET:launchPosts(SubRoom.MEET);
                break;
            case MOVIE:launchPosts(SubRoom.MOVIE);
                break;
            case GAME:launchPosts(SubRoom.GAME);
                break;
            case TRAVEL:launchPosts(SubRoom.TRAVEL);
                break;
            case FOOD:launchPosts(SubRoom.FOOD);
                break;
        }
        /*scene1 = Scene.getSceneForLayout(root.findViewById(R.id.scene1_add_post), R.layout.fragment_sub_room,getContext());
        scene2 = Scene.getSceneForLayout(root.findViewById(R.id.scene2), R.layout.fragment_add_post,getContext());
        scene1.enter();
        currentScene = scene1;
        Transition transition = TransitionInflater.from(getContext()).inflateTransition(R.transition.transition1);
        addPostImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentScene == scene1)
                    TransitionManager.go(scene2, transition);
            }
        });*/
        return root;
    }

    // Display the good room and their posts
    private void launchPosts(SubRoom sr) {
        Query query = mStoreBase.collection("room").whereEqualTo("room", sr);

        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post.class).build();

        adapter = new FirestoreRecyclerAdapter<Post, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder userViewHolder, int i, @NonNull final Post p) {
                userViewHolder.nbComment.setText(p.getNbComment()+"");
                userViewHolder.nbLike.setText(p.getNbLike()+"");
                userViewHolder.nbShare.setText(p.getNbShare()+"");
                userViewHolder.author.setText(p.getOwnerName());
                userViewHolder.message.setText(p.getMessage());
                if(p.getType() != TypePost.VIDEO){
                    userViewHolder.postImage.setVisibility(View.GONE);
                }else{
                    userViewHolder.postImage.setVisibility(View.VISIBLE);

                }
                userViewHolder.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mStoreBase.collection("users").document(currentUser.getUid()).collection("likes")
                                .document(p.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    if(!task.getResult().exists()) {
                                        userViewHolder.like.setImageDrawable(getResources().getDrawable(R.drawable.star_post_focused));
                                        BddPost post = new BddPost();
                                        post.addLike(p);
                                    }else{
                                        userViewHolder.like.setImageDrawable(getResources().getDrawable(R.drawable.star_post));
                                        BddPost post = new BddPost();
                                        post.removeLike(p);
                                    }
                                }
                            }
                        });
                    }
                });
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

    private TextView author;
    private TextView message;
    private TextView date;
    private TextView nbLike;
    private TextView nbComment;
    private TextView nbShare;
    private ImageView image;
    private ImageView like;
    private ImageView comment;
    private ImageView share;
    private ImageView postImage;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.author_post);
        message = itemView.findViewById(R.id.message_post);
        date = itemView.findViewById(R.id.date_post);
        like = itemView.findViewById(R.id.like_post);
        comment = itemView.findViewById(R.id.comment_post);
        share = itemView.findViewById(R.id.share_post);
        nbShare = itemView.findViewById(R.id.nb_share_post);
        nbLike = itemView.findViewById(R.id.nb_like_post);
        nbComment = itemView.findViewById(R.id.nb_comment_post);
        postImage = itemView.findViewById(R.id.image_post);
    }
    }
}