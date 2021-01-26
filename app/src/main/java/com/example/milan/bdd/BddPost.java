package com.example.milan.bdd;

import com.example.milan.objects.Post;

import static com.example.milan.utils.Constants.mStoreBase;

public class BddPost {

    public void addLike(Post post) {
        post.setNbLike(post.getNbLike()+1);
        updatePost(post);
        updateLikes(post);
    }

    public void addComment(Post post) {
        post.setNbComment(post.getNbComment()+1);
        updatePost(post);
    }

    public void addShare(Post post) {
        post.setNbShare(post.getNbShare()+1);
        updatePost(post);
    }

    public void removeLike(Post post) {
        post.setNbLike(post.getNbLike()-1);
        updatePost(post);
    }

    public void removeComment(Post post) {
        post.setNbComment(post.getNbComment()-1);
        updatePost(post);
    }

    public void removeShare(Post post) {
        post.setNbShare(post.getNbShare()-1);
        updatePost(post);
    }

    public void setMessage(Post post, String message) {
        post.setMessage(message);
        updatePost(post);
    }

    public void setImage(Post post) {

    }

    public void updatePost(Post post) {
        mStoreBase.collection("room").document(post.getId()).set(post);
    }

    public void updateLikes(Post post){
        //mStoreBase.collection("users").document(USER_ID).collection("likes").document(post.getId()).set(post);
    }


}
