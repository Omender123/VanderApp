package com.Vander.Vanderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.Vander.Vanderapp.R;

public class ThirdListStage extends AppCompatActivity {


        TextView categorgyname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_list_stage);
        initview();



    }

    private void initview() {

        categorgyname=findViewById(R.id.categorgyname);
        try {


            String item = getIntent().getExtras().getString("item");
            categorgyname.setText(item+" FASHION");
        } catch (Exception e) {


        }
    }
}