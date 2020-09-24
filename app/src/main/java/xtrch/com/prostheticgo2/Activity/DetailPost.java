package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class DetailPost extends AppCompatActivity {

    ImageView fotoPost, fotoAdmin;
    ImageButton btnBack;
    TextView tvJudul, tvIsi, tvNamaAdmin, tvTanggal;
    SwipeRefreshLayout reload;

    String Iid_info;
    String Ijudul_info;
    String Iisi_info;
    String Ifoto_info;
    String Ijenis_info;
    String Iid_user;
    String Itanggal_input;
    String Inama_depan_user;
    String Inama_belakang_user;
    String Iemail_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        //FindView
        setFindView();
        //getIntent
        getIntentData();
        //setTextFromRecycler
        setText();
        //OnClick
        setOnClick();
        //Reload
        setReload();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        Iid_info = intent.getStringExtra("id_info");
        Ijudul_info = intent.getStringExtra("judul_info");
        Iisi_info = intent.getStringExtra("isi_info");
        Ifoto_info = intent.getStringExtra("foto_info");
        Ijenis_info = intent.getStringExtra("jenis_info");
        Iid_user = intent.getStringExtra("id_user");
        Itanggal_input = intent.getStringExtra("tanggal_input");
        Inama_depan_user = intent.getStringExtra("nama_depan_user");
        Inama_belakang_user = intent.getStringExtra("nama_belakang_user");
        Iemail_user = intent.getStringExtra("email_user");
    }

    private void setText() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorPrimary);

        Glide.with(Objects.requireNonNull(getApplicationContext())).load(Konfigurasi.URL_IMAGE_INFORMASI + Ifoto_info).apply(requestOptions).into(fotoPost);
        tvJudul.setText(Ijudul_info);
        tvIsi.setText("    "+Iisi_info);
        tvNamaAdmin.setText(Inama_depan_user+" "+Inama_belakang_user);
        tvTanggal.setText(Itanggal_input);
    }

    private void setFindView(){
        fotoPost = findViewById(R.id.detail_post_foto);
        fotoAdmin = findViewById(R.id.detail_post_fotoAdmin);
        btnBack = findViewById(R.id.back_from_detail_post);
        tvJudul = findViewById(R.id.detail_post_judul);
        tvIsi = findViewById(R.id.detail_post_isi);
        tvNamaAdmin = findViewById(R.id.detail_post_namaAdmin);
        tvTanggal = findViewById(R.id.detail_post_tgl);
        reload = findViewById(R.id.detail_post_reload);
    }

    private void setOnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setReload(){
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload.setRefreshing(false);
            }
        });
    }
}