package xtrch.com.prostheticgo2.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import xtrch.com.prostheticgo2.R;

public class UpperExFragment extends Fragment {
    public UpperExFragment() {}

    ImageView btnInfo;
    Dialog dialogInfo;

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
//        dialogInfo.setMessage("Fungsi dari ekstremitas atas lebih sulit digantikan dibandingkan ekstremitas bawah. " +
//                "Selain untuk aktivitas motorik, ekstremitas atas juga digunakan untuk keperluan sehari-hari seperti merawat diri, interaksi dengan orang lain, dan ekspresi diri.");
//        dialogInfo.setCancelable(true);
//        dialogInfo.show();
        dialogInfo = new Dialog(getContext());
        dialogInfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogInfo.setContentView(R.layout.dialog_info);
        TextView tvTitle = dialogInfo.findViewById(R.id.dialog_info_title);
        TextView tvText1 = dialogInfo.findViewById(R.id.dialog_info_text1);
        TextView tvText2 = dialogInfo.findViewById(R.id.dialog_info_text2);
        Button btnOk = dialogInfo.findViewById(R.id.dialog_info_ok);
        tvTitle.setText("Ekstremitas Atas");
        tvText1.setText("Fungsi dari ekstremitas atas lebih sulit digantikan dibandingkan ekstremitas bawah.");
        tvText2.setText("Selain untuk aktivitas motorik, ekstremitas atas juga digunakan untuk keperluan sehari-hari seperti merawat diri, interaksi dengan orang lain, dan ekspresi diri.");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.dismiss();
            }
        });
        dialogInfo.show();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}