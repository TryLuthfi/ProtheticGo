package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import xtrch.com.prostheticgo2.R;

public class EditAkun extends AppCompatActivity {

    EditText etNamadepan, etNamabelakang, etEmail, etHp;
    Button btnSimpan;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        //FindView
        setFindFiew();
        //onClick
        setOnClick();
    }

    private void setFindFiew(){
        etNamadepan = findViewById(R.id.edit_akun_namadepan);
        etNamabelakang = findViewById(R.id.edit_akun_namabelakang);
        etEmail = findViewById(R.id.edit_akun_email);
        etHp = findViewById(R.id.edit_akun_hp);
        btnSimpan = findViewById(R.id.edit_akun_simpan_btn);
        btnBack = findViewById(R.id.back_from_edit_akun);
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