package application.client;

public class ValoracioPlats {

	private String sValoracio;
	private double dPuntuacio;
	
	public ValoracioPlats( String sValoracio, double dPuntuacio) {
		this.setsValoracio(sValoracio);
		this.setdPuntuacio(dPuntuacio);
	}

	public String getsValoracio() {
		return sValoracio;
	}

	public void setsValoracio(String sValoracio) {
		this.sValoracio = sValoracio;
	}

	public double getdPuntuacio() {
		return dPuntuacio;
	}

	public void setdPuntuacio(double dPuntuacio) {
		this.dPuntuacio = dPuntuacio;
	}

}
