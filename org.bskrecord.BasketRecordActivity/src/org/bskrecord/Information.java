package org.bskrecord;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Information extends Activity{
	private int Nmonth;
	private int Nday;
	private int Nyear;
	private int Nhour;
	private int Nmin;
	int judge=0,style;
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information);
		Bundle get = this.getIntent().getExtras();
		judge=get.getInt("judge");
		style=get.getInt("style");
		showView();
	}
	public void showView(){
		EditText date = (EditText)findViewById(R.id.date);
		final Calendar c = Calendar.getInstance(); 
		Nyear = c.get(Calendar.YEAR);
		Nmonth = c.get(Calendar.MONTH);
		Nday = c.get(Calendar.DATE);
		Nmin = c.get(Calendar.MINUTE);
		Nhour = c.get(Calendar.HOUR_OF_DAY);
		date.setText(Integer.toString(Nyear)+"/"+Integer.toString(Nmonth+1)+"/"+Integer.toString(Nday));
	}
	public void sendinfo(View v){
		EditText oppName = (EditText)findViewById(R.id.oppname);
		EditText recorder = (EditText)findViewById(R.id.recorder);
		Bundle infoForTb = new Bundle();
		if(oppName.getText().toString().length()!=0){
			infoForTb.putString("oppname", oppName.getText().toString());
			infoForTb.putString("recorder",recorder.getText().toString());
			infoForTb.putInt("year", Nyear);
			infoForTb.putInt("month", Nmonth+1);
			infoForTb.putInt("day", Nday);
			infoForTb.putInt("style", style);
			infoForTb.putInt("hour", Nhour);
			infoForTb.putInt("min", Nmin);
			Intent toName = new Intent();
			toName.putExtras(infoForTb);
			if(judge==0){
				toName.setClass(Information.this, InsertName.class);
				startActivity(toName);
				Information.this.finish();
			}
			else if(judge==1){
				toName.setClass(Information.this, Useold.class);
				startActivity(toName);
				Information.this.finish();
			}
		}
		else{
			Toast.makeText(Information.this, "¹ï¾Ô¶¤¥î¥²¶ñ¡AÁÂÁÂ¡I", Toast.LENGTH_LONG).show();
		}
		//SQLiteDatabase XD = QQ.getWritableDatabase();
		
	}
}
