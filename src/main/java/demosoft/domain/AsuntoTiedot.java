package demosoft.domain;

import java.math.BigDecimal;
import java.util.Objects;

import static demosoft.domain.AsunnonTyyppi.*;

public class AsuntoTiedot {
    private AsunnonTyyppi asunnonTyyppi;
    private String kuntaNumero;
    private String asuinKunta;
    private BigDecimal vuokraEur = BigDecimal.ZERO;
    private BigDecimal vastikeEur = BigDecimal.ZERO;
    private BigDecimal rahoitusMenotEur = BigDecimal.ZERO;
    private BigDecimal vesiPerHenkiloEur = BigDecimal.ZERO;
    private BigDecimal lammitysKustannuksetEur = BigDecimal.ZERO;
    private BigDecimal alivuokralaisenMaksamaVuokraEur = BigDecimal.ZERO;

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

    public BigDecimal getVuokraEur() {
        return vuokraEur;
    }

    public void setVuokraEur(BigDecimal vuokraEur) {
        this.vuokraEur = vuokraEur;
    }

    public BigDecimal getVastikeEur() {
        return vastikeEur;
    }

    public void setVastikeEur(BigDecimal vastikeEur) {
        this.vastikeEur = vastikeEur;
    }

    public BigDecimal getRahoitusMenotEur() {
        return rahoitusMenotEur;
    }

    public void setRahoitusMenotEur(BigDecimal rahoitusMenotEur) {
        this.rahoitusMenotEur = rahoitusMenotEur;
    }

    public BigDecimal getVesiPerHenkiloEur() {
        return vesiPerHenkiloEur;
    }

    public void setVesiPerHenkiloEur(BigDecimal vesiPerHenkiloEur) {
        this.vesiPerHenkiloEur = vesiPerHenkiloEur;
    }

    public BigDecimal getLammitysKustannuksetEur() {
        return lammitysKustannuksetEur;
    }

    public void setLammitysKustannuksetEur(BigDecimal lammitysKustannuksetEur) {
        this.lammitysKustannuksetEur = lammitysKustannuksetEur;
    }

    public BigDecimal getAlivuokralaisenMaksamaVuokraEur() {
        return alivuokralaisenMaksamaVuokraEur;
    }

    public void setAlivuokralaisenMaksamaVuokraEur(BigDecimal alivuokralaisenMaksamaVuokraEur) {
        this.alivuokralaisenMaksamaVuokraEur = alivuokralaisenMaksamaVuokraEur;
    }

    /**
     * Get rent without sub rent payments. Result is never negative.
     *
     * @return
     */
    public BigDecimal getAcceptedRent() {
        return vuokraEur.subtract(alivuokralaisenMaksamaVuokraEur).max(BigDecimal.ZERO);
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

    public AsuntoTiedot vuokraEur(BigDecimal vuokraEur) {
        this.vuokraEur = vuokraEur;
        return this;
    }

    public AsuntoTiedot vastikeEur(BigDecimal vastikeEur) {
        this.vastikeEur = vastikeEur;
        return this;
    }

    public AsuntoTiedot rahoitusMenotEur(BigDecimal rahoitusMenotEur) {
        this.rahoitusMenotEur = rahoitusMenotEur;
        return this;
    }

    public AsuntoTiedot vesiPerHenkiloEur(BigDecimal vesiPerHenkiloEur) {
        this.vesiPerHenkiloEur = vesiPerHenkiloEur;
        return this;
    }

    public AsuntoTiedot lammitysKustannuksetEur(BigDecimal lammitysKustannuksetEur) {
        this.lammitysKustannuksetEur = lammitysKustannuksetEur;
        return this;
    }

    public AsuntoTiedot alivuokralaisenMaksamaVuokraEur(BigDecimal alivuokralaisenMaksamaVuokraEur) {
        this.alivuokralaisenMaksamaVuokraEur = alivuokralaisenMaksamaVuokraEur;
        return this;
    }

    public boolean isVuokraAsunto() {
        return this.getAsunnonTyyppi() == VUOKRA;
    }

    public boolean isVuokraa() {
        return this.getVuokraEur().compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isOmistusOsake() {
        return this.getAsunnonTyyppi() == OMISTUS_OSAKE;
    }

    public boolean isMuuOmistus() {
        return this.getAsunnonTyyppi() == OMISTUS_MUU;
    }

}
