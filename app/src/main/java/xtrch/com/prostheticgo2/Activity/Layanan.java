package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class Layanan extends AppCompatActivity {

    ImageButton mBack;
    EditText etNama, etAlamat, etEmail, etKet;
    Button btnKirim;
    SwipeRefreshLayout reload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);

        //FindView
        setFindView();
        //OnClick
        setOnCLick();
        //Local Var
        setLocalVariable();
        //Reload
        setReload();
    }

    private void setFindView(){
        mBack = findViewById(R.id.back_from_layanan);
        etNama = findViewById(R.id.layanan_nama);
        etAlamat = findViewById(R.id.layanan_alamat);
        etEmail = findViewById(R.id.layanan_email);
        etKet = findViewById(R.id.layanan_ket);
        btnKirim = findViewById(R.id.layanan_kirim_btn);
        reload = findViewById(R.id.layanan_reload);
    }

    private void setOnCLick(){
        //Back
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail(){
        String[] TO = {"prosthetic.go@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "LAYANAN_PROSTESIS_" + etNama.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Nama : " + etNama.getText().toString() + "\n\n" +
                "Alamat : " + etAlamat.getText().toString() + "\n\n" +
                "Email : " + etEmail.getText().toString() + "\n\n" +
                "Keterangan : " + etKet.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Kirim email..."));
            finish();
//            Toast.makeText(this, "Berhasil mengirim email", Toast.LENGTH_SHORT).show();
        } catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this, "Tidak ada client email yang terinstall", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLocalVariable(){
        etNama.setText(Konfigurasi.Dnama_depan_user + " " + Konfigurasi.Dnama_belakang_user);
        etAlamat.setText(Konfigurasi.Dalamat_user);
        etEmail.setText(Konfigurasi.Demail_user);
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