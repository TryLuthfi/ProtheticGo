package xtrch.com.prostheticgo2.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
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
import java.util.Objects;

import xtrch.com.prostheticgo2.R;

public class AddProduk extends AppCompatActivity {

    SwipeRefreshLayout reload;
    ImageButton btnBack;
    ImageView btnFoto;
    EditText etNama, etDesc, etHarga;
    Button btnLanjut;
    TextView tvTitle;

    String idProduk;

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
        setContentView(R.layout.activity_add_produk);

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
        reload = findViewById(R.id.add_produk_reload);
        btnBack = findViewById(R.id.back_from_add_produk);
        btnFoto = findViewById(R.id.add_produk_foto);
        etNama = findViewById(R.id.add_produk_nama);
        etDesc = findViewById(R.id.add_produk_desc);
        etHarga = findViewById(R.id.add_produk_harga);
        btnLanjut = findViewById(R.id.add_produk_simpan);
        tvTitle = findViewById(R.id.add_produk_title);
    }

    private void getIntentState(){
        Intent id = getIntent();
        idProduk = id.getStringExtra("id_info");
        if (idProduk.equals("0")){
            tvTitle.setText("Tambah Produk untuk");
        } else {
            tvTitle.setText("Edit Produk");
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = AddProduk.this.managedQuery(uri, projection, null, null, null);
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
    }

    private void setPermission() {
        byteArrayOutputStream = new ByteArrayOutputStream();

        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } else {
            Toast.makeText(AddProduk.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                contentUri = data.getData();

                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(AddProduk.this).getContentResolver(), contentUri);
                    String selectedPath = getPath(contentUri);
                    btnFoto.setImageBitmap(imageUri);
                    btnFoto.setVisibility(View.VISIBLE);
                    f = new File(selectedPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddProduk.this, "Failed!", Toast.LENGTH_SHORT).show();
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