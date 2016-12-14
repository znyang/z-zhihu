package com.zen.android.zz.ui.theme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zen.android.zz.R;

//import butterknife.ButterKnife;

/**
 * @author zen yang - 2016/12/10
 */
public class ThemeFragment extends Fragment {

    public static ThemeFragment newInstance() {
        return new ThemeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_theme, container, false);
//        ButterKnife.bind(this, root);
        return root;
    }
}
