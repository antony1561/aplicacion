package com.example.aplicacion.ui.Producto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.aplicacion.R;
import com.example.aplicacion.helpers.QueueUtils;
import com.example.aplicacion.models.Producto;
import com.example.aplicacion.R;
import com.example.aplicacion.helpers.QueueUtils;
import com.example.aplicacion.models.Producto;

public class ProductoFragment extends Fragment {


    //TextView cantidad;
     //Button  menos;
     //Button mas;
     //private int counter = 0;
     ///////////////////////////////////////
    ////////////////////////////////////////
    private ProductoViewModel mViewModel;
    public Producto productoobj;
    ImageLoader imageLoader;
    public static ProductoFragment newInstance() {
        return new ProductoFragment();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.producto_fragment, container, false);
        NetworkImageView image = root.findViewById(R.id.image);
        imageLoader = QueueUtils.getInstance(this.getContext()).getImageLoader();
        image.setImageUrl(this.productoobj.urlImage, imageLoader);
        TextView name = root.findViewById(R.id.name);
        name.setText(this.productoobj.nickname);
        TextView name1 = root.findViewById(R.id.name1);
        name1.setText(this.productoobj.phone);
        /////////////////////////////////////////////////////////////////
        //TextView cantidad = root.findViewById(R.id.cantidad);
        //Button menos = root.findViewById(R.id.menos);
        //menos.setOnClickListener(clickListener);
        //Button mas = root.findViewById(R.id.mas);
        //mas.setOnClickListener(clickListener);
        /////////////////////////////////////////////////////////////////
        
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductoViewModel.class);
        // TODO: Use the ViewModel
    }
    ///////////////////////////////////////
    /////////////para eleguir la cantidad de la salida////////
   // private View.OnClickListener clickListener = new View.OnClickListener() {
     //  @Override
       // public void onClick(View v) {

     //  }/
    //};
}
