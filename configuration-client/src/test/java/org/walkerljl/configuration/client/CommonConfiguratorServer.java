package org.walkerljl.configuration.client;

import java.io.InputStream;
import java.util.Properties;

import org.walkerljl.configuration.client.impl.CommonConfigurator;
import org.walkerljl.configuration.client.impl.ConfiguratorResources;
import org.walkerljl.configuration.client.impl.RemotableConfigurator;
import org.walkerljl.configuration.client.impl.RemoteConfigurator;
import org.walkerljl.toolkit.lang.PropertiesUtils;

/**
 * @author: lijunlin
 */
public class CommonConfiguratorServer {

    public static void main(String[] args) throws InterruptedException {
        InputStream ins = null;

        try {
            ins = ConfiguratorFactory.class.getResourceAsStream("conf.properties");
        } catch(Exception e) {
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
        RemoteConfigurator remoteConfigurator = new RemoteConfigurator(remotableConfigurator);
        remoteConfigurator.init();

        synchronized (CommonConfiguratorServer.class) {
            CommonConfiguratorServer.class.wait();
        }
    }
}
