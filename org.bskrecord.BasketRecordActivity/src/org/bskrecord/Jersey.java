package org.bskrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Jersey extends Activity{
	RadioGroup change;
	RadioButton  blue,white,orange,red,green;
	int style=0;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose);
		change = (RadioGroup)findViewById(R.id.options);
		blue = (RadioButton)findViewById(R.id.chooseblue);
		white = (RadioButton)findViewById(R.id.choosewhite);
		red = (RadioButton)findViewById(R.id.choosered);
		orange = (RadioButton)findViewById(R.id.chooseorange);
		green = (RadioButton)findViewById(R.id.choosegreen);
		
	
	change.setOnCheckedChangeListener(new OnCheckedChangeListener(){
		 
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if(checkedId==blue.getId()){
				style=1;
				//Toast.makeText(main.this,"b1选中", Toast.LENGTH_LONG).show();
			}
			if(checkedId==red.getId()){
				style=2;
				//Toast.makeText(main.this,"b2选中", Toast.LENGTH_LONG).show();
			}
			if(checkedId==green.getId()){
				style=3;
				//Toast.makeText(main.this,"b3选中", Toast.LENGTH_LONG).show();
			}
			if(checkedId==white.getId()){
				style=4;
			}
			if(checkedId==orange.getId()){
				style=5;
			}

		}

    });
	}
	public void backmenu(View v){
		
		
		Toast.makeText(Jersey.this, Integer.toString(style), Toast.LENGTH_LONG);
		Bundle backsty = new Bundle();
		Intent tomenu = new Intent();
		backsty.putInt("style", style);
		tomenu.putExtras(backsty);
		tomenu.setClass(Jersey.this, Mainmenu.class);
		startActivity(tomenu);
		Jersey.this.finish();
	}
	
	
	
}
