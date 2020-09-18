package xtrch.com.prostheticgo2.Adapter;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import xtrch.com.prostheticgo2.Fragment.LowerExFragment;
import xtrch.com.prostheticgo2.Fragment.LowerRehabFragment;
import xtrch.com.prostheticgo2.Fragment.UpperExFragment;
import xtrch.com.prostheticgo2.Fragment.UpperRehabFragment;

public class AdapterTabRehab extends FragmentStatePagerAdapter implements UpperRehabFragment.OnFragmentInteractionListener, LowerRehabFragment.OnFragmentInteractionListener {

    int numTabRehab;

    public AdapterTabRehab(FragmentManager fm, int numTabRehab){
        super(fm);
        this.numTabRehab = numTabRehab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                UpperRehabFragment upperRehabFragment = new UpperRehabFragment();
                return upperRehabFragment;
            case 1:
                LowerRehabFragment lowerRehabFragment = new LowerRehabFragment();
                return lowerRehabFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabRehab;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
