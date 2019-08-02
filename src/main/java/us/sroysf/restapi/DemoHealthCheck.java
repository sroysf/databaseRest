package us.sroysf.restapi;

public class DemoHealthCheck extends com.codahale.metrics.health.HealthCheck {

    public DemoHealthCheck() {
        super();
    }

    /* (non-Javadoc)
     * @see com.codahale.metrics.health.HealthCheck#check()
     */
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

}
