package com.example.teht7_1;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class OmaAdapteri extends ArrayAdapter {

    private Context context;
    ArrayList<RowItem> lista;

    public OmaAdapteri(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            View v = LayoutInflater.from(context).inflate(R.layout.listaitem, parent, false);
            TextView t1 = v.findViewById(R.id.ownertxt);
            TextView t2 = v.findViewById(R.id.licensetxt);
            ImageView i1 = v.findViewById(R.id.foto);
            t1.setText(lista.get(position).getOwnertext());
            t2.setText(lista.get(position).getLicensetexttext());
            i1.setImageResource(lista.get(position).getPic_id());
            convertView = v;
        }
        return convertView;
    }

}
