package com.example.myfight.data;

import java.util.Stack;

import javax.security.auth.PrivateCredentialPermission;

import android.R.integer;

public class OneAttack {
	private int attack_type;
	private int harm;
	private int current_hero_HP;
	private int current_monster_HP;
	private String decription;
	
	public OneAttack(){
		this.decription = "";
	}
	
	public void setAttackType(int attack_type){
		this.attack_type = attack_type;
	}
	
	public int getAttackType(){
		return this.attack_type;
	}
	
	public void setHarm(int harm){
		this.harm = harm;
	}
	
	public int getHarm(){
		return this.harm;
	}
	
	public void setCurrentHeroHP(int current_hero_HP){
		this.current_hero_HP = current_hero_HP;
	}
	
	public int getCurrentHeroHP(){
		return this.current_hero_HP;
	}
	
	public void setCurrentMonsterHP(int current_monster_HP){
		this.current_monster_HP = current_monster_HP;
	}
	
	public int getCurrentMonsterHP(){
		return this.current_monster_HP;
	}
	
	public void setDescription(String description){
		this.decription = description;
	}
	
	public String getDescription(){
		return decription;
	}
}
