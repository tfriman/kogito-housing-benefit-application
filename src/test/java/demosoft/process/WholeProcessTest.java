package demosoft.process;

import demosoft.domain.AsuntoTiedot;
import demosoft.domain.BenefitApplication;
import demosoft.domain.Language;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Test the whole e2e stuff.
 */
@QuarkusTest
public class WholeProcessTest {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationProcessTest.class);
    private final LocalDate DATE_2018 = LocalDate.of(2018, 4, 9);
    @Inject
    @Named("application")
    Process<? extends Model> applicationsProcess;

    @Inject
    private DecisionElementSolver solver;

    @Test
    public void happyPathFinnish() {
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
                        .asuinKunta("kuntax" +
                                "" +
                                "")
                        .vuokraEur(500d)
                        .vesiPerHenkiloEur(15d)
                )
                .kuntaRyhma(1)
                .language(Language.FI)
                .alkuPvm(DATE_2018).build()
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
        List<String> localizedResult = solver.resolve(application.getDecisionElements(), new Locale("FI"));
        for (String s : localizedResult) {
            LOG.info("Localized: {}", s);
        }

        localizedResult.stream()
                .filter(this::doesntContainSuomi)
                .forEach(s -> {
                    Assertions.fail("Contained !suomi:" + s);
                });
    }

    @Test
    public void happyPathSwedish() {
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
                        .asuinKunta("kuntax")
                        .vuokraEur(500d)
                        .vesiPerHenkiloEur(15d)
                )
                .kuntaRyhma(1)
                .language(Language.SV)
                .alkuPvm(DATE_2018).build()
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
        List<String> localizedResult = solver.resolve(application.getDecisionElements(), new Locale("SV"));
        for (String s : localizedResult) {
            LOG.info("Localized: {}", s);
        }

        localizedResult.stream()
                .filter(this::doesntContainSvenska)
                .forEach(s -> {
                    Assertions.fail("Contained !svenska:" + s);
                });
    }

    @Test
    public void happyPathEnglish() {
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
                        .asuinKunta("kuntax")
                        .vuokraEur(500d)
                        .vesiPerHenkiloEur(15d)
                )
                .kuntaRyhma(1)
                .language(Language.EN)
                .alkuPvm(DATE_2018).build()
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
        List<String> localizedResult = solver.resolve(application.getDecisionElements(), new Locale("EN"));
        for (String s : localizedResult) {
            LOG.info("Localized: {}", s);
        }

        localizedResult.stream()
                .filter(this::doesntContainEnglish)
                .forEach(s -> Assertions.fail("Contained !svenska:" + s));
    }

    private boolean doesntContainSuomi(String s) {
        return !s.toLowerCase().contains("suom");
    }

    private boolean doesntContainSvenska(String s) {
        return !s.toLowerCase().contains("svenska");
    }

    private boolean doesntContainEnglish(String s) {
        return !s.toLowerCase().contains("english");
    }
}
