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
import android.widget.Spinner;
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

public class AddPsikologi extends AppCompatActivity {

    ImageView btnTambahFoto;
    EditText etJudul, etIsi;
//    Spinner sJenis;
    ImageButton btnBack;
    TextView tvTitle;
    Button btnSimpan;
    SwipeRefreshLayout reload;

    String idPsiko;

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
        setContentView(R.layout.activity_add_psikologi);

        //Find View
        setFindView();
        //permisson
        setPermission();
        //getIntent
        getIntentState();
        //OnClick
        setOnClick();
        //reload
        setReload();
    }

    private void setFindView(){
        btnTambahFoto = findViewById(R.id.add_psiko_foto);
        etJudul = findViewById(R.id.add_psiko_judul);
        etIsi = findViewById(R.id.add_psiko_isi);
        btnBack = findViewById(R.id.back_from_add_psiko);
        tvTitle = findViewById(R.id.add_psiko_title);
        btnSimpan = findViewById(R.id.add_psiko_simpan);
        reload = findViewById(R.id.add_psiko_reload);
    }

    private void getIntentState(){
        Intent psiko = getIntent();
        idPsiko = psiko.getStringExtra("id_psiko");
        if(idPsiko.equals("0")){
            tvTitle.setText("Tambah Psikologi");
        } else {
            tvTitle.setText("Edit Psikologi");
        }
    }

    private void setOnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnTambahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getJudul = etJudul.getText().toString().trim();
                final String getIsi = etIsi.getText().toString().trim();

                if(getJudul.isEmpty()){
                    etJudul.setError("Harap isi judul psikologi");
                    Toast.makeText(AddPsikologi.this, "Harap isi judul informasi", Toast.LENGTH_SHORT).show();
                } else if(getIsi.isEmpty()){
                    etIsi.setError("Harap isi deskripsi psikologi");
                    Toast.makeText(AddPsikologi.this, "Harap isi deskripsi informasi", Toast.LENGTH_SHORT).show();
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
                                loading = ProgressDialog.show(AddPsikologi.this, "Sedang Diproses...", "Tunggu...", false, false);
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                loading.dismiss();
                                Toast.makeText(AddPsikologi.this, s, Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            protected String doInBackground(Void... v) {
                                HashMap<String, String> params = new HashMap<>();


                                params.put("id_user", Konfigurasi.Did_user);
                                params.put("judul_psikologi", getJudul);
                                params.put("isi_psikologi", getIsi);
                                params.put("ImageName", f.getName());
                                params.put("foto_psikologi", ConvertImage);

                                RequestHandler rh = new RequestHandler();
                                String res = rh.sendPostRequest(Konfigurasi.URL_ADD_PSIKO, params);
                                return res;
                            }
                        }

                        Upload ae = new Upload();
                        ae.execute();
                    } else {
                        Toast.makeText(AddPsikologi.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = AddPsikologi.this.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    private void setPermission() {
        byteArrayOutputStream = new ByteArrayOutputStream();

        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } else {
            Toast.makeText(AddPsikologi.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                contentUri = data.getData();

                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(AddPsikologi.this).getContentResolver(), contentUri);
                    String selectedPath = getPath(contentUri);
                    btnTambahFoto.setImageBitmap(imageUri);
                    btnTambahFoto.setVisibility(View.VISIBLE);
                    f = new File(selectedPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddPsikologi.this, "Failed!", Toast.LENGTH_SHORT).show();
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