package org.bskrecord;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertName extends Activity {
	String[] namess = {"","","","","","","","","","","","","","","",""};
	String[] numberss = {"","","","","","","","","","","","","","","",""}; 
	public int i = 0;
	private SQLite QQ = null;
	int style=1;
	StringBuilder tableName = new StringBuilder("");
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertnew);
        
	}
	public void insertCall(View v){
		InsertFragment fuck = (InsertFragment)getFragmentManager().findFragmentById(R.id.insert);
		EditText name = (EditText)fuck.getView().findViewById(R.id.inplayerName);
		EditText number = (EditText)fuck.getView().findViewById(R.id.inplayerNumber);
		InsertDataFragment getArray = (InsertDataFragment)getFragmentManager().findFragmentById(R.id.showinsert);
		if(i<15){
			getArray.names[i] = name.getText().toString();
			namess[i] = name.getText().toString();
			getArray.numbers[i] = number.getText().toString();
			numberss[i] = number.getText().toString();
			getArray.setView();
			i++;
			name.setText("");
			number.setText("");
			//Toast.makeText(InsertName.this, Integer.toString(i), Toast.LENGTH_LONG).show();
			
		}
		else{
			Toast.makeText(InsertName.this, "超過人數上限！！", Toast.LENGTH_LONG).show();
			todb();
			i=0;
			
		}
	}
	public void todbCall(View v){
		if(i>=5){
		todb();
		Bundle getopp = this.getIntent().getExtras();
		String opp="";
		opp=getopp.getString("oppname");
		Bundle getsty = this.getIntent().getExtras();
		style=getsty.getInt("style");
		Intent toRecord = new Intent();
		Bundle table = new Bundle();
		table.putString("table", tableName.toString());
		table.putStringArray("num", numberss);
		table.putInt("sfls", 0);
		table.putInt("ofls", 0);
		table.putInt("ourpts", 0);
		table.putInt("opppts", 0);
		table.putInt("style", style);
		table.putInt("quater", 0);
		int[] quaterp = new int[8];
		for(int y =0;y<8;y++){
			quaterp[y]=0;
		}
		table.putIntArray("qpoint", quaterp);
		table.putString("oppname", opp);
		toRecord.putExtras(table);
		toRecord.setClass(InsertName.this, Recording.class);
		startActivity(toRecord);
		InsertName.this.finish();
		}
	}
	@Override
	public void onBackPressed(){
		Intent back = new Intent();
		back.setClass(InsertName.this, Information.class);
		startActivity(back);
		InsertName.this.finish();
	}
	public void todb(){
		int no = 0;
		Bundle extra = this.getIntent().getExtras();
		QQ = new SQLite(InsertName.this,"data",null,1);
		String oppname = extra.getString("oppname");
		String year = Integer.toString(extra.getInt("year"));
		String month = Integer.toString(extra.getInt("month"));
		String day = Integer.toString(extra.getInt ("day"));
		String recorder = extra.getString("recorder");
		int hour = extra.getInt("hour");
		int min = extra.getInt("min");
		tableName.append(oppname).append(year).append(month).append(day).append(Integer.toString(hour)).append(Integer.toString(min));
		QQ.createTable(tableName.toString());
		if(i>=5){
			for(no=0;no<i;no++){
				QQ.addplayer(namess[no],numberss[no] , tableName.toString(), recorder, extra.getInt("month"), extra.getInt("day"),extra.getInt("year"),hour,min,oppname);
			}
		}
		else{
			Toast.makeText(InsertName.this, "人數未滿五人！！", Toast.LENGTH_LONG).show();
		}
		QQ.close();
	}
}
