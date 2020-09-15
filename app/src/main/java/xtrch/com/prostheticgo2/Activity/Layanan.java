package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import xtrch.com.prostheticgo2.R;

public class Layanan extends AppCompatActivity {

    ImageButton mBack;
    RelativeLayout hubWA, hubCall, hubEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);

        //FindView
        setFindView();
        //Back
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setFindView(){
        mBack = findViewById(R.id.back_from_layanan);
    }
}