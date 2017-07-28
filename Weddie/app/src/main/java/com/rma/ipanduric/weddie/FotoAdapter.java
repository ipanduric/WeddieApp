package com.rma.ipanduric.weddie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ipand on 28.7.2017..
 */

public class FotoAdapter extends BaseAdapter{

    private ArrayList<FotoItem> mFoto;
    private ArrayList<FotoItem> arraylist = null;
    private Context context;

    public FotoAdapter(Context context, ArrayList<FotoItem> foto) {
        this.context = context;
        this.mFoto = foto;
        this.arraylist = new ArrayList<FotoItem>();
        this.arraylist.addAll(mFoto);
    }
    @Override
    public int getCount() {
        return this.mFoto.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mFoto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mFoto.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder fotoVH;
        if (convertView == null) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.items_foto, parent, false);
        fotoVH = new ViewHolder(convertView);
        convertView.setTag(fotoVH);
    } else {
        fotoVH = (ViewHolder) convertView.getTag();
    }

    FotoItem foto = this.mFoto.get(position);
        fotoVH.tvFotoIme.setText(foto.getfIme());
        return convertView;
}
private class ViewHolder {

    TextView tvFotoIme;

    public ViewHolder(View fotoView) {
        tvFotoIme = (TextView) fotoView.findViewById(R.id.tvFotoIme);
    }
}

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mFoto.clear();
        if (charText.length() == 0) {
            mFoto.addAll(arraylist);
        }
        else
        {
            for (FotoItem ft : arraylist) {
                if (ft.getfIme().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mFoto.add(ft);
                }
            }
        }
        notifyDataSetChanged();
    }


}
