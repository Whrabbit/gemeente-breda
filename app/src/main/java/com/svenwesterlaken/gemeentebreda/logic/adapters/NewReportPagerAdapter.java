package com.svenwesterlaken.gemeentebreda.logic.adapters;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.svenwesterlaken.gemeentebreda.presentation.fragments.NewReportCategoryFragment;
import com.svenwesterlaken.gemeentebreda.presentation.fragments.NewReportDescriptionFragment;
import com.svenwesterlaken.gemeentebreda.presentation.fragments.NewReportLocationFragment;
import com.svenwesterlaken.gemeentebreda.presentation.fragments.NewReportMediaFragment;
import com.svenwesterlaken.gemeentebreda.presentation.fragments.NewReportSummaryFragment;

/**
 * Created by Sven Westerlaken on 16-5-2017.
 */

public class NewReportPagerAdapter extends FragmentPagerAdapter {

    private static int tabCount = 5;

    public NewReportPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new NewReportMediaFragment();
            case 1:
                return new NewReportLocationFragment();
            case 2:
                return new NewReportCategoryFragment();
            case 3:
                return new NewReportDescriptionFragment();
            case 4:
                return new NewReportSummaryFragment();
            default:
                return null;
        }
    }
}