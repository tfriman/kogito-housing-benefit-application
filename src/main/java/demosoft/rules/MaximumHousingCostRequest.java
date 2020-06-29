package demosoft.rules;


import java.time.LocalDate;

public class MaximumHousingCostRequest {
    private String county;
    private int kalleusRyhma = 1;
    private int ruokakunnanKoko = 0;
    private LocalDate alkuPvm;

    public MaximumHousingCostRequest() {
    }

    public MaximumHousingCostRequest(int kalleusRyhma, int ruokakunnanKoko, String county, LocalDate alkuPvm) {
        this.kalleusRyhma = kalleusRyhma;
        this.ruokakunnanKoko = ruokakunnanKoko;
        this.alkuPvm = alkuPvm;
        this.county = county;
    }

    public int getKalleusRyhma() {
        return kalleusRyhma;
    }

    public void setKalleusRyhma(int kalleusRyhma) {
        this.kalleusRyhma = kalleusRyhma;
    }

    public int getRuokakunnanKoko() {
        return ruokakunnanKoko;
    }

    public void setRuokakunnanKoko(int ruokakunnanKoko) {
        this.ruokakunnanKoko = ruokakunnanKoko;
    }

    public LocalDate getAlkuPvm() {
        return alkuPvm;
    }

    public void setAlkuPvm(LocalDate alkuPvm) {
        this.alkuPvm = alkuPvm;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "MaximumHousingCostRequest{" +
                "county='" + county + '\'' +
                ", kalleusRyhma=" + kalleusRyhma +
                ", ruokakunnanKoko=" + ruokakunnanKoko +
                ", alkuPvm=" + alkuPvm +
                '}';
    }
}
