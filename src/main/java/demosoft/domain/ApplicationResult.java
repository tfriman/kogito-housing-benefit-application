package demosoft.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class ApplicationResult {

    BigDecimal enimmaisAsumisMenotEur = BigDecimal.ZERO;
    BigDecimal hoitoMenoMuu = BigDecimal.ZERO;
    BigDecimal rahoitusMeno = BigDecimal.ZERO;
    BigDecimal vuokraMeno = BigDecimal.ZERO;
    BigDecimal vastikeMeno = BigDecimal.ZERO;
    BigDecimal vesiMeno = BigDecimal.ZERO;
    BigDecimal lammitysMeno = BigDecimal.ZERO;

    public BigDecimal getAlivuokraTulo() {
        return alivuokraTulo;
    }

    public void setAlivuokraTulo(BigDecimal alivuokraTulo) {
        this.alivuokraTulo = alivuokraTulo;
    }

    BigDecimal alivuokraTulo = BigDecimal.ZERO;

    public BigDecimal getHyvaksytytAsumismenotEur() {
        // todo this should be in rule as well
        return hoitoMenoMuu
                .add(rahoitusMeno)
                .add(vuokraMeno)
                .add(vastikeMeno)
                .add(vesiMeno)
                .add(lammitysMeno)
                .subtract(alivuokraTulo)
                .min(enimmaisAsumisMenotEur);
    }

    public BigDecimal getEnimmaisAsumisMenotEur() {
        return enimmaisAsumisMenotEur;
    }

    public void setEnimmaisAsumisMenotEur(BigDecimal enimmaisAsumisMenotEur) {
        this.enimmaisAsumisMenotEur = enimmaisAsumisMenotEur;
    }

    public BigDecimal getHoitoMenoMuu() {
        return hoitoMenoMuu;
    }

    public void setHoitoMenoMuu(BigDecimal hoitoMenoMuu) {
        this.hoitoMenoMuu = hoitoMenoMuu;
    }

    public BigDecimal getRahoitusMeno() {
        return rahoitusMeno;
    }

    public void setRahoitusMeno(BigDecimal rahoitusMeno) {
        this.rahoitusMeno = rahoitusMeno;
    }

    public BigDecimal getVuokraMeno() {
        return vuokraMeno;
    }

    public void setVuokraMeno(BigDecimal vuokraMeno) {
        this.vuokraMeno = vuokraMeno;
    }

    public BigDecimal getVastikeMeno() {
        return vastikeMeno;
    }

    public void setVastikeMeno(BigDecimal vastikeMeno) {
        this.vastikeMeno = vastikeMeno;
    }

    public BigDecimal getVesiMeno() {
        return vesiMeno;
    }

    public void setVesiMeno(BigDecimal vesiMeno) {
        this.vesiMeno = vesiMeno;
    }

    public BigDecimal getLammitysMeno() {
        return lammitysMeno;
    }

    public void setLammitysMeno(BigDecimal lammitysMeno) {
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
