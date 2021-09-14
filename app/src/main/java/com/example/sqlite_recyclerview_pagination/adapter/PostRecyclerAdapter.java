package com.example.sqlite_recyclerview_pagination.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sqlite_recyclerview_pagination.model.DataModel;
import com.example.sqlite_recyclerview_pagination.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
public class PostRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private List<DataModel> mPostItems;
    public PostRecyclerAdapter(List<DataModel> postItems) {
        this.mPostItems = postItems;
    }
    @NonNull @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }
    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == mPostItems.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }
    @Override
    public int getItemCount() {
        return mPostItems == null ? 0 : mPostItems.size();
    }
    public void addItems(List<DataModel> postItems) {
        mPostItems.addAll(postItems);
        notifyDataSetChanged();
    }
    public void addLoading() {
        isLoaderVisible = true;
        mPostItems.add(new DataModel());
        notifyItemInserted(mPostItems.size() - 1);
    }
    public void removeLoading() {
        isLoaderVisible = false;
        int position = mPostItems.size() - 1;
        DataModel item = getItem(position);
        if (item != null) {
            mPostItems.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear() {
        mPostItems.clear();
        notifyDataSetChanged();
    }
    DataModel getItem(int position) {
        return mPostItems.get(position);
    }
    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.bookname)
        TextView textViewTitle;
        @BindView(R.id.bookauthor)
        TextView textViewDescription;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        protected void clear() {
        }
        public void onBind(int position) {
            super.onBind(position);
            DataModel item = mPostItems.get(position);
            textViewTitle.setText(item.getBook_name());
            textViewDescription.setText(item.getAuthor());
        }


    }
    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @Override
        protected void clear() {
        }
    }
}
