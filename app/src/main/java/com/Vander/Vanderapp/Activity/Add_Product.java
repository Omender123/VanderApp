package com.Vander.Vanderapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.Vander.Vanderapp.ModelResponse.CategoryListModel;
import com.Vander.Vanderapp.ModelResponse.Secondcategory_itemlist;
import com.Vander.Vanderapp.R;
import com.Vander.Vanderapp.Retrofit.ApiClient;
import com.Vander.Vanderapp.adapter.CategorySpinnerAdapter;
import com.Vander.Vanderapp.adapter.SubCategorySpinnerAdapter;
import com.Vander.Vanderapp.dialogs.LoadingDialogs;
import com.Vander.Vanderapp.model.SubCategoryList;
import com.Vander.Vanderapp.model.categoryid;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product extends AppCompatActivity {

    Spinner categorySP, subcategorySP;
    ArrayList<categoryid> arrayList1;
    ArrayList<SubCategoryList> secondcategoryItemlists;

    LoadingDialogs loadingDialogs;

    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri filepath;
    private Bitmap bitmap;
    private ViewFlipper viewFlipper;

    LinearLayout ll_document_upload_take_photo;
    ImageView iv_document_upload_preview;

    Button btn_document_upload_save,btn_document_upload_retake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);

        InitView();

        // categoryListModel = new CategoryListModel();


    }

    private void InitView() {
        requestStoragePermission();
        categorySP = (Spinner) findViewById(R.id.categorySP);
        subcategorySP = (Spinner) findViewById(R.id.subcategorySP);
        loadingDialogs = new LoadingDialogs(this);
        ll_document_upload_take_photo=findViewById(R.id.ll_document_upload_take_photo);
        iv_document_upload_preview=findViewById(R.id.iv_document_upload_preview);
        btn_document_upload_save=findViewById(R.id.btn_document_upload_save);
        btn_document_upload_retake=findViewById(R.id.btn_document_upload_retake);
        arrayList1 = new ArrayList<>();
        secondcategoryItemlists = new ArrayList<>();
        viewFlipper = findViewById(R.id.viewflipper_registration);
        viewFlipper.setDisplayedChild(0);
        GetCategoryList();


        ll_document_upload_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFileChooser();
            }
        });
        btn_document_upload_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(0);
            }
        });
        btn_document_upload_retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFileChooser();
            }
        });
    }


    public void back(View view) {

        onBackPressed();
    }


    public void GetCategoryList() {

        loadingDialogs.startLoadingDialogs();


        Call<List<CategoryListModel>> userlist = ApiClient.getUserService().getcategories();

        userlist.enqueue(new Callback<List<CategoryListModel>>() {
            @Override
            public void onResponse(Call<List<CategoryListModel>> call, Response<List<CategoryListModel>> response) {

                if (response.isSuccessful()) {

                    for (CategoryListModel categoryListModel : response.body()) {
                        String name = categoryListModel.getCategoryname();
                        int id = categoryListModel.getId();
                        categoryid categoryids = new categoryid(name, id);

                        arrayList1.add(categoryids);
                        loadingDialogs.dismissDialog();


                    }
                    CategorySpinnerAdapter coinSpinnerAdapter = new CategorySpinnerAdapter(getApplicationContext(), arrayList1);
                    categorySP.setAdapter(coinSpinnerAdapter);
                    categorySP.setBackground(getResources().getDrawable(R.drawable.ic__arrow_down_24));
                    categorySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            GetSubCategoryList(String.valueOf(arrayList1.get(position).getId()));
                            Toast.makeText(Add_Product.this, arrayList1.get(position).getId() + "", Toast.LENGTH_SHORT).show();
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
                loadingDialogs.dismissDialog();

                Toast.makeText(Add_Product.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void GetSubCategoryList(String id) {


        loadingDialogs.startLoadingDialogs();

        Call<List<Secondcategory_itemlist>> userlist = ApiClient.getUserService().getSubCategories(id);

        userlist.enqueue(new Callback<List<Secondcategory_itemlist>>() {
            @Override
            public void onResponse(Call<List<Secondcategory_itemlist>> call, Response<List<Secondcategory_itemlist>> response) {
                Toast.makeText(Add_Product.this, response.code() + "", Toast.LENGTH_SHORT).show();

                loadingDialogs.dismissDialog();
                if (response.isSuccessful()) {
                   //

                    secondcategoryItemlists.clear();
                    for (Secondcategory_itemlist categoryListModel : response.body()) {

                        // categoryid categoryids=new categoryid(name,id);


                        String name = categoryListModel.getSubcatename();
                        int id = categoryListModel.getId();
                        SubCategoryList subCategoryList = new SubCategoryList(name, id);



                        secondcategoryItemlists.add(subCategoryList);


                    }
                    SubCategorySpinnerAdapter subCategorySpinnerAdapter = new SubCategorySpinnerAdapter(getApplicationContext(), secondcategoryItemlists);
                    subcategorySP.setAdapter(subCategorySpinnerAdapter);

                    subcategorySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // loadingDialogs.startLoadingDialogs();


                            Toast.makeText(Add_Product.this, arrayList1.get(position).getId() + "", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<List<Secondcategory_itemlist>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                loadingDialogs.dismissDialog();

                Toast.makeText(Add_Product.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void selectImage(View view) {
        viewFlipper.setDisplayedChild(1);


    }
    private void ShowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    private void requestStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {

            filepath = data.getData();
            try {
                viewFlipper.setDisplayedChild(2);

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                iv_document_upload_preview.setImageBitmap(bitmap);
               // tv.setText(filepath.toString());
                // Toast.makeText(getApplicationContext(),getPath(filepath),Toast.LENGTH_LONG).show();
            } catch (Exception ex) {

            }
        }
    }

}