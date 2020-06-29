package demosoft.domain;

public class RuokakunnanJasen extends Henkilo {
	private boolean lisatilanTarve = false;

	public boolean isLisatilanTarve() {
		return lisatilanTarve;
	}
	public void setLisatilanTarve(boolean lisatilanTarve) {
		this.lisatilanTarve = lisatilanTarve;
	}

	public RuokakunnanJasen hetu(String hetu) {
		this.setHetu(hetu);
		return this;
	}

	public RuokakunnanJasen etuNimi(String etuNimi) {
		this.setEtuNimi(etuNimi);
		return this;
	}

	public RuokakunnanJasen sukuNimi(String sukuNimi) {
		this.setSukuNimi(sukuNimi);
		return this;
	}

	public RuokakunnanJasen lisatilanTarve(boolean lisatilanTarve) {
		this.lisatilanTarve = lisatilanTarve;
		return this;
	}


	@Override
	public String toString() {
		return "RuokakunnanJasen{" +
				"lisatilanTarve=" + lisatilanTarve +
				super.toString() +
				'}';
	}
}
