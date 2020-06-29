package demosoft.process;

import demosoft.domain.AsuntoTiedot;
import demosoft.domain.BenefitApplication;
import demosoft.domain.RuokakunnanJasen;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.kie.kogito.rules.RuleUnit;
import org.kie.kogito.rules.RuleUnitInstance;
import org.kie.kogito.rules.units.SessionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;

//@QuarkusTest
public class RuleTest {

    private static final Logger LOG = LoggerFactory.getLogger(RuleTest.class);
    @Inject
    @Named("benefitKS")
    RuleUnit<SessionData> ruleUnit;


    //@Test
    public void simpleTest() {

        SessionData memory = new SessionData();
        BenefitApplication application = new BenefitApplication.BenefitApplicationBuilder()

                .lisaaRuokakunnanJasen(new RuokakunnanJasen()
                        .etuNimi("Matti")
                        .sukuNimi("Meikalainen")
                )
                .lisaaRuokakunnanJasen(new RuokakunnanJasen()
                        .etuNimi("Maija")
                        .sukuNimi("Meikalainen")
                )
                .addAsuntoTiedot(new AsuntoTiedot()
                        .vuokraEur(new BigDecimal(100))
                        .vesiPerHenkiloEur(new BigDecimal(15))
                )
                .alkuPvm(new Date()).build();

        memory.add(application);
        RuleUnitInstance<SessionData> instance = ruleUnit.createInstance(memory);

        instance.fire();

        LOG.info("application = {}", application);

        Assertions.assertEquals("30", application.getDecisionElements().stream().filter(e -> e.getRule().equals("vesimaksut")).findFirst().get().getKeys()[0]);
    }

}
