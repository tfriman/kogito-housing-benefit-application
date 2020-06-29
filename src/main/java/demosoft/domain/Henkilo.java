package demosoft.domain;

public class Henkilo {
	private String hetu;
	private String etuNimi;
	private String sukuNimi;
	public String getHetu() {
		return hetu;
	}
	public void setHetu(String hetu) {
		this.hetu = hetu;
	}
	public String getEtuNimi() {
		return etuNimi;
	}
	public void setEtuNimi(String etuNimi) {
		this.etuNimi = etuNimi;
	}
	public String getSukuNimi() {
		return sukuNimi;
	}
	public void setSukuNimi(String sukuNimi) {
		this.sukuNimi = sukuNimi;
	}

	@Override
	public String toString() {
		return "Henkilo{" +
				"etuNimi='" + etuNimi + '\'' +
				", sukuNimi='" + sukuNimi + '\'' +
				'}';
	}
}
