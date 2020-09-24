package xtrch.com.prostheticgo2.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import xtrch.com.prostheticgo2.Model.ModelRehabLower;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class LowerRehabFragment extends Fragment {
    public LowerRehabFragment() {}


    List<ModelRehabLower> lowerRehabList;
    RecyclerView recyclerViewLowerRehab;
    ProgressBar loading;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lower_rehab, container, false);

        //FindView
        setFindView(view);
        loadPostingan();
        //Reload
        setOnClick();

        return view;
    }

    private void setFindView(View view){
        recyclerViewLowerRehab = view.findViewById(R.id.lower_rehab_recycler);
        loading = view.findViewById(R.id.lower__rehab_loading);
        swipeRefreshLayout = view.findViewById(R.id.lower_rehab_swipe);


        recyclerViewLowerRehab.setHasFixedSize(true);
        recyclerViewLowerRehab.setLayoutManager(new LinearLayoutManager(getActivity()));
        lowerRehabList = new ArrayList<>();
    }

    private void setOnClick(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lowerRehabList.clear();
                swipeRefreshLayout.setRefreshing(true);
                loadPostingan();
            }
        });
    }

    private void loadPostingan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_VIEW_REHABILITASI,

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
                                if(product.getString("jenis_rehabilitasi").equals("lower")) {
                                    lowerRehabList.add(new ModelRehabLower(
                                            product.getString("id_rehabilitas"),
                                            product.getString("judul_rehabilitasi"),
                                            product.getString("isi_rehabilitasi"),
                                            product.getString("foto_rehabilitasi"),
                                            product.getString("jenis_rehabilitasi"),
                                            product.getString("id_user"),
                                            product.getString("tgl_input"),
                                            product.getString("nama_depan_user"),
                                            product.getString("nama_belakang_user"),
                                            product.getString("email_user")
                                    ));
                                }
                            }

                            AdapterLowerRehabRecycler adapter= new AdapterLowerRehabRecycler(getActivity(), lowerRehabList);

                            if (adapter != null){
                                recyclerViewLowerRehab.setAdapter(adapter);
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