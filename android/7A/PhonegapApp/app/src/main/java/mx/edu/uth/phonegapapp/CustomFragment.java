package mx.edu.uth.phonegapapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by elite on 08/12/2015.
 */
public class CustomFragment extends Fragment {
    public static final String ARG_ID_ENTRADA_SELECIONADA = "item_id";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_custom, container);
        return view;
    }

}


