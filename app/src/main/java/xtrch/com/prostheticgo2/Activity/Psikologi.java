package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import xtrch.com.prostheticgo2.R;

public class Psikologi extends AppCompatActivity {

    ImageButton mBack;
    ImageView btnAddPsiko;
    SwipeRefreshLayout reload;
    RecyclerView recyclerView;

    String getStatusUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psikologi);

        //FindView
        setFindView();
        //getSharedPreference
        getSharedPreference();
        //setLocalVar
        setLocalVariable();
        //OnClick
        setOnClick();
        //Reload
        setReload();
    }

    private void setFindView(){
        mBack = findViewById(R.id.back_from_psiko);
        btnAddPsiko = findViewById(R.id.psiko_add_btn);
        reload = findViewById(R.id.psiko_reload);
        recyclerView = findViewById(R.id.psiko_recycler);
    }

    private void setLocalVariable() {
        if(getStatusUser.equals("admin")){
            btnAddPsiko.setVisibility(View.VISIBLE);
        } else {
            btnAddPsiko.setVisibility(View.INVISIBLE);
        }
    }

    private void setOnClick(){
        //Back
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAddPsiko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPsiko = new Intent(Psikologi.this, AddPsikologi.class);
                addPsiko.putExtra("id_psiko", "0");
                startActivity(addPsiko);
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

    private void getSharedPreference(){
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getStatusUser = preferences.getString("status_user", "null");
    }
}