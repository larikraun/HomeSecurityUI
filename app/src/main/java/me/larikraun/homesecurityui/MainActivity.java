package me.larikraun.homesecurityui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	ListView keyHolderList;
	CustomAdapter mCustomAdapter;
	ImageView mImageView;
	View v2;

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		getWindow ().setStatusBarColor (getResources ().getColor (R.color.anzac));
		setContentView (R.layout.activity_main);
		ArrayList<KeyHolderModel> keyHolderModels = new ArrayList<> ();
		KeyHolderModel keyHolder1 = new KeyHolderModel ("Omolara Adejuwon", "Everyday", "12:00pm to 3:00pm", "1");
		KeyHolderModel keyHolder2 = new KeyHolderModel ("Oreoluwa Adejuwon", "Everyday", "12:00pm to 3:00pm", "0");
		KeyHolderModel keyHolder3 = new KeyHolderModel ("Adejumoke Adejuwon", "Everyday", "12:00pm to 3:00pm", "1");
		KeyHolderModel keyHolder4 = new KeyHolderModel ("Adeyimika Adejuwon", "Everyday", "12:00pm to 3:00pm", "1");
		keyHolderModels.add (keyHolder1);
		keyHolderModels.add (keyHolder2);
		keyHolderModels.add (keyHolder3);
		keyHolderModels.add (keyHolder4);
		mCustomAdapter = new CustomAdapter (this, keyHolderModels);
		keyHolderList = (ListView) findViewById (R.id.keyHoldersList);
		mImageView = (ImageView) findViewById (R.id.lock);
		keyHolderList.setAdapter (mCustomAdapter);
		mImageView.setOnClickListener (new View.OnClickListener () {
			@Override
			public void onClick (View v) {
				LayoutInflater inflater = LayoutInflater.from (MainActivity.this);
				v2 = inflater.inflate (R.layout.lock_screen, null, false);
				v2.setOnClickListener (new View.OnClickListener () {
					@Override
					public void onClick (View v) {

					}
				});
				addContentView (v2, new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			}
		});
	}

	@Override
	public void onBackPressed () {
		if (v2 != null) {
			((ViewGroup) v2.getParent ()).removeView (v2);
		} else {
			super.onBackPressed ();
		}
	}
}
