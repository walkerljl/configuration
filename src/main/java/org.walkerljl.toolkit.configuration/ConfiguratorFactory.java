package org.walkerljl.toolkit.configuration;

import java.io.InputStream;
import java.util.Properties;

import org.walkerljl.toolkit.configuration.impl.CommonConfigurator;
import org.walkerljl.toolkit.configuration.impl.ConfiguratorProvider;
import org.walkerljl.toolkit.configuration.impl.ConfiguratorProviderRepository;
import org.walkerljl.toolkit.configuration.impl.ConfiguratorResources;
import org.walkerljl.toolkit.configuration.impl.RemotableConfigurator;
import org.walkerljl.toolkit.configuration.impl.RemoteConfigurator;
import org.walkerljl.toolkit.configuration.impl.StdConfigurator;
import org.walkerljl.toolkit.lang.PropertiesUtils;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * @author lijunlin
 */
public class ConfiguratorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguratorFactory.class);

    private static Configurator stdConfigurator;
    private static Configurator remoteConfigurator;

    public static ConfiguratorFactory getInstance() {
        return getIns();
    }

    public static ConfiguratorFactory getIns() {
        return ConfiguratorFactoryHolder.instance;
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (stdConfigurator != null) {
                    stdConfigurator.destroy();
                }

                if (remoteConfigurator != null) {
                    remoteConfigurator.destroy();
                }
            }
        });
    }

    /**
     * Bind
     *
     * @param provider
     */
    public void bind(ConfiguratorProvider provider) {
        ConfiguratorProviderRepository.getInstance().bind(provider);
    }

    public void unbind(ConfiguratorProvider provider) {
        ConfiguratorProviderRepository.getInstance().unbind(provider.getId());
    }

    public Configurator getStdConfigurator() {
        if (stdConfigurator == null) {
            synchronized (ConfiguratorFactory.class) {
                if (stdConfigurator == null) {
                    ConfiguratorResources resources = new ConfiguratorResources("1", "StdConfigurator");
                    RemotableConfigurator remotableConfigurator = new CommonConfigurator(resources);
                    stdConfigurator = new StdConfigurator(remotableConfigurator);
                    stdConfigurator.init();
                }
            }
        }
        return stdConfigurator;
    }

    public Configurator getRemoteConfigurator() {
        if (remoteConfigurator == null) {
            synchronized (ConfiguratorFactory.class) {
                if (remoteConfigurator == null) {
                    InputStream ins = null;

                    try {
                        ins = ConfiguratorFactory.class.getResourceAsStream("conf.properties");
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                        throw new ConfiguratorException(e);
                    }
                    Properties properties = PropertiesUtils.createFromInputStream(ins);
                    int port = Integer.parseInt(properties.getProperty("rmiRegistryPort"));
                    String host = properties.getProperty("rmiRegistryHost");

                    ConfiguratorResources resources = new ConfiguratorResources("2", "RemoteConfigurator");
                    resources.setRmiRegistryPort(port);
                    resources.setRmiRegistryHost(host);
                    resources.setRmiCreateRegistryStrategy("always");
                    RemotableConfigurator remotableConfigurator = new CommonConfigurator(resources);
                    remoteConfigurator = new RemoteConfigurator(remotableConfigurator);
                    remoteConfigurator.init();
                }
            }
        }
        return remoteConfigurator;
    }

    /**
     * ConfiguratorFactoryHolder
     *
     * @author lijunlin
     */
    private static class ConfiguratorFactoryHolder {
        private static ConfiguratorFactory instance = new ConfiguratorFactory();
    }
}