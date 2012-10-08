package org.bskrecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.GestureLibraries;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Indata extends ListActivity{
	private ListView list;
	private  SimpleAdapter ItemAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth);
        findViews();
    }
	public void findViews(){
		Bundle bundle = this.getIntent().getExtras();
		
		SQLite data2 = new SQLite(Indata.this,"data",null,1);
		SQLiteDatabase dbname = data2.getWritableDatabase();
		Cursor cur = dbname.query(bundle.getString("Tablename"), null, null, null, null, null, null);
		StringBuilder allData = new StringBuilder();
		allData.append(String.format("%9s "+"/"+ "%s %2s %2s %2s %2s %2s %2s %2s %s/%s %2s %s/%s %2s %s/%s %2s\n","姓名","背號","得分","籃板","助攻","抄截","火鍋","失誤","犯規","二中","二投","二命","三中","三投","三命","罰中","罰投","罰命") );
		
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		if(cur.moveToFirst()){
			int indexName = cur.getColumnIndexOrThrow(SQLite.NAME);
    		int indexNumber = cur.getColumnIndexOrThrow(SQLite.NUMBER);
    		int indexPts = cur.getColumnIndexOrThrow(SQLite.PTS);
    		int indexRbs = cur.getColumnIndexOrThrow(SQLite.RBS);
    		int indexAsts = cur.getColumnIndexOrThrow(SQLite.ASTS);
    		int indexRecordname = cur.getColumnIndexOrThrow(SQLite.RECORDNAME);
    		int indexStls = cur.getColumnIndexOrThrow(SQLite.STLS);
    		int indexBlks = cur.getColumnIndexOrThrow(SQLite.BLKS);
    		int indexTos = cur.getColumnIndexOrThrow(SQLite.TOS);
    		int indexFls = cur.getColumnIndexOrThrow(SQLite.FLS);
    		int indexTwoAp = cur.getColumnIndexOrThrow(SQLite.TWOAP);
    		int indexTwoMd = cur.getColumnIndexOrThrow(SQLite.TWOMD);
    		int indexThreeAp = cur.getColumnIndexOrThrow(SQLite.THREEAP);
    		int indexThreeMd = cur.getColumnIndexOrThrow(SQLite.THREEMD);
    		int indexFreeAp = cur.getColumnIndexOrThrow(SQLite.FREEAP);
    		int indexFreeMd = cur.getColumnIndexOrThrow(SQLite.FREEMD);
    		
    		 String[] line ={"","","","","","","","","","","","","",""};
	            line[0]=String.format("%10s "+"/"+" %2s", "Tony","13");
	            line[1]=String.format(" %3d", 25);
	            line[2]=String.format(" %3d", 6);
	            line[3]=String.format(" %3d", 12);
	            line[4]=String.format(" %3d", 3);
	            line[5]=String.format(" %3d", 1);
	            line[6]=String.format(" %3d", 2);
	            line[7]=String.format(" %3d", 4);
	            line[8]=String.format(" %2d"+"/"+"%2d", 10,20);
	            line[9]=String.format(" %3s"+"%%", "50");
	            line[10]=String.format(" %2d"+"/"+"%2d", 1,5);
	            line[11]=String.format(" %3s"+"%%", "20");
	            line[12]=String.format(" %2d"+"/"+"%2d", 2,2);
	            line[13]=String.format(" %3s"+"%%", "100");
	            for(int g = 0;g<14;g++){
	            	allData.append(line[g]);
	            }
	            allData.append("\n");
	            HashMap<String, String> title = new HashMap<String, String>();
	    		title.put( "txt7" , "Name/Number");     //文字
	            title.put( "txt8" , "得分");
	            title.put("txt9", "籃板");
	            title.put("txt10", "助攻");
	            title.put("txt11", "抄截");
	            title.put("txt12", "火鍋");
	            title.put("txt13", "失誤");
	            title.put("txt14", "犯規");
	            title.put("txt15", "兩分中/兩分出手");
	            title.put("txt16", "命中率 (%)");
	            title.put("txt17", "三分中/三分出手");
	            title.put("txt18", "命中率 (%)");
	            title.put("txt19", "罰球中/罰球出手");
	            title.put("txt20", "命中率 (%)");
	            list.add(title);
			while (!cur.isAfterLast()) {
				String name = cur.getString(indexName);
				String number = cur.getString(indexNumber);
				String recordname = cur.getString(indexRecordname);
				int pts = cur.getInt(indexPts);
				int rbs = cur.getInt(indexRbs);
				int asts = cur.getInt(indexAsts);
				int stls = cur.getInt(indexStls);
				int blks = cur.getInt(indexBlks);
				int tos = cur.getInt(indexTos);
				int fls = cur.getInt(indexFls);
				int twoAp = cur.getInt(indexTwoAp);
				int twoMd = cur.getInt(indexTwoMd);
				int threeAp = cur.getInt(indexThreeAp);
				int threeMd = cur.getInt(indexThreeMd);
				int freeAp = cur.getInt(indexFreeAp);
				int freeMd = cur.getInt(indexFreeMd);
				
				HashMap<String, String> map = new  HashMap<String, String>();
	            map.put( "txt7" , name+"/"+number);     //文字
	            map.put( "txt8" , String.format("%2d", pts));
	            map.put("txt9", String.format("%2d", rbs));
	            map.put("txt10", String.format("%2d",asts));
	            map.put("txt11", String.format("%2d",stls));
	            map.put("txt12", String.format("%2d", blks));
	            map.put("txt13", String.format("%2d", tos));
	            map.put("txt14", String.format("%2d", fls));
	            map.put("txt15", Integer.toString(twoMd)+"/"+Integer.toString(twoAp));
	            map.put("txt16", calculus(twoMd,twoAp)+" %");
	            map.put("txt17", Integer.toString(threeMd)+"/"+Integer.toString(threeAp));
	            map.put("txt18", calculus(threeMd,threeAp)+" #");
	            map.put("txt19", Integer.toString(freeMd)+"/"+Integer.toString(freeAp));
	            map.put("txt20", calculus(freeMd,freeAp)+" &");
	            String[] aline ={"","","","","","","","","","","","","",""};
	            aline[0]=String.format("%10s "+"/"+" %2s", name,number);
	            aline[1]=String.format(" %3d", pts);
	            aline[2]=String.format(" %3d", rbs);
	            aline[3]=String.format(" %3d", asts);
	            aline[4]=String.format(" %3d", stls);
	            aline[5]=String.format(" %3d", blks);
	            aline[6]=String.format(" %3d", tos);
	            aline[7]=String.format(" %3d", fls);
	            aline[8]=String.format(" %2d"+"/"+"%2d", twoMd,twoAp);
	            aline[9]=String.format(" %3s"+"%%", calculus(twoMd,twoAp));
	            aline[10]=String.format(" %2d"+"/"+"%2d", threeMd,threeAp);
	            aline[11]=String.format(" %3s"+"%%", calculus(threeMd,threeAp));
	            aline[12]=String.format(" %2d"+"/"+"%2d", freeMd,freeAp);
	            aline[13]=String.format(" %3s"+"%%", calculus(freeMd,freeAp));
	            for(int g = 0;g<14;g++){
	            	allData.append(aline[g]);
	            }
	            allData.append("\n");
	            list.add(map);     
				cur.moveToNext();
				
			}
			cur.close();
			dbname.close();
			//createFile(bundle.getString("Tablename"),allData.toString());
		}
		ItemAdapter = new SimpleAdapter(this,list,R.layout.listitem2,new String [] {"txt7","txt8","txt9","txt10","txt11",
				"txt12","txt13","txt14","txt15","txt16","txt17","txt18","txt19","txt20"},
				new int [] {R.id.txt7,R.id.txt8,R.id.txt9,R.id.txt10,R.id.txt11,R.id.txt12,R.id.txt13,R.id.txt14,
				R.id.txt15,R.id.txt16,R.id.txt17,R.id.txt18,R.id.txt19,R.id.txt20});
		setListAdapter(ItemAdapter);
		
	}
	public String calculus(int md,int ap){
		if(ap == 0){
			String no = "0";
			return no;
		}
		else{
			float res = (float)(md/ap);
			String ans = Float.toString(res*100);
			return ans;
		}
	}
	public void createFile(String tableName,String data){
		File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    	//create(file,str.toString());
    	try {
			File myFile = new File(path,tableName+".txt");
			myFile.createNewFile();
			FileOutputStream fOut = new FileOutputStream(myFile);
			OutputStreamWriter myOutWriter = 
									new OutputStreamWriter(fOut);
			myOutWriter.append(data);
			myOutWriter.close();
			fOut.close();
			Toast.makeText(getBaseContext(),
					"Done writing SD 'mysdfile.txt'",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}  
	}
	@Override
	public void onBackPressed(){ 
		Intent backOld = new Intent();
		backOld.setClass(Indata.this, OldData.class);
		startActivity(backOld);
		Indata.this.finish();
	}
}
