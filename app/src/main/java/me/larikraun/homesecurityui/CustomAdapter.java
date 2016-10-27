package me.larikraun.homesecurityui;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by omolara on 10/25/16.
 */
public class CustomAdapter extends BaseExpandableListAdapter {
	private Context mContext;
	private ArrayList<KeyHolderModel> mKeyHolderModels;
	private HashMap<KeyHolderModel, ArrayList<String>> mListDataChild;
	
	public CustomAdapter (Context context, ArrayList<KeyHolderModel> keyHolders,
						  HashMap<KeyHolderModel, ArrayList<String>> listChildData) {
		this.mContext = context;
		this.mKeyHolderModels = keyHolders;
		this.mListDataChild = listChildData;
	}
	
	@Override
	public int getGroupCount () {
		return mKeyHolderModels.size ();
	}
	
	@Override
	public int getChildrenCount (int groupPosition) {
		return this.mListDataChild.get (this.mKeyHolderModels.get (groupPosition))
				.size ();
	}
	
	@Override
	public Object getGroup (int groupPosition) {
		return this.mKeyHolderModels.get (groupPosition);
	}
	
	@Override
	public Object getChild (int groupPosition, int childPosition) {
		return this.mListDataChild.get (this.mKeyHolderModels.get (groupPosition))
				.get (childPosition);
	}
	
	@Override
	public long getGroupId (int groupPosition) {
		return groupPosition;
	}
	
	@Override
	public long getChildId (int groupPosition, int childPosition) {
		return childPosition;
	}
	
	@Override
	public boolean hasStableIds () {
		return false;
	}
	
	@Override
	public View getGroupView (int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		LayoutInflater inflater = LayoutInflater.from (mContext);
		final ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate (R.layout.keyholder_list, parent, false);
			holder = new ViewHolder (convertView);
			convertView.setTag (holder);
		} else {
			holder = (ViewHolder) convertView.getTag ();
		}
		KeyHolderModel keyholder = mKeyHolderModels.get (groupPosition);
		holder.name.setText (keyholder.getName ());
		holder.day.setText (keyholder.getDay ());
		holder.time.setText (keyholder.getTime ());
		holder.status.setChecked (keyholder.getStatus ().equals ("1"));
		return convertView;
	}
	
	@Override
	public View getChildView (int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final String childText = (String) getChild (groupPosition, childPosition);
		
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from (mContext);
			convertView = inflater.inflate (R.layout.ex_list_item, null);
		}
		
		TextView txtListChild = (TextView) convertView
				.findViewById (R.id.child);
		
		txtListChild.setText (childText);
		return convertView;
	}
	
	@Override
	public boolean isChildSelectable (int groupPosition, int childPosition) {
		return false;
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
			Typeface f = Typeface.createFromAsset (mContext.getAssets (), "fonts/gotham_book.ttf");
			name.setTypeface (f);
			day.setTypeface (f);
			time.setTypeface (f);
		}
	}
}
