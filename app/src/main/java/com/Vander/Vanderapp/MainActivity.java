package com.Vander.Vanderapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.Vander.Vanderapp.Activity.Add_Product;
import com.Vander.Vanderapp.Activity.Profile;
import com.Vander.Vanderapp.Activity.ContactUs_Activity;
import com.Vander.Vanderapp.Fragments.HomeFragment;
import com.Vander.Vanderapp.Activity.InventoryActivity;
import com.Vander.Vanderapp.Activity.Legal_Activity;
import com.Vander.Vanderapp.Activity.OrderActivity;
import com.Vander.Vanderapp.Activity.SaleActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    public static BottomNavigationView navigation;

    LinearLayout My_Order, My_Reward, My_Walllet, My_Cart;
    private Menu nav_menu;
    NavigationView navigationView;
    LinearLayout viewpa;
    TextView mTitle, username;
    DrawerLayout mdrawerLayout;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        util.blackiteamstatusbar(this, R.color.light_background);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mdrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mdrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        toggle.syncState();

        // CHANGE TOGGLE ICON
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_icon);
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentPanel,new HomeFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.Deshbord);
        }
        changeStatusBarColor();
    }
    @Override
    public void onBackPressed() {
        if (mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Deshbord:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel,new HomeFragment())
                        .commit();
                Toast.makeText(this, "Welcome to Home fragment", Toast.LENGTH_SHORT).show();
                break;

            case R.id.profile:
               startActivity(new Intent(getApplicationContext(), Profile.class));
                Toast.makeText(this, "Welcome to Profile ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.sale:
                startActivity(new Intent(getApplicationContext(), SaleActivity.class));

                Toast.makeText(this, "Welcome to Report of Sale", Toast.LENGTH_SHORT).show();

                break;
            case R.id.Inventory:
                startActivity(new Intent(getApplicationContext(), InventoryActivity.class));
                Toast.makeText(this, "Welcome to Inventory ", Toast.LENGTH_SHORT).show();

                break;

            case R.id.order:
                startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                Toast.makeText(this, "Welcome to Order", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact:

                startActivity(new Intent(getApplicationContext(), ContactUs_Activity.class));

                Toast.makeText(this, "Welcome to Contact Us", Toast.LENGTH_SHORT).show();

                break;
            case R.id.legal:
            startActivity(new Intent(getApplicationContext(), Legal_Activity.class));
              //  startActivity(new Intent(getApplicationContext(), Add_Product.class));
                Toast.makeText(this, "Welcome to Legal", Toast.LENGTH_SHORT).show();
                break;
                   }
        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    public void OnClick_AddProduct(View view) {

        Intent intent = new Intent(MainActivity.this, Add_Product.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();


    }
}
