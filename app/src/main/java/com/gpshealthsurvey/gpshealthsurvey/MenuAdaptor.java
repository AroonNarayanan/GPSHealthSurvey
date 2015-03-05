package com.gpshealthsurvey.gpshealthsurvey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aroon on 2/15/2015.
 */
public class MenuAdaptor extends BaseAdapter {
    private ArrayList<MenuListItem> MenuArray;

    public MenuAdaptor(ArrayList<MenuListItem> aMenuArray){
        this.MenuArray = aMenuArray;
    }

    @Override
    public int getCount() {
        return MenuArray.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return MenuArray.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent){
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.mainmenu_adaptor,parent,false);
        }
        final MenuListItem item = MenuArray.get(index);

        TextView title = (TextView) view.findViewById(R.id.menu_title);
        TextView desc = (TextView) view.findViewById(R.id.menu_desc);
        ImageView img = (ImageView) view.findViewById(R.id.menu_img);

        title.setText(item.getName());
        desc.setText(item.getDescription());

        return view;
    }
}
