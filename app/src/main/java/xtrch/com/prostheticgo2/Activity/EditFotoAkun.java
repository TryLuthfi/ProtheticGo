package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import xtrch.com.prostheticgo2.R;

public class EditFotoAkun extends AppCompatActivity {

    ImageView imgProfil, imgDepan, imgSamping, imgBlkg;
    Button btnProfil, btnDepan, btnSamping, btnBlkg;
    SwipeRefreshLayout reload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_foto_akun);

        //FindView
        setFindView();
        //Reload
        setReload();
    }

    private void setFindView(){
        imgProfil = findViewById(R.id.edit_foto_akun_profil);
        imgDepan = findViewById(R.id.edit_foto_akun_depan);
        imgSamping = findViewById(R.id.edit_foto_akun_samping);
        imgBlkg = findViewById(R.id.edit_foto_akun_blkg);
        btnProfil = findViewById(R.id.edit_foto_akun_profil_btn);
        btnDepan = findViewById(R.id.edit_foto_akun_depan_btn);
        btnSamping = findViewById(R.id.edit_foto_akun_samping_btn);
        btnBlkg = findViewById(R.id.edit_foto_akun_blkg_btn);
        reload = findViewById(R.id.edit_foto_akun_reload);
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