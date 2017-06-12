package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/now_playing?api_key=c68f3a9f7fc8c2ddb8734e1b05b5d21a";
    public TextView textViewHeadet;
    public TextView textViewDescet;
    public TextView textViewReview;
    public ImageView imageViewDetail;
    public EditText editTextJudul;
    public EditText editTextDes;
    public String url;
    public String urlGambar;
    boolean isPressed = true;
    FavouriteItem favouriteitem;
    private Integer mPostkey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPostkey = getIntent().getExtras().getInt("blog_id");
        loadRecyclerViewData();

        textViewHeadet = (TextView) findViewById(R.id.textViewHeadet);
        textViewDescet = (TextView) findViewById(R.id.textViewDescet);
        textViewReview = (TextView) findViewById(R.id.textViewReview);
        imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPressed) {
                    doSave();
                    Snackbar.make(view, "Berhasil ditambahkan ke favorit", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    doDelete();
                } else {

                    Snackbar.make(view, "Film favorit anda", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                isPressed = !isPressed;

            }

            private void doDelete() {
                String judul = textViewHeadet.getText().toString();
                SharedPreferences.Editor editor = getSharedPreferences(judul, MODE_PRIVATE).edit();
                editor.putBoolean("isNew", false);
                editor.commit();
            }

            private void doSave() {
                String judul = textViewHeadet.getText().toString();
                String deskripsi = textViewDescet.getText().toString();
                String urlgambar = urlGambar;
                favouriteitem = new FavouriteItem(judul, deskripsi, urlgambar);
                favouriteitem.save();

                SharedPreferences.Editor editor = getSharedPreferences(judul, MODE_PRIVATE).edit();
                editor.putBoolean("isNew", true);
                editor.commit();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(mPostkey);


                            textViewHeadet.setText(o.getString("original_title") + "\n");
                            textViewDescet.setText("Release Date : " + "\n" + o.getString("release_date"));
                            textViewReview.setText("Overview : " + "\n" + o.getString("overview"));
                            urlGambar = o.getString("poster_path");
                            //url = o.getJSONObject("link").getString("url");

                            Glide
                                    .with(DetailActivity.this)
                                    .load(urlGambar)
                                    .into(imageViewDetail);

                            Glide
                                    .with(DetailActivity.this)
                                    .load("http://image.tmdb.org/t/p/w500" + o.getString("poster_path"))
                                    .into(imageViewDetail);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
