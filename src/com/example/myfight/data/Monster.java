package com.example.myfight.data;

import android.R.integer;

public class Monster {
	private String name;
	private int HP;
	private int attack;
	private int defence;
	private int ex;
	
	public Monster(){
		this.name = "“∞Õ√";
		this.HP = 50;
		this.attack = 15;
		this.defence = 5;
		this.ex = 10;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setHP(int HP){
		this.HP = HP;
	}
	
	public int getHP() {
		return this.HP;
	}
	
	public int getAttack(){
		return this.attack;
	}
	
	public int getDefence(){
		return this.defence;
	}
	
	public int getEx(){
		return this.ex;
	}
}
