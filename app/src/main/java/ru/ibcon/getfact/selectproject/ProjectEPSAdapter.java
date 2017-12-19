package ru.ibcon.getfact.selectproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ru.ibcon.getfact.R;

import java.util.ArrayList;

public class ProjectEPSAdapter extends BaseAdapter {
    private Activity context;
    private ArrayList<SelectItem> items;
    private static LayoutInflater inflater = null;

    public ProjectEPSAdapter(Activity context, ArrayList<SelectItem> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<SelectItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertedView, ViewGroup parent) {
        View itemView = convertedView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_item, null) : itemView;
        TextView textViewId = (TextView) itemView.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        SelectItem selectItem = items.get(position);
        textViewId.setText(selectItem.getNumber());
        textViewName.setText(selectItem.getName());
        System.out.println("PESPA getView " + selectItem.toString());
        return itemView;
    }
}
