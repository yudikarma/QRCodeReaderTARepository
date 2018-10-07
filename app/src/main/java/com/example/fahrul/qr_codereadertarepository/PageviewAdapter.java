package com.example.fahrul.qr_codereadertarepository;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class PageviewAdapter extends FragmentPagerAdapter {
    public PageviewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                home_fragment mainActivityFragment = new home_fragment();
                return mainActivityFragment;
            case 1:
                listDownload_fragment notification = new listDownload_fragment();
                return notification;



                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
