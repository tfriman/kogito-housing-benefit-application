package demosoft.domain;

import java.util.Date;

public abstract class PerusteTiedot {
    private Date alkuPvm;
    private Date loppuPvm;

    public Date getAlkuPvm() {
        return alkuPvm;
    }
    public void setAlkuPvm(Date alkuPvm) {
        this.alkuPvm = alkuPvm;
    }
    public Date getLoppuPvm() {
        return loppuPvm;
    }
    public void setLoppuPvm(Date loppuPvm) {
        this.loppuPvm = loppuPvm;
    }


    public PerusteTiedot alkuPvm(Date alkuPvm) {
        this.alkuPvm = alkuPvm;
        return this;
    }

    public PerusteTiedot loppuPvm(Date loppuPvm) {
        this.loppuPvm = loppuPvm;
        return this;
    }
}
