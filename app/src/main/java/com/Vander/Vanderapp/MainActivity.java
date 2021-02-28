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
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.Vander.Vanderapp.Fragments.ContactUs_Fragment;
import com.Vander.Vanderapp.Fragments.HomeFragment;
import com.Vander.Vanderapp.Fragments.InventoryFragment;
import com.Vander.Vanderapp.Fragments.Legal_Fragment;
import com.Vander.Vanderapp.Fragments.Order;
import com.Vander.Vanderapp.Fragments.Sale;
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
            navigationView.setCheckedItem(R.id.profile);
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
            case R.id.profile:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel,new HomeFragment())
                        .commit();
                Toast.makeText(this, "Welcome to home fragment", Toast.LENGTH_SHORT).show();
                break;

            case R.id.sale:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel,new Sale())
                        .commit();

                Toast.makeText(this, "Welcome to Report of Sale", Toast.LENGTH_SHORT).show();

                break;
            case R.id.Inventory:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel,new InventoryFragment())
                        .commit();
                Toast.makeText(this, "Welcome to home fragment", Toast.LENGTH_SHORT).show();

                break;

            case R.id.order:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel,new Order())
                        .commit();
                Toast.makeText(this, "Welcome to Order", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel,new ContactUs_Fragment())
                        .commit();
                Toast.makeText(this, "Welcome to Contact Us", Toast.LENGTH_SHORT).show();

                break;
            case R.id.legal:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel,new Legal_Fragment())
                        .commit();
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
}
