package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

import xtrch.com.prostheticgo2.R;

public class Rehabilitasi extends AppCompatActivity {

    ImageButton mBack;
    TabLayout rehabTab;
    ViewPager rehabPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehabilitasi);

        setFindView();

        //Back
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setFindView(){
        mBack = findViewById(R.id.back_from_rehab);
        rehabTab = findViewById(R.id.rehab_tab);
        rehabPager = findViewById(R.id.rehab_pager);
    }

    private void setTabLayout(){
        rehabTab.addTab(rehabTab.newTab().setText("Upper Extrimity"));
        rehabTab.addTab(rehabTab.newTab().setText("Lower Extrimity"));
    }
}