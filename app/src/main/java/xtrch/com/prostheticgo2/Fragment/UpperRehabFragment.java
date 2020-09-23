package xtrch.com.prostheticgo2.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xtrch.com.prostheticgo2.R;

public class UpperRehabFragment extends Fragment {
    public UpperRehabFragment() {}

    SwipeRefreshLayout reload;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_upper_rehab, container, false);

        //FindView
        setFindView(view);
        //Reload
        setReload();

        return view;
    }

    private void setFindView(View view){
        reload = view.findViewById(R.id.upper_rehab_reload);
        recyclerView = view.findViewById(R.id.lower_rehab_recycler);
    }

    private void setReload()
    {
        reload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload.setRefreshing(false);
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}