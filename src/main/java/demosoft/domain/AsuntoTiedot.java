package demosoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

import static demosoft.domain.AsunnonTyyppi.*;

public class AsuntoTiedot {
    private AsunnonTyyppi asunnonTyyppi;
    private String kuntaNumero;
    private String asuinKunta;
    private Double vuokraEur = 0d;
    private Double vastikeEur = 0d;
    private Double rahoitusMenotEur = 0d;
    private Double vesiPerHenkiloEur = 0d;
    private Double lammitysKustannuksetEur = 0d;
    private Double alivuokralaisenMaksamaVuokraEur = 0d;

    @Override
    public String toString() {
        return "AsuntoTiedot{" +
                "asunnonTyyppi=" + asunnonTyyppi +
                ", kuntaNumero='" + kuntaNumero + '\'' +
                ", asuinKunta='" + asuinKunta + '\'' +
                ", vuokraEur=" + vuokraEur +
                ", vastikeEur=" + vastikeEur +
                ", rahoitusMenotEur=" + rahoitusMenotEur +
                ", vesiPerHenkiloEur=" + vesiPerHenkiloEur +
                ", lammitysKustannuksetEur=" + lammitysKustannuksetEur +
                ", alivuokralaisenMaksamaVuokraEur=" + alivuokralaisenMaksamaVuokraEur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsuntoTiedot that = (AsuntoTiedot) o;
        return asunnonTyyppi == that.asunnonTyyppi &&
                Objects.equals(kuntaNumero, that.kuntaNumero) &&
                Objects.equals(asuinKunta, that.asuinKunta) &&
                Objects.equals(vuokraEur, that.vuokraEur) &&
                Objects.equals(vastikeEur, that.vastikeEur) &&
                Objects.equals(rahoitusMenotEur, that.rahoitusMenotEur) &&
                Objects.equals(vesiPerHenkiloEur, that.vesiPerHenkiloEur) &&
                Objects.equals(lammitysKustannuksetEur, that.lammitysKustannuksetEur) &&
                Objects.equals(alivuokralaisenMaksamaVuokraEur, that.alivuokralaisenMaksamaVuokraEur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asunnonTyyppi, kuntaNumero, asuinKunta, vuokraEur, vastikeEur, rahoitusMenotEur, vesiPerHenkiloEur, lammitysKustannuksetEur, alivuokralaisenMaksamaVuokraEur);
    }

    public AsunnonTyyppi getAsunnonTyyppi() {
        return asunnonTyyppi;
    }

    public void setAsunnonTyyppi(AsunnonTyyppi asunnonTyyppi) {
        this.asunnonTyyppi = asunnonTyyppi;
    }

    public String getKuntaNumero() {
        return kuntaNumero;
    }

    public void setKuntaNumero(String kuntaNumero) {
        this.kuntaNumero = kuntaNumero;
    }

    public String getAsuinKunta() {
        return asuinKunta;
    }

    public void setAsuinKunta(String asuinKunta) {
        this.asuinKunta = asuinKunta;
    }

    public Double getVuokraEur() {
        return vuokraEur;
    }

    public void setVuokraEur(Double vuokraEur) {
        this.vuokraEur = vuokraEur;
    }

    public Double getVastikeEur() {
        return vastikeEur;
    }

    public void setVastikeEur(Double vastikeEur) {
        this.vastikeEur = vastikeEur;
    }

    public Double getRahoitusMenotEur() {
        return rahoitusMenotEur;
    }

    public void setRahoitusMenotEur(Double rahoitusMenotEur) {
        this.rahoitusMenotEur = rahoitusMenotEur;
    }

    public Double getVesiPerHenkiloEur() {
        return vesiPerHenkiloEur;
    }

    public void setVesiPerHenkiloEur(Double vesiPerHenkiloEur) {
        this.vesiPerHenkiloEur = vesiPerHenkiloEur;
    }

    public Double getLammitysKustannuksetEur() {
        return lammitysKustannuksetEur;
    }

    public void setLammitysKustannuksetEur(Double lammitysKustannuksetEur) {
        this.lammitysKustannuksetEur = lammitysKustannuksetEur;
    }

    public Double getAlivuokralaisenMaksamaVuokraEur() {
        return alivuokralaisenMaksamaVuokraEur;
    }

    public void setAlivuokralaisenMaksamaVuokraEur(Double alivuokralaisenMaksamaVuokraEur) {
        this.alivuokralaisenMaksamaVuokraEur = alivuokralaisenMaksamaVuokraEur;
    }

    /**
     * Get rent without sub rent payments. Result is never negative.
     *
     * @return
     */
    @JsonIgnore
    public Double getAcceptedRent() {
        Double result = vuokraEur - alivuokralaisenMaksamaVuokraEur;
        return java.lang.Math.max(result, 0d);
    }

    public AsuntoTiedot asunnonTyyppi(AsunnonTyyppi asunnonTyyppi) {
        this.asunnonTyyppi = asunnonTyyppi;
        return this;
    }

    public AsuntoTiedot kuntaNumero(String kuntaNumero) {
        this.kuntaNumero = kuntaNumero;
        return this;
    }

    public AsuntoTiedot asuinKunta(String asuinKunta) {
        this.asuinKunta = asuinKunta;
        return this;
    }

    public AsuntoTiedot vuokraEur(Double vuokraEur) {
        this.vuokraEur = vuokraEur;
        return this;
    }

    public AsuntoTiedot vastikeEur(Double vastikeEur) {
        this.vastikeEur = vastikeEur;
        return this;
    }

    public AsuntoTiedot rahoitusMenotEur(Double rahoitusMenotEur) {
        this.rahoitusMenotEur = rahoitusMenotEur;
        return this;
    }

    public AsuntoTiedot vesiPerHenkiloEur(Double vesiPerHenkiloEur) {
        this.vesiPerHenkiloEur = vesiPerHenkiloEur;
        return this;
    }

    public AsuntoTiedot lammitysKustannuksetEur(Double lammitysKustannuksetEur) {
        this.lammitysKustannuksetEur = lammitysKustannuksetEur;
        return this;
    }

    public AsuntoTiedot alivuokralaisenMaksamaVuokraEur(Double alivuokralaisenMaksamaVuokraEur) {
        this.alivuokralaisenMaksamaVuokraEur = alivuokralaisenMaksamaVuokraEur;
        return this;
    }

    public boolean isVuokraAsunto() {
        return this.getAsunnonTyyppi() == VUOKRA;
    }

    public boolean isVuokraa() {
        return this.getVuokraEur().compareTo(0d) > 0;
    }

    public boolean isOmistusOsake() {
        return this.getAsunnonTyyppi() == OMISTUS_OSAKE;
    }

    public boolean isMuuOmistus() {
        return this.getAsunnonTyyppi() == OMISTUS_MUU;
    }

}
