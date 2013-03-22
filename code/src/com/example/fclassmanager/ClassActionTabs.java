package com.example.fclassmanager;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

public class ClassActionTabs extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Bundle args = intent.getExtras();
		String titleName = args.getString("name");
		setTitle(titleName);
		
		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		
		Resources res = getResources();
		String[] classfuncs = res.getStringArray(R.array.class_func);
		
		bar.addTab(bar.newTab()
				.setText(classfuncs[0])
				.setTabListener(new TabListener<ClassRosterFragment>(this, "roster", ClassRosterFragment.class )));
		
		bar.addTab(bar.newTab()
				.setText(classfuncs[1])
				.setTabListener(new TabListener<AttendanceFragment>(this, "attendance", AttendanceFragment.class)));
		
		bar.addTab(bar.newTab()
				.setText(classfuncs[2])
				.setTabListener(new TabListener<AssignmentFragment>(this, "assignment", AssignmentFragment.class)));
	
		bar.addTab(bar.newTab()
				.setText(classfuncs[3])
				.setTabListener(new TabListener<ClassSettingsFragment>(this, "settings", ClassSettingsFragment.class)));
	
		
		if(savedInstanceState != null) {
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}

	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;
		private final Bundle mArgs;
		private Fragment mFragment;
		
		public TabListener(Activity act, String tag, Class<T> clz) {
			this(act, tag, clz, null);
		}
		
		public TabListener(Activity act, String tag, Class<T> clz, Bundle args) {
			mActivity = act;
			mTag = tag;
			mClass = clz;
			mArgs = args;
			
			// Check to see if we already have a fragment for this probably 
			// from a prev saved state. if so, deactivate it, because our
			// initial state is that a tab isn't shown.
			mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
			if (mFragment != null && !mFragment.isDetached()) {
				FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
				ft.detach(mFragment);
				ft.commit();
			}
		}
		
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (mFragment == null) {
				mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				ft.attach(mFragment);
			}
		}
		
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.detach(mFragment);
			}
		}
		
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			 
		}
	}
}
