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
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	ListView keyHolderList;
	CustomAdapter mCustomAdapter;
	ImageView mImageView;
	View v2;
	TextView cancel;
	ArrayList<Integer> passFields = new ArrayList<> ();
	ArrayList<Integer> numberFields = new ArrayList<> ();

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		getWindow ().setStatusBarColor (getResources ().getColor (R.color.anzac));
		setContentView (R.layout.activity_main);
		ArrayList<KeyHolderModel> keyHolderModels = new ArrayList<> ();
		KeyHolderModel keyHolder1 = new KeyHolderModel ("Omolara Adejuwon", "Everyday", "12:00pm to 3:00pm", "1");
		KeyHolderModel keyHolder2 = new KeyHolderModel ("Adejumoke Adejuwon", "Everyday", "12:00pm to 3:00pm", "0");
		KeyHolderModel keyHolder3 = new KeyHolderModel ("Oreoluwa Adejuwon", "Everyday", "12:00pm to 3:00pm", "1");
		KeyHolderModel keyHolder4 = new KeyHolderModel ("Adeyimika Adejuwon", "Everyday", "12:00pm to 3:00pm", "1");
		keyHolderModels.add (keyHolder1);
		keyHolderModels.add (keyHolder2);
		keyHolderModels.add (keyHolder3);
		keyHolderModels.add (keyHolder4);

		passFields.add (R.id.pass_1);
		passFields.add (R.id.pass_2);
		passFields.add (R.id.pass_3);
		passFields.add (R.id.pass_4);

		numberFields.add (R.id.no_0);
		numberFields.add (R.id.no_1);
		numberFields.add (R.id.no_2);
		numberFields.add (R.id.no_3);
		numberFields.add (R.id.no_4);
		numberFields.add (R.id.no_5);
		numberFields.add (R.id.no_6);
		numberFields.add (R.id.no_7);
		numberFields.add (R.id.no_8);
		numberFields.add (R.id.no_9);

		mCustomAdapter = new CustomAdapter (this, keyHolderModels);
		keyHolderList = (ListView) findViewById (R.id.keyHoldersList);
		mImageView = (ImageView) findViewById (R.id.lock);
		keyHolderList.setAdapter (mCustomAdapter);
		mImageView.setOnClickListener (this);LayoutInflater inflater = LayoutInflater.from (MainActivity.this);
		v2 = inflater.inflate (R.layout.lock_screen, null, false);
		cancel = (TextView) v2.findViewById (R.id.cancel);
		cancel.setOnClickListener (this);

		for (int i : numberFields) {
			v2.findViewById (i).setOnClickListener (this);
		}
	}

	@Override
	public void onBackPressed () {
		if (v2 != null) {
			((ViewGroup) v2.getParent ()).removeView (v2);
		} else {
			super.onBackPressed ();
		}
	}

	@Override
	public void onClick (View v) {
		int id = v.getId ();
		switch (id) {
			case R.id.cancel:
				clearPassFields ();
				break;
			case R.id.lock:

				v2.setOnClickListener (new View.OnClickListener () {
					@Override
					public void onClick (View v) {
						//deliberately left blank
					}
				});
				addContentView (v2, new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				break;
			case R.id.no_0:
			case R.id.no_1:
			case R.id.no_2:
			case R.id.no_3:
			case R.id.no_4:
			case R.id.no_5:
			case R.id.no_6:
			case R.id.no_7:
			case R.id.no_8:
			case R.id.no_9:
				fillPassFields ();
		}
	}

	private void clearPassFields () {
		for (int i : passFields) {
			v2.findViewById (i).setBackground (getResources ().getDrawable (R.drawable.white_circular_border, null));
		}
	}

	int count = 0;

	private void fillPassFields () {
		if (count < 4) {
			v2.findViewById (passFields.get (count)).setBackground (getResources ().getDrawable (R.drawable.white_circular_fill));
			count++;
		}
	}
}
