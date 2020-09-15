package xtrch.com.prostheticgo2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import xtrch.com.prostheticgo2.R;

public class Rehabilitasi extends AppCompatActivity {

    ImageButton mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehabilitasi);

        setFindView();

        //Back
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setFindView(){
        mBack = findViewById(R.id.back_from_rehab);
    }
}