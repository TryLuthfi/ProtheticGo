package xtrch.com.prostheticgo2.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.google.android.material.tabs.TabLayout;

import xtrch.com.prostheticgo2.Adapter.AdapterTabInfo;
import xtrch.com.prostheticgo2.R;

public class InfoFragment extends Fragment {
    public InfoFragment() {}

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        //FindView
        setFindView(view);
        //TabLayout
        setTabLayout();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setFindView(View view){
        tabLayout = view.findViewById(R.id.info_tab_layout);
        viewPager = view.findViewById(R.id.info_pager);
    }

    private void setTabLayout(){
        //TabLayout Tabs
        tabLayout.addTab(tabLayout.newTab().setText("Upper Extremity"));
        tabLayout.addTab(tabLayout.newTab().setText("Lower Extremity"));

        //Adapter TabLayout
        AdapterTabInfo adapterTabInfo = new AdapterTabInfo(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterTabInfo);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
}