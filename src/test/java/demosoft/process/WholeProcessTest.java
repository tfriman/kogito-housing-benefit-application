package demosoft.process;

import demosoft.domain.AsuntoTiedot;
import demosoft.domain.BenefitApplication;
import demosoft.domain.RuokakunnanJasen;
import demosoft.domain.decision.DecisionElementSolver;
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
import java.util.*;

/**
 * Test the whole e2e stuff.
 */
@QuarkusTest
public class WholeProcessTest {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationProcessTest.class);
    DecisionElementSolver solver = new DecisionElementSolver(ResourceBundle.getBundle("resourcebundles.testresource"));
    @Inject
    @Named("application")
    Process<? extends Model> applicationsProcess;

    @Test
    public void happyPath() {
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
                        .lisatilanTarve(true)
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
        Assertions.assertEquals(951, application.getResult().getEnimmaisAsumisMenotEur().intValue());
        // todo check if 530 is correct!
        Assertions.assertEquals(545, application.getResult().getHyvaksytytAsumismenotEur().intValue());
        List<String> localizedResult = solver.resolve(application.getDecisionElements());
        for (String s : localizedResult) {
            LOG.info("Localized: {}", s);
        }
    }
}
