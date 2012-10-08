package org.bskrecord;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class DetailFragment extends Fragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
		private int position;
		public DetailFragment(){
			
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		}
		public int getIndex(){
			return position;
		}
		@Override
		public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
			super.onCreateView(inflater, container, savedInstanceState);
			View view = inflater.inflate(R.layout.fordialog,container, false);
			
			//TextView name = (TextView)container.findViewById(R.id.txtuu);
			//playername.setText(name.toString());
			
			//TextView number = (TextView)container.findViewById(R.id.ttnum1);
			//playernumber.setText(number.toString());
			return view;
		}
		public void setname(String item){
			View vv = getView();
			if(vv!=null){
			EditText playername = (EditText)getView().findViewById(R.id.playerName);
			playername.setText(item, TextView.BufferType.EDITABLE);
			}
		}
		public void setnumber(String item){
			EditText playernumber = (EditText)getView().findViewById(R.id.playerNumber);
			playernumber.setText(item,TextView.BufferType.EDITABLE);
		}
}
