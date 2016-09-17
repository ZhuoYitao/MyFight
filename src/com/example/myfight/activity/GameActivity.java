package com.example.myfight.activity;

import com.example.myfight.R;
import com.example.myfight.R.layout;
import com.example.myfight.R.menu;
import com.example.myfight.data.Hero;
import com.example.myfight.data.Monster;
import com.example.myfight.data.OneAttack;
import com.example.myfight.data.WholeFight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class GameActivity extends Activity implements OnClickListener{
	// �����ؼ���Ӧ�ı���
	private ScrollView game_info_scroll;
	private LinearLayout game_info_linearl;
	
	private TextView root_character;
	private TextView root_equipment;
	
	private LinearLayout character_detail;
	private TextView game_level_value;
	private TextView game_HP_value;
	private TextView game_attack_value;
	private TextView game_defence_value;
	private TextView game_currentEx_value;
	private TextView game_neededEx_value;
	
	private LinearLayout equipment_detail;
	private TextView game_weapon;
	private TextView game_armor; // �ؼ��������������˽���
	
	private boolean timer_thread_running = true;
	private final int alter_time = 500;
	private boolean new_fight = true;
	private int attack_index = 0;
	private Monster monster;
	private WholeFight whole_fight;
	private float fightInfoSize = 20;
	private TextView fight_info;
	private Hero hero;
	private OneAttack one_attack;
	private int fight_info_max_number = 50;
	
	// ���ܵ���ʱ����֪ͨ������һ�ι���
	private Handler handler = new Handler(){		
		@Override
		public void handleMessage(Message msg){
			switch (msg.what) {
			case 10:
				if(new_fight){
					// ��ս�������㱾��ս������Ϣ������ʾ������Ϣ
					Log.w("MyTest", "��ʼ��һ����ս��");
					monster = new Monster();	
					fight_info = new TextView(GameActivity.this);
					fight_info.setTextSize(fightInfoSize);
					fight_info.setText("��������"+monster.getName());
					game_info_linearl.addView(fight_info);
					// Log.w("My", "����Ӣ�ۣ�"+hero.getName());
					whole_fight = new WholeFight(hero, monster);
					whole_fight.runWholeFight();
					new_fight = false;
					Log.w("MyTest", "��������һ����ս��");
				}else{
					// ս����û��������ʾ���ι�������Ϣ
					if(attack_index<whole_fight.getSize()){
						Log.w("MyTest", "��ʼ��ʾһ�ι�����Ϣ");
						one_attack = whole_fight.getAttack(attack_index);
						fight_info = new TextView(GameActivity.this);
						fight_info.setTextSize(fightInfoSize);
						fight_info.setText(one_attack.getDescription());
						game_info_linearl.addView(fight_info);
						// ����ǹ��޹�����Ӣ�ۣ������UI��Ӣ�۵�HPֵ
						if(one_attack.getAttackType()==WholeFight.m2h){
							game_HP_value.setText(one_attack.getCurrentHeroHP()+"");
						}
						Log.w("MyTest", "��ʾ����һ�ι�����Ϣ");
						attack_index += 1;
					}else{
						// ս���Ѿ����������½�ɫ״̬������ʾ��õ���Ʒ��
						new_fight = true;
						attack_index = 0;
						Log.w("MyTest", "��ʼ���½�ɫ״̬");
						hero.setHP(hero.getMaxHP());						
						game_level_value.setText(hero.getLevel()+"");
						game_HP_value.setText(hero.getMaxHP()+"");
						game_attack_value.setText(hero.getAttack()+"");
						game_defence_value.setText(hero.getDefence()+"");
						game_currentEx_value.setText(hero.getCurEx()+"");
						game_neededEx_value.setText(hero.upLevelEx()+"");
						Log.w("MyTest", "�������˽�ɫ״̬");
						// ��ʾս�������ķָ���
						fight_info = new TextView(GameActivity.this);
						fight_info.setTextSize(fightInfoSize);
						fight_info.setText("----------");
						game_info_linearl.addView(fight_info);
					}
					
				}
                // �����ʾ��̫���ս����Ϣ����ȥ����ǰ���ս������Ϣ��
                if(game_info_linearl.getChildCount()>fight_info_max_number){
                	game_info_linearl.removeViewAt(0);
                }
                // ����Ϣ��������ײ�
                scrollToBottom(game_info_scroll, game_info_linearl);
				break;
			default:
				break;
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);
		initiateWidgets();
		loadGameData();
		TimerThread timerThread = new TimerThread();
		timerThread.start();
	}
	
	@Override
	protected void onDestroy(){
		saveGameData();
		super.onDestroy();
		timer_thread_running = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	// ���R.color.current_itemʱ����ѧ���ľ��飺
	// �ǵñ����xml�ļ����޸ģ����R.java�ļ�û�Զ����£�ȡ��Ȼ������ѡ��˵�����ġ���Ŀ����ġ��Զ�������
	// Ҫѧ����Ӣ�Ĳ�������
	@Override
	public void onClick(View v) {
		Log.w("myTest", ""+v.getId()+"is clicked.");
		switch (v.getId()) {
		case R.id.root_character:
			root_character.setBackgroundResource(R.color.current_item);
			root_equipment.setBackgroundResource(R.color.transparent);
			character_detail.setVisibility(View.VISIBLE);
			equipment_detail.setVisibility(View.GONE);
			break;
		case R.id.root_equipment:
			root_equipment.setBackgroundResource(R.color.current_item);
			root_character.setBackgroundResource(R.color.transparent);
			equipment_detail.setVisibility(View.VISIBLE);
			character_detail.setVisibility(View.GONE);
			break;											
		default:
			break;
		}
		
	}
	
	/**
	 * ������Ϸ����
	 */
	private void saveGameData(){
		return ;
	}
	
	/**
	 * ������Ϸ����
	 */
	private void loadGameData(){
		hero = new Hero();
		Log.w("My", "��������Ӣ��"+hero.getName());
		initiateGameData();
	}
	/**
	 * ��ʼ����Ϸ�ؼ�
	 */
	private void initiateWidgets(){
		correlateVarAndView();
		setClickListeners();
	}
	
	//���������Ƿ���Ը�����
	/**
	 * �ѿؼ��Ͷ�Ӧ�ı�����������
	 */
	private void correlateVarAndView(){
		game_info_scroll = (ScrollView) findViewById(R.id.game_info_scroll);
		game_info_linearl = (LinearLayout) findViewById(R.id.game_info_linearl);
		
		root_character = (TextView) findViewById(R.id.root_character);
		root_equipment = (TextView) findViewById(R.id.root_equipment);
		
		character_detail = (LinearLayout) findViewById(R.id.character_detail);
		equipment_detail = (LinearLayout) findViewById(R.id.equipment_detail);
		
		game_level_value = (TextView) findViewById(R.id.game_level_value);
		game_HP_value = (TextView) findViewById(R.id.game_HP_value); 
		game_attack_value = (TextView) findViewById(R.id.game_attack_value); 
		game_defence_value = (TextView) findViewById(R.id.game_defence_value);
		game_currentEx_value = (TextView) findViewById(R.id.game_currentEx_value);
		game_neededEx_value = (TextView) findViewById(R.id.game_neededEx_value);

		game_weapon = (TextView) findViewById(R.id.game_weapon);
		game_armor = (TextView) findViewById(R.id.game_armor);
	}
	
	/**
	 * ���ÿؼ��ĵ���¼�
	 */
	private void setClickListeners(){
		root_character.setOnClickListener(this);
		root_equipment.setOnClickListener(this);
	}
	
	private void initiateGameData(){
		game_level_value.setText("1");
		game_HP_value.setText(hero.getMaxHP()+"");
		game_attack_value.setText(hero.getAttack()+"");
		game_defence_value.setText(hero.getDefence()+"");
		game_currentEx_value.setText(hero.getCurEx()+"");
		game_neededEx_value.setText(hero.upLevelEx()+"");
	}

	/**
	 * ��ʱ�����߳��࣬ÿ��alter_time�����̷߳����ź�
	 */
	private class TimerThread extends Thread{
		@Override
		public void run(){
			while(timer_thread_running){
				try{
					Thread.sleep(alter_time);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
				handler.sendEmptyMessage(10);
			}
		}
	}

	/**
	 * ��ս����Ϣ��������ײ�
	 */
	private void scrollToBottom(final View scroll, final View inner){
		Handler mHandler = new Handler();
		
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				if (scroll==null || inner==null){
					return ;
				}
				int offset = inner.getMeasuredHeight()-scroll.getHeight();
				if(offset<0){
					offset = 0;
				}
				scroll.scrollTo(0, offset);
				
			}
		});
		
	}
}
