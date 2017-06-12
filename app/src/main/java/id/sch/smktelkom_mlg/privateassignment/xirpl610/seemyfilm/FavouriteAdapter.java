package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;

/**
 * Created by Dini Eka on 14/05/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private final Context context;
    ArrayList<FavouriteItem> favItem;
    IFavouriteAdapter iFavouriteAdapter;

    public FavouriteAdapter(ArrayList<FavouriteItem> favouriteItem, Context context) {

        this.favItem = favouriteItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FavouriteItem favouriteItem = favItem.get(position);
        holder.textViewHeadfav.setText(favouriteItem.judul);
        holder.textViewDescfav.setText(favouriteItem.deskripsi);
        Glide
                .with(context)
                .load(favouriteItem.urlgambar)
                .into(holder.imageViewOtoffav);
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FavouriteItem favouriteItem1 = favItem.get(position);
                favItem.remove(position);
                favouriteItem1.delete();
                FavouriteAdapter.this.notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return favItem.size();
    }

    public interface IFavouriteAdapter {
        void doDelete(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHeadfav;
        public TextView textViewDescfav;
        public ImageView imageViewOtoffav;


        public Button buttonDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHeadfav = (TextView) itemView.findViewById(R.id.textViewHeadfav);
            textViewDescfav = (TextView) itemView.findViewById(R.id.textViewDescfav);
            imageViewOtoffav = (ImageView) itemView.findViewById(R.id.imageViewOtoffav);
            buttonDelete = (Button) itemView.findViewById(R.id.buttonDelete);
        }
    }
}
