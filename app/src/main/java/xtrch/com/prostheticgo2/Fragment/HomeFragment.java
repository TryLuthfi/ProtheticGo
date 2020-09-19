package xtrch.com.prostheticgo2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import xtrch.com.prostheticgo2.Activity.Belanja;
import xtrch.com.prostheticgo2.Activity.Layanan;
import xtrch.com.prostheticgo2.Activity.Psikologi;
import xtrch.com.prostheticgo2.Activity.Rehabilitasi;
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

        setFindView(view);
        menuOnClick();
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

    private void menuOnClick(){
        menuRehab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rehab = new Intent(getContext(), Rehabilitasi.class);
                startActivity(rehab);
            }
        });
        menuPsiko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent psiko = new Intent(getContext(), Psikologi.class);
                startActivity(psiko);
            }
        });
        menuBelanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent belanja = new Intent(getContext(), Belanja.class);
                startActivity(belanja);
            }
        });
        menuLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent layanan = new Intent(getContext(), Layanan.class);
                startActivity(layanan);
            }
        });
    }
}