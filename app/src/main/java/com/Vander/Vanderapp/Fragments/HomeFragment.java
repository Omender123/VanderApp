package com.Vander.Vanderapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.Vander.Vanderapp.Activity.OrderActivity;
import com.Vander.Vanderapp.R;
import com.Vander.Vanderapp.adapter.HomeAdapter;
import com.Vander.Vanderapp.model.Item;

import java.util.ArrayList;

public class HomeFragment extends Fragment  {

    LinearLayout Search_layout;
    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;

    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        return view;

    }






}