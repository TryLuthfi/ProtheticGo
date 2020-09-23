package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import xtrch.com.prostheticgo2.Adapter.AdapterTabRehab;
import xtrch.com.prostheticgo2.R;

public class Rehabilitasi extends AppCompatActivity {

    ImageButton mBack;
    TabLayout rehabTab;
    ViewPager rehabPager;
    ImageView btnAddRehab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehabilitasi);

        //FindView
        setFindView();
        //TabLayout
        setTabLayout();
        //OnClick
        setOnClick();
    }

    private void setFindView(){
        mBack = findViewById(R.id.back_from_rehab);
        rehabTab = findViewById(R.id.rehab_tab);
        rehabPager = findViewById(R.id.rehab_pager);
        btnAddRehab = findViewById(R.id.rehab_add_btn);
    }

    private void setOnClick(){
        //Back
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Add
        btnAddRehab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addRehab = new Intent(Rehabilitasi.this, AddRehab.class);
                addRehab.putExtra("id_rehab", "0");
                startActivity(addRehab);
            }
        });
    }

    private void setTabLayout(){
        rehabTab.addTab(rehabTab.newTab().setText("Upper Extrimity"));
        rehabTab.addTab(rehabTab.newTab().setText("Lower Extrimity"));
        //Adapter
        AdapterTabRehab adapterTabRehab = new AdapterTabRehab(getSupportFragmentManager(), rehabTab.getTabCount());
        rehabPager.setAdapter(adapterTabRehab);
        rehabPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(rehabTab));
        rehabPager.setOffscreenPageLimit(adapterTabRehab.getCount());
        //OnClick
        rehabTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                rehabPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}