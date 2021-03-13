package com.Vander.Vanderapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.Vander.Vanderapp.R;

public class OrderActivity extends AppCompatActivity {

    public OrderActivity() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.fragment_order);
    }


    public void BackFragmentOrder(View view) {
        onBackPressed();



    }


    public void OnClickUpcoming_orders(View view) {
        Intent intent=new Intent(OrderActivity.this,Upcoming_ordersActivity.class);
        startActivity(intent);


    }

    public void OnClickCompleted_orders(View view) {
        Intent intent=new Intent(OrderActivity.this,Upcoming_ordersActivity.class);
        intent.putExtra("history","history");

        startActivity(intent);

    }
}