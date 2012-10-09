package org.bskrecord;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class InsertDataFragment extends ListFragment{
	private  ArrayList<HashMap<String, String>> listItems;
	private  SimpleAdapter listItemAdapter; 
	public String[] names={"","","","","","","","","","","","","","","",""};
	public String[] numbers={"","","","","","","","","","","","","","","",""};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		setView();
		
		
		View detailFrame = getActivity().findViewById(R.id.showinsert);
	}
	public void setView(){
		listItems = new ArrayList<HashMap<String, String>>(); 
		HashMap<String, String> dsa = new HashMap<String,String>();
		dsa.put("name", "Name");
		dsa.put("number", "Number");
		listItems.add(dsa);
		int j = 0;
		while(names[j].length()!=0){
			HashMap<String, String> ddd = new HashMap<String,String>();
			ddd.put("name", names[j]);
			ddd.put("number", numbers[j]);
			j++;
			listItems.add(ddd);
		}
		listItemAdapter = new SimpleAdapter(getActivity(),listItems,R.layout.listname,new String []{"name","number"},new int [] {R.id.txtuu,R.id.ttnum1});	
		setListAdapter(listItemAdapter);
	}
	/*@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.listname,container, false);
		return view;
	}*/
}
