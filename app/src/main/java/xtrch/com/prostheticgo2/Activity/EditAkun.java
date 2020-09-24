package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;
import xtrch.com.prostheticgo2.Request.RequestHandler;

public class EditAkun extends AppCompatActivity {

    EditText etNamadepan, etNamabelakang, etEmail, etHp;
    Button btnSimpan;
    ImageButton btnBack;


    String getIdUser;
    String getStatusUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        //FindView
        setFindFiew();
        getSharedPreference();
        setLocalVariable();
        //onClick
        setOnClick();
    }

    private void setLocalVariable() {
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
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditProfile();
            }
        });
    }

    private void setEditProfile() {
        class UpdateData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditAkun.this, "Sedang Diproses...", "Tunggu...", false, false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                if(s.equals("Berhasil diperbarui")){
                    loading.dismiss();
                    setPreference(etNamadepan.getText().toString(), etNamabelakang.getText().toString(), etEmail.getText().toString());
                    Intent intent = new Intent(getApplicationContext(),HomePage.class);
                    startActivity(intent);
                }
//                Log.d(TAG, "onPostExecute: " + s);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_user", getIdUser);
                params.put("nama_depan_user", etNamadepan.getText().toString());
                params.put("nama_belakang_user", etNamabelakang.getText().toString());
                params.put("email_user", etEmail.getText().toString());

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_EDIT_PROFILE, params);
                return res;
            }
        }
        UpdateData ae = new UpdateData();
        ae.execute();
    }

    private void getSharedPreference(){
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getIdUser = preferences.getString("id_user", "null");
        getStatusUser = preferences.getString("status_user", "null");

    }

    void setPreference(String nama_depan_user, String nama_belakang_user, String email_user){
        SharedPreferences mSettings = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("nama_depan_user", nama_depan_user);
        editor.putString("nama_belakang_user", nama_belakang_user);
        editor.putString("email_user", email_user);
        editor.apply();
    }
}