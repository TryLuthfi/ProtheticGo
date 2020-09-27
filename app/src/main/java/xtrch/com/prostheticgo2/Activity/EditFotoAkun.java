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

public class EditFotoAkun extends AppCompatActivity {

    ImageView imgFoto;
    Button btnSimpan;
    SwipeRefreshLayout reload;
    ImageButton btnBack;
    TextView tvTitle;

    private File f;
    private Bitmap imageUri;
    private Uri contentUri;
    private static final int PICK_IMAGE = 100;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteArray;
    private String ConvertImage;

    String stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_foto_akun);

        //FindView
        setFindView();
        //getIntent
        getIntentState();
        //OnClick
        setOnClick();
        //permission
        setPermission();
        //Reload
        setReload();
    }

    private void setFindView(){
        imgFoto = findViewById(R.id.edit_foto_akun_profil);
        btnSimpan = findViewById(R.id.edit_foto_akun_profil_btn);
        reload = findViewById(R.id.edit_foto_akun_reload);
        btnBack = findViewById(R.id.back_from_edit_foto_akun);
        tvTitle = findViewById(R.id.edit_foto_title);
    }

    private void setOnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stat.equals("profil")){
                    editFotoProfil();
                } else if(stat.equals("depan")){
                    editFotoDepan();
                } else if(stat.equals("samping")){
                    editFotoSamping();
                } else if (stat.equals("blkg")){
                    editFotoBlkg();
                } else if(stat.equals("prost")){
                    editFotoProst();
                }
            }
        });
    }

    private void getIntentState(){
        Intent status = getIntent();
        stat = status.getStringExtra("status");
        if(status.getStringExtra("status").equals("profil")){
            tvTitle.setText("Edit Foto Profil");
        } else if(status.getStringExtra("status").equals("depan")){
            tvTitle.setText("Edit Foto Body Depan");
        } else if(status.getStringExtra("status").equals("samping")){
            tvTitle.setText("Edit Foto Body Samping");
        } else if(status.getStringExtra("status").equals("blkg")){
            tvTitle.setText("Edit Foto Body Belakang");
        } else if(status.getStringExtra("status").equals("prost")){
            tvTitle.setText("Edit Foto Yang Akan Dipasangi Prosthesis");
        }
    }

    private void editFotoProfil(){
        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            class Upload extends AsyncTask<Void, Void, String> {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(EditFotoAkun.this, "Sedang Diproses...", "Tunggu...", false, false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(EditFotoAkun.this, s, Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                protected String doInBackground(Void... v) {
                    HashMap<String, String> params = new HashMap<>();

                    params.put("id_user", Konfigurasi.Did_user);
                    params.put("ImageName", f.getName());
                    params.put("foto", ConvertImage);

                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(Konfigurasi.URL_ADD_IMG_PROFIL, params);
                    return res;
                }
            }

            Upload ae = new Upload();
            ae.execute();
        } else {
            Toast.makeText(EditFotoAkun.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void editFotoDepan(){
        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            class Upload extends AsyncTask<Void, Void, String> {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(EditFotoAkun.this, "Sedang Diproses...", "Tunggu...", false, false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(EditFotoAkun.this, s, Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                protected String doInBackground(Void... v) {
                    HashMap<String, String> params = new HashMap<>();

                    params.put("id_user", Konfigurasi.Did_user);
                    params.put("ImageName", f.getName());
                    params.put("foto", ConvertImage);

                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(Konfigurasi.URL_ADD_IMG_DEPAN, params);
                    return res;
                }
            }

            Upload ae = new Upload();
            ae.execute();
        } else {
            Toast.makeText(EditFotoAkun.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void editFotoSamping(){
        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            class Upload extends AsyncTask<Void, Void, String> {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(EditFotoAkun.this, "Sedang Diproses...", "Tunggu...", false, false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(EditFotoAkun.this, s, Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                protected String doInBackground(Void... v) {
                    HashMap<String, String> params = new HashMap<>();

                    params.put("id_user", Konfigurasi.Did_user);
                    params.put("ImageName", f.getName());
                    params.put("foto", ConvertImage);

                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(Konfigurasi.URL_ADD_IMG_SAMPING, params);
                    return res;
                }
            }

            Upload ae = new Upload();
            ae.execute();
        } else {
            Toast.makeText(EditFotoAkun.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void editFotoBlkg(){
        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            class Upload extends AsyncTask<Void, Void, String> {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(EditFotoAkun.this, "Sedang Diproses...", "Tunggu...", false, false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(EditFotoAkun.this, s, Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                protected String doInBackground(Void... v) {
                    HashMap<String, String> params = new HashMap<>();

                    params.put("id_user", Konfigurasi.Did_user);
                    params.put("ImageName", f.getName());
                    params.put("foto", ConvertImage);

                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(Konfigurasi.URL_ADD_IMG_BLKG, params);
                    return res;
                }
            }

            Upload ae = new Upload();
            ae.execute();
        } else {
            Toast.makeText(EditFotoAkun.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void editFotoProst(){
        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            class Upload extends AsyncTask<Void, Void, String> {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(EditFotoAkun.this, "Sedang Diproses...", "Tunggu...", false, false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(EditFotoAkun.this, s, Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                protected String doInBackground(Void... v) {
                    HashMap<String, String> params = new HashMap<>();

                    params.put("id_user", Konfigurasi.Did_user);
                    params.put("ImageName", f.getName());
                    params.put("foto", ConvertImage);

                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(Konfigurasi.URL_ADD_IMG_PROST, params);
                    return res;
                }
            }

            Upload ae = new Upload();
            ae.execute();
        } else {
            Toast.makeText(EditFotoAkun.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = EditFotoAkun.this.managedQuery(uri, projection, null, null, null);
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
            Toast.makeText(EditFotoAkun.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                contentUri = data.getData();

                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(EditFotoAkun.this).getContentResolver(), contentUri);
                    String selectedPath = getPath(contentUri);
                    imgFoto.setImageBitmap(imageUri);
                    imgFoto.setVisibility(View.VISIBLE);
                    f = new File(selectedPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(EditFotoAkun.this, "Failed!", Toast.LENGTH_SHORT).show();
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