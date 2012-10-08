package org.bskrecord;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Recording extends Activity {
	String[] mNum = new String[6];
	String[] bNum = new String[11];
	String[] fullNum = new String[16];
	int lastX,lastY;
	int k=0,n=0;
	int[] tmpX = new int[11];
	int[] tmpY = new int[11];
	int[] tmpX2 = new int[11];
	int[] tmpY2 = new int[11];
	int[] tbX = new int[11];
	int[] tbY = new int[11];
	int[] tbX2 = new int[11];
	int[] tbY2 = new int[11];
	int startX,endX;
	int startY,endY;
	int point = 0;
	int selffl = 0,oppfl = 0;
	int opppts = 0,ourpts=0;
	int step=0;
	String str = "";
	String table = "";
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recordpage);
		final Button[] mBtn = new Button[6];
		final Button[] bBtn = new Button[11];
		final String[] fnum = {"","","","","","","","","","",""};
		Bundle getname = this.getIntent().getExtras();
		table = getname.getString("table");
		fullNum = getname.getStringArray("num");
		selffl = getname.getInt("sfls");
		oppfl = getname.getInt("ofls");
		ourpts = getname.getInt("ourpts");
		opppts = getname.getInt("opppts");
		String[] columns = {"number"};
		DisplayMetrics dm = getResources().getDisplayMetrics();
        final int screenWidth = dm.widthPixels;  
        final int screenHeight = dm.heightPixels-50;
        /*SQLite qq = new SQLite(Recording.this,"data",null,1);
        SQLiteDatabase ha = qq.getWritableDatabase();
        Cursor c = ha.query(table, columns, null, null, null, null, null);
        int numIndex = c.getColumnIndexOrThrow("number");
        c.moveToFirst();*/
		for(int i=0;i<5;i++){
			int getmName = getResources().getIdentifier("mainplayer"+(i+1), "id", getPackageName());
			mBtn[i] = (Button)findViewById(getmName);
			mNum[i] = fullNum[i];
			//mNum[i] = c.getString(numIndex);
			mBtn[i].setText(mNum[i]);
			//c.moveToNext();
		}
		for(int j=0;j<10;j++){
			/*int getbName = getResources().getIdentifier("benchplayer"+(j+1), "id", getPackageName());
			bBtn[j] = (Button)findViewById(getbName);*/
			//if(!c.isAfterLast()){
				
				bNum[j] = fullNum[j+5];
				//bBtn[j].setText(bNum[j]);
				fnum[j]=bNum[j];
				//c.moveToNext();
			//}
			//else{
				 
				// c.close();
				 //ha.close();
			//}
		}
		showopts(opppts);
		showwepts(ourpts);
		getbfoul(oppfl);
		getafoul(selffl);
		/*mBtn[0].setOnTouchListener(new Button.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event) {
				int ea=event.getAction();  
	             Log.i("TAG", "Touch:"+ea);   
	             boolean df=func(ea,event,v,screenWidth,screenHeight,bBtn,mBtn[0],1);
	             return df;  
			}
		});*/
		
        	
		
        
		
		mBtn[0].setOnLongClickListener(new Button.OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				Builder builder = new Builder(Recording.this);
				builder.setTitle("蠢干瞴");
		        builder.setItems(fnum, new DialogInterface.OnClickListener(){
		        	public void onClick(DialogInterface dialog, int which) {
		    			// TODO Auto-generated method stub
		    			str = fnum[which];
		    			getNum(mBtn[0],bBtn,fnum);
		    			
		    			//Toast.makeText(Recording.this, "fuck!!!", Toast.LENGTH_LONG).show();
		    			}
		           });
		        final AlertDialog mutiItemDialog = builder.create();
		        mutiItemDialog.show();	
				return false;
		        }
        });
		mBtn[0].setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				toBtnpage(mBtn[0].getText().toString());
			}
		});

		mBtn[1].setOnLongClickListener(new Button.OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				Builder builder = new Builder(Recording.this);
				builder.setTitle("蠢干瞴");
		        builder.setItems(fnum, new DialogInterface.OnClickListener(){
		        	public void onClick(DialogInterface dialog, int which) {
		    			// TODO Auto-generated method stub
		    			str = fnum[which];
		    			getNum(mBtn[1],bBtn,fnum);
		    			
		    			//Toast.makeText(Recording.this, "fuck!!!", Toast.LENGTH_LONG).show();
		    			}
		           });
		        final AlertDialog mutiItemDialog = builder.create();
		        mutiItemDialog.show();	
				return false;
		        }
        });
		mBtn[1].setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				toBtnpage(mBtn[1].getText().toString());
			}
		});
		mBtn[2].setOnLongClickListener(new Button.OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				Builder builder = new Builder(Recording.this);
				builder.setTitle("蠢干瞴");
		        builder.setItems(fnum, new DialogInterface.OnClickListener(){
		        	public void onClick(DialogInterface dialog, int which) {
		    			// TODO Auto-generated method stub
		    			str = fnum[which];
		    			getNum(mBtn[2],bBtn,fnum);
		    			
		    			//Toast.makeText(Recording.this, "fuck!!!", Toast.LENGTH_LONG).show();
		    			}
		           });
		        final AlertDialog mutiItemDialog = builder.create();
		        mutiItemDialog.show();	
				return false;
		        }
        });
		
		mBtn[2].setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				toBtnpage(mBtn[2].getText().toString());
			}
		});
		mBtn[3].setOnLongClickListener(new Button.OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				Builder builder = new Builder(Recording.this);
				builder.setTitle("蠢干瞴");
		        builder.setItems(fnum, new DialogInterface.OnClickListener(){
		        	public void onClick(DialogInterface dialog, int which) {
		    			// TODO Auto-generated method stub
		    			str = fnum[which];
		    			getNum(mBtn[3],bBtn,fnum);
		    			
		    			//Toast.makeText(Recording.this, "fuck!!!", Toast.LENGTH_LONG).show();
		    			}
		           });
		        final AlertDialog mutiItemDialog = builder.create();
		        mutiItemDialog.show();	
				return false;
		        }
        });
		mBtn[3].setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				toBtnpage(mBtn[3].getText().toString());
			}
		});
		mBtn[4].setOnLongClickListener(new Button.OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				Builder builder = new Builder(Recording.this);
				builder.setTitle("蠢干瞴");
		        builder.setItems(fnum, new DialogInterface.OnClickListener(){
		        	public void onClick(DialogInterface dialog, int which) {
		    			// TODO Auto-generated method stub
		    			str = fnum[which];
		    			getNum(mBtn[4],bBtn,fnum);
		    			
		    			//Toast.makeText(Recording.this, "fuck!!!", Toast.LENGTH_LONG).show();
		    			}
		           });
		        final AlertDialog mutiItemDialog = builder.create();
		        mutiItemDialog.show();	
				return false;
		        }
        });
		mBtn[4].setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				toBtnpage(mBtn[4].getText().toString());
			}
		});
		
			/*bBtn[0].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[0],0);
		             return df;  
				}
				
			});
			bBtn[1].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[1],0);
		             return df;  
				}
			});
			bBtn[2].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[2],0);
		             return df;  
				}
			});
			bBtn[3].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[3],0);
		             return df;  
				}
			});
			bBtn[4].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[4],0);
		             return df;  
				}
			});
			bBtn[5].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[5],0);
		             return df;  
				}
			});
			bBtn[6].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[6],0);
		             return df;  
				}
			});
			bBtn[7].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[7],0);
		             return df;  
				}
			});
			bBtn[8].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[8],0);
		             return df;  
				}
			});
			bBtn[9].setOnTouchListener(new Button.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					int ea=event.getAction();  
		             Log.i("TAG", "Touch:"+ea);   
		             boolean df=func(ea,event,v,screenWidth,screenHeight,mBtn,bBtn[9],0);
		             return df;  
				}
			});*/
	}
	
	/*public void monebtn(View v){
		v.setClickable(true);
		toBtnpage(mNum[0]);
	}
	public void mtwobtn(View v){
		v.setClickable(true);
		toBtnpage(mNum[1]);
	}
	public void mthreebtn(View v){
		v.setClickable(true);
		toBtnpage(mNum[2]);
	}
	public void mfourbtn(View v){
		v.setClickable(true);
		toBtnpage(mNum[3]);
	}
	public void mfivebtn(View v){
		v.setClickable(true);
		toBtnpage(mNum[4]);
	}*/
	public void undo(View v){
		if(step==1){
			opppts--;
			step=0;
			showopts(opppts);
		}
		else if(step==2){
			opppts-=2;
			step=0;
			showopts(opppts);
		}
		else if(step==3){
			opppts-=3;
			step=0;
			showopts(opppts);
		}
		else if(step==4){
			selffl--;
			step=0;
			getafoul(selffl);
		}
		else if(step==5){
			oppfl--;
			step=0;
			getbfoul(oppfl);
		}
	}
	public void Gameover(View v){
		SQLite qq = new SQLite(Recording.this,"data",null,1);
        SQLiteDatabase ha = qq.getWritableDatabase();
        String[] column = {"selfpts","opppts"};
        Cursor c = ha.query(table, column, null, null, null, null, null);
        int oppIndex = c.getColumnIndexOrThrow("opppts");
        int selfIndex = c.getColumnIndexOrThrow("selfpts");
        ContentValues dd = new ContentValues();
        dd.put(SQLite.OPPPTS, opppts);
        dd.put(SQLite.SELFPTS, ourpts);
        ha.update(table, dd, null, null);
        ha.close();
        Intent toIndata = new Intent();
        Bundle tname = new Bundle();
        tname.putString("Tablename", table);
        toIndata.putExtras(tname);
        toIndata.setClass(Recording.this, Indata.class);
        Recording.this.finish();
        //c.moveToFirst();
	}
	public void bfouladd(View v){
		oppfl++;
		step=5;
		getbfoul(oppfl);
	}
	public void afouladd(View v){
		selffl++;
		step=4;
		getafoul(selffl);
	}
	public void endquater(View v){
		oppfl=0;
		selffl=0;
		getafoul(selffl);
		getbfoul(oppfl);
	}
	public void showwepts(int pt){
		TextView ourscore = (TextView)findViewById(R.id.ourscore);
		ourscore.setText(Integer.toString(pt));
	}
	public void getbfoul(int fl){
		TextView[] bfoullight = new TextView[5];
		if(fl<=5&&fl>0){
		for(int i=0;i<fl;i++){
			int id = getResources().getIdentifier("bfoullight"+(i+1), "id", getPackageName());
			bfoullight[i] = (TextView)findViewById(id);
			bfoullight[i].setBackgroundColor(Color.RED);
		}
		for(int j=fl;j<5;j++){
			int id = getResources().getIdentifier("bfoullight"+(j+1), "id", getPackageName());
			bfoullight[j] = (TextView)findViewById(id);
			bfoullight[j].setBackgroundColor(Color.BLACK);
		}
		}
		else if(fl==0){
			for(int i=0;i<5;i++){
				int id = getResources().getIdentifier("bfoullight"+(i+1), "id", getPackageName());
				bfoullight[i] = (TextView)findViewById(id);
				bfoullight[i].setBackgroundColor(Color.BLACK);
			}
		}
		else{
			Toast.makeText(Recording.this,"デ砏笷秈籃" , Toast.LENGTH_LONG);
			
		}
	}
	public void getafoul(int fl){
		TextView[] afoullight = new TextView[5];
		if(fl<=5&&fl>0){
		for(int i=0;i<fl;i++){
			int id = getResources().getIdentifier("afoullight"+(i+1),"id",getPackageName());
			afoullight[i] = (TextView)findViewById(id);
			afoullight[i].setBackgroundColor(Color.RED);
		}
		for(int j=fl;j<5;j++){
			int id = getResources().getIdentifier("afoullight"+(j+1),"id",getPackageName());
			afoullight[j] = (TextView)findViewById(id);
			afoullight[j].setBackgroundColor(Color.BLACK);
		}
		}
		else if(fl==0){
			for(int i=0;i<5;i++){
				int id = getResources().getIdentifier("afoullight"+(i+1),"id",getPackageName());
				afoullight[i] = (TextView)findViewById(id);
				afoullight[i].setBackgroundColor(Color.BLACK);
			}
		}
		else{
			Toast.makeText(Recording.this,"デ砏笷秈籃" , Toast.LENGTH_LONG);
		}
	}
	public void optsadd(View v){
		opppts++;
		step=1;
		showopts(opppts);
	}
	public void addtw(View v){
		if(opppts>0){
		opppts+=2;
		step=2;
		}
		showopts(opppts);
	}
	public void addthr(View v){
		opppts+=3;
		step=3;
		showopts(opppts);
	}
	public void showopts(int pt){
		TextView opp = (TextView)findViewById(R.id.oppscore);
		opp.setText(Integer.toString(pt));
	}
	public void toBtnpage(String number){
		Bundle table = this.getIntent().getExtras();
		Intent btnpg = new Intent();
		Bundle getData = new Bundle();
		getData.putString("number", number);
		getData.putString("table", table.getString("table"));
		getData.putStringArray("num", fullNum);
		getData.putInt("ourpts", ourpts);
		getData.putInt("sfls", selffl);
		getData.putInt("ofls", oppfl);
		getData.putInt("opppts", opppts);
		//getData.putStringArray("mNum", mNum);
		//getData.putStringArray("bNum", bNum);
		btnpg.putExtras(getData);
		btnpg.setClass(Recording.this, GameRecord.class);
		startActivity(btnpg);
		
	}
	public void getNum(Button bu,Button[] bBtn,final String[] fnum){
		String ss = bu.getText().toString();
		String stop = "";
		for(int i =0;i<15;i++){
			if(fullNum[i]==str.toString()){
				bu.setText(str.toString());
				int y=0;
				while(fullNum[y]!=ss&&y<15){
					y++;
				}
				if(y<15){
					stop = fullNum[y];
					fullNum[y]=fullNum[i];
					fullNum[i]=stop;
				}
				//bBtn[i-5].setText(stop);
				bNum[i-5]=stop;
				fnum[i-5]=bNum[i-5];
			}
		}
	}

	/*public AlertDialog getMutiItemDialog( final String[] items) {
		
	}*/
	public void show(Button[] mBtn,Button[] bBtn,final String[] fnum){
		for(int i=0;i<5;i++){
			int getmName = getResources().getIdentifier("mainplayer"+(i+1), "id", getPackageName());
			mBtn[i] = (Button)findViewById(getmName);
			mBtn[i].setText(mNum[i]);
		}
		/*for(int j=0;j<10;j++){
			int getbName = getResources().getIdentifier("benchplayer"+(j+1), "id", getPackageName());
			bBtn[j] = (Button)findViewById(getbName);
				bBtn[j].setText(bNum[j]);
				fnum[j]=bNum[j];
		}*/
	}


	public boolean func(int ea,MotionEvent event,View v,int screenWidth,int screenHeight,Button btn[],Button bu,int flag){
    	
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
        		toBtnpage(bu.getText().toString());
        	}
        	else{
	            int midX,midY;
	            midX=(int)bu.getLeft()+(bu.getWidth())/2;
	            midY=(int)bu.getTop()+(bu.getHeight())/2;
	            int dd=0;
	            if(flag!=1){
	            	for(int h=0;h<5;h++){
	                  	 tmpX[h] = (int)btn[h].getLeft();
	                  	 tmpY[h] = (int)btn[h].getTop();
	                  	 tmpX2[h] = (int)tmpX[h]+btn[h].getMeasuredWidth();
	                  	 tmpY2[h] = (int)tmpY[h]+btn[h].getMeasuredHeight();
	                   }
	            	for(int g =0;g<5;g++){
	            		if(tmpX[g]<=midX && tmpY[g]<=midY){
	            			if(midX<=tmpX2[g] && midY<=tmpY2[g]){
	            			String str = btn[g].getText().toString();
	               	 		btn[g].setText(bu.getText( ).toString());
	               	 		bu.setText(str);
	            			}
	            			
	            		}		
	            	}
	            	flag=0;
	            }
	            else{
	            	for(int h=0;h<10;h++){
	                 	 tbX[h] = (int)btn[h].getLeft();
	                 	 tbY[h] = (int)btn[h].getTop();
	                 	 tbX2[h] = (int)tmpX[h]+btn[h].getMeasuredWidth();
	                 	 tbY2[h] = (int)tmpY[h]+btn[h].getMeasuredHeight();
	                  }
	            	for(int g =0;g<10;g++){
	            		if(tbX[g]<=midX && tbY[g]<=midY){
	            			if(midX<=tbX2[g] && midY<=tbY2[g]){
	            			String str = btn[g].getText().toString();
	               	 		btn[g].setText(bu.getText( ).toString());
	               	 		bu.setText(str);
	            			}
	            			
	            		}
	            	}
	            }
        	}
         break;            
        }
   	 return false;
   }
}
