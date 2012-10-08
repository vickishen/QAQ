package org.bskrecord;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameRecord extends Activity{
	Cursor team = null;
	Cursor player =null;
	int twomdIndex,twoapIndex ;
	int threemdIndex,threeapIndex;
	int freemdIndex,freeapIndex;
	int ptsIndex,astIndex,rebIndex,stlIndex,flIndex,toIndex,blkIndex;
	int selfptIndex,oppIndex;
	int startX,endX,lastX;
	int startY,endY,lastY;
	int twoap,twomd,threeap,threemd,freemd,freeap;
	int rbs,asts,blks,tos,stls,fls,pts;
	int sfls=0,oppfls=0;
	SQLite qq=null;
	SQLiteDatabase data = null;
	SQLiteDatabase data2 = null;
	String table = "";
	String num = "";
	String[] Num = new String[16];
	int teampts = 0,opppts=0;
	int prestep = 0;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailpage);
		Bundle getdata = this.getIntent().getExtras();
		DisplayMetrics dm = getResources().getDisplayMetrics();
        final int screenWidth = dm.widthPixels;  
        final int screenHeight = dm.heightPixels-50;
        sfls=getdata.getInt("sfls");
        oppfls=getdata.getInt("ofls");
        Num=getdata.getStringArray("num");
		qq = new SQLite(GameRecord.this,"data",null,1);
		data = qq.getWritableDatabase();
		data2 = qq.getWritableDatabase();
		table = getdata.getString("table");
		num = getdata.getString("number");
		teampts = getdata.getInt("ourpts");
		opppts = getdata.getInt("opppts");
		String[] column = {"selfpts","opppts"};
		String selection = "number LIKE "+num;
		team = data2.query(table, column, null, null, null, null, null);
		player = data.query(table,null , selection, null, null, null, null);
		player.moveToFirst();
		TextView namenum = (TextView)findViewById(R.id.viewPlayerinfo);
		int nameIndex = player.getColumnIndexOrThrow("name");
		namenum.setText(num+"  "+player.getString(nameIndex));
		twomdIndex = player.getColumnIndexOrThrow(SQLite.TWOMD);
		twoapIndex = player.getColumnIndexOrThrow(SQLite.TWOAP);
		threemdIndex = player.getColumnIndexOrThrow(SQLite.THREEMD);
		threeapIndex = player.getColumnIndexOrThrow(SQLite.THREEAP);
		freemdIndex = player.getColumnIndexOrThrow(SQLite.FREEMD);
		freeapIndex = player.getColumnIndexOrThrow(SQLite.FREEAP);
		ptsIndex = player.getColumnIndexOrThrow(SQLite.PTS);
		astIndex = player.getColumnIndexOrThrow(SQLite.ASTS);
		rebIndex = player.getColumnIndexOrThrow(SQLite.RBS);
		stlIndex = player.getColumnIndexOrThrow(SQLite.STLS);
		flIndex = player.getColumnIndexOrThrow(SQLite.FLS);
		toIndex = player.getColumnIndexOrThrow(SQLite.TOS);
		blkIndex = player.getColumnIndexOrThrow(SQLite.BLKS);
		selfptIndex = team.getColumnIndexOrThrow(SQLite.SELFPTS);
		oppIndex = team.getColumnIndexOrThrow(SQLite.OPPPTS);
		team.moveToFirst();
		//Button twoin = (Button)findViewById(R.id.btnTwopIn);
		TextView two = (TextView)findViewById(R.id.Twop);
		TextView three = (TextView)findViewById(R.id.Threep);
		TextView free = (TextView)findViewById(R.id.Freethrow);
		final Button reb = (Button)findViewById(R.id.btnReb);
		final Button blk = (Button)findViewById(R.id.btnBlk);
		final Button ast = (Button)findViewById(R.id.btnAst);
		final Button stl = (Button)findViewById(R.id.btnStl);
		final Button to = (Button)findViewById(R.id.btnTo);
		final Button fl = (Button)findViewById(R.id.btnFoul);
		final Button twoin  = (Button)findViewById(R.id.btnTwopIn);
		final Button twoout  = (Button)findViewById(R.id.btnTwopOut);
		final Button threein  = (Button)findViewById(R.id.btnThreepIn);
		final Button threeout  = (Button)findViewById(R.id.btnThreepOut);
		final Button freein = (Button)findViewById(R.id.btnFreethrowIn);
		final Button freeout = (Button)findViewById(R.id.btnFreethrowOut);
		TextView pscore = (TextView)findViewById(R.id.playerscore);
		final ImageButton del = (ImageButton)findViewById(R.id.btnDelete);
		twomd = player.getInt(twomdIndex);
		twoap = player.getInt(twoapIndex);
		Show(twoap,twomd,two,"兩分球");
		threemd = player.getInt(threemdIndex);
		threeap = player.getInt(threeapIndex);
		Show(threeap,threemd,three,"三分球");
		freemd = player.getInt(freemdIndex);
		freeap = player.getInt(freeapIndex);
		Show(freeap,freemd,free,"罰球");
		rbs = player.getInt(rebIndex);
		Show2(rbs,reb,"籃板");
		blks = player.getInt(blkIndex);
		Show2(blks,blk,"火鍋");
		asts = player.getInt(astIndex);
		Show2(asts,ast,"助攻");
		stls = player.getInt(stlIndex);
		Show2(stls,stl,"抄截");
		tos = player.getInt(toIndex);
		Show2(tos,to,"失誤");
		fls = player.getInt(flIndex);
		Show2(fls,fl,"犯規");
		pts = player.getInt(ptsIndex);
		showscore(pts,pscore);
		/*twoin.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v,MotionEvent event){
				int ea = event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,twoin,0);
				return df;
			}
		});
		twoout.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v,MotionEvent event){
				int ea = event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,twoout,0);
				return df;
			}
		});
		threein.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v,MotionEvent event){
				int ea = event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,threein,0);
				return df;
			}
		});
		threeout.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v,MotionEvent event){
				int ea = event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,threeout,0);
				return df;
			}
		});
		freein.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v,MotionEvent event){
				int ea = event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,freein,0);
				return df;
			}
		});
		freeout.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v,MotionEvent event){
				int ea = event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,freeout,0);
				return df;
			}
		});
		blk.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event){
				int ea=event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,blk,0);
				return df;
			}
		});
		ast.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event){
				int ea=event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,ast,0);
				return df;
			}
		});
		stl.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event){
				int ea=event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,stl,0);
				return df;
			}
		});
		to.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event){
				int ea=event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,to,0);
				return df;
			}
		});
		reb.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event){
				int ea=event.getAction();
				boolean df = func(ea,event,v,screenWidth,screenHeight,del,reb,0);
				return df;
			}
		});
		fl.setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v,MotionEvent event){
				int ea=event.getAction();
				boolean df= func(ea,event,v,screenWidth,screenHeight,del,fl,0);
				return df;
			}
		});*/
		
		/*Button twoout = (Button)findViewById(R.id.btnTwopOut);
		Button threein = (Button)findViewById(R.id.btnThreepIn);
		Button threeout = (Button)findViewById(R.id.btnThreepOut);
		Button freein = (Button)findViewById(R.id.btnFreethrowIn);
		Button freeout = (Button)findViewById(R.id.btnFreethrowOut);
		Button reb = (Button)findViewById(R.id.btnReb);*/
		
	}
	public void bfuncTwopIn(View v){
		bfuncTwopInin(1);
	}
	public void bfuncTwopOut(View v){
		bfuncTwopOutin(1);
	}
	public void bfuncThreepIn(View v){
		bfuncThreepInin(1);
	}
	public void bfuncThreepOut(View v){
		bfuncThreepOutin(1);
	}
	public void bfuncFreethrowIn(View v){
		bfuncFreethrowInin(1);
	}
	public void bfuncFreethrowOut(View v){
		bfuncFreethrowOutin(1);
	}
	public void bfuncReb(View v){
		bfuncRebin(1);
	}
	public void bfuncBlk(View v){
		bfuncBlkin(1);
	}
	public void bfuncStl(View v){
		bfuncStlin(1);
	}
	public void bfuncTo(View v){
		bfuncToin(1);
	}
	public void bfuncFoul(View v){
		bfuncFoulin(1);
	}
	public void bfuncAst(View v){
		bfuncAstin(1);
	}
	public void bfuncTwopInin(int flag){
		TextView two = (TextView)findViewById(R.id.Twop);
		final TextView pscore = (TextView)findViewById(R.id.playerscore);
		int selfpt = team.getInt(selfptIndex);
		if(flag==1){
			twomd++;
			twoap++;
			pts = pts + 2;
			selfpt = selfpt + 2;
			teampts+=2;
		}
		else{
			if(pts>0&&twomd>0&&twoap>0){
				twomd--;
				twoap--;
				pts=pts-2;
				selfpt=selfpt-2;
				teampts-=2;
			}
		}
		ContentValues teamV = new ContentValues();
		teamV.put(SQLite.SELFPTS, selfpt);
		data2.update(table, teamV, null, null);
		ContentValues values = new ContentValues();
		values.put(SQLite.TWOAP, twoap);
		values.put(SQLite.TWOMD, twomd);
		values.put(SQLite.PTS,pts);
		data.update(table, values, "number = "+num, null);
		Show(twoap,twomd,two,"兩分球");
		showscore(pts,pscore);
		prestep =1;
		//goBack();
	}
	public void bfuncTwopOutin(int flag){
		TextView two = (TextView)findViewById(R.id.Twop);
		final TextView pscore = (TextView)findViewById(R.id.playerscore);
		if(flag==1){
			twoap++;
		}
		else{
			if(twoap>0&&(twomd!=twoap)){
			twoap--;
			}
		}
		Show(twoap,twomd,two,"兩分球");
		showscore(pts,pscore);
		ContentValues values = new ContentValues();
		values.put(SQLite.TWOAP, twoap);
		data.update(table, values, "number = "+num, null);
		//goBack();
		prestep=2;
	}
	public void bfuncThreepInin(int flag){
		TextView three = (TextView)findViewById(R.id.Threep);
		final TextView pscore = (TextView)findViewById(R.id.playerscore);
		int selfpt = team.getInt(selfptIndex);
		if(flag==1){
			pts =pts + 3;
			threemd++;
			threeap++;
			selfpt = selfpt + 3;
			teampts+=3;
		}
		else{
			if(pts>0&&threemd>0&&threeap>0){
			pts =pts -3;
			threemd--;
			threeap--;
			selfpt = selfpt - 3;
			teampts-=3;
			}
		}
		Show(threeap,threemd,three,"三分球");
		showscore(pts,pscore);
		ContentValues values = new ContentValues();
		values.put(SQLite.THREEAP, threeap);
		values.put(SQLite.THREEMD, threemd);
		values.put(SQLite.PTS, pts);
		data.update(table, values, "number = "+num, null);
		ContentValues teamV = new ContentValues();
		teamV.put(SQLite.SELFPTS, selfpt);
		data2.update(table, teamV, null, null);
		//goBack();
		prestep=3;
	}
	public void bfuncThreepOutin(int flag){
		TextView three = (TextView)findViewById(R.id.Threep);
		final TextView pscore = (TextView)findViewById(R.id.playerscore);
		if(flag==1)
			threeap++;
		else{
			if(threeap>0&&(threeap!=threemd)){
			threeap--;
			}
		}
		Show(threeap,threemd,three,"三分球");
		showscore(pts,pscore);
		ContentValues values = new ContentValues();
		values.put(SQLite.THREEAP, threeap);
		data.update(table, values, "number = "+num, null);
		//goBack();
		prestep=4;
	}
	public void bfuncFreethrowInin(int flag){
		TextView free = (TextView)findViewById(R.id.Freethrow);
		final TextView pscore = (TextView)findViewById(R.id.playerscore);
		int selfpt = team.getInt(selfptIndex);
		if(flag==1){
			pts++;
			freemd++;
			freeap++;
			selfpt++;
			teampts++;
		}
		else{
			if(pts>0&&freemd>0&&freeap>0){
			pts--;
			freemd--;
			freeap--;
			selfpt--;
			teampts--;
			}
		}
		Show(freeap,freemd,free,"罰球");
		showscore(pts,pscore);
		ContentValues values = new ContentValues();
		values.put(SQLite.FREEMD, freemd);
		values.put(SQLite.FREEAP, freeap);
		values.put(SQLite.PTS, pts);
		data.update(table, values, "number = "+num, null);
		ContentValues teamV = new ContentValues();
		teamV.put(SQLite.SELFPTS, selfpt);
		data2.update(table, teamV, null, null);
		//goBack();
		prestep=5;
	}
	public void bfuncFreethrowOutin(int flag){
		TextView free = (TextView)findViewById(R.id.Freethrow);
		final TextView pscore = (TextView)findViewById(R.id.playerscore);
		//int ap = player.getInt(freeapIndex);
		if(flag==1){
		freeap++;
		}
		else{
			if(freeap>0&&(freeap!=freemd)){
			freeap--;
			}
		}
		int md = player.getInt(freemdIndex);
		Show(freeap,freemd,free,"罰球");
		showscore(pts,pscore);
		ContentValues values = new ContentValues();
		values.put(SQLite.FREEAP, freeap);
		data.update(table, values, "number = "+num, null);
		//goBack();
		prestep=6;
	}
	public void bfuncRebin(int flag){
		Button rebs = (Button)findViewById(R.id.btnReb);
		//int reb = player.getInt(rebIndex);
		if(flag==1)
		rbs++;
		else{
			if(rbs>0){
			rbs--;
			}
		}	
		Show2(rbs,rebs,"籃板");
		ContentValues values = new ContentValues();
		values.put(SQLite.RBS, rbs);
		data.update(table, values, "number = "+num, null);
		//goBack();
		prestep=7;
	}
	public void bfuncAstin(int flag){
		Button ast = (Button)findViewById(R.id.btnAst);
		//int ast = player.getInt(astIndex);
		if(flag==1)
		asts++;
		else{
			if(asts>0)
			asts--;
		}
		Show2(asts,ast,"助攻");
		ContentValues values = new ContentValues();
		values.put(SQLite.ASTS, asts);
		data.update(table, values, "number = "+num, null);
		//goBack();
		prestep=8;
	}
	public void bfuncStlin(int flag){
		Button stl = (Button)findViewById(R.id.btnStl);
		//int stl = player.getInt(stlIndex);
		if(flag==1)
		stls++;
		else{
			if(stls>0)
			stls--;
		}
		Show2(stls,stl,"抄截");
		ContentValues values = new ContentValues();
		values.put(SQLite.STLS, stls);
		data.update(table, values, "number = "+num, null);
		//goBack();
		prestep=9;
	}
	public void bfuncBlkin(int flag){
		Button blk = (Button)findViewById(R.id.btnBlk);
		//int blk = player.getInt(blkIndex);
		if(flag==1)
		blks++;
		else{
			if(blks>0)
			blks--;
		}
		Show2(blks,blk,"火鍋");
		ContentValues values = new ContentValues();
		values.put(SQLite.BLKS, blks);
		data.update(table, values,"number = "+num, null);
		//goBack();
		prestep=10;
	}
	public void bfuncToin(int flag){
		Button to = (Button)findViewById(R.id.btnTo);
		//int to = player.getInt(toIndex);
		if(flag==1)
		tos++;
		else{
			if(tos>0)
			tos--;
		}
		Show2(tos,to,"失誤");
		ContentValues values = new ContentValues();
		values.put(SQLite.TOS, tos);
		data.update(table, values, "number = "+num, null);
		//goBack();
		prestep=11;
	}
	public void bfuncFoulin(int flag){
		Button fl = (Button)findViewById(R.id.btnFoul);
		int fls = player.getInt(flIndex);
		if(flag==1){
		fls++;
		//sfls++;
		}
		else{
			if(fls>0){
			fls--;
			//sfls--;
			}
		}
		Show2(fls,fl,"犯規");
		if(fls==4){
			Toast.makeText(GameRecord.this, "Wacth out!! 4th Personal Foul", Toast.LENGTH_SHORT);
		}
		if(fls>5){
			Toast.makeText(GameRecord.this, "already 5th Personal Foul", Toast.LENGTH_LONG);
			fls=5;
		}
		ContentValues values = new ContentValues();
		values.put(SQLite.FLS, fls);
		data.update(table, values, "number = "+num, null);
		//goBack();
		prestep=12;
	}
	public void bfuncDelete(View v){
		switch(prestep){
		case 1:
			bfuncTwopInin(0);
			break;
		case 2:
			bfuncTwopOutin(0);
			
			break;
		case 3:
			bfuncThreepInin(0);
			
			break;
		case 4:
			bfuncThreepOutin(0);
			
			break;
		case 5:
			bfuncFreethrowInin(0);
			
			break;
		case 6:
			bfuncFreethrowOutin(0);
			
			break;
		case 7:
			bfuncRebin(0);
			break;
		case 8:
			bfuncAstin(0);
			break;
		case 9:
			bfuncStlin(0);
			break;
		case 10:
			bfuncBlkin(0);
			break;
		case 11:
			bfuncToin(0);
			break;
		case 12:
			bfuncFoulin(0);
			break;
		}
		
	}
	public void goBack(){
		Bundle tableback = new Bundle();
		tableback.putString("table", table);
		//tableback.putString("number", number);
		tableback.putStringArray("num", Num);
		tableback.putInt("sfls", sfls);
		tableback.putInt("ofls", oppfls);
		tableback.putInt("ourpts", teampts);
		tableback.putInt("opppts", opppts);
		Intent back = new Intent();
		back.putExtras(tableback);
		back.setClass(GameRecord.this, Recording.class);
		startActivity(back);
		GameRecord.this.finish();
		data.close();
		data2.close();
		player.close();
		team.close();
	}
	public void Show(int ap,int md,TextView tv,String str){
		tv.setText(str+" "+md+"/"+ap);
	}
	public void Show2(int data,Button tv,String str){
		tv.setText(str+" "+data);
	}
	public int getBtn(Button bu){
		final Button reb = (Button)findViewById(R.id.btnReb);
		final Button blk = (Button)findViewById(R.id.btnBlk);
		final Button ast = (Button)findViewById(R.id.btnAst);
		final Button stl = (Button)findViewById(R.id.btnStl);
		final Button to = (Button)findViewById(R.id.btnTo);
		final Button fl = (Button)findViewById(R.id.btnFoul);
		final Button twoin = (Button)findViewById(R.id.btnTwopIn);
		final Button twoout = (Button)findViewById(R.id.btnTwopOut);
		final Button threein = (Button)findViewById(R.id.btnThreepIn);
		final Button threeout = (Button)findViewById(R.id.btnThreepOut);
		final Button freein = (Button)findViewById(R.id.btnFreethrowIn);
		final Button freeout = (Button)findViewById(R.id.btnFreethrowOut);
		if(bu==reb)
			return 1;
		else if(bu==blk)
			return 2;
		else if(bu==ast)
			return 3;
		else if(bu==stl)
			return 4;
		else if(bu==to)
			return 5;
		else if(bu==fl)
			return 6;
		else if(bu==twoin)
			return 7;
		else if(bu==twoout)
			return 8;
		else if(bu==threein)
			return 9;
		else if(bu==threeout)
			return 10;
		else if(bu==freein)
			return 11;
		else if(bu==freeout)
			return 12;
		else
			return 0;
		
	}
	 /*public boolean func(int ea,MotionEvent event,View v,int screenWidth,int screenHeight,ImageButton btn,Button bu,int flag){
    	
	   	 switch(ea){  
	        case MotionEvent.ACTION_DOWN:             
	         startX = (int)event.getRawX();
	         startY = (int)event.getRawY();
	         lastX=(int)event.getRawX();
	         lastY=(int)event.getRawY();
	         //tempX = (int)bu.getLeft();
	         //tempY = (int)bu.getTop();
	         //tempX2 = (int)bu2.getLeft();
	         //tempY2 = (int)bu2.getTop();
	         
	         break;  
	         
	        case MotionEvent.ACTION_MOVE:  
	         int dx=(int)event.getRawX()-lastX;  
	         int dy=(int)event.getRawY()-lastY;             
	         
	         
	         int l=v.getLeft()+dx;   
	         int b=v.getBottom()+dy;  
	         int r=v.getRight()+dx;  
	         int t=v.getTop()+dy;  


	         if(l<0){  
	          l=0;      
	          r=l+v.getWidth();  
	         }  
	           
	         if(t<0){  
	          t=0;  
	          b=t+v.getHeight();  
	         }  
	           
	         if(r>screenWidth){  
	          r=screenWidth;  
	          l=r-v.getWidth();  
	         }  
	          
	         if(b>screenHeight){  
	          b=screenHeight;  
	          t=b-v.getHeight();  
	         }  
	         v.layout(l, t, r, b);  
	           
	         lastX=(int)event.getRawX();  
	         lastY=(int)event.getRawY();  
	         v.postInvalidate();             
	         break;  
	        case MotionEvent.ACTION_UP:
	        	endX=(int)event.getRawX();
	        	endY = (int)event.getRawY();
	        	lastX=(int)event.getRawX();  
	            lastY=(int)event.getRawY();
	        	if(startX == endX&&startY==endY){
	        		int judge = getBtn(bu);
	        		set(judge,1,bu);
	        	}
	        	else{
	        		int dd = getBtn(bu);
		            int midX,midY;
		            midX=(int)bu.getLeft()+(bu.getWidth())/2;
		            midY=(int)bu.getTop()+(bu.getHeight())/2;
		           int delX,delY,delX2,delY2;
		           delX=(int)btn.getLeft();
		           delY=(int)btn.getTop();
		           delX2 = (int)(delX+btn.getMeasuredWidth());
		           delY2=(int)(delY+btn.getMeasuredHeight());
		           if(delX<=midX&&delY<=midY){
		        	   if(delX2>=midX&&delY2>=midY){
		        		   set(dd,0,bu);
		        	   }
		           }
	        	}
	         break;            
	        }
	   	 return false;
	   }*/
	/*public void set(int judge,int flag,Button bu){
		switch(judge){
		case 1:
			bfuncReb(bu,flag);
			break;
		case 2:
			bfuncBlk(bu,flag);
			break;
		case 3:
			bfuncAst(bu,flag);
			break;
		case 4:
			bfuncStl(bu,flag);
			break;
		case 5:
			bfuncTo(bu,flag);
			break;
		case 6:
			bfuncFoul(bu,flag);
			break;
		case 7:
			bfuncTwopIn(bu,flag);
			break;
		case 8:
			bfuncTwopOut(bu,flag);
			break;
		case 9:
			bfuncThreepIn(bu,flag);
			break;
		case 10:
			bfuncThreepOut(bu,flag);
			break;
		case 11:
			bfuncFreethrowIn(bu,flag);
			break;
		case 12:
			bfuncFreethrowOut(bu,flag);
			break;
		}
	}*/
	public void showscore(int pt,TextView view){
		view.setText(Integer.toString(pt));
	}
	@Override
	public void onBackPressed(){
		goBack();
	}
}
