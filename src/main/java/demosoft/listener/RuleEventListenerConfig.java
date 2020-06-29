package demosoft.listener;

import org.drools.core.config.DefaultRuleEventListenerConfig;
import org.kie.kogito.monitoring.rule.PrometheusMetricsDroolsListener;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RuleEventListenerConfig extends DefaultRuleEventListenerConfig {

    public RuleEventListenerConfig() {
        super(new PrometheusMetricsDroolsListener("benefit-application-rules"));
    }
}
