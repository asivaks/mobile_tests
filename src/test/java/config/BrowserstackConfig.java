package config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE) //http://owner.aeonbits.org/docs/loading-strategies/
@Config.Sources({
        "classpath:config/browserstack.properties"
})

public interface BrowserstackConfig extends Config {

    // TODO: 04.07.2022  why do I have to add it just to highlight this property in .property file?
    @Key("browserstackLogin")
    String browserstackLogin();

    @Key("browserstackPassword")
    String browserstackPassword();

    String app();

    String device();

    @Key("osVersion")
    String osVersion();

    String project();

    String build();

    String name();

}
