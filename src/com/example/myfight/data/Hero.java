package com.example.myfight.data;

import java.util.ArrayList;

import android.R.integer;
import android.text.GetChars;

public class Hero {
	private String name;
	private int maxHP;
	private int HP;
	private int attack;
	private int defence;
	private int HP_growth;
	private int attack_growth;
	private int defence_growth;
	private int money;
	private int level;
	private int current_ex;
	private int weapon_id;
	private int armor_id;
	
	
	public Hero(){
		name = "åÐÒ£Éú";
		maxHP = 100;
		HP = maxHP;
		HP_growth = 5;
		attack_growth = 1;
		defence_growth = 1;
		attack = 15;
		defence = 10;
		money = 100;
		level = 1;
		current_ex = 0;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setMaxHP(int maxHP){
		this.maxHP = maxHP;
	}
	
	public int getMaxHP(){
		return this.maxHP;
	}
	
	public void setHP(int HP){
		this.HP = HP;
	}
	
	public int getHP(){
		return this.HP;
	}

	public void setHPGrowth(int HP_growth){
		this.HP_growth = HP_growth;
	}
	
	public int getHPGrowth(){
		return HP_growth;
	}
	
	public void setAttack(int attack){
		this.attack = attack;
	}
	
	public int getAttack(){
		return this.attack;
	}
	
	public void setDefence(int defence){
		this.defence = defence;
	}
	
	public int getDefence(){
		return this.defence;
	}
	
	public void setAttackGrowth(int attack_growth){
		this.attack_growth = attack_growth;
	}
	
	public int getAttackGrowth(){
		return this.attack_growth;
	}
	
	public void setDefenceGrowth(int defence_growth){
		this.defence_growth = defence_growth;
	}
	
	public int getDefenceGrowth(){
		return this.defence_growth;
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	public int getMoney(){
		return this.money;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setCurEx(int current_ex){
		this.current_ex = current_ex;
		checkUpLevel();
	}
	
	public int getCurEx(){
		return this.current_ex;
	}
	
	public void setWeapon(int weapon_id){
		this.weapon_id = weapon_id;
	}
	
	public int getWeapon(){
		return this.weapon_id;
	}
	
	public void setArmor(int armor_id){
		this.armor_id = armor_id;
	}
	
	public int getArmor(){
		return this.armor_id;
	}
	
	public int upLevelEx(){
		return 10*level*level;
	}
	
	private void checkUpLevel(){
		if(this.current_ex>=this.upLevelEx()){
			this.current_ex -= this.upLevelEx();
			this.level += 1;
			this.HP += this.HP_growth;
			this.attack += this.attack_growth;
			this.defence += this.defence_growth;
		}
	}
}
