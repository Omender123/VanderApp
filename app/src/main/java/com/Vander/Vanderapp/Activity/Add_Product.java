package com.Vander.Vanderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.Vander.Vanderapp.ModelResponse.CategoryListModel;
import com.Vander.Vanderapp.ModelResponse.categoryid;
import com.Vander.Vanderapp.R;
import com.Vander.Vanderapp.Retrofit.ApiClient;
import com.Vander.Vanderapp.adapter.CategorySpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product extends AppCompatActivity {
    Spinner categorySP, subcategorySP;
    ArrayList<categoryid> arrayList1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);
        categorySP = (Spinner) findViewById(R.id.categorySP);
        subcategorySP = (Spinner) findViewById(R.id.subcategorySP);

         arrayList1=new ArrayList<>();
       // categoryListModel = new CategoryListModel();

        GetCategoryList();
          }




    public void back(View view) {
    }


    public void GetCategoryList() {



        Call<List<CategoryListModel>> userlist = ApiClient.getUserService().getcategories();

        userlist.enqueue(new Callback<List<CategoryListModel>>() {
            @Override
            public void onResponse(Call<List<CategoryListModel>> call, Response<List<CategoryListModel>> response) {

                if (response.isSuccessful()) {
                    for (CategoryListModel categoryListModel : response.body()) {
                        String name = categoryListModel.getCategoryname();
                        int id = categoryListModel.getId();
                        categoryid categoryids=new categoryid(name,id);

                        arrayList1.add(categoryids);


                    }
                    CategorySpinnerAdapter coinSpinnerAdapter =new CategorySpinnerAdapter(getApplicationContext(),arrayList1);
                    categorySP.setAdapter(coinSpinnerAdapter);

                    categorySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            Toast.makeText(Add_Product.this, arrayList1.get(position).getId()+"", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });



/*

                    List<CategoryListModel> userResponses = response.body();
                    Toast.makeText(Add_Product.this, userResponses+"", Toast.LENGTH_SHORT).show();
*/

                 /*   userResponses

                    categoryListAdapterr.setData(userResponses);
                    recyclerView.setAdapter(categoryListAdapterr);
*/

                }

            }

            @Override
            public void onFailure(Call<List<CategoryListModel>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Toast.makeText(Add_Product.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });

    }


}