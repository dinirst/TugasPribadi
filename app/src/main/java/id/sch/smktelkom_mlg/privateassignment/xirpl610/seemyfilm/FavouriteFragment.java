package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm.R.id.recyclerViewfav;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    //public static final String URL_DATA = " https://api.themoviedb.org/3/movie/upcoming?api_key=c68f3a9f7fc8c2ddb8734e1b05b5d21a";

    ArrayList<FavouriteItem> favList = new ArrayList<>();
    FavouriteAdapter fAdapter;
    TextView textView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        recyclerView = (RecyclerView) view.findViewById(recyclerViewfav);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favList = new ArrayList<>();

        adapter = new FavouriteAdapter(favList, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);

        favList.addAll(FavouriteItem.listAll(FavouriteItem.class));
        adapter.notifyDataSetChanged();

        return view;
    }
}
