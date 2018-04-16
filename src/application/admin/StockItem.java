package application.admin;

import java.util.ArrayList;

public class StockItem {
	int id;
	String nom;
	int quantitat;
	
	public StockItem(int id, String nom, int quantitat) {
		this.id = id;
		this.nom = nom;
		this.quantitat = quantitat;
	}
	
	public String toString() {
		return id+"- "+nom+" x"+quantitat;
	}
}

class StockCategory {
	int id;
	String nom;
	ArrayList<StockItem> productesList = new ArrayList<StockItem>();
	
	StockCategory(int id, String nom){
		this.nom = nom;
		this.id = id;
	}
	
	public String toString() {
		return nom;
	}

}
