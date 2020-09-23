package xtrch.com.prostheticgo2.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import xtrch.com.prostheticgo2.Adapter.AdapterTabInfo;
import xtrch.com.prostheticgo2.Fragment.AkunFragment;
import xtrch.com.prostheticgo2.Fragment.FirstFragment;
import xtrch.com.prostheticgo2.Fragment.HomeFragment;
import xtrch.com.prostheticgo2.Fragment.InfoFragment;
import xtrch.com.prostheticgo2.Fragment.SecondFragment;
import xtrch.com.prostheticgo2.Fragment.ThirdFragment;
import xtrch.com.prostheticgo2.R;

public class HomePage extends AppCompatActivity {

    String getId_user;

    BottomNavigationView menuHome;
    FrameLayout frameLayout;
    RelativeLayout tabContainer;
    TabLayout tabLayout;
    ViewPager viewPager;

    HomeFragment homeFragment;
    AkunFragment akunFragment;
    InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //FIndView
        setFindView();
        //IdUser
        getIdUser();
//        Toast.makeText(this, getId_user, Toast.LENGTH_SHORT).show();
        //MenuHome
        setMenuHome();
        //DefaultFragment
        setFragment(homeFragment);
        //TabLayout
        setTabLayout();
    }

    private void setFindView() {
        //FindView
        frameLayout = findViewById(R.id.home_frame);
        menuHome = findViewById(R.id.home_menu);
        tabContainer = findViewById(R.id.home_tab_container);
        tabLayout = findViewById(R.id.home_tab_layout);
        viewPager = findViewById(R.id.home_pager);

        //Fragment Instatntiation
        homeFragment = new HomeFragment();
        infoFragment = new InfoFragment();
        akunFragment = new AkunFragment();
    }

    private void setMenuHome(){
        menuHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.botnav_home:
                        setFragment(homeFragment);
                        frameLayout.setVisibility(View.VISIBLE);
                        tabContainer.setVisibility(View.GONE);
                        return true;
                    case R.id.botnav_info:
                        frameLayout.setVisibility(View.GONE);
//                        setFragment(infoFragment);
                        tabContainer.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.botnav_akun:
                        setFragment(akunFragment);
                        frameLayout.setVisibility(View.VISIBLE);
                        tabContainer.setVisibility(View.GONE);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void setTabLayout(){
        //TabLayout Tabs
        tabLayout.addTab(tabLayout.newTab().setText("Upper Extremity"));
        tabLayout.addTab(tabLayout.newTab().setText("Lower Extremity"));

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

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_frame, fragment);
        fragmentTransaction.commit();
    }

    private void getIdUser(){
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getId_user = preferences.getString("id_user", "null");

    }
}