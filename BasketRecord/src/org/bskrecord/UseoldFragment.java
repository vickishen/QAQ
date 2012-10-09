package org.bskrecord;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class UseoldFragment extends ListFragment{
	private  ArrayList<HashMap<String, String>> listItems;
	private ListView listView;
	private  SimpleAdapter listItemAdapter; 
	private String tableName ="";
	String[] namearr = new String[16];
	String[] numarr = new String[16];
	int po=-1;
	int i=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		startView();
		
		
		View detailFrame = getActivity().findViewById(R.id.detail);
		if(savedInstanceState!=null){
			po = savedInstanceState.getInt("position",0);
		}
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		getListView().setItemChecked(po, true);
		//TextView playname = (TextView)v.findViewById(R.id.txtuu);
		
		//TextView playnumber = (TextView)v.findViewById(R.id.ttnum1);
		
		//String name = playname.toString();
		//String number = playnumber.toString();
	}
	public void startView(){
		listItems = new ArrayList<HashMap<String, String>>();
		SQLite data2 = new SQLite(getActivity(),"data",null,1);
		SQLiteDatabase XD = data2.getWritableDatabase();
		Cursor old = XD.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
		old.moveToLast();
		String table = old.getString(0);
		tableName = old.getString(0);
		Cursor player = XD.query(table, new String [] {"name","number"}, null, null, null, null, null);	
		HashMap<String, String> dsa = new HashMap<String,String>();
		dsa.put("name1", "©m¦W");
		dsa.put("number", "­I¸¹");
		listItems.add(dsa);
		player.moveToFirst();
		
		if(player.moveToFirst()){
			while(!player.isAfterLast()){
				String Name = player.getString(player.getColumnIndexOrThrow("name"));
				String Number = player.getString(player.getColumnIndexOrThrow("number"));
				
				namearr[i]=Name;
				numarr[i]=Number;
				i++;
				
				player.moveToNext();
			}
			
			player.close();
			XD.close();
		}
		updatelist(i);
		
	}
	public void updatelist(int d){
		listItems = new ArrayList<HashMap<String, String>>();
		for(int j=0;j<d;j++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name1", namearr[j]);
			map.put("number", numarr[j]);
			listItems.add(map);
		}
		listItemAdapter = new SimpleAdapter(getActivity(),listItems,R.layout.listname,new String []{"name1","number"},new int [] {R.id.txtuu,R.id.ttnum1});
		setListAdapter(listItemAdapter);
	}
	@Override
	public void onListItemClick(ListView l,View v,int position,long id){
		super.onListItemClick(l, v, position, id);
		
		
		//LayoutInflater inflater = LayoutInflater.from(getActivi
		l.setItemChecked(position, true);
		
		TextView playname = (TextView)v.findViewById(R.id.txtuu);
		TextView playnumber = (TextView)v.findViewById(R.id.ttnum1);
		
		String name = playname.getText().toString();
		String name2 = "";
		String number2 = ""; 
		String number = playnumber.getText().toString();
		DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail);
		if(detailFragment ==null){
			detailFragment = new DetailFragment();
		}
		if(detailFragment!=null){
			detailFragment.setname(name);
			detailFragment.setnumber(number);
			po=position;
		}
		
		/*EditText playername = (EditText)detailFragment.findViewById(R.id.playerName);
		EditText playernumber = (EditText)detailFragment.findViewById(R.id.playerNumber);
		playername.setText("ll");
		playernumber.setText("hh");*/
		/*FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();*/
		//context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	}	

}
