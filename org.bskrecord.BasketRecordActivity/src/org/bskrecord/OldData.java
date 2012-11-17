package org.bskrecord;

//import android.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

public class OldData extends ListActivity {
	private  ArrayList<HashMap<String, String>> listItems;
	private ListView listView;
	private  SimpleAdapter listItemAdapter; 
	private Toast toast;
	int i=0;
	String[] name = {"","","","","","","","","",""};
	String num="";
	@Override
    public  void  onCreate(Bundle icicle) {
        super .onCreate(icicle);
        setContentView(R.layout.secondhalf);
        initListView();
        this .setListAdapter(listItemAdapter);
        listView = getListView();
        listView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@SuppressWarnings("deprecation")
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO A
				//Toast.makeText(OldData.this, "work!!", Toast.LENGTH_LONG).show();
				final StringBuilder table = new StringBuilder("");
				String opp =((TextView)arg1.findViewById(R.id.txt2)).getText().toString();
				final String time = ((TextView)arg1.findViewById(R.id.txt1)).getText().toString();
				table.append(opp);
				table.append(del(time));
				//AlertDialog dialog = new AlertDialog.Builder(OldData.this).create();
				new AlertDialog.Builder(OldData.this).setTitle("刪除記錄").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//SQLite data3 = new SQLite(Useold.this,"data",null,1);
						//SQLiteDatabase XP = data3.getWritableDatabase();
						//ContentValues values = new ContentValues();
						//EditText secondName = (EditText)dialogView.findViewById(R.id.playerName);
						
						//values.put("name",secondName.getText().toString() );
						//int change = XP.update(tableName, values, "name"+"= '"+data+"'", null);
						//SQLite data3 = new SQLite(OldData.this,"data",null,1);
						//SQLiteDatabase XP = data3.getWritableDatabase();
						
						//XP.execSQL("DROP TABLE IF EXISTS "+table.toString());
						/*if(deletedata==0){
							Toast.makeText(OldData.this, "failed!", Toast.LENGTH_LONG).show();
						}
						else{
							Toast.makeText(OldData.this, "success", Toast.LENGTH_LONG).show();
						}*/
						//XP.close();
						//dialog.cancel();
						//Toast.makeText(OldData.this, table.toString(), Toast.LENGTH_LONG).show();
						SQLite QQ = new SQLite(OldData.this,"data",null,1);
						SQLiteDatabase db = QQ.getWritableDatabase();
						db.execSQL("DROP TABLE IF EXISTS "+table.toString());
						initListView();
						setListAdapter(listItemAdapter);
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				}).show();
				
				/*dialog.setTitle("刪除比賽記錄");
				dialog.setMessage("刪除：\n"+"對手： "+opp+"\n日期： "+time+"\n");
				dialog.setButton("確定刪除", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						int k=0,end=0,times=0,start=0;
						char[] arr = time.toCharArray();
						while(k<time.length()){
							
							if(arr[k]=='/'){
								
								if(times==0){
									end=k;
									for(int f=0;f<end;f++){
										table.append(arr[f]);
									}
									times++;
								}
								else{
									for(int f=end+1;f<k;f++){
										table.append(arr[f]);
									}
									end=k;
								}
							}
								k++;
						}
						for(int d=end+1;d<time.length();d++){
							table.append(arr[d]);
						}
						SQLite QQ = new SQLite(OldData.this,"data",null,1);
						SQLiteDatabase db = QQ.getWritableDatabase();
						db.delete(table.toString(), null, null);
						initListView();
						 //long del = arg0.getItemIdAtPosition(arg2);
						//listItemAdapter.notifyDataSetChanged();
						Toast.makeText(OldData.this, table.toString(), Toast.LENGTH_SHORT).show();
					}
					
				});*/
				
				return true;
			}
        });
        
    }
	 private  void  initListView() {   
	        listItems = new  ArrayList<HashMap<String, String>>();
	        SQLite data = new SQLite(OldData.this,"data",null,1);
	        SQLiteDatabase DB = data.getWritableDatabase();
	        Cursor c = DB.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
	        
	        if(c.getCount()==0){
        		Toast.makeText(OldData.this, "no old data", Toast.LENGTH_LONG).show();
        		Intent it = new Intent();
       		 	it.setClass(OldData.this, BasketRecordActivity.class);
       		 	startActivity(it);
       		 	OldData.this.finish();
        	}
	        HashMap<String ,String> title = new HashMap<String, String>();
	        title.put("txt1", "日期");
	        title.put("txt2", "對手");
	        title.put("txt3", "對手 : 自己");
	        listItems.add(title);
	        if(c.moveToFirst()){
	        	
	        	c.moveToNext();
	        	
				while (!c.isAfterLast()) {
					String str = c.getString(0);

					//name[i]=str;
					Cursor cu = DB.query(str, new String [] {"oppname","day","month","year","hour","min","opppts","selfpts"}, null, null, null, null, null);
					cu.moveToFirst();
					//cu.moveToNext();
					//Log.d("OLDdataRecordname=", Integer.toString(indexRecordname));
					String ing = cu.getString(cu.getColumnIndexOrThrow("oppname"));
					String Month = Integer.toString(cu.getInt(cu.getColumnIndexOrThrow("month")));
					String Day = Integer.toString(cu.getInt(cu.getColumnIndexOrThrow("day")));
					String Year = Integer.toString(cu.getInt(cu.getColumnIndexOrThrow("year")));
					String hour = Integer.toString(cu.getInt(cu.getColumnIndexOrThrow("hour")));
					String min  = Integer.toString(cu.getInt(cu.getColumnIndexOrThrow("min")));
					String opppts = Integer.toString(cu.getInt(cu.getColumnIndexOrThrow("opppts")));
					String selfpts = Integer.toString(cu.getInt(cu.getColumnIndexOrThrow("selfpts")));
					HashMap<String, String> map = new  HashMap<String, String>();  
					num=Year+Month+Day+hour+min;
		            map.put( "txt1" ,Year+"/"+ Month+"/"+Day+"-"+hour+":"+min);     //文字
		            map.put( "txt2" , ing);
		            map.put("txt3", opppts+":"+selfpts);//圖片   
		            listItems.add(map);
		            //cu.close();
		            i++;
					c.moveToNext();
				}
				c.close();
				DB.close();
			}
	        else{
	        	Toast.makeText(OldData.this, "fuckyou", Toast.LENGTH_SHORT);
	        	onBackPressed();
	        }
	          
	            	        //生成適配器的Item和動態數組對應的元素   
	        listItemAdapter = new  SimpleAdapter( this ,listItems,    // listItems數據源    
	                R.layout.listitem,   //ListItem的XML佈局實現  
	                new  String[] { "txt1" , "txt2","txt3" },      //動態數組與ImageItem對應的子項         
	                new  int [ ] {R.id.txt1, R.id.txt2,R.id.txt4}       //list_item.xml佈局文件裡面的一個ImageView的ID,一個TextView的ID  
	        ); 
	}
	 protected void onListItemClick(ListView l , View v, int position , long id){
		 super.onListItemClick(l, v, position, id);
		 //toast.makeText(getApplicationContext(), ((TextView)v.findViewById(R.id.txt1)).getText().toString(), Toast.LENGTH_SHORT).show();
		 Intent InData = new Intent();
		 InData.setClass(OldData.this, Indata.class);
		 String tablename = ((TextView)v.findViewById(R.id.txt2)).getText().toString()+del(((TextView)v.findViewById(R.id.txt1)).getText().toString());
		 //String tableName = ((TextView)v.findViewById(R.id.txt2)).getText().toString()+((TextView)v.findViewById(R.id.txt1)).getText().toString();
		 //Toast.makeText(OldData.this, tablename, Toast.LENGTH_LONG).show();
		 Bundle bundle = new Bundle();
		 bundle.putString("Tablename",tablename);
		 
		 InData.putExtras(bundle);
		 startActivity(InData);
		 OldData.this.finish();
	 }
	 
	 @Override
	 public void onBackPressed(){
		 Intent it = new Intent();
		 it.setClass(OldData.this, BasketRecordActivity.class);
		 startActivity(it);
		 OldData.this.finish();
	 }
	 public String del(String str){
		 int start=0,stop=0,record=0;
		 char[] repo = str.toCharArray();
		 StringBuilder ans = new StringBuilder("");
		 for(int i=0;i<str.length();i++){
			 if(repo[i]!='/'&&repo[i]!='-'&&repo[i]!=':'){
				 ans.append(repo[i]);
			 }
		 }
		 //Toast.makeText(OldData.this, ans.toString(), Toast.LENGTH_LONG).show();
		 return ans.toString();
	 }
	 /*public void hah(View v){
		 //Button buu = (Button)findViewById(R.id.buu);
		 Intent InData = new Intent();
	    	InData.setClass(OldData.this, Indata.class);
	    	startActivity(InData); 
	 }*/
	 /*protected void create(File file,String datain){
	    	File parentPath = file.getParentFile();
	    	if (!isSDExist()){
				Toast.makeText(this, 
						"SD Not Found!", Toast.LENGTH_LONG).show();
				return;
			}
	        try {
	            if(!parentPath.exists())
	            	parentPath.mkdirs();
	            if(file.exists())
	            	file.delete();
	            OutputStream os = new FileOutputStream(file);
	            byte[] data = new byte[datain.length()];
	            //is.read(data);
	            os.write(data);
	            //tvMsg.setText(getString(R.string.saveFileTo) + file.toString());
	            //is.close();
	            os.close();  
	        } catch (IOException e) {
	            Log.e("ExternalStorageEx", e.toString());
	        }	
	        
	        String[] paths = {file.toString()};
	        callMediaScanner(paths);
		}*/
	 /*private boolean isSDExist() { 
			String state = Environment.getExternalStorageState();
			
			if (state.equals(Environment.MEDIA_MOUNTED))
				return true;
			else			
				return false;		
		}*/
	 
}
