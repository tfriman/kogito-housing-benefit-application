package demosoft.domain.decision;

import demosoft.domain.BenefitApplication;

import java.util.Arrays;
import java.util.function.IntFunction;

public class DecisionElement {
    private String[] keys;
    private String rule;

    public DecisionElement() {
    }

    public DecisionElement(String ruleKey, Object... keys) {
        //System.out.println("DecisionElement created with rule = " + rule + ", application = " + application);
        this.rule = ruleKey;
        this.keys = Arrays.stream(keys).map(Object::toString).toArray(String[]::new);
    }

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "DE{" +
                "rule='" + rule + '\'' +
                '}';
    }

    public DecisionElement ruleName(String rule) {
        this.rule = rule;
        return this;
    }
}
