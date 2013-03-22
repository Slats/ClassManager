package com.example.fclassmanager;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClassRosterFragment extends Fragment {
int mNum;
	
	static ClassRosterFragment newInstance(int num) {
		ClassRosterFragment f = new ClassRosterFragment();
		
		// Uses argument
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);
		
		return f;
	}
	
	/**
	 * When the fragment is created, the instance number is retrieved
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNum = getArguments() != null ? getArguments().getInt("num") : 0;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Resources res = getResources();
		String[] classDesc = res.getStringArray(R.array.junk_data);
		View v = inflater.inflate(R.layout.desc_view, container, false);
		View tv = v.findViewById(R.id.class_desc_view);
		((TextView)tv).setText(classDesc[mNum]);
		return v;
	}
}
