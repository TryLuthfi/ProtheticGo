package xtrch.com.prostheticgo2.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;
import xtrch.com.prostheticgo2.Request.RequestHandler;

public class AddProvider extends AppCompatActivity {

    SwipeRefreshLayout reload;
    ImageView btnFoto;
    ImageButton btnBack;
    EditText etNama, etHP;
    TextView tvTitle;
    Button btnSimpan;

    String idProvider;

    private File f;
    private Bitmap imageUri;
    private Uri contentUri;
    private static final int PICK_IMAGE = 100;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteArray;
    private String ConvertImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);

        //FindView
        setFindView();
        //getIntent
        getIntentState();
        //permission
        setPermission();
        //OnClick
        setOnClick();
        //reload
        setReload();
    }

    private void setFindView(){
        reload = findViewById(R.id.add_prov_reload);
        btnFoto = findViewById(R.id.add_prov_foto);
        btnBack = findViewById(R.id.back_from_add_prov);
        etNama = findViewById(R.id.add_prov_nama);
        etHP = findViewById(R.id.add_prov_hp);
        tvTitle = findViewById(R.id.add_prov_title);
        btnSimpan = findViewById(R.id.add_prov_simpan);
    }

    private void getIntentState(){
        Intent id = getIntent();
        idProvider = id.getStringExtra("id_provider");
        if (idProvider.equals("0")){
            tvTitle.setText("Tambah Provider");
        } else {
            tvTitle.setText("Edit Provider");
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = AddProvider.this.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    private void setOnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getNama = etNama.getText().toString().trim();
                final String getHP = etHP.getText().toString().trim();

                if(getNama.isEmpty()){
                    etNama.setError("Harap isi nama provider");
                    Toast.makeText(AddProvider.this, "Harap isi judul informasi", Toast.LENGTH_SHORT).show();
                } else if(getHP.isEmpty()){
                    etHP.setError("Harap isi nomor HP provider");
                    Toast.makeText(AddProvider.this, "Harap isi deskripsi informasi", Toast.LENGTH_SHORT).show();
                } else{
                    if (imageUri != null) {
                        imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
                        byteArray = byteArrayOutputStream.toByteArray();
                        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                        class Upload extends AsyncTask<Void, Void, String> {

                            ProgressDialog loading;

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                loading = ProgressDialog.show(AddProvider.this, "Sedang Diproses...", "Tunggu...", false, false);
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                loading.dismiss();
                                Toast.makeText(AddProvider.this, s, Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            protected String doInBackground(Void... v) {
                                HashMap<String, String> params = new HashMap<>();


//                                params.put("id_user", id_user);
                                params.put("nama_provider", getNama);
                                params.put("nohp_provider", getHP);
                                params.put("ImageName", f.getName());
                                params.put("foto_provider", ConvertImage);

                                RequestHandler rh = new RequestHandler();
                                String res = rh.sendPostRequest(Konfigurasi.URL_ADD_PROVIDER, params);
                                return res;
                            }
                        }

                        Upload ae = new Upload();
                        ae.execute();
                    } else {
                        Toast.makeText(AddProvider.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setPermission() {
        byteArrayOutputStream = new ByteArrayOutputStream();

        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } else {
            Toast.makeText(AddProvider.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                contentUri = data.getData();

                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(AddProvider.this).getContentResolver(), contentUri);
                    String selectedPath = getPath(contentUri);
                    btnFoto.setImageBitmap(imageUri);
                    btnFoto.setVisibility(View.VISIBLE);
                    f = new File(selectedPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddProvider.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void setReload(){
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload.setRefreshing(false);
            }
        });
    }
}