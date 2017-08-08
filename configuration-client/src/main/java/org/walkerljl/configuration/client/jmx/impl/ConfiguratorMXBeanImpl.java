package org.walkerljl.configuration.client.jmx.impl;

import org.walkerljl.configuration.client.impl.RemotableConfigurator;
import org.walkerljl.configuration.client.ConfiguratorException;
import org.walkerljl.configuration.client.jmx.ConfiguratorMXBean;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * ConfiguratorMXBeanImpl
 *
 * @author: lijunlin
 */
public class ConfiguratorMXBeanImpl implements ConfiguratorMXBean {

    private RemotableConfigurator target;

    public ConfiguratorMXBeanImpl(RemotableConfigurator target) {
        this.target = target;
    }

    @Override
    public void init() throws AppException {
        try {
            target.init();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            target.destroy();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public String getInstanceId() {
        try {
            return target.getInstanceId();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public String getId() {
        try {
            return target.getId();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public String getName() {
        try {
            return target.getName();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getAsString(String key) {
        try {
            return target.getAsString(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public void set(String key, String value) {
        try {
            target.set(key, value);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public void del(String key) {
        try {
            target.del(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }
}
