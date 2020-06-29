package demosoft.domain.decision;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class DecisionElementSolverTest {
    private final String KEY_NO_ARGS = "noArgskey";
    private final String KEY_SINGLE_ARG = "singleArgkey";
    private final String KEY_DOUBLE_ARG = "doubleArgkey";
    private final String KEY_REVERSED_DOUBLE_ARG = "reversedDoubleArgkey";

    private final String ARG_1 = "firstarg";
    private final String ARG_2 = "secondarg";

    Locale localeFi = new Locale("fi", "FI");
    Locale localeSe = new Locale("sv", "SE");

    DecisionElementSolver solver = new DecisionElementSolver(ResourceBundle.getBundle("resourcebundles.testresource"));

    private void setUpLocale(Locale locale) {
        ResourceBundle testBundle = ResourceBundle.getBundle("resourcebundles.testresource", locale);
        this.solver = new DecisionElementSolver(testBundle);
    }

    @Test
    void resolveNoArgs() {
        DecisionElement element = new DecisionElement(KEY_NO_ARGS);
        String resolved = solver.resolve(element);
        assertEquals("this is no args key", resolved);
    }

    @Test
    void resolveSingleArg() {
        DecisionElement element = new DecisionElement(KEY_SINGLE_ARG, ARG_1);
        String resolved = solver.resolve(element);
        assertEquals("this is " + ARG_1 + " message", resolved);
    }

    @Test
    void resolveTwoArgs() {
        DecisionElement element = new DecisionElement(KEY_DOUBLE_ARG, ARG_1, ARG_2);
        String resolved = solver.resolve(element);
        assertEquals("this is " + ARG_1 + " and " + ARG_2 + " message", resolved);
    }

    @Test
    void resolveReversedTwoArgs() {
        DecisionElement element = new DecisionElement(KEY_REVERSED_DOUBLE_ARG, ARG_1, ARG_2);
        String resolved = solver.resolve(element);
        assertEquals("this is " + ARG_2 + " and " + ARG_1 + " message", resolved);
    }

    @Test
    void finnishResolveTwoArgs() {
        setUpLocale(localeFi);
        DecisionElement element = new DecisionElement(KEY_DOUBLE_ARG, ARG_1, ARG_2);
        String resolved = solver.resolve(element);
        assertEquals("Tämä on " + ARG_1 + " ja " + ARG_2 + "!", resolved);
    }

    @Test
    void swedishResolveTwoArgs() {
        setUpLocale(localeSe);
        DecisionElement element = new DecisionElement(KEY_DOUBLE_ARG, ARG_1, ARG_2);
        String resolved = solver.resolve(element);
        assertEquals("Det här är " + ARG_1 + " och " + ARG_2 + "!", resolved);
    }
}