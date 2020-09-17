package xtrch.com.prostheticgo2.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import xtrch.com.prostheticgo2.R;

public class UpperExFragment extends Fragment {
    public UpperExFragment() {}

    ImageView btnInfo;

    AlertDialog.Builder dialogInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upper_ex, container, false);

        //FindView
        setFindView(view);
        //OnClick
        setOnClick();

        return view;
    }

    private void setFindView(View view){
        btnInfo = view.findViewById(R.id.upper_ex_info_icon);
    }

    private void setOnClick(){
        //Info
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialogInfo();
            }
        });
    }

    private void setDialogInfo(){
        dialogInfo = new AlertDialog.Builder(getContext());
        dialogInfo.setTitle("Ekstrimitas Atas");
        dialogInfo.setMessage("Fungsi dari ekstremitas atas lebih sulit digantikan dibandingkan ekstremitas bawah. Selain untuk aktivitas motorik, ekstremitas atas juga digunakan untuk keperluan sehari-hari seperti merawat diri, interaksi dengan orang lain, dan ekspresi diri.");
        dialogInfo.setCancelable(true);
        dialogInfo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogInfo.show();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}