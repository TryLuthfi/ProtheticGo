package xtrch.com.prostheticgo2.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;
import xtrch.com.prostheticgo2.Request.Utils;

public class Register extends AppCompatActivity {
    ImageView btn_back;
    EditText et_firstname;
    EditText et_lastname;
    EditText et_email;
    EditText et_password;
    CardView btn_signup;
    TextView tv_login;

    String first_name;
    String last_name;
    String email;
    String password;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getFindView();
        setAnime();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmptyData();
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin();
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
        btn_back = findViewById(R.id.Register_btnBack);
        et_firstname = findViewById(R.id.Register_firstname);
        et_lastname = findViewById(R.id.Register_lastname);
        et_email = findViewById(R.id.Register_username);
        et_password = findViewById(R.id.Register_password);
        btn_signup = findViewById(R.id.Register_btnSignup);
        tv_login = findViewById(R.id.Register_btnLogin);

        progressDialog = new ProgressDialog(this);
    }

    private void gotoLogin(){
        startActivity(new Intent(getApplicationContext(), Login.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

    private void getEmptyData() {
        first_name = et_firstname.getText().toString().trim();
        last_name = et_lastname.getText().toString().trim();
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();

        if(first_name.isEmpty()){
            Toast.makeText(this, "Harap isi nama anda", Toast.LENGTH_SHORT).show();
        } else if(last_name.isEmpty()){
            Toast.makeText(this, "Harap isi nama anda", Toast.LENGTH_SHORT).show();
        } else if(email.isEmpty()){
            Toast.makeText(this, "harap isi email anda", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(this, "harap isi password anda", Toast.LENGTH_SHORT).show();
        } else {
            getDataRegister();
        }
    }

    private void getDataRegister() {
        progressDialog.setMessage("Register Process...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Konfigurasi.SUCCESS)) {
                            progressDialog.hide();
                            gotoLogin();
                            Toast.makeText(Register.this, "Terimakasih sudah mendaftar", Toast.LENGTH_SHORT).show();
                        } else if(response.contains(Konfigurasi.FAILURE)){
                            progressDialog.hide();
                            Toast.makeText(Register.this, "Upss... Gagal untuk mendaftar", Toast.LENGTH_SHORT).show();
                        } else if(response.contains(Konfigurasi.ALREADY)){
                            progressDialog.hide();
                            Toast.makeText(Register.this, "Akun anda sudah terdaftar lohh", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.hide();
                            Toast.makeText(Register.this, "Register gagal.. Cek koneksimu ya", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(Register.this, "Tidak bisa menjangaku server", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Konfigurasi.NAMA, first_name+" "+last_name);
                params.put(Konfigurasi.EMAIL, email);
                params.put(Konfigurasi.PASSWORD, password);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}