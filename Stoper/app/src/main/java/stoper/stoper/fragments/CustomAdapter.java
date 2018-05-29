package stoper.stoper.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import stoper.stoper.R;
import stoper.stoper.model.Ride;

/**
 * Created by Lenovo on 29.5.2018..
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<Ride> rowItems;
    CustomAdapter(Context context, List<Ride> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
	/* private view holder class */
    private class ViewHolder {
        TextView startDestination;
        TextView endDestination;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_item, null);
            holder = new ViewHolder();
            holder.startDestination = (TextView) convertView
                    .findViewById(R.id.id);
            holder.endDestination = (TextView) convertView
                    .findViewById(R.id.content);
            Ride row_pos = rowItems.get(position);
            holder.startDestination.setText(row_pos.getStartDestination());
            holder.endDestination.setText(row_pos.getEndDestination());
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}