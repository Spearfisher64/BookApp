package com.example.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private Context mContext;
    private List<BookModelClass> mData;

    public BookAdapter(Context mContext, List<BookModelClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.book_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(mData.get(position).getId());
        holder.name.setText(mData.get(position).getName());

        Glide.with(mContext)
                .load(mData.get(position).getImg())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,name;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.id_text);
            name=itemView.findViewById(R.id.name_text);
            img=itemView.findViewById(R.id.bookImage);
        }
    }
}
