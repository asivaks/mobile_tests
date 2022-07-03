package config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE) //http://owner.aeonbits.org/docs/loading-strategies/
@Config.Sources({
        "classpath:galaxyA51.properties"
})
// TODO: 04.07.2022 We are using the same code except this from one config to another. Can we somehow pass just a source?

public interface GalaxyA51Config extends Config {

    // TODO: 04.07.2022  why do I have to add it just to highlight this property in .property file?
    @Key("platformName")
    String platformName();

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

}
