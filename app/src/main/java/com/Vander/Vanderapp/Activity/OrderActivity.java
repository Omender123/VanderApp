package com.Vander.Vanderapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Vander.Vanderapp.R;
import com.Vander.Vanderapp.adapter.Secondcategory_Adapter;
import com.Vander.Vanderapp.model.Secondcategory_itemlist;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    public OrderActivity() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.fragment_order);
    }




}