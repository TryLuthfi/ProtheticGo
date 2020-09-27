package xtrch.com.prostheticgo2.Fragment;

import android.app.AlertDialog;
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
import xtrch.com.prostheticgo2.Activity.DetailPost;
import xtrch.com.prostheticgo2.Activity.DetailPostPsiko;
import xtrch.com.prostheticgo2.Activity.EditAkun;
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
    TextView intro, sejarahProst, modernProst, indoProst;
    CircleImageView menuRehab, menuPsiko, menuBelanja, menuLayanan, menuProst;
    Calendar c;
    SimpleDateFormat date;
    String formattedDate;
    int waktu;
    AlertDialog.Builder dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        setFindView(view);
        menuOnClick();
        getSharedPreference();
        setIntro(waktu);
        setText();

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
        sejarahProst = view.findViewById(R.id.home_sejarah_prost);
        modernProst = view.findViewById(R.id.home_modern_prost);
        indoProst = view.findViewById(R.id.home_indo_prost);
        dialog = new AlertDialog.Builder(getContext());

        c = Calendar.getInstance();
        date = new SimpleDateFormat("HH");
        formattedDate = date.format(c.getTime());
        waktu = Integer.parseInt(formattedDate);
//        Toast.makeText(getContext(), "" + waktu, Toast.LENGTH_SHORT).show();
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
                Intent psiko = new Intent(getContext(), DetailPostPsiko.class);
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
                } else if(Konfigurasi.Dalamat_user.equals("null") || Konfigurasi.Dnohp_user.equals("null")){
                    final Dialog dialog= new Dialog(getActivity());
                    dialog.setCancelable(true);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setContentView(R.layout.popup_haruslogin);
                    TextView textView = dialog.findViewById(R.id.popup_login_text);
                    Button button = dialog.findViewById(R.id.btnLogin_popup);
                    textView.setText("Kamu harus melengkapi datamu untuk bisa membeli produk");
                    button.setText("Lengkapi Data");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(), EditAkun.class));
                        }
                    });
                    dialog.show();
                    Window window = dialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                else {
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
            else if (waktu >= 10 && waktu < 15) {
                intro.setText("Selamat Siang ");
            }
            else if (waktu >= 15 && waktu < 18) {
                intro.setText("Selamat Sore ");
            }
            else if(waktu >= 18) {
                intro.setText("Selamat Malam ");
            }
        } else{
            if (waktu >= 4 && waktu < 10) {
                intro.setText("Selamat Pagi, " + Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
            }
            else if (waktu >= 10 && waktu < 15) {
                intro.setText("Selamat Siang, " + Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
            }
            else if (waktu >= 15 && waktu < 18) {
                intro.setText("Selamat Sore, " + Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
            }
            else if (waktu >= 18){
                intro.setText("Selamat Malam, " + Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
            }
        }
    }

    private void setText(){
        sejarahProst.setText("Dikenal sejak era kerajaan Mesir, bidang Prostetik telah berubah menjadi contoh bagaimana determinasi manusia untuk menjadi lebih baik.\n(Klik untuk selengkapnya)");
        modernProst.setText("Beralih ke era modern seiring berlangsungnya Perang Saudara Amerika Serikat, jumlah kasus amputasi meningkat drastis.\n(Klik untuk selengkapnya)");
        indoProst.setText("Pada tahun 1946 bersama Suroto Reksopranoto pertama kali membuat alat bantu Ortotik dan Prostetik yang sangat sederhana...\n(Klik untuk selengkapnya");
        sejarahProst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("Sejarah Prosthesis");
                dialog.setMessage("Dikenal sejak era kerajaan Mesir, bidang Prostetik telah berubah menjadi contoh bagaimana determinasi manusia untuk menjadi lebih baik. Sejarah evolusi dari prostetik merupakan sangatlah panjang, dari bentuk awal yang primitif hingga bentuk masa kini yang canggih.\n\nTeknologi Prostesis tertua ditemukan di kota Thebes, Mesir pada tahun 2000. Jari jempol palsu ditemukan di mummy  yang diduga menderita komplikasi dari penyakit Diabetes Mellitus. Jari jempol palsu ini diduga dibuat pada tahun 1069 hingga 664 Sebelum Masehi. Kaki Palsu bersejarah lainnya yang diduga dibuat pada sekitar 300 sebelum masehi ditemukan di Capua, Itali, pada tahun 1858. Kaki palsu ini dibuat menggunakan besi dan peruggu dengan inti dari kayu, diduga untuk pasien aputasi dibawah lutut.");
                dialog.setCancelable(true);
                dialog.setIcon(R.drawable.app_logo_trans);
                dialog.show();
            }
        });
        modernProst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("Prosthetic Modern");
                dialog.setMessage("Beralih ke era modern seiring berlangsungnya Perang Saudara Amerika Serikat, jumlah kasus amputasi meningkat drastis. James Hanger, salah satu korban amputasi pertama pada Perang Saudara mengembangkan apa yang kemudian dinamakan “Hanger Limb”. Orang-orang seperti Hanger, Selpho, Palmer dan A. A. Marks membantu mengembangkan dan memajukan bidang prostetik dalam hal mekanisme dan bahan.");
                dialog.setCancelable(true);
                dialog.setIcon(R.drawable.app_logo_trans);
                dialog.show();
            }
        });
        indoProst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("Prosthetic di Indonesia");
                dialog.setMessage("Pada tahun 1946 bersama Suroto Reksopranoto pertama kali membuat alat bantu Ortotik dan Prostetik yang sangat sederhana (dari bambu) yang merupakan cikal bakal / perintisan bengkel Protese di Indonesia yang kemudian berkembang menjadi training center.");
                dialog.setCancelable(true);
                dialog.setIcon(R.drawable.app_logo_trans);
                dialog.show();
            }
        });
    }

    private void getSharedPreference(){
        SharedPreferences preferences = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getIdUser = preferences.getString("id_user", "null");
        getStatusUser = preferences.getString("status_user", "null");

    }
}
