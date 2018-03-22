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
	String nom;
	ArrayList<StockItem> productesList = new ArrayList<StockItem>();
	
	StockCategory(String nom){
		this.nom = nom;
	}
	
	public String toString() {
		return nom;
	}

}
