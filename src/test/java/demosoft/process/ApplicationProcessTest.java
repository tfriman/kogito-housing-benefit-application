package demosoft.process;

import demosoft.domain.ApplicationResult;
import demosoft.domain.BenefitApplication;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static demosoft.process.BenefitApplicationGenerator.*;
import static java.time.LocalDate.of;

@QuarkusTest
public class ApplicationProcessTest {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationProcessTest.class);
    private final int FAMILY_2 = 2;
    private final LocalDate DATE_2015 = of(2015, 2, 12);
    private final LocalDate DATE_2018 = of(2018, 4, 5);
    private final String APPLICATION = "application";
    private final int KUNTARYHMA_3 = 3;
    private final int KUNTARYHMA_1 = 1;
    private final String REGION_UUSIMAA = "UUSIMAA";
    private final String REGION_ESAVO = "ETELÄ-SAVO";
    private final double DELTA = 0.004d;

    @Inject
    @Named("application")
    Process<? extends Model> applicationsProcess;

    private BenefitApplication runProcess(BenefitApplication benefitApplication) {
        Model m = applicationsProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(APPLICATION, benefitApplication);
        m.fromMap(parameters);
        ProcessInstance<?> processInstance = applicationsProcess.createInstance(m);
        processInstance.start();
        Model result = (Model) processInstance.variables();
        return (BenefitApplication) result.toMap().get(APPLICATION);
    }

    private Object findDecisionByRuleName(final BenefitApplication application, final String ruleName, int key) {
        return application.getDecisionElements()
                .stream()
                .filter(e -> ruleName.equals(e.getRule()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("rule name not found:" + ruleName))
                .getKeys()[key];
    }

    @Test
    public void eiLisatilavaadetta_2018() {
        BenefitApplication application = runProcess(createXMembersRenting(FAMILY_2, 500, DATE_2018, 15, 0, REGION_UUSIMAA, 1));
        LOG.info("application = {}", application);
        Assertions.assertEquals("30.0", findDecisionByRuleName(application, "costs.water", 1));
        Assertions.assertEquals("2", findDecisionByRuleName(application, "household.calculated.size", 0));
        Assertions.assertEquals(746, application.getResult().getEnimmaisAsumisMenotEur().intValue());
        Assertions.assertEquals(530, application.getResult().getHyvaksytytAsumismenotEur().intValue());
    }

    @Test
    public void eiLisatilavaadetta_2015() {
        BenefitApplication application = runProcess(createXMembersRenting(FAMILY_2, 900, DATE_2015, 15, 0, REGION_UUSIMAA, 1));
        LOG.info("application = {}", application);
        Assertions.assertEquals("30.0", findDecisionByRuleName(application, "costs.water", 1));
        Assertions.assertEquals("2", findDecisionByRuleName(application, "household.calculated.size", 0));
        Assertions.assertEquals(735, application.getResult().getEnimmaisAsumisMenotEur().intValue());
        // Maximum accepted > actual rent, so take max.
        Assertions.assertEquals(735, application.getResult().getHyvaksytytAsumismenotEur().intValue());
    }

    @Test
    public void lisaTilaVaade() {
        BenefitApplication benefitApplication = createXMembersRenting(FAMILY_2, 600, DATE_2018, 15, 0, REGION_UUSIMAA, 1);
        addLisatilaVaade(benefitApplication);
        BenefitApplication application = runProcess(benefitApplication);
        LOG.info("application = {}", application);
        Assertions.assertEquals(951, application.getResult().getEnimmaisAsumisMenotEur().intValue());
        Assertions.assertEquals("45.0", findDecisionByRuleName(application, "costs.water", 1));
        Assertions.assertEquals("3", findDecisionByRuleName(application, "household.calculated.size", 0));
        Assertions.assertEquals("3", findDecisionByRuleName(application, "need.extra.space", 0));
        Assertions.assertEquals(645, application.getResult().getHyvaksytytAsumismenotEur().intValue());
    }

    @Test
    public void testYksinVuokrallaAsuvaSavo_2015() {
        int rent = 320;
        int waterPerPerson = 15;
        int heating = 30;
        int otherHousingCost = 0;
        BenefitApplication benefitApplication = createXMembersRenting(1, rent, DATE_2015, waterPerPerson, heating, REGION_ESAVO, KUNTARYHMA_3);
        BenefitApplication application = runProcess(benefitApplication);
        LOG.info("result: {}", application);
        Assertions.assertEquals(411, application.getResult().getEnimmaisAsumisMenotEur().intValue(), "max costs");
        Assertions.assertEquals("15.0", findDecisionByRuleName(application, "costs.water", 1));
        Assertions.assertEquals("1", findDecisionByRuleName(application, "household.calculated.size", 0));
        Assertions.assertEquals("1.04", findDecisionByRuleName(application, "increased_housing", 0));
        Assertions.assertEquals((double) (rent + waterPerPerson + (1.04 * (heating + otherHousingCost))), application.getResult().getHyvaksytytAsumismenotEur(), "values should match");
    }

    @Test
    public void testYksinVuokrallaAsuvaLappi_2018() {
        int rent = 320;
        int waterPerPerson = 15;
        int heating = 30;
        int otherHousingCost = 0;
        BenefitApplication benefitApplication = createXMembersRenting(1, rent, DATE_2018, waterPerPerson, heating, "LAPPI", KUNTARYHMA_3);
        BenefitApplication application = runProcess(benefitApplication);
        LOG.info("result: {}", application);
        Assertions.assertEquals(396, application.getResult().getEnimmaisAsumisMenotEur().intValue(), "max costs");
        Assertions.assertEquals("15.0", findDecisionByRuleName(application, "costs.water", 1));
        Assertions.assertEquals("1", findDecisionByRuleName(application, "household.calculated.size", 0));
        Assertions.assertEquals("1.08", findDecisionByRuleName(application, "increased_housing", 0));
        Assertions.assertEquals((double) (rent + waterPerPerson + (1.08 * (heating + otherHousingCost))), application.getResult().getHyvaksytytAsumismenotEur(), "values should match");
    }

    @Test
    public void testHoitomenotOther5_2018() {
        // Tästä 73 prosenttia, lisäksi pitäisi tulla 208 euroa hoitomenoja (ruokakunnan koon mukaan)
        // 900 * 0.73 + 208 = 865
        int otherCosts = 900;
        BenefitApplication benefitApplication = createXMembersOtherType(5, otherCosts, DATE_2018, REGION_UUSIMAA, KUNTARYHMA_1);
        BenefitApplication application = runProcess(benefitApplication);
        LOG.info("result: {}", application);
        Assertions.assertEquals(1250, application.getResult().getEnimmaisAsumisMenotEur().intValue(), "max costs");
        Assertions.assertEquals("208.0", findDecisionByRuleName(application, "costs.other", 0));
        Assertions.assertEquals("657.0", findDecisionByRuleName(application, "costs.owned.interestpayments", 0));
        Assertions.assertEquals("73", findDecisionByRuleName(application, "costs.owned.interestpayments", 1));
        Assertions.assertEquals("900.0", findDecisionByRuleName(application, "costs.owned.interestpayments", 2));
        Assertions.assertEquals("5", findDecisionByRuleName(application, "household.calculated.size", 0));
        Double hyvaksytytAsumismenotEur = application.getResult().getHyvaksytytAsumismenotEur();
        Assertions.assertEquals(865, hyvaksytytAsumismenotEur.intValue(), "values should match");
    }

    @Test
    public void subRentReductionTest_2015() {
        int rent = 420;
        int waterPerPerson = 15;
        int heating = 30;
        int subRent = 150;
        BenefitApplication benefitApplication = createXMembersRenting(1, rent, DATE_2015, waterPerPerson, heating, REGION_ESAVO, KUNTARYHMA_3);
        benefitApplication.getAsuntoTiedot().setAlivuokralaisenMaksamaVuokraEur(Double.valueOf(subRent));
        BenefitApplication application = runProcess(benefitApplication);
        LOG.info("result: {}", application);
        Assertions.assertEquals(411, application.getResult().getEnimmaisAsumisMenotEur().intValue(), "max costs");
        Assertions.assertEquals("1", findDecisionByRuleName(application, "household.calculated.size", 0));
        Double hyvaksytytAsumismenotEur = application.getResult().getHyvaksytytAsumismenotEur();
        Assertions.assertEquals(316.20d, hyvaksytytAsumismenotEur, "values should match");
    }

    @Test
    public void aloneLotOfMaintenance2015() {
        int maintenance = 320; // Tämä (oik. vastike+vesi) rajataan 30 prosenttiin enimmäisasumismenoista, 0,3 * 508 = 152,4 (ylijäämästä 73 pros rahoitusmenoihin = (15+320-152.4)*0.73=133.298)
        int waterPerPerson = 15;
        int interestPayments = 200;  // Tästä 73 pros hyväksytään = 146, plus hoitomenojen ylijäämästä 73 pros 133.298 yht 279.298
        BenefitApplication benefitApplication = createXMembersOwned(1, interestPayments, maintenance, DATE_2015, waterPerPerson, REGION_UUSIMAA, KUNTARYHMA_1);
        BenefitApplication application = runProcess(benefitApplication);
        LOG.info("result: {}", application);
        ApplicationResult result = application.getResult();
        Assertions.assertEquals(508, result.getEnimmaisAsumisMenotEur().intValue(), "max costs");
        Assertions.assertEquals("1", findDecisionByRuleName(application, "household.calculated.size", 0));

        Assertions.assertEquals("320.0", findDecisionByRuleName(application, "costs.owned.maintenance", 0));
        Assertions.assertEquals("30", findDecisionByRuleName(application, "costs.owned.maintenance.limited", 0));
        Assertions.assertEquals("146.0", findDecisionByRuleName(application, "costs.owned.interestpayments", 0));
        Assertions.assertEquals("146.0", findDecisionByRuleName(application, "costs.owned.interestpayments.limited", 0));
        Assertions.assertEquals("146.0", findDecisionByRuleName(application, "costs.owned.overflow.limited", 0));

        Double vastikeMeno = result.getVastikeMeno();
        //Assertions.assertEquals(152.4, vastikeMeno.setScale(1, RoundingMode.HALF_EVEN).doubleValue(), "maintenance costs should match");
        Assertions.assertEquals(137.4f, vastikeMeno, DELTA, "maintenance costs should match");

        Double rahoitusMeno = result.getRahoitusMeno();
        Assertions.assertEquals(279.298f, rahoitusMeno, DELTA, "financing costs should match");

        Double hyvaksytytAsumismenotEur = result.getHyvaksytytAsumismenotEur();
        Assertions.assertEquals(431.698f, hyvaksytytAsumismenotEur, DELTA, "total costs should match");
    }

    @Test
    public void aloneLowMaintenance2015() {
        int maintenance = 120; // tämä jää alle 30% maksimista joka on 152.4, eli 120
        int waterPerPerson = 15; // 15
        int interestPayments = 200;  // Tästä 73 pros hyväksytään = 146
        BenefitApplication benefitApplication = createXMembersOwned(1, interestPayments, maintenance, DATE_2015, waterPerPerson, REGION_UUSIMAA, KUNTARYHMA_1);
        BenefitApplication application = runProcess(benefitApplication);
        LOG.info("result: {}", application);
        ApplicationResult result = application.getResult();
        Assertions.assertEquals(508, result.getEnimmaisAsumisMenotEur().intValue(), "max costs");
        Assertions.assertEquals("1", findDecisionByRuleName(application, "household.calculated.size", 0));

        Double vastikeMeno = result.getVastikeMeno();

        Assertions.assertEquals(120, vastikeMeno, "maintenance costs should match");

        Double rahoitusMeno = result.getRahoitusMeno();
        Assertions.assertEquals(146, rahoitusMeno, "financing costs should match");

        Double hyvaksytytAsumismenotEur = result.getHyvaksytytAsumismenotEur();
        Assertions.assertEquals(281, hyvaksytytAsumismenotEur, "total costs should match");
    }
}
