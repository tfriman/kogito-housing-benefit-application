package demosoft.rules;

import java.util.Date;

public class MaximumHousingCostRequest {
    public int kalleusRyhma = 1;
    public int ruokakunnanKoko = 0;
    public Date alkuPvm;

    public MaximumHousingCostRequest() {
    }

    public MaximumHousingCostRequest(int kalleusRyhma, int ruokakunnanKoko, Date alkuPvm) {
        this.kalleusRyhma = kalleusRyhma;
        this.ruokakunnanKoko = ruokakunnanKoko;
        this.alkuPvm = alkuPvm;
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

    public Date getAlkuPvm() {
        return alkuPvm;
    }

    public void setAlkuPvm(Date alkuPvm) {
        this.alkuPvm = alkuPvm;
    }

    @Override
    public String toString() {
        return "MaksimiAsuinkulut{" +
                "kalleusRyhma=" + kalleusRyhma +
                ", ruokakunnanKoko=" + ruokakunnanKoko +
                ", alkuPvm=" + alkuPvm +
                '}';
    }
}
