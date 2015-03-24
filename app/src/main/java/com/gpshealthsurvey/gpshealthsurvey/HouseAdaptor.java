package com.gpshealthsurvey.gpshealthsurvey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aroon on 3/22/2015.
 */
public class HouseAdaptor extends BaseAdapter {
    private ArrayList<Household> HouseArray;

    public HouseAdaptor(ArrayList<Household> aHouseArray){
        this.HouseArray = aHouseArray;
    }

    @Override
    public int getCount() {
        return HouseArray.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return HouseArray.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent){
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.house_listadaptor,parent,false);
        }
        final Household item = HouseArray.get(index);

        TextView title = (TextView) view.findViewById(R.id.house_desc);
        TextView desc = (TextView) view.findViewById(R.id.house_loc);
        ImageView img = (ImageView) view.findViewById(R.id.house_thumbnail);

        title.setText(item.description);
        desc.setText(Double.toString(item.latitude) + ", " + Double.toString(item.longitude));
        //TODO: set image thumbnail

        return view;
    }

}
