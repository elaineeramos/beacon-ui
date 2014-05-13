package net.magis.BeaconPH.UI.Extra;

import java.util.ArrayList;

import net.magis.BeaconPH.Data.GoogleMapsPerson;

import com.example.beacon.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class MyPersonsAdapter extends ArrayAdapter<GoogleMapsPerson> {
 
        private final Context context;
        private final ArrayList<GoogleMapsPerson> itemsArrayList;
 
        public MyPersonsAdapter(Context context, ArrayList<GoogleMapsPerson> itemsArrayList) {
 
            super(context, R.layout.row, itemsArrayList);
 
            this.context = context;
            this.itemsArrayList = itemsArrayList;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
 
            // 1. Create inflater 
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
            // 2. Get rowView from inflater
            View rowView = inflater.inflate(R.layout.row, parent, false);
 
            // 3. Get the two text view from the rowView
            TextView labelView = (TextView) rowView.findViewById(R.id.label);
            TextView valueView = (TextView) rowView.findViewById(R.id.value);
 
            // 4. Set the text for textView 
            labelView.setText(itemsArrayList.get(position).getGivenName());
            valueView.setText(itemsArrayList.get(position).getLastLocation());
 
            // 5. retrn rowView
            return rowView;
        }
}