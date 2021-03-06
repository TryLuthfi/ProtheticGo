package xtrch.com.prostheticgo2.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

import xtrch.com.prostheticgo2.Activity.EditAkun;
import xtrch.com.prostheticgo2.Activity.EditFotoAkun;
import xtrch.com.prostheticgo2.Activity.GantiPassword;
import xtrch.com.prostheticgo2.Activity.HomePage;
import xtrch.com.prostheticgo2.Activity.Login;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class AkunFragment extends Fragment {
    public AkunFragment() {}

    Button btnLogout;
    Button btnLogin;
    SwipeRefreshLayout reload;
    ImageView btnEdit, btnFoto;
    Dialog dialog,dialogFoto;
    ScrollView container2;

    String getIdUser;
    String getStatusUser;

    TextView nama;
    TextView tgl;
    TextView alamat;
    TextView nohp;
    TextView pekerjaan;

    String[] tanggal_lahir;
    String bulan;

    SimpleDateFormat dateFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        //FindView
        setFindView(view);
        //SharedPreference
        getSharedPreference();
        //setNamaBulan
//        getMonth();
        //cekSessions
        cekSessions();
        //on Click
        setOnClick();
        //Logout
        logoutBtn();
        //Login
        loginBtn();
        //Reload Page
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSharedPreference();
                reload.setRefreshing(false);
            }
        });

        return view;
    }

    private void cekSessions() {
        if(Konfigurasi.Did_user.equals("")){
            container2.setVisibility(View.VISIBLE);
            reload.setVisibility(View.GONE);
        } else {
            setLocalVariable();
            container2.setVisibility(View.GONE);
            reload.setVisibility(View.VISIBLE);
        }
    }

    private void getMonth() {
        tanggal_lahir = Konfigurasi.Dtanggal_lahir_user.trim().split("-");
        if(tanggal_lahir[1].equals("01")){
            bulan = "Januari";
        } else if(tanggal_lahir[1].equals("02")){
            bulan = "Februari";
        } else if(tanggal_lahir[1].equals("03")){
            bulan = "Maret";
        } else if(tanggal_lahir[1].equals("04")){
            bulan = "April";
        } else if(tanggal_lahir[1].equals("05")){
            bulan = "Mei";
        } else if(tanggal_lahir[1].equals("06")){
            bulan = "Juni";
        } else if(tanggal_lahir[1].equals("07")){
            bulan = "Juli";
        } else if(tanggal_lahir[1].equals("08")){
            bulan = "Agustus";
        } else if(tanggal_lahir[1].equals("09")){
            bulan = "September";
        } else if(tanggal_lahir[1].equals("10")){
            bulan = "Oktober";
        } else if(tanggal_lahir[1].equals("11")){
            bulan = "November";
        } else if(tanggal_lahir[1].equals("12")){
            bulan = "Desember";
        } else {
            bulan = "?";
        }
    }

    private void setLocalVariable() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorWhite);
        nama.setText(Konfigurasi.Dnama_depan_user +" "+Konfigurasi.Dnama_belakang_user);
        tgl.setText(Konfigurasi.Dtempat_lahir_user+", "+ dateFormat.format(Date.valueOf(Konfigurasi.Dtanggal_lahir_user)));
        alamat.setText(Konfigurasi.Dalamat_user);
        nohp.setText(Konfigurasi.Dnohp_user);
        pekerjaan.setText(Konfigurasi.Dpekerjaan_user);
            Glide.with(Objects.requireNonNull(this)).load(Konfigurasi.URL_IMAGE_USER + Konfigurasi.Dfoto_user).apply(requestOptions).into(btnFoto);
    }

    private void setFindView(View view){
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        btnLogout = view.findViewById(R.id.akun_logout_btn);
        btnLogin = view.findViewById(R.id.akun_login_btn);
        reload = view.findViewById(R.id.akun_fragment_reload);
        nama = view.findViewById(R.id.akun_nama);
        tgl = view.findViewById(R.id.akun_tgl);
        alamat = view.findViewById(R.id.akun_alamat);
        nohp = view.findViewById(R.id.akun_nohp);
        pekerjaan = view.findViewById(R.id.akun_pekerjaan);
        btnEdit = view.findViewById(R.id.akun_fragment_edit);
        container2 = view.findViewById(R.id.container2);
        btnFoto = view.findViewById(R.id.akun_foto_profil);
    }

    private void setOnClick(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialogEdit();
            }
        });
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialogFoto();
            }
        });
    }

    private void setDialogEdit(){
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_akun);
        dialog.setCancelable(true);
        RelativeLayout btnEdit = dialog.findViewById(R.id.dialog_edit_akun_edit_btn);
        RelativeLayout btnPass = dialog.findViewById(R.id.dialog_edit_akun_pass_btn);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getContext(), EditAkun.class);
                dialog.dismiss();
                startActivity(edit);
            }
        });
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ganti = new Intent(getContext(), GantiPassword.class);
                dialog.dismiss();
                startActivity(ganti);
            }
        });
        dialog.show();
    }

    private void setDialogFoto(){
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_foto);
        dialog.setCancelable(true);
        RelativeLayout btnProfil = dialog.findViewById(R.id.dialog_foto_profil);
        RelativeLayout btnDepan = dialog.findViewById(R.id.dialog_foto_depan);
        RelativeLayout btnSamping = dialog.findViewById(R.id.dialog_foto_samping);
        RelativeLayout btnBlkg = dialog.findViewById(R.id.dialog_foto_blkg);
        RelativeLayout btnProst = dialog.findViewById(R.id.dialog_foto_prost);
//        ImageView btnCancel = dialog.findViewById(R.id.dialog_edit_akun_cancel);
        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getContext(), EditFotoAkun.class);
                edit.putExtra("status", "profil");
                dialog.dismiss();
                startActivity(edit);
            }
        });
        btnDepan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getContext(), EditFotoAkun.class);
                edit.putExtra("status", "depan");
                dialog.dismiss();
                startActivity(edit);
            }
        });
        btnSamping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getContext(), EditFotoAkun.class);
                edit.putExtra("status", "samping");
                dialog.dismiss();
                startActivity(edit);
            }
        });
        btnBlkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getContext(), EditFotoAkun.class);
                edit.putExtra("status", "blkg");
                dialog.dismiss();
                startActivity(edit);
            }
        });
        btnProst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getContext(), EditFotoAkun.class);
                edit.putExtra("status", "prost");
                dialog.dismiss();
                startActivity(edit);
            }
        });
        dialog.show();
    }

    private void logoutBtn() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearSharedPreference();
                SharedPreferences preferences = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                startActivity(new Intent(getActivity(), HomePage.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                editor.clear();
                editor.apply();
                getActivity().finish();
            }
        });
    }

    private void clearSharedPreference() {
        Konfigurasi.Did_user = "";
        Konfigurasi.Dnama_depan_user = "";
        Konfigurasi.Dnama_belakang_user = "";
        Konfigurasi.Demail_user = "";
        Konfigurasi.Dpass_user = "";
        Konfigurasi.Dalamat_user = "";
        Konfigurasi.Dnohp_user = "";
        Konfigurasi.Dpekerjaan_user = "";
        Konfigurasi.Dtempat_lahir_user = "";
        Konfigurasi.Dtanggal_lahir_user = "";
        Konfigurasi.Dstatus_user = "";
        Konfigurasi.Dtanggal_daftar = "";
    }

    private void loginBtn() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();
            }
        });
    }

    private void getSharedPreference(){
        SharedPreferences preferences = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getIdUser = preferences.getString("id_user", "null");
        getStatusUser = preferences.getString("status_user", "null");

    }
}