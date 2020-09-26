package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import xtrch.com.prostheticgo2.R;

public class ListProvider extends AppCompatActivity {

    ImageButton btnBack;
    SwipeRefreshLayout reload;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_provider);

        //FindView
        setFindView();
        //OnClick
        setOnClick();
        //Reload
        setReload();
    }

    private void setFindView(){
        reload = findViewById(R.id.listProv_reload);
        recyclerView = findViewById(R.id.listProv_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        btnBack = findViewById(R.id.back_from_listProv);
    }

    private void setOnClick(){
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