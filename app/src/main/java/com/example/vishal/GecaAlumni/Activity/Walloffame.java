package com.example.vishal.GecaAlumni.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.vishal.GecaAlumni.Adapter.WallAdapter;
import com.example.vishal.GecaAlumni.Pojo.WallPojo;
import com.example.vishal.GecaAlumni.R;

import java.util.ArrayList;

public class Walloffame extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    WallAdapter wallAdapter;

    WallPojo wallPojo;
    ArrayList<WallPojo> wallPojoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walloffame);

        recyclerView = (RecyclerView) findViewById(R.id.wall_RecycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        wallPojoArrayList = new ArrayList<>();
        wallAdapter = new WallAdapter(Walloffame.this,wallPojoArrayList);
        recyclerView.setAdapter(wallAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.actionsearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewAdapter.getFilter().filter(newText);
                return false;
            }
        });*/
        return true;
    }
}
