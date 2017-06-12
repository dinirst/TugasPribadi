package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm.R.id.recyclerViewfav;

public class FavoriteActivity extends AppCompatActivity {

    ArrayList<FavouriteItem> favList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(recyclerViewfav);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favList = new ArrayList<>();

        ImageView ivFav = (ImageView) findViewById(R.id.imageViewfav);

        adapter = new FavouriteAdapter(favList, this.getApplicationContext());
        recyclerView.setAdapter(adapter);

        Glide
                .with(getApplicationContext())
                .load("http://image.tmdb.org/t/p/w500")
                .into(ivFav);

        favList.addAll(FavouriteItem.listAll(FavouriteItem.class));
        adapter.notifyDataSetChanged();
    }
}
