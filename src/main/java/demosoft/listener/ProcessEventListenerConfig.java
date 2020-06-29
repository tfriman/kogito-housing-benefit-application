package demosoft.listener;

import org.kie.kogito.monitoring.process.PrometheusProcessEventListener;
import org.kie.kogito.process.impl.DefaultProcessEventListenerConfig;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessEventListenerConfig extends DefaultProcessEventListenerConfig {

    public ProcessEventListenerConfig() {
        super(new PrometheusProcessEventListener("benefit-application-process"));
    }
}
