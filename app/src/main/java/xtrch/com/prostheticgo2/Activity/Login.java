package xtrch.com.prostheticgo2.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.AppController;
import xtrch.com.prostheticgo2.Request.Konfigurasi;
import xtrch.com.prostheticgo2.Request.Utils;
import xtrch.com.prostheticgo2.Request.encryptMd5;

public class Login extends AppCompatActivity {
    ImageView btn_back;
    EditText et_email;
    EditText et_password;
    TextView tv_signup;
    ProgressDialog progressDialog;
    CardView cd_login;
    String email = "";
    String password = "";
    String hasilmd5;

    String getId_user;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getFindView();
        setAnime();
        getDataUser();
        cekSessions();

        cd_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmptyData();
            }
        });

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegister();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setAnime() {
        Utils.darkenStatusBar(this, R.color.colorGrey);
    }

    private void getFindView() {
        btn_back = findViewById(R.id.Login_btnBack);
        et_email = findViewById(R.id.Login_email);
        et_password = findViewById(R.id.Login_password);
        tv_signup = findViewById(R.id.Login_btn_signup);
        progressDialog= new ProgressDialog(this);
        cd_login = findViewById(R.id.Login_btn_login);
        dialog= new ProgressDialog(Login.this);

        dialog.setMessage("Mengambil data...");
        dialog.setTitle("Harap sabar...");

    }

    private void gotoRegister() {
        startActivity(new Intent(getApplicationContext(), Register.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void cekSessions() {
        getIdUser();
        if(getId_user != "null"){
            getDataUser();
        }
    }

    //asd
    private void getDataLogin() {
        progressDialog.setMessage("Login Process...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Konfigurasi.SUCCESS)) {
                            progressDialog.hide();
                            String id_user = response.toString().split(";")[1];
                            String status_user = response.toString().split(";")[6];
                            setPreference(id_user,status_user);
                            gotoHomepage();
                        } else {
                            progressDialog.hide();
                            Toast.makeText(Login.this, "Email atau password anda salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(Login.this, "Tidak bisa menjangaku server", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Konfigurasi.EMAIL, email);
                params.put(Konfigurasi.PASSWORD, hasilmd5);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void getEmptyData() {
        email = et_email.getText().toString().trim();
        password= et_password.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(this, "Harap isi email", Toast.LENGTH_SHORT).show();
        } else if(password.isEmpty()){
            Toast.makeText(this, "Harap isi password", Toast.LENGTH_SHORT).show();
        } else{
            btnMD5();
        }
    }

    private void gotoHomepage() {
        dialog.dismiss();
        startActivity(new Intent(getApplicationContext(), HomePage.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

    public void btnMD5(){
        byte[] md5input = et_password.getText().toString().getBytes();
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

        hasilmd5 = md5Str;
        getDataLogin();

    }

    void setPreference(String id_user,String status_user){
        SharedPreferences mSettings = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("id_user", id_user);
        editor.putString("status_user", status_user);
        editor.apply();
    }

    private void getIdUser(){
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getId_user = preferences.getString("id_user", "null");

    }

    public void getDataUser() {
        dialog.show();
        StringRequest loadInvoice = new StringRequest(Request.Method.POST,
                Konfigurasi.URL_VIEW_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            DecimalFormat formatRupiah = (DecimalFormat) NumberFormat.getInstance();
                            formatRupiah.setPositivePrefix("Rp. ");
                            formatRupiah.setMinimumFractionDigits(0);
                            formatRupiah.setMaximumFractionDigits(0);

                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (getId_user.equals(jsonObject.getString("id_user"))) {

                                    Konfigurasi.Did_user = jsonObject.getString("id_user");
                                    Konfigurasi.Dnama_depan_user = jsonObject.getString("nama_depan_user");
                                    Konfigurasi.Dnama_belakang_user = jsonObject.getString("nama_belakang_user");
                                    Konfigurasi.Demail_user = jsonObject.getString("email_user");
                                    Konfigurasi.Dpass_user = jsonObject.getString("pass_user");
                                    Konfigurasi.Dalamat_user = jsonObject.getString("alamat_user");
                                    Konfigurasi.Dnohp_user = jsonObject.getString("nohp_user");
                                    Konfigurasi.Dpekerjaan_user = jsonObject.getString("pekerjaan_user");
                                    Konfigurasi.Dtempat_lahir_user = jsonObject.getString("tempat_lahir_user");
                                    Konfigurasi.Dtanggal_lahir_user = jsonObject.getString("tanggal_lahir_user");
                                    Konfigurasi.Dstatus_user = jsonObject.getString("status_user");
                                    Konfigurasi.Dtanggal_daftar = jsonObject.getString("tanggal_daftar");

                                    gotoHomepage();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(loadInvoice);
    }
}