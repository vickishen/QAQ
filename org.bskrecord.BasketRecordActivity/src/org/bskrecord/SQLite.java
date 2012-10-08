package org.bskrecord;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.EditText;

public class SQLite extends SQLiteOpenHelper {
	
	public static final String TB_NAME = "playerdata";
	public static final String NAME = "name";
	public static final String NUMBER = "number";
	public static final String PTS = "pts";
	public static final String RBS = "rbs";
	public static final String ASTS = "asts";
	public static final String STLS = "stls";
	public static final String FLS = "fls";
	public static final String TOS = "tos";
	public static final String BLKS = "blks";
	public static final String TWOAP ="twoap";
	public static final String TWOMD = "twomd";
	public static final String THREEAP = "threeap";
	public static final String THREEMD = "threemd";
	public static final String FREEAP = "freeap";
	public static final String FREEMD = "freemd";
	public static final String RECORDNAME = "recordname";
	public static final String MONTH = "month";
	public static final String DAY = "day";
	public static final String SELFPTS = "selfpts";
	public static final String OPPPTS = "opppts";
	public static final String YEAR = "year";
	public static final String OPPNAME = "oppname";
	public SQLite(Context context, String name, CursorFactory factory,int version) {
		super(context,name,factory,version);
		this.getWritableDatabase();
	}
	public void close(){
		this.getWritableDatabase().close();
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		 //db.execSQL("CREATE TABLE IF NOT EXISTS " + "FISRTTABLE "+" ( "+NAME+" TEXT NOT NULL, "+NUMBER+" TEXT NOT NULL, "+PTS+" INTEGER, "+RBS+" INTEGER, "+ASTS+" INTEGER"+");");
		 //db.execSQL("CREATE TABLE IF NOT EXISTS " + "SECONDTABLE "+" ( "+NAME+" TEXT NOT NULL, "+NUMBER+" TEXT NOT NULL, "+PTS+" INTEGER, "+RBS+" INTEGER, "+ASTS+" INTEGER"+");");
		 //db.execSQL("CREATE TABLE IF NOT EXISTS " + "THIRDTABLE "+" ( "+NAME+" TEXT NOT NULL, "+NUMBER+" TEXT NOT NULL, "+PTS+" INTEGER, "+RBS+" INTEGER, "+ASTS+" INTEGER"+");");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
		onCreate(db);
	}
	public void addplayer(String name,String number,String tableName,String recordname,int month,int day,int year,String oppname){
		ContentValues values = new ContentValues();
		values.put(SQLite.NAME, name);
		values.put(SQLite.NUMBER, number);
		values.put(SQLite.PTS,0);
		values.put(SQLite.RBS,0);
		values.put(SQLite.ASTS,0);
		values.put(SQLite.BLKS, 0);
		values.put(SQLite.STLS, 0);
		values.put(SQLite.FLS, 0);
		values.put(SQLite.TOS, 0);
		values.put(SQLite.TWOAP, 0);
		values.put(SQLite.TWOMD, 0);
		values.put(SQLite.THREEAP, 0);
		values.put(SQLite.THREEMD, 0);
		values.put(SQLite.FREEAP, 0);
		values.put(SQLite.FREEMD, 0);
		values.put(SQLite.RECORDNAME,recordname);
		values.put(SQLite.MONTH, month);
		values.put(SQLite.DAY, day);
		values.put(SQLite.SELFPTS, 0);
		values.put(SQLite.OPPPTS, 0);
		values.put(SQLite.YEAR, year);
		values.put(SQLite.OPPNAME, oppname);
		this.getWritableDatabase().insert(tableName,null, values);
		this.getWritableDatabase().close();
	}
	public void createTable(String tableName){
		this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS "
				+ tableName + " ( "
				+ NAME + " TEXT NOT NULL, "
				+ NUMBER + " TEXT NOT NULL, "
				+ RECORDNAME + " TEXT NOT NULL, "
				+ OPPNAME + " TEXT NOT NULL, "
				+ PTS + " INTEGER, "
				+ RBS + " INTEGER, "
				+ ASTS + " INTEGER, "
				+ STLS + " INTEGER, "
				+ BLKS + " INTEGER, "
				+ TOS + " INTEGER, "
				+ FLS + " INTEGER, "
				+ TWOAP + " INTEGER, "
				+ TWOMD + " INTEGER, "
				+ THREEAP + " INTEGER, "
				+ THREEMD + " INTEGER, "
				+ FREEAP + " INTEGER, "
				+ FREEMD + " INTEGER, "
				+ MONTH + " INTEGER, "
				+ DAY + " INTEGER, "
				+ SELFPTS + " INTEGER, "
				+ OPPPTS + " INTEGER, "
				+ YEAR + " INTEGER"
				+ ");");
	}
	/*public void outputData(){
		this.getWritableDatabase().execSQL(".output csie.txt"
				+ "SELECT * FROM csie;"
				+".output stdout");
	}*/
	
}
