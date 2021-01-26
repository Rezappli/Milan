package com.example.milan.utils;

import com.example.milan.objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Constants {
    public static User currentUser;
    public static FirebaseFirestore mStoreBase = FirebaseFirestore.getInstance();
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
}
