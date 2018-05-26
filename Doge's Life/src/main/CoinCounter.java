package main;

import java.util.ArrayList;

public class CoinCounter {

	private ArrayList<Coin> coins;
	private ArrayList<Coin> add;
	private ArrayList<Coin> remove;
	
	public CoinCounter(){
		coins = new ArrayList<>();
		add = new ArrayList<>();
		remove = new ArrayList<>();
	}
	
	public Coin getCoint(int index){
		return coins.get(index);
	}
	
	public void remove(Coin obj){
		remove.add(obj);
	}
	
	public void add(Coin c){
		add.add(c);
	}
	
	public void refactor(){
		for(Coin c: remove){
			coins.remove(c);
		}
		for(Coin c : add){
			coins.add(c);
		}
		add = new ArrayList<>();
		remove = new ArrayList<>();
	}

	public ArrayList<Coin> getCoins() {
		return coins;
	}
}
