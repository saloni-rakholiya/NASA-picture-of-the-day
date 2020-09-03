package com.example.nasa;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

public class forsearch extends AppCompatActivity {

    androidx.recyclerview.widget.RecyclerView recyclerView;

    adapter madapter;
    RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forsearch);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);


        list=new ArrayList<>();

        list.add(new item("pooo",R.drawable.ic_android_black_24dp));
        list.add(new item("pee",R.drawable.ic_android_black_24dp));
        list.add(new item("PiiiMM",R.drawable.ic_android_black_24dp));
        list.add(new item("UMMM",R.drawable.ic_android_black_24dp));
        list.add(new item("222",R.drawable.ic_android_black_24dp));

        mlayoutmanager=new LinearLayoutManager(this);
        madapter=new adapter(list);

        recyclerView.setLayoutManager(mlayoutmanager);
        recyclerView.setAdapter(madapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search,menu);

        MenuItem searchitem=menu.findItem(R.id.search);
       // SearchView searchView= (SearchView) searchitem.getActionView();

        SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchitem);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                madapter.getFilter().filter(newText);
                return false;
            }
        });



        return true;
    }
}
