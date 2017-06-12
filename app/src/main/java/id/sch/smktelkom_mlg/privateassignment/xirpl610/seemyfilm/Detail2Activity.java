package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

public class Detail2Activity extends AppCompatActivity {

    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/popular?api_key=c68f3a9f7fc8c2ddb8734e1b05b5d21a";
    public TextView textViewHeadet;
    public TextView textViewDescet;
    public TextView textViewReview;
    public ImageView imageViewDetail;
    public String url;
    private Integer mPostkey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPostkey = getIntent().getExtras().getInt("blog_id");
        loadRecyclerViewData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        textViewHeadet = (TextView) findViewById(R.id.textViewHeadet2);
        textViewDescet = (TextView) findViewById(R.id.textViewDescet2);
        textViewReview = (TextView) findViewById(R.id.textViewReview2);
        imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail2);
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
                            //url = o.getJSONObject("link").getString("url");
                            Glide
                                    .with(Detail2Activity.this)
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
