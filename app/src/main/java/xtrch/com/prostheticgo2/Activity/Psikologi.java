package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import xtrch.com.prostheticgo2.Model.ModelPsikologi;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class Psikologi extends AppCompatActivity {

    ImageButton mBack;
    ImageView btnAddPsiko;

    String getStatusUser;

    List<ModelPsikologi> psikoList;
    RecyclerView recyclerViewPsiko;
    ProgressBar loading;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psikologi);

        //FindView
        setFindView();
        //getSharedPreference
        getSharedPreference();
        //setLocalVar
        setLocalVariable();
        //OnClick
        setOnClick();
    }

    private void setFindView(){
        mBack = findViewById(R.id.back_from_psiko);
        btnAddPsiko = findViewById(R.id.psiko_add_btn);
        recyclerViewPsiko = findViewById(R.id.psiko_recycler);
        loading = findViewById(R.id.psiko_loading);
        swipeRefreshLayout = findViewById(R.id.psiko_swipe);


        recyclerViewPsiko.setHasFixedSize(true);
        recyclerViewPsiko.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        psikoList = new ArrayList<>();
    }

    private void setLocalVariable() {
        if(getStatusUser.equals("admin")){
            btnAddPsiko.setVisibility(View.VISIBLE);
        } else {
            btnAddPsiko.setVisibility(View.INVISIBLE);
        }
    }

    private void setOnClick(){
        //Back
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAddPsiko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPsiko = new Intent(Psikologi.this, AddPsikologi.class);
                addPsiko.putExtra("id_psiko", "0");
                startActivity(addPsiko);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                psikoList.clear();
                swipeRefreshLayout.setRefreshing(true);
                loadPostingan();
            }
        });
    }

    private void loadPostingan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_VIEW_PSIKOLOGI,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);


                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                if(product.getString("id_psikologi").equals("1")) {
                                    psikoList.add(new ModelPsikologi(
                                            product.getString("id_psikologi"),
                                            product.getString("judul_psikologi"),
                                            product.getString("isi_psikologi"),
                                            product.getString("foto_psikologi"),
                                            product.getString("id_user"),
                                            product.getString("tgl_input"),
                                            product.getString("nama_depan_user"),
                                            product.getString("nama_belakang_user"),
                                            product.getString("email_user")
                                    ));
                                }
                            }

                            AdapterPsikoRecycler adapter= new AdapterPsikoRecycler(Psikologi.this, psikoList);

                            if (adapter != null){
                                recyclerViewPsiko.setAdapter(adapter);
                                loading.setVisibility(View.INVISIBLE);
                                swipeRefreshLayout.setRefreshing(false);

                            }else {
                                Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
                            }

//                            loading.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext())).add(stringRequest);
    }

    private void getSharedPreference(){
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getStatusUser = preferences.getString("status_user", "null");
    }
}