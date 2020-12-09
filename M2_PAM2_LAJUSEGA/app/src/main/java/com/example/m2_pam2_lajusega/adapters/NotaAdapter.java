package com.example.m2_pam2_lajusega.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.m2_pam2_lajusega.R;
import com.example.m2_pam2_lajusega.models.NotaModel;

import java.util.ArrayList;
import java.util.Random;

public class NotaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NotaModel> list;

    public NotaAdapter(Context context, ArrayList<NotaModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NotaModel getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(R.layout.nota_item, viewGroup, false);
        }

        TextView item_titulo, item_contenido, item_fecha, item_frase;
        ImageView item_imagen;
        String titulo, contenido, frase;

        item_imagen = view.findViewById(R.id.item_imagen);
        item_titulo = view.findViewById(R.id.item_titulo);
        item_contenido = view.findViewById(R.id.item_contenido);
        item_fecha = view.findViewById(R.id.item_fecha);
        item_frase = view.findViewById(R.id.item_frase);


        //Forma 1
        titulo = list.get(i).getTitulo().trim();
        Log.i("INFONA", titulo);
        if(titulo.equals("")){
            titulo = "(Sin título)";
        }
        item_titulo.setText(titulo);

        //Forma 2

        item_frase.setText(list.get(i).getFrase());

        item_titulo.setText(list.get(i).getTitulo());

        item_contenido.setText(list.get(i).getContenido());

        item_fecha.setText("10/10/20");



        //Forma dinámica
        // Iconos
        String[] iconos = { "ic_account_circle_white_18dp"};
        Random r =new Random();
        int randomNumber = r.nextInt(iconos.length);

        int icono = view.getResources().getIdentifier("com.example.myapplication:drawable/" + iconos[randomNumber], null, null);
        item_imagen.setImageResource(icono);

        // Colores
        String[] colores = {"#ff33b5e5", "#ffff8800", "#ff99cc00", "#ffff4444", "#ff0099cc", "#ff669900", "#ffaa66cc", "#ffffbb33"};
        randomNumber = r.nextInt(colores.length);
        view.setBackgroundColor(Color.parseColor(colores[randomNumber]));


        //Forma estática
        //Icono 1
        //Drawable img = ResourcesCompat.getDrawable(view.getResources(), R.drawable.ic_android_white_18dp, null);
        //item_imagen.setImageDrawable(img);

        //Icono 2
        //item_imagen.setImageResource(R.drawable.ic_pregnant_woman_white_18dp);

        //Color
        //view.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_light));

        return view;
    }
}
