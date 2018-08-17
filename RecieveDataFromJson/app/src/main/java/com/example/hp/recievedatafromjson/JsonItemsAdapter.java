package com.example.hp.recievedatafromjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.recievedatafromjson.activities.MainActivity;
import com.example.hp.recievedatafromjson.fragments.BigPictureDialogFragment;
import com.example.hp.recievedatafromjson.models.Models;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JsonItemsAdapter extends RecyclerView.Adapter<JsonItemsAdapter.ItemsViewHolder> {
    private List<Models> list;
    private Context context;

    public JsonItemsAdapter(List<Models> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public JsonItemsAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.json_object_item,parent,false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonItemsAdapter.ItemsViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getThumbnailUrl()).into(holder.imageView);
        holder.textView.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ItemsViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.item_iamge);
            textView= itemView.findViewById(R.id.item_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)context).setJsonModel(list.get(getAdapterPosition()));
                    BigPictureDialogFragment dialogFragment = new BigPictureDialogFragment();
                    dialogFragment.show(((MainActivity)context).getFragmentManager(),"tag");
                }
            });
        }
    }
}
