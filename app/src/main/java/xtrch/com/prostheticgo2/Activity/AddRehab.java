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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class AddRehab extends AppCompatActivity {

    ImageView btnTambahFoto;
    EditText etJudul, etIsi;
    Spinner sJenis;
    ImageButton btnBack;
    TextView tvTitle;
    Button btnSimpan;
    SwipeRefreshLayout reload;

    String idRehab;
    String jenis_informasi = "kosong";
    public String selectedItemText;

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
        setContentView(R.layout.activity_add_rehab);

        //FindView
        setFindView();
        //getintent
        getIntentState();
        //getPermissionFoto
        setPermission();
        //set Spinner
        setSpinner();
        //OnClick
        setOnClick();
        //Reload
        setReload();
    }

    private void setPermission() {
        byteArrayOutputStream = new ByteArrayOutputStream();

        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } else {
            Toast.makeText(AddRehab.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSpinner() {
        String [] countries ={"PILIH KATEGORI","Upper","Lower"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,countries
        );
        sJenis.setAdapter(adapter);
        sJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);

                if(selectedItemText.equals("PILIH CHAPTER")){
                    jenis_informasi = "kosong";
                } else if(selectedItemText.equals("Upper")){
                    jenis_informasi = "upper";
                } else if(selectedItemText.equals("Lower")){
                    jenis_informasi = "lower";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = AddRehab.this.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    private void setFindView(){
        btnTambahFoto = findViewById(R.id.add_rehab_foto);
        etJudul = findViewById(R.id.add_rehab_judul);
        etIsi = findViewById(R.id.add_rehab_isi);
        sJenis = findViewById(R.id.add_rehab_jenis);
        btnBack = findViewById(R.id.back_from_add_rehab);
        tvTitle = findViewById(R.id.add_rehab_title);
        btnSimpan = findViewById(R.id.add_rehab_simpan);
        reload = findViewById(R.id.add_rehab_reload);
    }

    private void getIntentState(){
        Intent id = getIntent();
        idRehab = id.getStringExtra("id_rehab");
        if (idRehab.equals("0")){
            tvTitle.setText("Tambah Rehabilitasi");
        } else {
            tvTitle.setText("Edit Rehabilitasi");
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
            public void onClick(View view) {
                openGallery();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getJudul = etJudul.getText().toString().trim();
                final String getIsi = etIsi.getText().toString().trim();

                if(getJudul.isEmpty()){
                    etJudul.setError("Harap isi judul informasi");
                    Toast.makeText(AddRehab.this, "Harap isi judul informasi", Toast.LENGTH_SHORT).show();
                } else if(getIsi.isEmpty()){
                    etIsi.setError("Harap isi deskripsi informasi");
                    Toast.makeText(AddRehab.this, "Harap isi deskripsi informasi", Toast.LENGTH_SHORT).show();
                } else if(jenis_informasi.equals("kosong")){
                    Toast.makeText(AddRehab.this, "Harap Pilih Kategori", Toast.LENGTH_SHORT).show();
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
                                loading = ProgressDialog.show(AddRehab.this, "Sedang Diproses...", "Tunggu...", false, false);
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                loading.dismiss();
                                Toast.makeText(AddRehab.this, s, Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            protected String doInBackground(Void... v) {
                                HashMap<String, String> params = new HashMap<>();


//                                params.put("id_user", id_user);
                                params.put("judul_rehabilitasi", getJudul);
                                params.put("isi_rehabilitasi", getIsi);
                                params.put("ImageName", f.getName());
                                params.put("foto_rehabilitasi", ConvertImage);
                                params.put("jenis_rehabilitasi", jenis_informasi);;

                                RequestHandler rh = new RequestHandler();
                                String res = rh.sendPostRequest(Konfigurasi.URL_ADD_REHABILITASI, params);
                                return res;
                            }
                        }

                        Upload ae = new Upload();
                        ae.execute();
                    } else {
                        Toast.makeText(AddRehab.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setReload(){
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload.setRefreshing(false);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                contentUri = data.getData();

                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(AddRehab.this).getContentResolver(), contentUri);
                    String selectedPath = getPath(contentUri);
                    btnTambahFoto.setImageBitmap(imageUri);
                    btnTambahFoto.setVisibility(View.VISIBLE);
                    f = new File(selectedPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddRehab.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}