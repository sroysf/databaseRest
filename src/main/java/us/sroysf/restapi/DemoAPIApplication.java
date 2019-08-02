package us.sroysf.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import us.sroysf.restapi.resources.HelloResource;

public class DemoAPIApplication extends
        Application<DemoAPIConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(DemoAPIApplication.class);

    public static void main(String[] argv) throws Exception {
        new DemoAPIApplication().run(argv);
    }

    /* (non-Javadoc)
     * @see io.dropwizard.Application#initialize(io.dropwizard.setup.Bootstrap)
     */
    @Override
    public void initialize(Bootstrap<DemoAPIConfiguration> configuration) {
    }

    /* (non-Javadoc)
     * @see io.dropwizard.Application#run(io.dropwizard.Configuration, io.dropwizard.setup.Environment)
     */
    @Override
    public void run(DemoAPIConfiguration configuration,
                    Environment environment) throws Exception {
        log.info("Using first name : " + configuration.getFirstName());
        log.info("Using last name : " + configuration.getLastName());

        final HelloResource helloResource = new HelloResource();
        environment.jersey().register(helloResource);
        environment.healthChecks().register("healthCheck", new DemoHealthCheck());
    }
}
