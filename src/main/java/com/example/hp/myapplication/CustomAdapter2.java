package com.example.hp.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2/14/2019.
 */

public class CustomAdapter2 extends ArrayAdapter<DataModel2> {
    private ArrayList<DataModel2> dataSet;
    Context mContext;
    private List AllDataModel = new ArrayList();
    List<String[]> items;


    public CustomAdapter2(List<DataModel2> data, Context context) {
        super(context, R.layout.row_item2, data);
        this.dataSet = (ArrayList<DataModel2>) data;
        this.mContext=context;
    }
    // View lookup cache
    private static class ViewHolder {
        TextView date;
        TextView time;
        TextView gas;

    }



 /*   public CustomAdapter(List<String[]> data, Context context) {
        super(context, data);
        this.dataSet = (ArrayList<DataModel>) data;
        this.mContext=context;
    }*/

    @Override
    public DataModel2 getItem(int position){
        return dataSet.get(position);
    }
    // @Override
   /* public void add(String[] object) {
        dataSet.add(object);
        //super.add(object);
    }*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        //  DataModel dataModel = getItem(position);
        DataModel2 dataModel=getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item2, parent, false);
            viewHolder.gas = (TextView) convertView.findViewById(R.id.gas);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.date.setText(dataModel.getDate());
        viewHolder.time.setText(dataModel.getTime());
        viewHolder.gas.setText(dataModel.getGas());
        // Return the completed view to render on screen
        return convertView;
    }
}
