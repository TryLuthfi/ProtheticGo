package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import xtrch.com.prostheticgo2.R;

public class Psikologi extends AppCompatActivity {

    ImageButton mBack;
    ImageView btnAddPsiko;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psikologi);

        //FindView
        setFindView();
        //OnClick
        setOnClick();
    }

    private void setFindView(){
        mBack = findViewById(R.id.back_from_psiko);
        btnAddPsiko = findViewById(R.id.psiko_add_btn);
    }

    private void setOnClick(){
        //Back
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAddPsiko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPsiko = new Intent(Psikologi.this, AddPsikologi.class);
                addPsiko.putExtra("id_psiko", "0");
                startActivity(addPsiko);
            }
        });
    }
}