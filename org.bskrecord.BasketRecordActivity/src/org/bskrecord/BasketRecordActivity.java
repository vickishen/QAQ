package org.bskrecord;

//import android.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaScannerConnection;
import android.net.Uri;


public class BasketRecordActivity extends Activity {
    /** Called when the activity is first created. */
	Button newRecord;
	Button gotoNext;
	EditText[] name = new EditText[5];
	EditText[] number = new EditText[5];
	TextView dataview;
	GestureOverlayView gesture;
	GestureLibrary gestureLibrary;
	private SQLite QQ = null;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if(!gestureLibrary.load()){
        	finish();
        }
        //getMain();
    }
    /*public void getMain(){
    	newRecord = (Button)findViewById(R.id.newrecord);
    	gesture = (GestureOverlayView)findViewById(R.id.gesture);
    	gesture.addOnGesturePerformedListener( new OnGesturePerformedListener() {
    		@Override
    		public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture){
    			ArrayList<Prediction> predictions=gestureLibrary.recognize(gesture);
    			if(predictions.size()>0 && predictions.get(0).score>1.0){
    				setContentView(R.layout.second);
    			}
    		}
    	});
    }*/
    public void getNew(View v){
    	Intent haha = new Intent();
    	haha.setClass(BasketRecordActivity.this, Mainmenu.class);
    	startActivity(haha);
    	/*setContentView(R.layout.second);
    	
    	goNext();*/
    }
    public void goNext(){
    	int i = 0;
    	//gotoNext = (Button)findViewById(R.id.button1);
    	/*for(i=0;i<5;i++){
    		int getName = getResources().getIdentifier("name"+(i+1), "id", getPackageName());
        	int getNumber = getResources().getIdentifier("number"+(i+1), "id", getPackageName());
    		name[i] = (EditText)findViewById(getName);
    		number[i] = (EditText)findViewById(getNumber);
    	}*/
    }
    public void toOld(View v){
    	Intent oldData = new Intent();
    	oldData.setClass(BasketRecordActivity.this, OldData.class);
    	startActivity(oldData);
    }
    private void createdb(){
    	String dbname = "data";
    	QQ = new SQLite(BasketRecordActivity.this,dbname,null,1);
    	QQ.onCreate(QQ.getWritableDatabase());
    	assert(QQ != null);
    	
    }
    /*public void startdata(View v){
    	EditText tableData = (EditText)findViewById(R.id.tableData);
    	EditText tablemonth = (EditText)findViewById(R.id.tableMonth);
    	EditText tableday = (EditText)findViewById(R.id.tableDay);
    	String tableMonth = tablemonth.getText().toString();
    	String tableDay = tableday.getText().toString();
    	int month = Integer.parseInt(tableMonth);
    	int day = Integer.parseInt(tableDay);
    	String tableName = tableData.getText().toString();
    	createdb();
    	StringBuilder str = new StringBuilder("");
    	str.append(tableName).append(tableMonth).append(tableDay);
    	
    	
    	//insertdata(str.toString(),month,day,tableName);
    	setContentView(R.layout.third);
    	// viewData(str.toString());
    	//QQ.outputData();
    }*/
    /*public void insertdata(String tableName , int month,int day,String opp){
    	QQ.createTable(tableName);
    	
    	for(int j=0;j<5;j++){
    		QQ.addplayer(name[j].getText().toString(), number[j].getText().toString(),tableName,opp,month,day);
    	}
    	
    }*/
    /*private void viewData(String tableName){
    	Cursor c = QQ.getWritableDatabase().query(tableName, null, null, null, null, null, null);
    	StringBuilder str = new StringBuilder("");
    	if(c.moveToFirst()){
    		int indexName = c.getColumnIndexOrThrow(SQLite.NAME);
    		int indexNumber = c.getColumnIndexOrThrow(SQLite.NUMBER);
    		int indexPts = c.getColumnIndexOrThrow(SQLite.PTS);
    		int indexRbs = c.getColumnIndexOrThrow(SQLite.RBS);
    		int indexAsts = c.getColumnIndexOrThrow(SQLite.ASTS);
    		do{
    			str.append(c.getString(indexName));
    			str.append(" | ");
    			str.append(c.getString(indexNumber));
    			str.append(" | ");
    			str.append(c.getInt(indexPts));
    			str.append(" | ");
    			str.append(c.getInt(indexRbs));
    			str.append(" | ");
    			str.append(c.getInt(indexAsts));
    			str.append(" || ");
    			str.append(c.getString(0));
    			str.append(" \n");
    		}while(c.moveToNext());
    	}
    	c.close();
    	QQ.getWritableDatabase().close();
    	dataview = (TextView)findViewById(R.id.record);
    	dataview.setText(str.toString());
    	File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    	File file = new File(path,tableName+".txt");
    	create(file,str.toString());
    	try {
			File myFile = new File(path,tableName+".txt");
			myFile.createNewFile();
			FileOutputStream fOut = new FileOutputStream(myFile);
			OutputStreamWriter myOutWriter = 
									new OutputStreamWriter(fOut);
			myOutWriter.append(str.toString());
			myOutWriter.close();
			fOut.close();
			Toast.makeText(getBaseContext(),
					"Done writing SD 'mysdfile.txt'",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (QQ != null)
        {
            QQ.close();
        }
    }
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
	}
	
	private void callMediaScanner(String[] paths) {
        MediaScannerConnection.scanFile(this, paths, null,
        	new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String path, Uri uri) {
                Log.i("ExternalStorageEx", "Scanned " + path + ":");
                Log.i("ExternalStorageEx", "-> uri=" + uri); 
            }
        });		
	}*/

}