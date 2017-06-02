package com.example.uy.foodyver1.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uy.foodyver1.R;

/**
 * Created by UY on 5/12/2017.
 */
//TRANG LOAD QUÁN ĂN LÀ TRANG View/Home_Home
public class Collection_DiaDiem extends Fragment {
    FragmentTabHost mtabhost;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        mtabhost = new FragmentTabHost(getActivity());
        mtabhost.setup(getActivity(), getChildFragmentManager(), R.layout.collection_diadiem);
        return mtabhost;
    }
}
