package com.example.myfight.data;

import java.util.ArrayList;

import android.sax.EndElementListener;
import android.util.Log;

/**
 * 
 * 这个类记录了整场战斗的重要数据
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
		// Log.w("My", "英雄"+hero.getName()+"开始打"+monster.getName());
		this.hero = hero;
		this.monster = monster;
		// 使用变量之前，务必先给变量赋值！！！！！！
		this.attacks = new ArrayList<OneAttack>();
		// 构造函数中不要放循环！！！！！！
	}
	
	/**
	 * 计算出本次战斗中每次攻击的数据（主要是描述信息）
	 */
	public void runWholeFight(){
		Log.w("My", "开始计算本次战斗的数据");
		int type = h2m;
		int harm;
		int old_level;
		OneAttack one_attack;
		String description;
		while(type!=end){
			// Log.w("My", "开始生成新的OneAttack实例");
			one_attack = new OneAttack();
			// Log.w("My", "生成了新的OneAttack实例");
			// 攻击开始之前记录攻击类型
			one_attack.setAttackType(type);
			if(type==h2m){
				harm = hero.getAttack()-monster.getDefence();
				if(harm<0){harm=0;}
				description = "你对对方造成了"+harm+"点伤害";
				monster.setHP(monster.getHP()-harm);
				// Log.w("My", "怪兽的血量减少了"+harm);
				// 攻击结束后修改攻击类型
				type = m2h;
				if(monster.getHP()<=0){
					description += "\n你战胜了对方，获得了"+monster.getEx()+"点经验";
					old_level = hero.getLevel();
					hero.setCurEx(hero.getCurEx()+monster.getEx());
					if(old_level<hero.getLevel()){description += "\n你升级了！";}
					type = end;
					fight_result = hero_win;
				}
			}else{
				harm = monster.getAttack()-hero.getDefence();
				if(harm<0){harm=0;}
				description = "对方对你造成了"+harm+"点伤害";
				hero.setHP(hero.getHP()-harm);
				Log.w("My", "英雄的血量减少了"+harm);
				type = h2m;
				if(hero.getHP()<=0){
					description += "\n你被对方打败了";
					type = end;
					fight_result = monster_win;
				}
			}
			one_attack.setDescription(description);	
			one_attack.setCurrentHeroHP(hero.getHP());
			attacks.add(one_attack);
			// Log.w("My", "当前的type为："+type);
		}
	}
	
	/**
	 * 返回本场战斗的总攻击次数（包括敌我双方的）
	 */
	public int getSize(){
		return this.attacks.size();
	}
	
	/**
	 * 返回本场战斗遇到的怪兽
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
