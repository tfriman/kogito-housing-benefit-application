package demosoft.process;

import demosoft.domain.AsuntoTiedot;
import demosoft.domain.BenefitApplication;
import demosoft.domain.RuokakunnanJasen;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class ApplicationProcessTest {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationProcessTest.class);
    @Inject
    @Named("application")
    Process<? extends Model> applicationsProcess;

    @Test
    public void eiLisatilavaadetta() {
        Model m = applicationsProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("application", new BenefitApplication.BenefitApplicationBuilder()
                .lisaaRuokakunnanJasen(new RuokakunnanJasen()
                        .etuNimi("Matti")
                        .sukuNimi("Meikalainen")
                )
                .lisaaRuokakunnanJasen(new RuokakunnanJasen()
                        .etuNimi("Maija")
                        .sukuNimi("Meikalainen")
                )
                .addAsuntoTiedot(new AsuntoTiedot()
                        .vuokraEur(new BigDecimal(500))
                        .vesiPerHenkiloEur(new BigDecimal(15))
                )
                .kuntaRyhma(1)
                .alkuPvm(new Date()).build()
        );
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = applicationsProcess.createInstance(m);
        processInstance.start();
        Model result = (Model) processInstance.variables();

        var application = (BenefitApplication) result.toMap().get("application");
        LOG.info("application = {}", application);
        Assertions.assertEquals("30", findDecisionByRuleName(application, "vesimaksut", 1));
        Assertions.assertEquals("2", findDecisionByRuleName(application, "ruokakunnan.laskennallinen.koko", 0));
        Assertions.assertEquals(746, application.getResult().getEnimmaisAsumisMenotEur().intValue());
        // todo check if 530 is correct!
        Assertions.assertEquals(530, application.getResult().getHyvaksytytAsumismenotEur().intValue());
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
    public void lisaTilaVaade() {
        Model m = applicationsProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("application", new BenefitApplication.BenefitApplicationBuilder()
                .lisaaRuokakunnanJasen(new RuokakunnanJasen()
                        .etuNimi("Matti")
                        .sukuNimi("Meikalainen")
                        .lisatilanTarve(true)
                )
                .lisaaRuokakunnanJasen(new RuokakunnanJasen()
                        .etuNimi("Maija")
                        .sukuNimi("Meikalainen")
                )
                .addAsuntoTiedot(new AsuntoTiedot()
                        .vuokraEur(new BigDecimal(600))
                        .vesiPerHenkiloEur(new BigDecimal(15))
                )
                .kuntaRyhma(1)
                .alkuPvm(new Date()).build()
        );
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = applicationsProcess.createInstance(m);
        processInstance.start();
        Model result = (Model) processInstance.variables();

        BenefitApplication application = (BenefitApplication) result.toMap().get("application");
        LOG.info("application = {}", application);
        Assertions.assertEquals(951, application.getResult().getEnimmaisAsumisMenotEur().intValue());
        Assertions.assertEquals("45", findDecisionByRuleName(application, "vesimaksut", 1));
        Assertions.assertEquals("3", findDecisionByRuleName(application, "ruokakunnan.laskennallinen.koko", 0));
        Assertions.assertEquals(645, application.getResult().getHyvaksytytAsumismenotEur().intValue());

    }
}
