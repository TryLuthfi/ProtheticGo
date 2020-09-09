package xtrch.com.prostheticgo2.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import xtrch.com.prostheticgo2.Fragment.FirstFragment;
import xtrch.com.prostheticgo2.Fragment.HomeFragment;
import xtrch.com.prostheticgo2.Fragment.SecondFragment;
import xtrch.com.prostheticgo2.Fragment.ThirdFragment;
import xtrch.com.prostheticgo2.R;

public class HomePage extends AppCompatActivity {

    String getId_user;

    AHBottomNavigation menuHome;
    FrameLayout frameLayout;

    HomeFragment homeFragment;
    SecondFragment secondFragment;
    ThirdFragment thirdFragment;
    FirstFragment firstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        
        setFindView();
        getIdUser();
        Toast.makeText(this, getId_user, Toast.LENGTH_SHORT).show();
        setMenuHome();
        setFragment(homeFragment);
    }

    private void setFindView() {
        //FindView
        frameLayout = findViewById(R.id.home_frame);
        menuHome = findViewById(R.id.home_menu);

        //Fragment Instatntiation
        homeFragment = new HomeFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        firstFragment = new FirstFragment();
    }

    private void setMenuHome(){
        AHBottomNavigationItem home = new AHBottomNavigationItem("Home", R.drawable.ic_round_home_24, R.color.colorPrimary);
        AHBottomNavigationItem info = new AHBottomNavigationItem("Informasi", R.drawable.ic_round_menu_book_24, R.color.colorPrimary);
//        AHBottomNavigationItem psiko = new AHBottomNavigationItem("Psikologi", R.drawable.ic_round_accessibility_new_24, R.color.colorPrimary);
//        AHBottomNavigationItem belanja = new AHBottomNavigationItem("Belanja", R.drawable.ic_round_shopping_cart_24, R.color.colorPrimary);
//        AHBottomNavigationItem layanan = new AHBottomNavigationItem("Layanan", R.drawable.ic_baseline_call_24, R.color.colorPrimary);
        AHBottomNavigationItem akun = new AHBottomNavigationItem("Akun", R.drawable.ic_round_person_24, R.color.colorPrimary);
        menuHome.addItem(home);
        menuHome.addItem(info);
        menuHome.addItem(akun);
        menuHome.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        menuHome.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        menuHome.setAccentColor(Color.parseColor("#8BC34A"));

        menuHome.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position)
                {
                    case 0:
                        setFragment(homeFragment);
                        return true;
                    case 1:
                        setFragment(secondFragment);
                        return true;
                    case 2:
                        setFragment(firstFragment);
                        return true;
                    case 3:
                        setFragment(thirdFragment);
                        return true;
                    case 4:
                        setFragment(thirdFragment);
                        return true;
                    default:
                        return false;
                }
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