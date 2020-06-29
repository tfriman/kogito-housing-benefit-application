package demosoft.service;

import demosoft.domain.BenefitApplication;
import demosoft.domain.Language;
import demosoft.domain.decision.DecisionElementSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Locale;

/**
 * Called by the business process. Converts result elements as human readable result explanations.
 */
@ApplicationScoped
public class ResultTransformerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultTransformerService.class);

    @Inject
    DecisionElementSolver solver;

    public BenefitApplication process(final BenefitApplication application) {
        Language language = application.getLanguage();
        LOGGER.info("process called with lang: {} and solver {}", language, solver);
        List<String> localizedResult = solver.resolve(application.getDecisionElements(), new Locale(language.name()));
        application.setLocalizedDecisions(localizedResult);
        return application;
    }

}
