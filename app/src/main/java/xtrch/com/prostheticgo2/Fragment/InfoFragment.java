package xtrch.com.prostheticgo2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import xtrch.com.prostheticgo2.Activity.AddInformasi;
import xtrch.com.prostheticgo2.Adapter.AdapterTabInfo;
import xtrch.com.prostheticgo2.R;

public class InfoFragment extends Fragment {
    public InfoFragment() {}

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton floating;

    String getIdUser;
    String getNamaDepanUser;
    String getNamaBelakangUser;
    String getEmailUser;
    String getPasswordUser;
    String getStatusUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        //FindView
        setFindView(view);
        getSharedPreference();
        setLocalVariable();
        //TabLayout
        setTabLayout();
        setOnClick();

        return view;
    }

    private void setOnClick() {
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddInformasi.class);
                intent.putExtra("id_info",  "0");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setFindView(View view){
        tabLayout = view.findViewById(R.id.info_tab_layout);
        viewPager = view.findViewById(R.id.info_pager);
        floating = view.findViewById(R.id.upper_floating);
    }

    private void setLocalVariable() {
        if(getStatusUser.equals("admin")){
            floating.setVisibility(View.VISIBLE);
        } else {
            floating.setVisibility(View.VISIBLE);
        }
    }

    private void setTabLayout(){
        //TabLayout Tabs
        tabLayout.addTab(tabLayout.newTab().setText("Upper Extremity"));
        tabLayout.addTab(tabLayout.newTab().setText("Lower Extremity"));

        //Adapter TabLayout
        AdapterTabInfo adapterTabInfo = new AdapterTabInfo(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
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

    private void getSharedPreference(){
        SharedPreferences preferences = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getIdUser = preferences.getString("id_user", "null");
        getStatusUser = preferences.getString("status_user", "null");

    }
}