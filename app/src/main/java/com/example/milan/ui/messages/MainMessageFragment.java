package com.example.milan.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.milan.R;
import com.example.milan.ui.messages.ui.dashboard.GroupsMessageFragment;
import com.example.milan.ui.messages.ui.home.FriendsMessageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainMessageFragment extends Fragment {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_groups:
                    GroupsMessageFragment groupsMessageFragment = new GroupsMessageFragment();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_messages, groupsMessageFragment).commit();
                    return true;
                case R.id.navigation_friends:
                    FriendsMessageFragment friendsFragment = new FriendsMessageFragment();
                    FragmentManager fragmentManager1 = getChildFragmentManager();
                    fragmentManager1.beginTransaction().replace(R.id.frame_messages, friendsFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_main_message_fragment, container, false);

        FriendsMessageFragment friendsFragment = new FriendsMessageFragment();
        FragmentManager fragmentManager1 = getChildFragmentManager();
        fragmentManager1.beginTransaction().replace(R.id.frame_messages, friendsFragment).commit();
        BottomNavigationView navigation = (BottomNavigationView) v.findViewById(R.id.nav_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        // Inflate the layout for this fragment
        return v;
    }
}