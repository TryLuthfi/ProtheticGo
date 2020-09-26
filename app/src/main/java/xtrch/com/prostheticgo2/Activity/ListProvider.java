package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

import xtrch.com.prostheticgo2.Adapter.AdapterLowerRehabRecycler;
import xtrch.com.prostheticgo2.Adapter.AdapterProvider;
import xtrch.com.prostheticgo2.Model.ModelProvider;
import xtrch.com.prostheticgo2.Model.ModelRehabLower;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class ListProvider extends AppCompatActivity {

    ImageButton btnBack;
    SwipeRefreshLayout reload;
    RecyclerView recyclerView;
    ProgressBar loading;

    List<ModelProvider> mItems;
    AdapterProvider adapterProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_provider);

        //FindView
        setFindView();
        //OnClick
        setOnClick();
        //LoadProv
        loadProvider();
        //Reload
        setReload();
    }

    private void setFindView(){
        mItems = new ArrayList<>();
        reload = findViewById(R.id.listProv_reload);
        recyclerView = findViewById(R.id.listProv_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        btnBack = findViewById(R.id.back_from_listProv);
        loading = findViewById(R.id.listProv_loading);
    }

    private void setOnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setReload(){
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadProvider();
            }
        });
    }

    private void loadProvider() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_VIEW_PROVIDER,

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

                                //adding the product to product list
//                                if(product.getString("jenis_rehabilitasi").equals("lower")) {
                                    mItems.add(new ModelProvider(
                                            product.getString("id_provider"),
                                            product.getString("nama_provider"),
                                            product.getString("foto_provider"),
                                            product.getString("nohp_provider")
                                    ));
//                                }
                            }

                            adapterProvider = new AdapterProvider(ListProvider.this, mItems);

                            if (adapterProvider != null){
                                recyclerView.setAdapter(adapterProvider);
                                loading.setVisibility(View.INVISIBLE);
                                reload.setRefreshing(false);

                            }else {
                                Toast.makeText(ListProvider.this, "null", Toast.LENGTH_SHORT).show();
                            }

//                            loading.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ListProvider.this, "" + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListProvider.this, "" +error, Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext())).add(stringRequest);
    }
}