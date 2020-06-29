package demosoft.domain;

import demosoft.domain.decision.DecisionElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BenefitApplication extends PerusteTiedot {
    private int kuntaRyhma;
    private String asuinMaakunta;
    private int ruokakunnanKoko = 0;
    private AsuntoTiedot asuntoTiedot;
    private boolean alivuokralainen = false;
    private List<RuokakunnanJasen> ruokakunnanJasenet = new ArrayList<>();
    private List<DecisionElement> decisionElements = new ArrayList<>();

    private ApplicationResult result;

    public ApplicationResult getResult() {
        return result;
    }

    public void setResult(ApplicationResult result) {
        this.result = result;
    }

    public List<DecisionElement> getDecisionElements() {
        return decisionElements;
    }

    public void setDecisionElements(List<DecisionElement> decisionElements) {
        this.decisionElements = decisionElements;
    }

    public void addDecisionElement(DecisionElement decisionElement) {
        this.decisionElements.add(decisionElement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BenefitApplication that = (BenefitApplication) o;
        return kuntaRyhma == that.kuntaRyhma &&
                ruokakunnanKoko == that.ruokakunnanKoko &&
                alivuokralainen == that.alivuokralainen &&
                Objects.equals(asuinMaakunta, that.asuinMaakunta) &&
                Objects.equals(asuntoTiedot, that.asuntoTiedot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kuntaRyhma, asuinMaakunta, ruokakunnanKoko, asuntoTiedot, alivuokralainen);
    }

    public int getKuntaRyhma() {
        return kuntaRyhma;
    }

    public void setKuntaRyhma(int kuntaRyhma) {
        this.kuntaRyhma = kuntaRyhma;
    }

    public String getAsuinMaakunta() {
        return asuinMaakunta;
    }

    public void setAsuinMaakunta(String asuinMaakunta) {
        this.asuinMaakunta = asuinMaakunta;
    }

    public int getRuokakunnanKoko() {
        return ruokakunnanKoko;
    }

    public void setRuokakunnanKoko(int ruokakunnanKoko) {
        this.ruokakunnanKoko = ruokakunnanKoko;
    }

    public AsuntoTiedot getAsuntoTiedot() {
        return asuntoTiedot;
    }

    public void setAsuntoTiedot(AsuntoTiedot asuntoTiedot) {
        this.asuntoTiedot = asuntoTiedot;
    }

    public boolean isAlivuokralainen() {
        return alivuokralainen;
    }

    public void setAlivuokralainen(boolean alivuokralainen) {
        this.alivuokralainen = alivuokralainen;
    }

    public List<RuokakunnanJasen> getRuokakunnanJasenet() {
        return ruokakunnanJasenet;
    }

    public void setRuokakunnanJasenet(List<RuokakunnanJasen> ruokakunnanJasenet) {
        this.ruokakunnanJasenet = ruokakunnanJasenet;
    }

    public static class BenefitApplicationBuilder {

        private int kuntaRyhma;
        private String asuinMaakunta;
        private AsuntoTiedot asuntoTiedot;
        private boolean alivuokralainen;
        //private boolean lisatilanTarveHuomioitu;
        private int ruokakunnanKoko;
        private List<RuokakunnanJasen> ruokakunnanJasenet = new ArrayList<>();
        private List<DecisionElement> decisionElements = new ArrayList<>();
        private Date alkuPvm;

        public BenefitApplication build() {
            BenefitApplication result = new BenefitApplication();
            result.setKuntaRyhma(kuntaRyhma);
            result.setAsuinMaakunta(asuinMaakunta);
            result.setAsuntoTiedot(asuntoTiedot);
            result.setAlivuokralainen(alivuokralainen);
            //result.setLisatilanTarveHuomioitu(lisatilanTarveHuomioitu);
            result.setRuokakunnanKoko(ruokakunnanKoko);
            result.setRuokakunnanJasenet(ruokakunnanJasenet);
            result.setDecisionElements(decisionElements);
            result.setAlkuPvm(alkuPvm);
            return result;
        }

        public BenefitApplicationBuilder kuntaRyhma(int kuntaRyhma) {
            this.kuntaRyhma = kuntaRyhma;
            return this;
        }

        public BenefitApplicationBuilder asuinMaakunta(String asuinMaakunta) {
            this.asuinMaakunta = asuinMaakunta;
            return this;
        }

        public BenefitApplicationBuilder addAsuntoTiedot(AsuntoTiedot asuntoTiedot) {
            this.asuntoTiedot = asuntoTiedot;
            return this;
        }

        public BenefitApplicationBuilder alivuokralainen(boolean alivuokralainen) {
            this.alivuokralainen = alivuokralainen;
            return this;
        }

        public BenefitApplicationBuilder addRuokakunnanKoko(int i) {
            this.ruokakunnanKoko = i;
            return this;
        }

        public BenefitApplicationBuilder lisaaRuokakunnanJasenet(List<RuokakunnanJasen> ruokakunnanJasenet) {
            this.ruokakunnanJasenet = ruokakunnanJasenet;
            return this;
        }

        public BenefitApplicationBuilder decisionElements(List<DecisionElement> decisionElements) {
            this.decisionElements = decisionElements;
            return this;
        }

        public BenefitApplicationBuilder lisaaRuokakunnanJasen(RuokakunnanJasen jasen) {
            this.ruokakunnanJasenet.add(jasen);
            return this;
        }

        public BenefitApplicationBuilder alkuPvm(Date date) {
            this.alkuPvm = date;
            return this;
        }
    }

    @Override
    public String toString() {
        return "BenefitApplication{" +
                ", ruokakunnanKoko=" + ruokakunnanKoko +
                ", decisionElements=" + decisionElements +
                '}';
    }
}
