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

public class LowerExFragment extends Fragment {
    public LowerExFragment() {}

    ImageView btnInfo;

    AlertDialog.Builder dialogInfo;

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
        dialogInfo = new AlertDialog.Builder(getContext());
        dialogInfo.setTitle("Ekstrimitas Bawah");
        dialogInfo.setMessage("Saat amputasi pada ekstremitas bawah dilakukan, seleksi dari level yang tepat merupakan hal penting untuk mengoptimalisasi potensi penyembuhan dan juga pengembalian fungsi ekstremitas.");
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