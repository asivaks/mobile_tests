package config;


import org.aeonbits.owner.Config;
import org.checkerframework.checker.units.qual.K;

@Config.LoadPolicy(Config.LoadType.MERGE) //http://owner.aeonbits.org/docs/loading-strategies/
@Config.Sources({
        "classpath:browserstack.properties"
})

public interface BrowserstackConfig extends Config {

    //@Key("browserstackLogin")
    String browserstackLogin();

    //@Key("browserstackPassword")
    String browserstackPassword();

    String app();

    String device();

    //@Key("osVersion")
    String osVersion();

    String project();

    String build();

    String name();

}
