package xtrch.com.prostheticgo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class DetailFoto extends AppCompatActivity {

    ImageButton btnBack;
    ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_foto);

        //FindView
        setFindView();
        //onClick
        setOnCLick();
        //getIntent
        getIntentState();
    }

    private void setFindView(){
        btnBack = findViewById(R.id.back_from_detail_foto);
        imgFoto = findViewById(R.id.detail_foto_foto);
    }

    private void setOnCLick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntentState(){
        Intent foto = getIntent();
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorBlack);
        Glide.with(Objects.requireNonNull(this)).load(Konfigurasi.URL_IMAGE_PRODUK + foto.getStringExtra("foto_produk")).apply(requestOptions).into(imgFoto);
    }
}