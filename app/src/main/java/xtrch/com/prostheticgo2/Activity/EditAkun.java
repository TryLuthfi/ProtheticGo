package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;
import xtrch.com.prostheticgo2.Request.RequestHandler;

public class EditAkun extends AppCompatActivity {

    EditText etNamadepan, etNamabelakang, etTempat, etTanggal, etAlamat, etPekerjaan, etHp;
    Button btnSimpan;
    ImageButton btnBack;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;

    String[] tanggal_lahir;
    String bulan;

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

    private void getMonth() {
        tanggal_lahir = Konfigurasi.Dtanggal_lahir_user.trim().split("-");
        if(tanggal_lahir[1].equals("01")){
            bulan = "Januari";
        } else if(tanggal_lahir[1].equals("02")){
            bulan = "Februari";
        } else if(tanggal_lahir[1].equals("03")){
            bulan = "Maret";
        } else if(tanggal_lahir[1].equals("04")){
            bulan = "April";
        } else if(tanggal_lahir[1].equals("05")){
            bulan = "Mei";
        } else if(tanggal_lahir[1].equals("06")){
            bulan = "Juni";
        } else if(tanggal_lahir[1].equals("07")){
            bulan = "Juli";
        } else if(tanggal_lahir[1].equals("08")){
            bulan = "Agustus";
        } else if(tanggal_lahir[1].equals("09")){
            bulan = "September";
        } else if(tanggal_lahir[1].equals("10")){
            bulan = "Oktober";
        } else if(tanggal_lahir[1].equals("11")){
            bulan = "November";
        } else if(tanggal_lahir[1].equals("12")){
            bulan = "Desember";
        } else {
            bulan = "?";
        }
    }

    private void setDatePicker(){
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                etTanggal.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void setLocalVariable() {
        etNamadepan.setText(Konfigurasi.Dnama_depan_user);
        etNamabelakang.setText(Konfigurasi.Dnama_belakang_user);
        etTempat.setText(Konfigurasi.Dtempat_lahir_user);
        etAlamat.setText(Konfigurasi.Dalamat_user);
        etHp.setText(Konfigurasi.Dnohp_user);
        etPekerjaan.setText(Konfigurasi.Dpekerjaan_user);
        etTanggal.setText(dateFormat.format(Date.valueOf(Konfigurasi.Dtanggal_lahir_user)));
    }

    private void setFindFiew(){
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        etNamadepan = findViewById(R.id.edit_akun_namadepan);
        etNamabelakang = findViewById(R.id.edit_akun_namabelakang);
        etTempat = findViewById(R.id.edit_akun_tempat_lahir);
        etTanggal = findViewById(R.id.edit_akun_tanggal_lahir);
        etAlamat = findViewById(R.id.edit_akun_alamat);
        etPekerjaan = findViewById(R.id.edit_akun_pekerjaan);
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
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatePicker();
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
                    Konfigurasi.Dnama_depan_user = etNamadepan.getText().toString();
                    Konfigurasi.Dnama_belakang_user = etNamabelakang.getText().toString();
                    Konfigurasi.Dtempat_lahir_user = etTempat.getText().toString();
                    Konfigurasi.Dtanggal_lahir_user = etTanggal.getText().toString();
                    Konfigurasi.Dalamat_user = etAlamat.getText().toString();
                    Konfigurasi.Dnohp_user = etHp.getText().toString();
                    Konfigurasi.Dpekerjaan_user = etPekerjaan.getText().toString();
                    setPreference(etNamadepan.getText().toString(), etNamabelakang.getText().toString(), etPekerjaan.getText().toString());
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
                params.put("tempat_lahir_user", etTempat.getText().toString());
                params.put("tanggal_lahir_user", etTanggal.getText().toString());
                params.put("alamat_user", etAlamat.getText().toString());
                params.put("nohp_user", etHp.getText().toString());
                params.put("pekerjaan_user", etPekerjaan.getText().toString());

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