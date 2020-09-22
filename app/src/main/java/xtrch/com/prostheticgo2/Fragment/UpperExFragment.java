package xtrch.com.prostheticgo2.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import xtrch.com.prostheticgo2.Adapter.AdapterLowerInfoRecycler;
import xtrch.com.prostheticgo2.Adapter.AdapterUpperInfoReycler;
import xtrch.com.prostheticgo2.Model.ModelInfoLower;
import xtrch.com.prostheticgo2.Model.ModelInfoUpper;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class UpperExFragment extends Fragment {
    public UpperExFragment() {}

    List<ModelInfoUpper> upperInfoList;
    RecyclerView recyclerViewUpperInfo;
    ProgressBar loading;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upper_ex, container, false);

        //FindView
        setFindView(view);
        loadPostingan();
        //OnClick
        setOnClick();

        return view;
    }

    private void setFindView(View view){
        recyclerViewUpperInfo = view.findViewById(R.id.upper_recycler);
        loading = view.findViewById(R.id.upper_loading);
        swipeRefreshLayout = view.findViewById(R.id.upper_swipe);


        recyclerViewUpperInfo.setHasFixedSize(true);
        recyclerViewUpperInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        upperInfoList = new ArrayList<>();
    }

    private void setOnClick(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                upperInfoList.clear();
                swipeRefreshLayout.setRefreshing(true);
                loadPostingan();
            }
        });
    }

    private void loadPostingan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_VIEW_INFORMASI,

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
                                if(product.getString("jenis_informasi").equals("upper")) {
                                    upperInfoList.add(new ModelInfoUpper(
                                            product.getString("id_informasi"),
                                            product.getString("judul_informasi"),
                                            product.getString("isi_informasi"),
                                            product.getString("foto_informasi"),
                                            product.getString("jenis_informasi"),
                                            product.getString("id_user"),
                                            product.getString("tgl_input"),
                                            product.getString("nama_depan_user"),
                                            product.getString("nama_belakang_user"),
                                            product.getString("email_user")
                                    ));
                                }
                            }

                            AdapterUpperInfoReycler adapter= new AdapterUpperInfoReycler(getActivity(), upperInfoList);

                            if (adapter != null){
                                recyclerViewUpperInfo.setAdapter(adapter);
                                loading.setVisibility(View.INVISIBLE);
                                swipeRefreshLayout.setRefreshing(false);

                            }else {
                                Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
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

        //adding our stringrequest to queue
        Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(stringRequest);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}