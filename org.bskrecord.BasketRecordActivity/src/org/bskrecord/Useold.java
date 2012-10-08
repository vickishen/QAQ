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
	
	public  void  onCreate(Bundle icicle) {
        super .onCreate(icicle);
        setContentView(R.layout.useold);
        //startView();
        //this .setListAdapter(listItemAdapter);
        
        
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

