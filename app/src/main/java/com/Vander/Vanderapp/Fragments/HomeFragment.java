package com.Vander.Vanderapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.Vander.Vanderapp.R;
import com.Vander.Vanderapp.adapter.HomeAdapter;
import com.Vander.Vanderapp.model.Item;

import java.util.ArrayList;

public class HomeFragment extends Fragment  implements HomeAdapter.ItemListener{

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
        initView(view );



        return view;

    }

    private void initView(View view) {
        Search_layout = (LinearLayout) view.findViewById(R.id.search_layout);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        setRecyclerdata();
        Search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchFragment searchFragment = new SearchFragment();
                FragmentManager m = getFragmentManager();
                FragmentTransaction fragmentTransaction = m.beginTransaction();
                fragmentTransaction.replace(R.id.contentPanel, searchFragment);
                fragmentTransaction.commit();
            }
        });


    }

    public void setRecyclerdata()
    {
        arrayList = new ArrayList<>();


        arrayList.add(new Item("Apparel", R.drawable.apparel, "#E0E0E0"));
        arrayList.add(new Item("Car Wash", R.drawable.car_wash, "#E0E0E0"));
        arrayList.add(new Item("Beauty Parlour", R.drawable.beautyparlour, "#E0E0E0"));
        arrayList.add(new Item("Groceries", R.drawable.groceries, "#E0E0E0"));
        arrayList.add(new Item("Hotels", R.drawable.hotels, "#E0E0E0"));
        arrayList.add(new Item("Restaurants", R.drawable.hotels, "#E0E0E0"));

        HomeAdapter adapter = new HomeAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }
    @Override
    public void onItemClick(Item item) {
        SecondListStage secondListStage = new SecondListStage ();
        Bundle args = new Bundle();


        if(item.text.trim().equals("Apparel"))
        {



            args.putString( "item",item.text);

          /*
            SearchFragment searchFragment = new SearchFragment();
            FragmentManager m = getFragmentManager();
            FragmentTransaction fragmentTransaction = m.beginTransaction();
            fragmentTransaction.replace(R.id.contentPanel, searchFragment);
            fragmentTransaction.commit();*/

        }else if (item.text.trim().equals("Car Wash"))
        {
            args.putString( "item",item.text);


        }
        else if (item.text.trim().equals("Beauty Parlour"))
        {
            args.putString( "item",item.text);
        }
        else if (item.text.trim().equals("Groceries"))
        {
            args.putString( "item",item.text);


        }
        else if (item.text.trim().equals("Hotels"))
        {

            args.putString( "item",item.text);

        }
        else if (item.text.trim().equals("Restaurants"))
        {
            args.putString( "item",item.text);


        }
        secondListStage.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.contentPanel, secondListStage).commit();


        //Toast.makeText(getContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }


}