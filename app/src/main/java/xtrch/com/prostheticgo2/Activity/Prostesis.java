package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import xtrch.com.prostheticgo2.Adapter.AdapterTabInfo;
import xtrch.com.prostheticgo2.R;

public class Prostesis extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton btnAdd;
    ImageButton btnBack;

    String getStatusUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prostesis);

        //FindView
        setFindView();
        //getSharedPref
        getIdUser();
        //setLocalVar
        setLocalVariable();
        //TabLayout
        setTabLayout();
        //OnClick
        setOnClick();
    }

    private void setFindView(){
        tabLayout = findViewById(R.id.prost_tab_layout);
        viewPager = findViewById(R.id.prost_pager);
        btnAdd = findViewById(R.id.prost_fab);
        btnBack = findViewById(R.id.back_from_prost);
    }

    private void setLocalVariable(){
        if(getStatusUser.equals("admin")){
            btnAdd.setVisibility(View.VISIBLE);
        } else {
            btnAdd.setVisibility(View.GONE);
        }
    }

    private void setOnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setTabLayout(){
        //TabLayout Tabs
        tabLayout.addTab(tabLayout.newTab().setText("Ekstremitas Atas"));
        tabLayout.addTab(tabLayout.newTab().setText("Ekstremitas Bawah"));

        //Adapter TabLayout
        AdapterTabInfo adapterTabInfo = new AdapterTabInfo(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterTabInfo);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(adapterTabInfo.getCount());

        //TabLayout OnClick
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void getIdUser(){
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
//        getId_user = preferences.getString("id_user", "null");
        getStatusUser = preferences.getString("status_user", "null");
    }
}