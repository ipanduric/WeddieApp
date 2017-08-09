package com.rma.ipanduric.weddie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ipand on 29.7.2017..
 */

public class ReminderAdapter extends BaseAdapter{

    List<ZadatakItem> mZadaci;
    Context context;

    public ReminderAdapter (List<ZadatakItem> zadaci, Context context)
    {
        this.mZadaci = zadaci;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mZadaci.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mZadaci.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        ReminderAdapter.ReminderViewHolder reminderViewHolder = null;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.items_zadatak, parent, false);
            reminderViewHolder = new ReminderAdapter.ReminderViewHolder (v);
            v.setTag(reminderViewHolder);
        } else {
            reminderViewHolder = (ReminderAdapter.ReminderViewHolder) v.getTag();
        }
        final ZadatakItem zadatak = this.mZadaci.get(position);
        reminderViewHolder.tvNaziv.setText(zadatak.getzNaziv());
        reminderViewHolder.tvOpis.setText(zadatak.getzOpis());
        reminderViewHolder.tvDatum.setText(zadatak.getzVrijeme());

        if (position % 2 == 1) {
            v.setBackgroundColor(Color.WHITE);
        } else {
            v.setBackgroundColor(Color.parseColor("#F5ECCE"));
        }

        final String sCalendar = zadatak.getzVrijeme();
        final Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date firstDate =sdf.parse(sCalendar);
            calendar.setTime(sdf.parse(sCalendar));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("Error Parsing",e.toString());
        }

        return v;
    }


    public void deleteAt(int position) {
        this.mZadaci.remove(position);
        this.notifyDataSetChanged();
    }


    static class ReminderViewHolder {
        TextView tvNaziv, tvOpis, tvDatum;
        RelativeLayout relativeLayout;
        public ReminderViewHolder (View reminderView) {
            this.tvNaziv = (TextView) reminderView.findViewById(R.id.tvNazivZadatka);
            this.tvOpis = (TextView) reminderView.findViewById(R.id.tvOpisZadatka);
            this.tvDatum= (TextView) reminderView.findViewById(R.id.tvDatum);
            this.relativeLayout = (RelativeLayout) reminderView.findViewById(R.id.rlZad);
        }
    }
}
