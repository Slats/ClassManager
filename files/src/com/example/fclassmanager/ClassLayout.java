package com.example.fclassmanager;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ClassLayout extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_class_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_layout, menu);
		return true;
	}
	
	
	public static class ClassNamesFragment extends ListFragment {
		String curClassName;
		int mCurrentPosition = 0;
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			String[] classNames = getResources().getStringArray(R.array.class_name);
			// Populate list with static array of classes.
			setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, classNames));
		
			if (savedInstanceState != null) {
				mCurrentPosition = savedInstanceState.getInt("curChoice", 0);
				curClassName = savedInstanceState.getString("curName", null);
			}
			
			
		}
		
		@Override 
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("curChoice", mCurrentPosition);
			outState.putString("curName", curClassName);
		}
		
		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			String[] names = getResources().getStringArray(R.array.class_name);
			callClassActionsActivity(names[position], position);
		}
		
		private void callClassActionsActivity(String name, int position) {
			mCurrentPosition = position;
			curClassName = name;
			
			Intent intent = new Intent();
			intent.setClass(getActivity(), ClassActionTabs.class);
			intent.putExtra("name", name);
			intent.putExtra("index", position);
			startActivity(intent);
		}
	}

}
