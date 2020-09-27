package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import xtrch.com.prostheticgo2.Model.ModelPsikologi;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.AppController;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DetailPostPsiko extends AppCompatActivity {

    ImageView fotoPost, fotoAdmin;
    ImageButton btnBack;
    TextView tvJudul, tvIsi, tvNamaAdmin, tvTanggal;
    SwipeRefreshLayout reload;
    RelativeLayout header;
    ProgressBar progressBar;
    ScrollView scrollView;

    String Iid_info;
    String Ijudul_info;
    String Iisi_info;
    String Ifoto_info;
    String Ijenis_info;
    String Iid_user;
    String Itanggal_input;
    String Inama_depan_user;
    String Inama_belakang_user;
    String Iemail_user;
    String Istatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post_psiko);

        setFindView();
        loadPostingan();
        setOnRefresh();
    }

    private void setOnRefresh() {
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPostingan();
            }
        });
    }

    private void setText() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorPrimary);
        Glide.with(Objects.requireNonNull(getApplicationContext())).load(Konfigurasi.URL_IMAGE_PSIKO + Ifoto_info).apply(requestOptions).into(fotoPost);

        tvJudul.setText(Ijudul_info);
        tvIsi.setText("    "+Iisi_info);
        tvNamaAdmin.setText(Inama_depan_user+" "+Inama_belakang_user);
        tvTanggal.setText(Itanggal_input);
    }

    private void setFindView(){
        fotoPost = findViewById(R.id.detail_post_foto);
        fotoAdmin = findViewById(R.id.detail_post_fotoAdmin);
        btnBack = findViewById(R.id.back_from_detail_post);
        tvJudul = findViewById(R.id.detail_post_judul);
        tvIsi = findViewById(R.id.detail_post_isi);
        tvNamaAdmin = findViewById(R.id.detail_post_namaAdmin);
        tvTanggal = findViewById(R.id.detail_post_tgl);
        reload = findViewById(R.id.detail_post_reload);
        header = findViewById(R.id.header);
        progressBar = findViewById(R.id.detail_post_psiko_progressbar);
        scrollView = findViewById(R.id.scroll);
    }

    public void loadPostingan() {
        StringRequest loadInvoice = new StringRequest(Request.Method.POST,
                Konfigurasi.URL_VIEW_PSIKOLOGI,
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
                                if (jsonObject.getString("id_psikologi").equals("1")) {

                                    Iid_info = jsonObject.getString("id_psikologi");
                                    Ijudul_info = jsonObject.getString("judul_psikologi");
                                    Iisi_info = jsonObject.getString("isi_psikologi");
                                    Ifoto_info =  jsonObject.getString("foto_psikologi");
                                    Iid_user = jsonObject.getString("id_user");
                                    Itanggal_input = jsonObject.getString("tgl_input");
                                    Inama_depan_user = jsonObject.getString("nama_depan_user");
                                    Inama_belakang_user = jsonObject.getString("nama_belakang_user");
                                    Iemail_user = jsonObject.getString("email_user");

                                    reload.setRefreshing(false);
                                    progressBar.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    header.setVisibility(View.VISIBLE);
                                    setText();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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