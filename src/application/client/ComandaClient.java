package application.client;

public class ComandaClient {

	private String sPrimerPlat = "";
	private String sSegonPlat = "";
	private String sPostres = "";
	private String sCafe = "";
	private String sBeguda = "";
	
	public ComandaClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * COMPROVA QUE NO HI HA CAP CAMP BUIT
	 * @return
	 */
	public boolean comprovarPlatsNull() {
		
		if ( sPrimerPlat.equals( "" ) 
				|| sSegonPlat.equals( "" )
				|| sPostres.equals( "" )
				|| sCafe.equals( "" )
				|| sBeguda.equals( "" )) {
			
			return false;
			
		}
		return true;
		
	}

	public String getsPrimerPlat() {
		return sPrimerPlat;
	}

	public void setsPrimerPlat(String sPrimerPlat) {
		this.sPrimerPlat = sPrimerPlat;
	}

	public String getsPostres() {
		return sPostres;
	}

	public void setsPostres(String sPostres) {
		this.sPostres = sPostres;
	}

	public String getsSegonPlat() {
		return sSegonPlat;
	}

	public void setsSegonPlat(String sSegonPlat) {
		this.sSegonPlat = sSegonPlat;
	}

	public String getsCafe() {
		return sCafe;
	}

	public void setsCafe(String sCafe) {
		this.sCafe = sCafe;
	}

	public String getsBeguda() {
		return sBeguda;
	}

	public void setsBeguda(String beguda) {
		this.sBeguda = beguda;
	}

}
