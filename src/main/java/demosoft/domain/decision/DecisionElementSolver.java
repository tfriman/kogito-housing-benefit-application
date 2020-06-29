package demosoft.domain.decision;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Resolves decision phrases.
 */
@ApplicationScoped
public class DecisionElementSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DecisionElementSolver.class);
    public DecisionElementSolver() {}

    public List<String> resolve(List<DecisionElement> elements, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("resourcebundles.decisionmessages", locale);
        LOGGER.info("resolve with locale: {} and bundle: {}", locale, bundle);
        return elements.stream()
                .map(e -> resolve(e, bundle))
                .collect(Collectors.toUnmodifiableList());

    }

    public String resolve(DecisionElement element, ResourceBundle bundle) {
        String format = bundle.getString(element.getRule());
        return MessageFormat.format(format, element.getKeys());
    }

}
