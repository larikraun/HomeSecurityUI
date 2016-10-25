package me.larikraun.homesecurityui;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by omolara on 10/25/16.
 */
public class CustomAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<KeyHolderModel> mKeyHolderModels;

	public CustomAdapter (Context context, ArrayList<KeyHolderModel> keyHolders) {
		this.mContext = context;
		this.mKeyHolderModels = keyHolders;
	}

	@Override
	public int getCount () {
		return mKeyHolderModels.size ();
	}

	@Override
	public Object getItem (int position) {
		return null;
	}

	@Override
	public long getItemId (int position) {
		return 0;
	}

	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from (mContext);
		final ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate (R.layout.keyholder_list, parent, false);
			holder = new ViewHolder (convertView);
			convertView.setTag (holder);
		} else {
			holder = (ViewHolder) convertView.getTag ();
		}
		KeyHolderModel keyholder = mKeyHolderModels.get (position);
		holder.name.setText (keyholder.getName ());
		holder.day.setText (keyholder.getDay ());
		holder.time.setText (keyholder.getTime ());
		holder.status.setChecked (keyholder.getStatus ().equals ("1"));
		return convertView;
	}

	private class ViewHolder {
		TextView name;
		TextView day;
		TextView time;
		SwitchCompat status;

		public ViewHolder (View v) {
			name = (TextView) v.findViewById (R.id.name);
			day = (TextView) v.findViewById (R.id.days);
			time = (TextView) v.findViewById (R.id.time);
			status = (SwitchCompat) v.findViewById (R.id.status);
			Typeface f = Typeface.createFromAsset (mContext.getAssets (),"fonts/moon.otf");
			name.setTypeface (f);
			day.setTypeface (f);
			time.setTypeface (f);
		}
	}
}
