package com.longshihan.mymenu.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.longshihan.mymenu.R;

public class LeftMenu extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.left, container,false);
		v.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("Hello jikexueyuan.com");
			}
		});
		return v;
	}
}
