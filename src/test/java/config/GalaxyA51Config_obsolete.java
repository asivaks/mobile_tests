//obsolete, replaced by LocalMobileConfig
package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE) //http://owner.aeonbits.org/docs/loading-strategies/
@Config.Sources({
        "classpath:config/galaxyA51.properties"
})

public interface GalaxyA51Config_obsolete extends Config {

    @Key("platformName")
    String platformName();

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

}
