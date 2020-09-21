package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import xtrch.com.prostheticgo2.R;

public class AddRehab extends AppCompatActivity {

    ImageView btnTambahFoto;
    EditText etJudul, etIsi;
    Spinner sJenis;
    ImageButton btnBack;
    TextView tvTitle;
    Button btnSimpan;
    SwipeRefreshLayout reload;

    String idRehab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rehab);

        //FindView
        setFindView();
        //getintent
        getIntentState();
        //Reload
        setReload();
    }

    private void setFindView(){
        btnTambahFoto = findViewById(R.id.add_rehab_foto);
        etJudul = findViewById(R.id.add_rehab_judul);
        etIsi = findViewById(R.id.add_rehab_isi);
        sJenis = findViewById(R.id.add_rehab_jenis);
        btnBack = findViewById(R.id.back_from_add_rehab);
        tvTitle = findViewById(R.id.add_rehab_title);
        btnSimpan = findViewById(R.id.add_rehab_simpan);
        reload = findViewById(R.id.add_rehab_reload);
    }

    private void getIntentState(){
        Intent id = getIntent();
        idRehab = id.getStringExtra("id_info");
        if (idRehab.equals("0")){
            tvTitle.setText("Tambah Rehabilitasi");
        } else {
            tvTitle.setText("Edit Rehabilitasi");
        }
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