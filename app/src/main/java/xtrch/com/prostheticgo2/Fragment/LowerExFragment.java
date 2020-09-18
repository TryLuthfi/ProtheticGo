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

public class LowerExFragment extends Fragment {
    public LowerExFragment() {}

    ImageView btnInfo;

    Dialog dialogInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lower_ex, container, false);

        //FindView
        setFindView(view);
        //OnClick
        setOnClick();

        return view;
    }

    private void setFindView(View view){
        btnInfo = view.findViewById(R.id.lower_ex_info_icon);
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
        dialogInfo = new Dialog(getContext());
        dialogInfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogInfo.setContentView(R.layout.dialog_info);
        TextView tvTitle = dialogInfo.findViewById(R.id.dialog_info_title);
        TextView tvText1 = dialogInfo.findViewById(R.id.dialog_info_text1);
        TextView tvText2 = dialogInfo.findViewById(R.id.dialog_info_text2);
        Button btnOk = dialogInfo.findViewById(R.id.dialog_info_ok);
        tvTitle.setText("Ekstremitas Bawah");
        tvText1.setText("Saat amputasi pada ekstremitas bawah dilakukan, seleksi dari level yang tepat merupakan hal penting untuk mengoptimalisasi potensi penyembuhan dan juga pengembalian fungsi ekstremitas.");
        tvText2.setText("");
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