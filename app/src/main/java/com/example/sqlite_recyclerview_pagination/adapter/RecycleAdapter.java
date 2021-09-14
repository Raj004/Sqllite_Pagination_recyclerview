package com.example.sqlite_recyclerview_pagination.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sqlite_recyclerview_pagination.model.DataModel;
import com.example.sqlite_recyclerview_pagination.R;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Myholder> {
    List<DataModel> dataModelArrayList;

    public RecycleAdapter(List<DataModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder{
        TextView book_name,author;

        public Myholder(View itemView) {
            super(itemView);

            book_name = (TextView) itemView.findViewById(R.id.bookname);
            author = (TextView) itemView.findViewById(R.id.bookauthor);
        }
    }


    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        DataModel dataModel=dataModelArrayList.get(position);
        holder.book_name.setText(dataModel.getBook_name());
        holder.author.setText(dataModel.getAuthor());

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}