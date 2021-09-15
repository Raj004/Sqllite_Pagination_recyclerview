package com.example.sqlite_recyclerview_pagination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlite_recyclerview_pagination.adapter.PostRecyclerAdapter;
import com.example.sqlite_recyclerview_pagination.model.DataModel;
import com.example.sqlite_recyclerview_pagination.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.sqlite_recyclerview_pagination.PaginationListener.PAGE_START;


public class ViewallActivity extends AppCompatActivity {

    DatabaseHelper database;
    RecyclerView recyclerView;
    PostRecyclerAdapter recycler;
    List<DataModel> datamodel;
    public Context context;

    private PostRecyclerAdapter adapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;
    public PostRecyclerAdapter.RemoveClickListner itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        datamodel = new ArrayList<DataModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        database = new DatabaseHelper(ViewallActivity.this);
        datamodel = database.getdata();
        recycler = new PostRecyclerAdapter(datamodel, itemClickListener);


        Log.i("HIteshdata", "" + datamodel);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);

        recyclerView.addOnScrollListener(new PaginationListener((LinearLayoutManager) reLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                doApiCall();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }
 /*   private void deletePlace(int position){
        // mListner.OnRemoveClick(position);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        DataModel postItem = new DataModel();

        databaseHelper.removePlace(postItem.book_name, postItem.getAuthor());
        datamodel.remove(position);

    }*/


    private void doApiCall() {
        Toast.makeText(this, "Progress check",
                Toast.LENGTH_LONG).show();
        final ArrayList<DataModel> items = new ArrayList<>();
        new Handler().postDelayed(() -> {

            for (int i = 0; i < 10; i++) {
                itemCount++;
                DataModel postItem = new DataModel();
                postItem.setBook_name(postItem.book_name);
                postItem.setAuthor(postItem.author);
                items.add(postItem);
            }
          /*  if (currentPage != PAGE_START) adapter.removeLoading();
            adapter.addItems(items);
            // check weather is last page or not
            if (currentPage < totalPage) {
                adapter.addLoading();
            } else {
                isLastPage = true;
            }
            isLoading = false;*/

        }, 1500);

    }


}
