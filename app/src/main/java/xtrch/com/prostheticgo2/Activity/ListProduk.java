package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import xtrch.com.prostheticgo2.R;

public class ListProduk extends AppCompatActivity {

    ImageButton btnBack;
    SwipeRefreshLayout reload;
    RecyclerView recyclerView;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produk);

        //FindView
        setFindView();
        //OnClick
        setOnCLick();
        //Reload
        setReload();
    }

    private void setFindView(){
        btnBack = findViewById(R.id.back_from_listProduk);
        reload = findViewById(R.id.listProduk_reload);
        recyclerView = findViewById(R.id.listProduk_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        tvTitle = findViewById(R.id.listProduk_title);
    }

    private void setOnCLick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setReload(){
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload.setRefreshing(false);
            }
        });
    }
}