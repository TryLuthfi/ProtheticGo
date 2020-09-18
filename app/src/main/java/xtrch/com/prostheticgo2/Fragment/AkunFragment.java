package xtrch.com.prostheticgo2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import xtrch.com.prostheticgo2.Activity.Login;
import xtrch.com.prostheticgo2.R;

public class AkunFragment extends Fragment {
    public AkunFragment() {}

    Button btnLogout;
    SwipeRefreshLayout reload;

    String getIdUser;
    String getNamaUser;
    String getEmailUser;
    String getPasswordUser;

    TextView nama;
    TextView email;
    TextView notelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        //FindView
        setFindView(view);
        //SharedPreference
        getSharedPreference();
        //SetLocalVariable
        setLocalVariable();
        //Logout
        logoutBtn();

        //Reload Page
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSharedPreference();
                setLocalVariable();
                reload.setRefreshing(false);
            }
        });

        return view;
    }

    private void setLocalVariable() {
        nama.setText(getNamaUser);
        email.setText(getEmailUser);
        notelp.setText("");

    }

    public void setFindView(View view){
        btnLogout = view.findViewById(R.id.akun_logout_btn);
        reload = view.findViewById(R.id.akun_fragment_reload);
        nama = view.findViewById(R.id.akun_nama);
        email = view.findViewById(R.id.akun_email);
        notelp = view.findViewById(R.id.akun_notelp);
    }

    private void logoutBtn() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                startActivity(new Intent(getActivity(), Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                editor.clear();
                editor.apply();
                getActivity().finish();
            }
        });
    }

    private void getSharedPreference(){
        SharedPreferences preferences = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        getIdUser = preferences.getString("id_user", "null");
        getNamaUser = preferences.getString("nama_user", "null");
        getEmailUser = preferences.getString("email_user", "null");
        getPasswordUser = preferences.getString("password_user", "null");

    }
}