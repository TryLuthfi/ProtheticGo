package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;
import xtrch.com.prostheticgo2.Request.RequestHandler;
import xtrch.com.prostheticgo2.Request.encryptMd5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.HashMap;

public class GantiPassword extends AppCompatActivity {

    EditText old_pass, new_pass, new_pass_repeat;
    Button btnSimpan;

    String Sold, Snew, SnewP;
    String hasilmd5baru, hasilmd5lama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);

        setFindView();
        setOnCLick();
    }

    private void setString() {
        Sold = old_pass.getText().toString().trim();
        Snew = new_pass.getText().toString().trim();
        SnewP = new_pass_repeat.getText().toString().trim();
    }

    private void setOnCLick() {
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMD5lama();
            }
        });
    }

    private void checkPasswordLama() {
        if(!hasilmd5lama.equals(Konfigurasi.Dpass_user)){
            Toast.makeText(this, "Kata sandi saat ini salah", Toast.LENGTH_SHORT).show();
        } else {
            checkPasswordBaru();
        }
    }

    private void checkPasswordBaru() {
        if(Snew.equals(Sold)){
            Toast.makeText(this, "Kata sandi tidak berubah", Toast.LENGTH_SHORT).show();
        } else if(!Snew.equals(SnewP)){
            Toast.makeText(this, "Kata sandi tidak cocok", Toast.LENGTH_SHORT).show();
        } else if(Snew.equals(SnewP)){

            class UpdateData extends AsyncTask<Void,Void,String> {
                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(GantiPassword.this, "Sedang Diproses...", "Tunggu...", false, false);

                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    if(s.equals("Berhasil diperbarui")){
                        loading.dismiss();
                        Konfigurasi.Dpass_user = hasilmd5baru;
                        finish();
                    }
                    loading.dismiss();
                }

                @Override
                protected String doInBackground(Void... v) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("id_user", Konfigurasi.Did_user);
                    params.put("pass_user", hasilmd5baru);

                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(Konfigurasi.URL_EDIT_PASSWORD, params);
                    return res;
                }
            }
            UpdateData ae = new UpdateData();
            ae.execute();
        }

    }

    private void setFindView() {
        old_pass = findViewById(R.id.old_pass);
        new_pass = findViewById(R.id.new_pass);
        new_pass_repeat = findViewById(R.id.new_pass_repeat);
        btnSimpan = findViewById(R.id.ganti_password_simpan_btn);
    }

    public void btnMD5baru(){
        byte[] md5input = new_pass.getText().toString().getBytes();
        BigInteger md5Data = null;

        try{
            md5Data =new BigInteger(1, encryptMd5.encryptMD5(md5input));
        }catch (Exception e){
            e.printStackTrace();
        }

        String md5Str = md5Data.toString(16);
        if(md5Str.length() < 32){
            md5Str = 0 + md5Str;
        }

        hasilmd5baru = md5Str;
        checkDataKosong();
    }

    public void btnMD5lama(){
        byte[] md5input = old_pass.getText().toString().getBytes();
        BigInteger md5Data = null;

        try{
            md5Data =new BigInteger(1, encryptMd5.encryptMD5(md5input));
        }catch (Exception e){
            e.printStackTrace();
        }

        String md5Str = md5Data.toString(16);
        if(md5Str.length() < 32){
            md5Str = 0 + md5Str;
        }

        hasilmd5lama = md5Str;
        btnMD5baru();
    }

    private void checkDataKosong() {
        setString();
        if(Sold.isEmpty()){
            old_pass.setError("Harap lengkapi data");
            Toast.makeText(this, "Harap lengkapi data", Toast.LENGTH_SHORT).show();
        } else if(Snew.isEmpty()){
            new_pass.setError("Harap lengkapi data");
            Toast.makeText(this, "Harap lengkapi data", Toast.LENGTH_SHORT).show();
        } else if(SnewP.isEmpty()){
            new_pass_repeat.setError("Harap lengkapi data");
            Toast.makeText(this, "Harap lengkapi data", Toast.LENGTH_SHORT).show();
        } else {
            checkPasswordLama();
        }
    }
}