package nebo;

import java.util.ArrayList;

public class Zvezde {

	private ArrayList<Zvezda> zvezde;
	private ArrayList<Zvezda> add;
	private ArrayList<Zvezda> remove;
	
	public Zvezde(){
		zvezde = new ArrayList<>();
		add = new ArrayList<>();
		remove = new ArrayList<>();
		//pocetne zvezde

	}

	public Zvezda getCoint(int index){
		return zvezde.get(index);
	}
	
	public void remove(Zvezda obj){
		remove.add(obj);
	}
	
	public void add(Zvezda c){
		add.add(c);
	}
	
	public void refactor(){
		for(Zvezda c: remove){
			zvezde.remove(c);
		}
		for(Zvezda c : add){
			zvezde.add(c);
		}
		add = new ArrayList<>();
		remove = new ArrayList<>();
	}

	public ArrayList<Zvezda> getZvezde() {
		return zvezde;
	}

}
