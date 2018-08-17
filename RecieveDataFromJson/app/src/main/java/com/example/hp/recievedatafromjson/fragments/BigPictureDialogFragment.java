package com.example.hp.recievedatafromjson.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hp.recievedatafromjson.R;
import com.example.hp.recievedatafromjson.activities.MainActivity;
import com.squareup.picasso.Picasso;

public class BigPictureDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_style,container,false);
        ImageView imageView = view.findViewById(R.id.big_picture);
        Picasso.get().load(((MainActivity)getActivity()).getJsonModel().getUrl()).into(imageView);
        return view;

    }
}
