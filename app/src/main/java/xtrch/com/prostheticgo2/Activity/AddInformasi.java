package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import xtrch.com.prostheticgo2.R;

public class AddInformasi extends AppCompatActivity {

    ImageView btnTambahFoto;
    EditText etJudul, etIsi;
    Spinner sJenis;
    ImageButton btnBack;
    TextView tvTitle;
    Button btnSimpan;

    String idInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_informasi);

        //FindView
        setFindView();
        //getIntent
        getIntentState();
    }

    private void setFindView(){
        btnTambahFoto = findViewById(R.id.add_info_foto);
        btnBack = findViewById(R.id.back_from_add_info);
        etJudul = findViewById(R.id.add_info_judul);
        etIsi = findViewById(R.id.add_info_isi);
        sJenis = findViewById(R.id.add_info_jenis);
        tvTitle = findViewById(R.id.add_info_title);
        btnSimpan = findViewById(R.id.add_info_simpan);
    }

    private void getIntentState(){
        Intent id = getIntent();
        idInfo = id.getStringExtra("id_info");
        if (idInfo.equals("0")){
            tvTitle.setText("Tambah Informasi");
        } else {
            tvTitle.setText("Edit Informasi");
        }
    }
}