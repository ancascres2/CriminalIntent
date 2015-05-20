package com.example.angel.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Angel on 19.05.15.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
