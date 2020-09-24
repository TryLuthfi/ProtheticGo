package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import xtrch.com.prostheticgo2.R;

public class DetailPost extends AppCompatActivity {

    ImageView fotoPost, fotoAdmin;
    ImageButton btnBack;
    TextView tvJudul, tvIsi, tvNamaAdmin, tvTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        //FindView
        setFindView();
        //OnClick
        setOnClick();
    }

    private void setFindView(){
        fotoPost = findViewById(R.id.detail_post_foto);
        fotoAdmin = findViewById(R.id.detail_post_fotoAdmin);
        btnBack = findViewById(R.id.back_from_detail_post);
        tvJudul = findViewById(R.id.detail_post_judul);
        tvIsi = findViewById(R.id.detail_post_isi);
        tvNamaAdmin = findViewById(R.id.detail_post_namaAdmin);
        tvTanggal = findViewById(R.id.detail_post_tgl);
    }

    private void setOnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}