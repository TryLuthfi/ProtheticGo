package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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

import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.AppController;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class DetailProduk extends AppCompatActivity {

    ImageButton btnBack;
    ImageView fotoProduk, fotoProvider;
    TextView tvNama, tvDesk, tvHarga, tvNamaProvider, tvTgl;
    Button btnBeli;
    AlertDialog.Builder dialogBeli;

    String idProduk, idProvider, noHpProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        //FindView
        setFindView();
        //getIntent
        getIntentState();
        //OnClick
        setOnClick();
    }

    private void setFindView(){
        btnBack = findViewById(R.id.back_from_detail_produk);
        fotoProduk = findViewById(R.id.detail_produk_foto);
        fotoProvider = findViewById(R.id.detail_produk_fotoProvider);
        tvNama = findViewById(R.id.detail_produk_nama);
        tvDesk = findViewById(R.id.detail_produk_desk);
        tvHarga = findViewById(R.id.detail_produk_harga);
        tvNamaProvider = findViewById(R.id.detail_produk_namaProvider);
        tvTgl = findViewById(R.id.detail_produk_tgl);
        btnBeli = findViewById(R.id.detail_produk_beli);
        dialogBeli = new AlertDialog.Builder(this);
    }

    private void getIntentState(){
        DecimalFormat formatRupiah = (DecimalFormat) NumberFormat.getInstance();
        formatRupiah.setPositivePrefix("Rp. ");
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);
        Intent getIntent = getIntent();
        idProduk = getIntent.getStringExtra("id_produk");
        idProvider = getIntent.getStringExtra("id_provider");
        noHpProvider = getIntent.getStringExtra("nohp_provider");
        tvNama.setText(getIntent.getStringExtra("nama_produk"));
        tvDesk.setText(getIntent.getStringExtra("desk_produk"));
        tvHarga.setText(formatRupiah.format(Double.valueOf(getIntent.getStringExtra("harga_produk"))));
        tvNamaProvider.setText(getIntent.getStringExtra("nama_provider"));
        tvTgl.setText(getIntent.getStringExtra("tgl_input"));
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorPrimary);
        Glide.with(Objects.requireNonNull(this)).load(Konfigurasi.URL_IMAGE_PRODUK + getIntent.getStringExtra("foto_produk")).apply(requestOptions).into(fotoProduk);
        Glide.with(Objects.requireNonNull(this)).load(Konfigurasi.URL_IMAGE_PROVIDER + getIntent.getStringExtra("foto_provider")).apply(requestOptions).into(fotoProvider);
    }

    private void setOnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });
    }

    private void setDialog(){
        dialogBeli.setCancelable(false);
        dialogBeli.setTitle("Beli");
        dialogBeli.setIcon(R.drawable.ic_round_shopping_cart_24);
        dialogBeli.setMessage("Yakin akan beli " + tvNama.getText().toString() + ". Data akan masuk ke history anda");
        dialogBeli.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendHistory();
            }
        });
        dialogBeli.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBeli.show();
    }

    private void sendWA(){
        String url = "https://api.whatsapp.com/send?phone=" + noHpProvider.replaceFirst("^0+(?!$)", "+62") + "&text="
                + "NAMA%20:%20" + Konfigurasi.Dnama_depan_user + "%20" + Konfigurasi.Dnama_belakang_user + "%0A"
                + "ALAMAT%20:%20" + Konfigurasi.Dalamat_user + "%0A"
                + "EMAIL%20:%20" + Konfigurasi.Demail_user + "%0A"
                + "PRODUK YANG DIPESAN%20:%20" + tvNama.getText().toString() + "%0A"
                + "HARGA%20:%20" + tvHarga.getText().toString() + "%0A";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void sendHistory(){
        StringRequest sendHistory = new StringRequest(Request.Method.POST, Konfigurasi.URL_ADD_HISTORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        sendWA();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailProduk.this, "Terjadi Kesalahan, Harap coba lagi", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", Konfigurasi.Did_user);
                params.put("id_produk", idProduk);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(sendHistory);
    }
}