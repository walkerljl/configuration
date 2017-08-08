package org.walkerljl.configuration.client.impl;

import java.io.Serializable;

/**
 * ConfiguratorResources
 *
 * @author: lijunlin
 */
public class ConfiguratorResources implements Serializable {

    public static final String CREATE_REGISTRY_NEVER     = "never";
    public static final String CREATE_REGISTRY_ALWAYS    = "always";
    public static final String CREATE_REGISTRY_AS_NEEDED = "as_needed";

    private String id;
    private String name;
    private String instanceId;
    private String rmiRegistryHost           = null;
    private int    rmiRegistryPort           = 1099;
    private String rmiCreateRegistryStrategy = CREATE_REGISTRY_NEVER;
    private String  rmiBindName;
    private boolean jmxExport;
    private String  jmxObjectName;

    public ConfiguratorResources(String id, String name) {
        this(id, name, null);
    }

    public ConfiguratorResources(String id, String name, String instanceId) {
        setId(id);
        setName(name);
        if (instanceId != null && instanceId.trim().length() != 0) {
            setInstanceId(instanceId);
        }
    }

    public static String getUniqueIdentifier(String id, String insatnceId) {
        return id + "_$_" + insatnceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().length() == 0) {
            throw new IllegalArgumentException(
                    "Configurator id cannot be empty.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException(
                    "Configurator name cannot be empty.");
        }
        this.name = name;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        if (instanceId == null || instanceId.trim().length() == 0) {
            throw new IllegalArgumentException(
                    "Configurator instanceId cannot be empty.");
        }
        this.instanceId = instanceId;
    }

    public void setRMICreateRegistryStrategy(String rmiCreateRegistryStrategy) {
        if (rmiCreateRegistryStrategy == null
                || rmiCreateRegistryStrategy.trim().length() == 0) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_NEVER;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase("true")) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_AS_NEEDED;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase("false")) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_NEVER;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase(CREATE_REGISTRY_ALWAYS)) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_ALWAYS;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase(CREATE_REGISTRY_AS_NEEDED)) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_AS_NEEDED;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase(CREATE_REGISTRY_NEVER)) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_NEVER;
        } else {
            throw new IllegalArgumentException(
                    "Faild to set RMICreateRegistryStrategy - strategy unknown: '"
                            + rmiCreateRegistryStrategy + "'");
        }

        this.rmiCreateRegistryStrategy = rmiCreateRegistryStrategy;
    }

    public String getUniqueIdentifier() {
        return getUniqueIdentifier(id, instanceId);
    }

    public String getRmiRegistryHost() {
        return rmiRegistryHost;
    }

    public void setRmiRegistryHost(String rmiRegistryHost) {
        this.rmiRegistryHost = rmiRegistryHost;
    }

    public int getRmiRegistryPort() {
        return rmiRegistryPort;
    }

    public void setRmiRegistryPort(int rmiRegistryPort) {
        this.rmiRegistryPort = rmiRegistryPort;
    }

    public String getRmiCreateRegistryStrategy() {
        return rmiCreateRegistryStrategy;
    }

    public void setRmiCreateRegistryStrategy(String rmiCreateRegistryStrategy) {
        this.rmiCreateRegistryStrategy = rmiCreateRegistryStrategy;
    }

    public String getRMIBindName() {
        return (rmiBindName == null) ? getUniqueIdentifier() : rmiBindName;
    }

    public void setRMIBindName(String rmiBindName) {
        this.rmiBindName = rmiBindName;
    }

    public boolean getJMXExport() {
        return jmxExport;
    }

    public void setJMXExport(boolean jmxExport) {
        this.jmxExport = jmxExport;
    }

    public String getJMXObjectName() {
        return (jmxObjectName == null) ? generateJMXObjectName(name, instanceId) : jmxObjectName;
    }

    public void setJMXObjectName(String jmxObjectName) {
        this.jmxObjectName = jmxObjectName;
    }

    public static String generateJMXObjectName(String id, String instanceId) {
        return "configurator:type=Configurator" + ",id="
                + id.replaceAll(":|=|\n", ".")
                + ",instance=" + instanceId;
    }
}
