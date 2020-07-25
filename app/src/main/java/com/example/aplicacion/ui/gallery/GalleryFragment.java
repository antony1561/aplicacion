package com.example.aplicacion.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.toolbox.ImageLoader;
import com.example.aplicacion.R;
import com.example.aplicacion.adapters.ProductoAdaptador;
import com.example.aplicacion.helpers.QueueUtils;
import com.example.aplicacion.models.Producto;
import com.example.aplicacion.ui.Producto.ProductoFragment;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    Button btngallery;
    ListView lista;
    ProductoAdaptador productoadaptador;
    ArrayList<Producto> datos;
    QueueUtils.QueueObject encolador;
    ImageLoader encoladorImagenes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        encolador = QueueUtils.getInstance(this.getContext());
        encoladorImagenes = encolador.getImageLoader();
        datos = new ArrayList<>();
        Producto.injectContactsFromCloud(encolador,datos,this);
        productoadaptador = new ProductoAdaptador(this.getContext(), datos, encoladorImagenes);
        lista = root.findViewById(R.id.lista);
        lista.setAdapter(productoadaptador);
        //////////////////////////al seleccionar cualquier item nos llevara a nosotros fragment/////////
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view,int position, long id) {
                ProductoFragment o = new ProductoFragment();
                o.productoobj = datos.get(position);
                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, o)
                        .addToBackStack(null).commit();
            }
        });
        return root;
    }
    public void refreshList(){
        if (productoadaptador != null) {
            productoadaptador.notifyDataSetChanged();
        }
    }

}
