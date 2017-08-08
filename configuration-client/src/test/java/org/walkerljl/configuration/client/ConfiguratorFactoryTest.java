package org.walkerljl.configuration.client;

import org.walkerljl.configuration.client.impl.readonly.PropertiesConfiguratorProvider;

/**
 * @author: lijunlin
 */
public class ConfiguratorFactoryTest {

    public static void main(String[] args) {

        ConfiguratorFactory.bind(new PropertiesConfiguratorProvider("org/walkerljl/commons/conf.properties"));

        Configurator stdConfigurator = ConfiguratorFactory.getStdConfigurator();
        System.out.println(stdConfigurator.getAsString("userid"));
    }
}
