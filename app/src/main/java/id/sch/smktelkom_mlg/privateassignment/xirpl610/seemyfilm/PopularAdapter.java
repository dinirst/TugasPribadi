package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Dini Eka on 12/05/2017.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private List<PopularListItem> popularListItems;
    private Context context;

    public PopularAdapter(List<PopularListItem> popularListItems, Context context) {
        this.popularListItems = popularListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.ViewHolder holder, final int position) {
        final PopularListItem popitem = popularListItems.get(position);
        holder.textViewHead.setText(popitem.getHead());
        holder.textViewDesc.setText(popitem.getDesc());

        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w500" + popitem.getImageUrl())
                .into(holder.imageViewOtof);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, popitem.getHead(), Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, Detail2Activity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleBlogIntent.putExtra("blog_id", position);
                context.startActivity(singleBlogIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return popularListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageViewOtof;

        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead2);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc2);
            imageViewOtof = (ImageView) itemView.findViewById(R.id.imageViewOtof);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout2);

        }
    }
}
