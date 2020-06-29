package demosoft.domain.decision;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Resolves decision phrases.
 */
public class DecisionElementSolver {
    private final ResourceBundle bundle;

    public DecisionElementSolver(final ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String resolve(DecisionElement element) {
        String format = bundle.getString(element.getRule());
        return MessageFormat.format(format, element.getKeys());
    }

    public List<String> resolve(Collection<DecisionElement> elements) {
        return elements.stream()
                .map(this::resolve)
                .collect(Collectors.toUnmodifiableList());
    }

}
