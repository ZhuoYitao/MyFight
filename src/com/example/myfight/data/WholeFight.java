package com.example.myfight.data;

import java.util.ArrayList;

import android.sax.EndElementListener;
import android.util.Log;

/**
 * 
 * ������¼������ս������Ҫ����
 *
 */
public class WholeFight {
	private Hero hero;
	private Monster monster;
	private int whole_size;
	private ArrayList<OneAttack> attacks;
	private int fight_result;
	private static int hero_win = 1;
	private static int monster_win = 2;
	private int ex_got;
	public static int h2m = 1;
	public static int m2h = 2;
	public static int end = 3;
	
	public WholeFight(Hero hero, Monster monster){
		// Log.w("My", "Ӣ��"+hero.getName()+"��ʼ��"+monster.getName());
		this.hero = hero;
		this.monster = monster;
		// ʹ�ñ���֮ǰ������ȸ�������ֵ������������
		this.attacks = new ArrayList<OneAttack>();
		// ���캯���в�Ҫ��ѭ��������������
	}
	
	/**
	 * ���������ս����ÿ�ι��������ݣ���Ҫ��������Ϣ��
	 */
	public void runWholeFight(){
		Log.w("My", "��ʼ���㱾��ս��������");
		int type = h2m;
		int harm;
		int old_level;
		OneAttack one_attack;
		String description;
		while(type!=end){
			// Log.w("My", "��ʼ�����µ�OneAttackʵ��");
			one_attack = new OneAttack();
			// Log.w("My", "�������µ�OneAttackʵ��");
			// ������ʼ֮ǰ��¼��������
			one_attack.setAttackType(type);
			if(type==h2m){
				harm = hero.getAttack()-monster.getDefence();
				if(harm<0){harm=0;}
				description = "��ԶԷ������"+harm+"���˺�";
				monster.setHP(monster.getHP()-harm);
				// Log.w("My", "���޵�Ѫ��������"+harm);
				// �����������޸Ĺ�������
				type = m2h;
				if(monster.getHP()<=0){
					description += "\n��սʤ�˶Է��������"+monster.getEx()+"�㾭��";
					old_level = hero.getLevel();
					hero.setCurEx(hero.getCurEx()+monster.getEx());
					if(old_level<hero.getLevel()){description += "\n�������ˣ�";}
					type = end;
					fight_result = hero_win;
				}
			}else{
				harm = monster.getAttack()-hero.getDefence();
				if(harm<0){harm=0;}
				description = "�Է����������"+harm+"���˺�";
				hero.setHP(hero.getHP()-harm);
				Log.w("My", "Ӣ�۵�Ѫ��������"+harm);
				type = h2m;
				if(hero.getHP()<=0){
					description += "\n�㱻�Է������";
					type = end;
					fight_result = monster_win;
				}
			}
			one_attack.setDescription(description);	
			one_attack.setCurrentHeroHP(hero.getHP());
			attacks.add(one_attack);
			// Log.w("My", "��ǰ��typeΪ��"+type);
		}
	}
	
	/**
	 * ���ر���ս�����ܹ�����������������˫���ģ�
	 */
	public int getSize(){
		return this.attacks.size();
	}
	
	/**
	 * ���ر���ս�������Ĺ���
	 */
	public Monster getMonster(){
		return this.monster;
	}
	
	public ArrayList<OneAttack> getAttacks(){
		return this.attacks;
	}
	
	public OneAttack getAttack(int index){
		return this.attacks.get(index);
	}
}
