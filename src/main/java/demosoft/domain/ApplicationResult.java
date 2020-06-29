package demosoft.domain;

public class ApplicationResult {

    Double enimmaisAsumisMenotEur = 0d;
    Double hoitoMenoMuu = 0d;
    Double rahoitusMeno = 0d;
    Double vuokraMeno = 0d;
    Double vastikeMeno = 0d;
    Double vesiMeno = 0d;
    Double lammitysMeno = 0d;
    Double alivuokraTulo = 0d;

    public Double getAlivuokraTulo() {
        return alivuokraTulo;
    }

    public void setAlivuokraTulo(Double alivuokraTulo) {
        this.alivuokraTulo = alivuokraTulo;
    }

    public Double getHyvaksytytAsumismenotEur() {
        // todo this should be in rule as well

        Double meno = hoitoMenoMuu
                + rahoitusMeno
                + vuokraMeno
                + vastikeMeno
                + vesiMeno
                + lammitysMeno
                - alivuokraTulo;
        return java.lang.Math.min(meno, enimmaisAsumisMenotEur);
    }

    public Double getEnimmaisAsumisMenotEur() {
        return enimmaisAsumisMenotEur;
    }

    public void setEnimmaisAsumisMenotEur(Double enimmaisAsumisMenotEur) {
        this.enimmaisAsumisMenotEur = enimmaisAsumisMenotEur;
    }

    public Double getHoitoMenoMuu() {
        return hoitoMenoMuu;
    }

    public void setHoitoMenoMuu(Double hoitoMenoMuu) {
        this.hoitoMenoMuu = hoitoMenoMuu;
    }

    public Double getRahoitusMeno() {
        return rahoitusMeno;
    }

    public void setRahoitusMeno(Double rahoitusMeno) {
        this.rahoitusMeno = rahoitusMeno;
    }

    public Double getVuokraMeno() {
        return vuokraMeno;
    }

    public void setVuokraMeno(Double vuokraMeno) {
        this.vuokraMeno = vuokraMeno;
    }

    public Double getVastikeMeno() {
        return vastikeMeno;
    }

    public void setVastikeMeno(Double vastikeMeno) {
        this.vastikeMeno = vastikeMeno;
    }

    public Double getVesiMeno() {
        return vesiMeno;
    }

    public void setVesiMeno(Double vesiMeno) {
        this.vesiMeno = vesiMeno;
    }

    public Double getLammitysMeno() {
        return lammitysMeno;
    }

    public void setLammitysMeno(Double lammitysMeno) {
        this.lammitysMeno = lammitysMeno;
    }

    @Override
    public String toString() {
        return "ApplicationResult{" +
                "enimmaisAsumisMenotEur=" + enimmaisAsumisMenotEur +
                ", hoitoMenoMuu=" + hoitoMenoMuu +
                ", rahoitusMeno=" + rahoitusMeno +
                ", vuokraMeno=" + vuokraMeno +
                ", vastikeMeno=" + vastikeMeno +
                ", vesiMeno=" + vesiMeno +
                ", lammitysMeno=" + lammitysMeno +
                '}';
    }
}
