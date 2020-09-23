package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import xtrch.com.prostheticgo2.R;

public class AddPsikologi extends AppCompatActivity {

    ImageView btnTambahFoto;
    EditText etJudul, etIsi;
//    Spinner sJenis;
    ImageButton btnBack;
    TextView tvTitle;
    Button btnSimpan;
    SwipeRefreshLayout reload;

    String idPsiko;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_psikologi);

        //Find View
        setFindView();
        //getIntent
        getIntentState();
        //OnClick
        setOnClick();
        //reload
        setReload();
    }

    private void setFindView(){
        btnTambahFoto = findViewById(R.id.add_psiko_foto);
        etJudul = findViewById(R.id.add_psiko_judul);
        etIsi = findViewById(R.id.add_psiko_isi);
        btnBack = findViewById(R.id.back_from_add_psiko);
        tvTitle = findViewById(R.id.add_psiko_title);
        btnSimpan = findViewById(R.id.add_psiko_simpan);
        reload = findViewById(R.id.add_psiko_reload);
    }

    private void getIntentState(){
        Intent psiko = getIntent();
        idPsiko = psiko.getStringExtra("id_psiko");
        if(idPsiko.equals("0")){
            tvTitle.setText("Tambah Psikologi");
        } else {
            tvTitle.setText("Edit Psikologi");
        }
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