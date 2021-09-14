package com.example.sqlite_recyclerview_pagination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    Button show;
    DatabaseHelper database;
    RecyclerView recyclerView;
    PostRecyclerAdapter recycler;
    List<DataModel> datamodel;

    private PostRecyclerAdapter adapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        datamodel =new ArrayList<DataModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        database = new DatabaseHelper(ViewallActivity.this);
        datamodel=  database.getdata();
        recycler =new PostRecyclerAdapter(datamodel);


        Log.i("HIteshdata",""+datamodel);
        RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
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



    private void doApiCall() {


        Toast.makeText(this, "(Progress check",
                Toast.LENGTH_LONG).show();
        final ArrayList<DataModel> items = new ArrayList<>();
        new Handler().postDelayed(() -> {

            for (int i = 0; i < 5; i++) {
                itemCount++;
                DataModel postItem = new DataModel();
                postItem.setBook_name(postItem.book_name);
                postItem.setAuthor(postItem.author);


        /*        postItem.setBook_name(postItem.book_name) + itemCount);
                postItem.setAuthor(getString(R.string.text_description));*/
                items.add(postItem);
            }
            /**
             * manage progress view
             */

        }, 1500);
    }


}
