package com.gpshealthsurvey.gpshealthsurvey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aroon on 4/10/2015.
 */
public class SurveyBuilderAdaptor extends BaseAdapter {
    private ArrayList<Attribute> FieldArray;

    public SurveyBuilderAdaptor(ArrayList<Attribute> aHouseArray){
        this.FieldArray = aHouseArray;
    }

    public void add(Attribute attribute){
        this.FieldArray.add(attribute);
    }

    public void delete(int id){
        this.FieldArray.remove(id);
    }

    public void clear(){
        this.FieldArray.clear();
    }

    @Override
    public int getCount() {
        return FieldArray.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return FieldArray.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent){
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.survey_builder_adaptor,parent,false);
        }
        final Attribute item = FieldArray.get(index);
        final int id = index;

        EditText FieldName = (EditText) view.findViewById(R.id.fieldName);
        Spinner desc = (Spinner) view.findViewById(R.id.fieldType);
        Button deleteButton = (Button) view.findViewById(R.id.deleteField);

        //populate spinner with field type options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(parent.getContext(),R.array.field_types,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desc.setAdapter(adapter);

        //enable delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
            }
        });

        return view;
    }

}
