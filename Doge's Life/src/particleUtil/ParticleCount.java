package particleUtil;

import java.util.ArrayList;

import effects.Particle;

public class ParticleCount {

	private ArrayList<Particle> coins;
	private ArrayList<Particle> add;
	private ArrayList<Particle> remove;

	public ParticleCount(){
		coins = new ArrayList<>();
		add = new ArrayList<>();
		remove = new ArrayList<>();
	}
	
	public Particle getCoinP(int index){
		return coins.get(index);
	}
	
	public void remove(Particle obj){
		remove.add(obj);
	}
	
	public void add(Particle c){
		add.add(c);
	}
	
	public void refactor(){
		for(Particle c: remove){
			coins.remove(c);
		}
		for(Particle c : add){
			coins.add(c);
		}
		add = new ArrayList<>();
		remove = new ArrayList<>();
	}

	public ArrayList<Particle> getCoinParticles() {
		return coins;
	}
}
