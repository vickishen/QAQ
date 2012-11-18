package org.bskrecord;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

public class Useold extends Activity{
	String[] name = new String[16];
	String[] num = new String[16];
	private SQLite QQ = null;
	StringBuilder tableName = new StringBuilder("");
	@Override
	public  void  onCreate(Bundle icicle) {
        super .onCreate(icicle);
        setContentView(R.layout.useold);
        //startView();
        //this .setListAdapter(listItemAdapter);
        
        
    }
	public void revise(View v){
		EditText playername = (EditText)findViewById(R.id.playerName);
		EditText playernum = (EditText)findViewById(R.id.playerNumber);
		UseoldFragment data = (UseoldFragment)getFragmentManager().findFragmentById(R.id.master);
		if(data.po!=-1){
			int re = 0;
			for(re=data.po;re<data.numarr.length-1;re++){
				data.namearr[re] = data.namearr[re+1];
				data.numarr[re]=data.numarr[re+1];
			}
			data.updatelist(data.i);
		}
		else{
			playername.setText("請選擇要更改的球員!");
		}
	}
	public void togame(View v){
		//UseoldFragment getname = (UseoldFragment)find
		UseoldFragment data = (UseoldFragment)getFragmentManager().findFragmentById(R.id.master);
		int no=0;
		String[] arr = {"","","","","","","","","","","","","","","",""}; 
		for(int j=0;j<data.i;j++){
			arr[j]=data.numarr[j];
		}
		Bundle getdata = this.getIntent().getExtras();
		QQ = new SQLite(Useold.this,"data",null,1);
		String oppname = getdata.getString("oppname");
		String year = Integer.toString(getdata.getInt("year"));
		String month = Integer.toString(getdata.getInt("month"));
		String day = Integer.toString(getdata.getInt ("day"));
		String recorder = getdata.getString("recorder");
		int style = getdata.getInt("style");
		int hour = getdata.getInt("hour");
		int min = getdata.getInt("min");
		tableName.append(oppname).append(year).append(month).append(day);
		QQ.createTable(tableName.toString());
		for(no=0;no<data.i;no++){
			QQ.addplayer(data.namearr[no],data.numarr[no] , tableName.toString(), recorder, getdata.getInt("month"), getdata.getInt("day"),getdata.getInt("year"),hour,min,oppname);
		}
		QQ.close();
		Intent toRecord = new Intent();
		Bundle table = new Bundle();
		table.putString("table", tableName.toString());
		table.putStringArray("num", arr);
		table.putInt("sfls", 0);
		table.putInt("ofls", 0);
		table.putInt("ourpts", 0);
		table.putInt("opppts", 0);
		table.putInt("style", style);
		table.putString("oppname", oppname);
		toRecord.putExtras(table);
		toRecord.setClass(Useold.this, Recording.class);
		startActivity(toRecord);
		Useold.this.finish();
	}
	public void newPlayer(View v){
		EditText playername = (EditText)findViewById(R.id.playerName);
		EditText playernum = (EditText)findViewById(R.id.playerNumber);
		UseoldFragment data = (UseoldFragment)getFragmentManager().findFragmentById(R.id.master);
		if(data.i<15&&playername.getText().toString().length()!=0){
			data.namearr[data.i]=playername.getText().toString();
			data.numarr[data.i]=playernum.getText().toString();
			playername.setText("");
			playernum.setText("");
			data.i=data.i+1;
			data.updatelist(data.i);
		}
		else if(playername.getText().toString().length()==0||playernum.getText().toString().length()==0){
			Toast.makeText(Useold.this, "請填入姓名和背號!!", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(Useold.this, "超過人數上限!!", Toast.LENGTH_LONG).show();
		}
	}
}	
	
	
	/*@Override
	public void onListItemClick(ListView l , View v, int position , long id){
	 super.onListItemClick(l, v, position, id);*/
	 /*Intent InData = new Intent();
	 InData.setClass(Useold.this, BasketRecordActivity.class);
	 startActivity(InData);
	 Useold.this.finish();
	 //LayoutInflater inflater = LayoutInflater.from(Useold.this);
    //final View dialogView = inflater.inflate(R.layout.fordialog, null);
    
	 final String data = ((TextView)v.findViewById(R.id.txtuu)).getText().toString();
	 EditText playName = (EditText)dialogView.findViewById(R.id.playerName);
	 playName.setText(data,TextView.BufferType.EDITABLE);
	 EditText playNumber = (EditText)dialogView.findViewById(R.id.playerNumber);
	 playNumber.setText(data, TextView.BufferType.EDITABLE);
	 /*new AlertDialog.Builder(Useold.this).setView(dialogView).setPositiveButton("Rewrite", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			SQLite data3 = new SQLite(Useold.this,"data",null,1);
			SQLiteDatabase XP = data3.getWritableDatabase();
			ContentValues values = new ContentValues();
			EditText secondName = (EditText)dialogView.findViewById(R.id.playerName);
			
			values.put("name",secondName.getText().toString() );
			int change = XP.update(tableName, values, "name"+"= '"+data+"'", null);
			if(change == 0){
				Toast.makeText(Useold.this, "failed!", Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(Useold.this, "success", Toast.LENGTH_LONG).show();
			}
			XP.close();
			dialog.cancel();
			startView();
			Useold.this.setListAdapter(listItemAdapter);
		}
	})
	.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			SQLite data3 = new SQLite(Useold.this,"data",null,1);
			SQLiteDatabase XP = data3.getWritableDatabase();
			
			int deletedata = XP.delete(tableName, "name"+"= '"+data+"'", null);
			if(deletedata==0){
				Toast.makeText(Useold.this, "failed!", Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(Useold.this, "success", Toast.LENGTH_LONG).show();
			}
			XP.close();
			dialog.cancel();
		}
	}).show();*/

