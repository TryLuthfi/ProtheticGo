package xtrch.com.prostheticgo2.Adapter;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import xtrch.com.prostheticgo2.Fragment.LowerExFragment;
import xtrch.com.prostheticgo2.Fragment.UpperExFragment;

public class AdapterTabInfo extends FragmentStatePagerAdapter implements UpperExFragment.OnFragmentInteractionListener, LowerExFragment.OnFragmentInteractionListener {

    int numTabInfo;

    public AdapterTabInfo(FragmentManager fm, int numTabInfo){
        super(fm);
        this.numTabInfo = numTabInfo;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                UpperExFragment upperExFragment = new UpperExFragment();
                return upperExFragment;
            case 1:
                LowerExFragment lowerExFragment = new LowerExFragment();
                return lowerExFragment;
            default:
                return null;
        }
    }

    public int getCount(){
        return numTabInfo;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
