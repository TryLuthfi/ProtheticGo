package xtrch.com.prostheticgo2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import xtrch.com.prostheticgo2.R;

public class HomeFragment extends Fragment {
    public HomeFragment() {}

    SwipeRefreshLayout reload;
    CircleImageView menuRehab, menuPsiko, menuBelanja, menuLayanan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        //FindView
        setFindView(view);

        //Reload Page
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload.setRefreshing(false);
            }
        });

        return view;
    }

    private void setFindView(View view){
        reload = view.findViewById(R.id.home_fragment_reload);
        menuRehab = view.findViewById(R.id.home_menu_rehab);
        menuPsiko = view.findViewById(R.id.home_menu_psiko);
        menuBelanja = view.findViewById(R.id.home_menu_belanja);
        menuLayanan = view.findViewById(R.id.home_menu_layanan);
    }
}