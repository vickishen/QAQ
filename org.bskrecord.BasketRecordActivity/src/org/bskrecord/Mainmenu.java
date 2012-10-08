package org.bskrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mainmenu extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondpage);
	}
	public void addname(View v){
		
		Intent add = new Intent();
		Bundle set = new Bundle();
		set.putInt("judge", 0);
		add.putExtras(set);
		add.setClass(Mainmenu.this, Information.class);
		startActivity(add);
		Mainmenu.this.finish();
	}
	public void changename(View v){
		Intent add = new Intent();
		Bundle set = new Bundle();
		set.putInt("judge", 1);
		add.putExtras(set);
		add.setClass(Mainmenu.this, Information.class);
		startActivity(add);
		Mainmenu.this.finish();
	}
	public void choosecolor(View v){
		Intent add = new Intent();
		add.setClass(Mainmenu.this, Jersey.class);
		startActivity(add);
		Mainmenu.this.finish();
	}
	public void info(View v){
		Intent add = new Intent();
		add.setClass(Mainmenu.this, Jersey.class);
		startActivity(add);
		Mainmenu.this.finish();
	}
	public void back(View v){
		Intent add = new Intent();
		add.setClass(Mainmenu.this, BasketRecordActivity.class);
		startActivity(add);
		Mainmenu.this.finish();
	}
}
