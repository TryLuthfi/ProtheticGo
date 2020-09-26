package xtrch.com.prostheticgo2.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
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
import java.util.Objects;

import xtrch.com.prostheticgo2.R;

public class AddInformasi extends AppCompatActivity {

    ImageView btnTambahFoto;
    EditText etJudul, etIsi;
    Spinner sJenis;
    ImageButton btnBack;
    TextView tvTitle;
    Button btnSimpan;
    SwipeRefreshLayout reload;

    String idInfo;
    String jenis_informasi;
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
        setContentView(R.layout.activity_add_informasi);

        //FindView
        setFindView();
        //getIntent
        getIntentState();
        //getPermissionFoto
        setPermission();
        //set Spinner
        setSpinner();
        //onclick
        setOnClick();
        //Reload
        setReload();
    }

    private void setOnClick() {
        btnTambahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
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
            Toast.makeText(AddInformasi.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
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

    private void setFindView(){
        btnTambahFoto = findViewById(R.id.add_info_foto);
        btnBack = findViewById(R.id.back_from_add_info);
        etJudul = findViewById(R.id.add_info_judul);
        etIsi = findViewById(R.id.add_info_isi);
        sJenis = findViewById(R.id.add_info_jenis);
        tvTitle = findViewById(R.id.add_info_title);
        btnSimpan = findViewById(R.id.add_info_simpan);
        reload = findViewById(R.id.add_info_reload);
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = AddInformasi.this.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    private void getIntentState(){
        Intent id = getIntent();
        idInfo = id.getStringExtra("id_info");
        if (idInfo.equals("0")){
            tvTitle.setText("Tambah Informasi");
        } else {
            tvTitle.setText("Edit Informasi");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                contentUri = data.getData();

                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(AddInformasi.this).getContentResolver(), contentUri);
                    String selectedPath = getPath(contentUri);
                    btnTambahFoto.setImageBitmap(imageUri);
                    btnTambahFoto.setVisibility(View.VISIBLE);
                    f = new File(selectedPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddInformasi.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}