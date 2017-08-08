package org.walkerljl.configuration.client.impl;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.walkerljl.configuration.client.jmx.ConfiguratorMXBean;
import org.walkerljl.configuration.client.jmx.impl.ConfiguratorMXBeanImpl;
import org.walkerljl.configuration.client.ConfiguratorException;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * AbstractConfigurator
 *
 * @author lijunlin
 */
public class CommonConfigurator extends AbstractConfigurator implements RemotableConfigurator, Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonConfigurator.class);

    private boolean            boundRemotely = false;
    private ConfiguratorMXBean jmxBean       = null;

    public CommonConfigurator(ConfiguratorResources resources) {
        super(resources);
    }

    @Override
    public String getId() throws RemoteException {
        return null;
    }

    @Override
    public String getName() throws RemoteException {
        return null;
    }

    @Override
    public String getGroup() throws RemoteException {
        return null;
    }

    @Override
    public void init() throws AppException {
        try {
            //bindRmi();

            registerJMX();
        } catch (Exception re) {
            throw new ConfiguratorException(
                    "Unable to bind configurator to RMI Registry.", re);
        }
    }

    @Override
    public void destroy() {
        try {
            unregisterJMX();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public String getInstanceId() throws RemoteException {
        return null;
    }

    /**
     * Register the configurator in the local MBeanServer.
     */
    private void registerJMX() throws Exception {
        if (resources.getRmiRegistryPort() <= 0 || resources.getRmiRegistryHost() == null || "".equals(
                resources.getRmiRegistryHost().trim())) {
            return;
        }
        String jmxObjectName = resources.getJMXObjectName();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        jmxBean = new ConfiguratorMXBeanImpl(this);
        mbs.registerMBean(jmxBean, new ObjectName(jmxObjectName));

        //        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        //        mbs.registerMBean(adapter, new ObjectName("ConfiguratorAgent:name=confAgent"));
        //        adapter.start();
    }

    /**
     * Unregister the scheduler from the local MBeanServer.
     */
    private void unregisterJMX() throws Exception {
        String jmxObjectName = resources.getJMXObjectName();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        mbs.unregisterMBean(new ObjectName(jmxObjectName));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Configurator unregistered from name '" + jmxObjectName + "' in the local MBeanServer.");
        }
    }

    /**
     * Bind RMI service
     *
     * @throws RemoteException
     * @throws MalformedURLException
     */
    private void bindRmi() throws RemoteException, MalformedURLException {
        if (resources.getRmiRegistryPort() <= 0 || resources.getRmiRegistryHost() == null || "".equals(
                resources.getRmiRegistryHost().trim())) {
            return;
        }
        LocateRegistry.createRegistry(resources.getRmiRegistryPort());
        Naming.rebind(resources.getRmiRegistryHost(), this);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RMI bind, name is " + resources.getRmiRegistryHost() + ", object is " + this);
        }
        boundRemotely = true;
    }
}