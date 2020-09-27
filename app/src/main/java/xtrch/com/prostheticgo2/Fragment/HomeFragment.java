package xtrch.com.prostheticgo2.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import xtrch.com.prostheticgo2.Activity.Belanja;
import xtrch.com.prostheticgo2.Activity.HomePage;
import xtrch.com.prostheticgo2.Activity.Layanan;
import xtrch.com.prostheticgo2.Activity.ListProvider;
import xtrch.com.prostheticgo2.Activity.Login;
import xtrch.com.prostheticgo2.Activity.Prostesis;
import xtrch.com.prostheticgo2.Activity.Psikologi;
import xtrch.com.prostheticgo2.Activity.Rehabilitasi;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    String getIdUser;
    String getStatusUser;

    SwipeRefreshLayout reload;
    TextView intro;
    CircleImageView menuRehab, menuPsiko, menuBelanja, menuLayanan, menuProst;
    Calendar c;
    SimpleDateFormat date;
    String formattedDate;
    int waktu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        setFindView(view);
        menuOnClick();
        getSharedPreference();
        setIntro(waktu);

        return view;
    }

    private void setFindView(View view) {
        reload = view.findViewById(R.id.home_fragment_reload);
        menuRehab = view.findViewById(R.id.home_menu_rehab);
        menuPsiko = view.findViewById(R.id.home_menu_psiko);
        menuBelanja = view.findViewById(R.id.home_menu_belanja);
        menuLayanan = view.findViewById(R.id.home_menu_layanan);
        menuProst = view.findViewById(R.id.home_menu_prost);
        intro = view.findViewById(R.id.home_intro);

        c = Calendar.getInstance();
        date = new SimpleDateFormat("HH");
        formattedDate = date.format(c.getTime());
        waktu = Integer.parseInt(formattedDate);
    }

    private void menuOnClick() {
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
                if(Konfigurasi.Did_user.equals("")){
                    final Dialog dialog= new Dialog(getActivity());
                    dialog.setCancelable(true);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setContentView(R.layout.popup_haruslogin);
                    Button button = dialog.findViewById(R.id.btnLogin_popup);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(), Login.class));
                        }
                    });
                    dialog.show();
                    Window window = dialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                } else {
                    Intent belanja = new Intent(getContext(), ListProvider.class);
                    startActivity(belanja);
                }
            }
        });
        menuLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent layanan = new Intent(getContext(), Layanan.class);
                startActivity(layanan);
            }
        });
        menuProst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prost = new Intent(getContext(), Prostesis.class);
                startActivity(prost);
            }
        });
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload.setRefreshing(false);
            }
        });
    }

    private void setIntro(final int waktu) {
        if(Konfigurasi.Did_user.equals("")) {
            if (waktu >= 4 && waktu < 10) {
                intro.setText("Selamat Pagi ");
            }
            if (waktu >= 10 && waktu < 15) {
                intro.setText("Selamat Siang ");
            }
            if (waktu >= 15 && waktu < 19) {
                intro.setText("Selamat Sore ");
            } else {
                intro.setText("Selamat Malam ");
            }
        } else{
            if (waktu >= 4 && waktu < 10) {
                intro.setText("Selamat Pagi, " + Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
            }
            if (waktu >= 10 && waktu < 15) {
                intro.setText("Selamat Siang, " + Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
            }
            if (waktu >= 15 && waktu < 19) {
                intro.setText("Selamat Sore, " + Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
            } else {
                intro.setText("Selamat Malam, " + Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
            }
        }
    }

    private void getSharedPreference(){
        SharedPreferences preferences = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getIdUser = preferences.getString("id_user", "null");
        getStatusUser = preferences.getString("status_user", "null");

    }
}
