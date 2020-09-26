package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import xtrch.com.prostheticgo2.Adapter.AdapterProduk;
import xtrch.com.prostheticgo2.Adapter.AdapterProvider;
import xtrch.com.prostheticgo2.Model.ModelProvider;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class ListProduk extends AppCompatActivity {

    ImageButton btnBack;
    SwipeRefreshLayout reload;
    RecyclerView recyclerView;
    TextView tvTitle;
    ProgressBar loading;
    ImageView btnAdd;

    List<ModelProduk> mItems;
    String idProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produk);

        //FindView
        setFindView();
        //getINtent
        getIntentState();
        //setLocalVar
        setLocalVariable();
        //loadData
        loadProduk();
        //OnClick
        setOnCLick();
        //Reload
        setReload();
    }

    private void setFindView(){
        mItems = new ArrayList<>();
        btnBack = findViewById(R.id.back_from_listProduk);
        reload = findViewById(R.id.listProduk_reload);
        recyclerView = findViewById(R.id.listProduk_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        tvTitle = findViewById(R.id.listProduk_title);
        loading = findViewById(R.id.listProduk_loading);
        btnAdd = findViewById(R.id.listProduk_add);
    }

    private void setOnCLick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(ListProduk.this, AddProduk.class);
                add.putExtra("id_produk", "0");
                startActivity(add);
            }
        });
    }

    private void getIntentState(){
        Intent getIntent = getIntent();
        tvTitle.setText("Produk " + getIntent.getStringExtra("nama_provider"));
        idProvider = getIntent.getStringExtra("id_provider");
    }

    private void setLocalVariable(){
        if(Konfigurasi.Dstatus_user.equals("admin")){
            btnAdd.setVisibility(View.VISIBLE);
        } else {
            btnAdd.setVisibility(View.GONE);
        }
    }

    private void setReload(){
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mItems.clear();
                loadProduk();
            }
        });
    }

    private void loadProduk() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_VIEW_PRODUK,

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

                                if(product.getString("id_provider").equals(idProvider)) {
                                    mItems.add(new ModelProduk(
                                            product.getString("id_produk"),
                                            product.getString("nama_produk"),
                                            product.getString("deskripsi_produk"),
                                            product.getString("berat_produk"),
                                            product.getString("stok_produk"),
                                            product.getString("harga_produk"),
                                            product.getString("id_provider"),
                                            product.getString("tgl_input"),
                                            product.getString("nohp_provider"),
                                            product.getString("foto_produk")
                                    ));
                                }
                            }

                            AdapterProduk adapter= new AdapterProduk(ListProduk.this, mItems);

                            if (adapter != null){
                                recyclerView.setAdapter(adapter);
                                loading.setVisibility(View.INVISIBLE);
                                reload.setRefreshing(false);

                            }else {
                                Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
                            }

//                            loading.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ListProduk.this, "EX" + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListProduk.this, "ER" + error, Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext())).add(stringRequest);
    }
}