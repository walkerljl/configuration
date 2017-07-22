package org.walkerljl.toolkit.configuration;

import org.walkerljl.toolkit.configuration.impl.readonly.PropertiesConfiguratorProvider;

/**
 * @author: lijunlin
 */
public class ConfiguratorFactoryTest {

    public static void main(String[] args) {

        ConfiguratorFactory configuratorFactory = ConfiguratorFactory.getIns();
        configuratorFactory.bind(new PropertiesConfiguratorProvider("org/walkerljl/commons/conf.properties"));

        Configurator stdConfigurator = configuratorFactory.getStdConfigurator();
        System.out.println(stdConfigurator.getAsString("userid"));
    }
}
