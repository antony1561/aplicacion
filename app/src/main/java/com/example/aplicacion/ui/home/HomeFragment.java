package com.example.aplicacion.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.aplicacion.R;

public class HomeFragment extends Fragment {
    ViewFlipper v_flipper;
    private HomeViewModel homeViewModel;
    public void flipperimages(int image) {
        ImageView imageView = new ImageView(this.getContext());
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setAutoStart(true);
        TranslateAnimation ta = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 200);
        ta.setDuration(1000);
        ta.setFillAfter(true);
        v_flipper.setOutAnimation(this.getContext() ,android.R.anim.slide_out_right);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /////////////////////////////////////////////////////////////////////////////////
        int images[] = {R.drawable.descarga,R.drawable.descarga2,R.drawable.descarga3,R.drawable.descarga4};
        v_flipper = root.findViewById(R.id.v_flipper);
        flipperimages(images[0]);
        flipperimages(images[1]);
        flipperimages(images[2]);
        flipperimages(images[3]);
        return root;
    }
}
