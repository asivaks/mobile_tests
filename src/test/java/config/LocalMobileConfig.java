package config;


import org.aeonbits.owner.Config;

//@Config.LoadPolicy(Config.LoadType.MERGE) //http://owner.aeonbits.org/docs/loading-strategies/
@Config.Sources({
        "classpath:config/${host}.properties"
})

public interface LocalMobileConfig extends Config {

    // TODO: 04.07.2022  why do I have to add it just to highlight this property in .property file?
    @Key("platformName")
    String platformName();

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

}
