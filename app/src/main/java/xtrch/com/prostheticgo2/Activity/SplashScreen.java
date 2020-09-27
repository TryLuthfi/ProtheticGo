package xtrch.com.prostheticgo2.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.AppController;
import xtrch.com.prostheticgo2.Request.Konfigurasi;
import xtrch.com.prostheticgo2.Request.Utils;

public class SplashScreen extends AppCompatActivity {
    ImageView mainLogo;
    TextView version;
    Intent i;

    ProgressDialog dialog;
    String getId_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getFindView();
        setAnime();
        setTimmer();
    }

    private void setAnime() {
        Utils.darkenStatusBar(this, R.color.colorGrey);

        Animation fade_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mytransition);
        Animation from_bottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.frombottom);
        mainLogo.setAnimation(fade_out);
        version.setAnimation(from_bottom);
    }

    private void setTimmer() {
        cekSessions();
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        };
        timer.start();
    }

    private void cekSessions() {
        getIdUser();
        if(getId_user != "null"){
            getDataUser();
        } else{
            i = new Intent(getApplicationContext(), HomePage.class);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            startActivity(i);
            finish();
        }
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
                                } else{
                                    dialog.dismiss();
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

    private void gotoHomepage() {
        dialog.dismiss();
        startActivity(new Intent(getApplicationContext(), HomePage.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

    private void getFindView() {
        mainLogo = findViewById(R.id.SplashScreen_mainlogo);
        version = findViewById(R.id.SplashScreen_version);
        dialog= new ProgressDialog(SplashScreen.this);

        dialog.setMessage("Mengambil data...");
        dialog.setTitle("Harap sabar...");
    }

    private void getIdUser(){
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getId_user = preferences.getString("id_user", "null");

    }
}