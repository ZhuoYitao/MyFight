package com.example.myfight.activity;

import com.example.myfight.R;
import com.example.myfight.R.layout;
import com.example.myfight.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener{
	private Button menuStart;
	private Button menuContinue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		menuStart = (Button) findViewById(R.id.menu_start);
        menuContinue = (Button) findViewById(R.id.menu_continue);
        menuStart.setOnClickListener(this);
        menuContinue.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.menu_start:
			Log.w("myTest", "start button is pressed");
			showStartDialog();
			break;
		case R.id.menu_continue:
			Intent intent = new Intent(MenuActivity.this, GameActivity.class);
			intent.putExtra("NewGame", false);
			startActivity(intent);
			MenuActivity.this.finish();
			break;
		default:
			break;
		}
	}
	
	public void showStartDialog() {
		AlertDialog dialog = new Builder(this).create();
		dialog.setTitle("即将开始游戏");
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(MenuActivity.this, GameActivity.class);
				intent.putExtra("NewGame", true);
				startActivity(intent);
				MenuActivity.this.finish();
			}
		});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
}
