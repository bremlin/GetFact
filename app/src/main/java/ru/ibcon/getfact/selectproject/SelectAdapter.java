package ru.ibcon.getfact.selectproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ru.ibcon.getfact.R;

import java.util.ArrayList;

public class SelectAdapter extends ArrayAdapter<SelectItem> {
    private Activity activity;
    private ArrayList<SelectItem> listItem;
    private static LayoutInflater inflater = null;

    public SelectAdapter (Activity activity, int textViewResourceId, ArrayList<SelectItem> listItem) {
        super(activity, textViewResourceId, listItem);
        this.activity = activity;
        this.listItem = listItem;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return listItem.size();
    }

    public SelectItem getItem(SelectItem item) {
        return item;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView displayName;
        public TextView displayNumber;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.activity_select_project, null);
            holder = new ViewHolder();
//            holder.displayName = (TextView) view.findViewById(R.id.display_name);
//            holder.displayNumber = (TextView) view.findViewById(R.id.display_number);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.displayName.setText(listItem.get(position).getName());
        holder.displayNumber.setText(listItem.get(position).getNumber());

        return view;
    }


}
