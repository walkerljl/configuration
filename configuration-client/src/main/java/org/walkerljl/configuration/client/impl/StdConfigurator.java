package org.walkerljl.configuration.client.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.walkerljl.configuration.client.Configurator;
import org.walkerljl.configuration.client.ConfiguratorException;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * @author: lijunlin
 */
public class StdConfigurator implements Configurator {

    private RemotableConfigurator target;

    public StdConfigurator(RemotableConfigurator target) {
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
    public Byte getAsByte(String key) {
        try {
            return target.getAsByte(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Byte getAsByte(String key, Byte defaultValue) {
        try {
            return target.getAsByte(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Short getAsShort(String key) {
        try {
            return target.getAsShort(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Short getAsShort(String key, Short defaultValue) {
        try {
            return target.getAsShort(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Integer getAsInteger(String key) {
        try {
            return target.getAsInteger(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Integer getAsInteger(String key, Integer defaultValue) {
        try {
            return target.getAsInteger(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Long getAsLong(String key) {
        try {
            return target.getAsLong(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Long getAsLong(String key, Long defaultValue) {
        try {
            return target.getAsLong(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Float getAsFloat(String key) {
        try {
            return target.getAsFloat(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Float getAsFloat(String key, Float defaultValue) {
        try {
            return target.getAsFloat(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Double getAsDouble(String key) {
        try {
            return target.getAsDouble(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Double getAsDouble(String key, Double defaultValue) {
        try {
            return target.getAsDouble(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Character getAsChar(String key) {
        try {
            return target.getAsChar(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Character getAsChar(String key, Character defaultValue) {
        try {
            return target.getAsChar(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Boolean getAsBoolean(String key) {
        try {
            return target.getAsBoolean(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public Boolean getAsBoolean(String key, Boolean defaultValue) {
        try {
            return target.getAsBoolean(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
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
    public String getAsString(String key, String defaultValue) {
        try {
            return target.getAsString(key, defaultValue);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public String[] getAsStringArray(String key, String regex) {
        try {
            return target.getAsStringArray(key, regex);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public <T> List<T> getAsList(Class<T> type, String key, String regex) {
        try {
            return target.getAsList(type, key, regex);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public <T> Set<T> getAsSet(Class<T> type, String key, String regex) {
        try {
            return target.getAsSet(type, key, regex);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public <T> Map<String, T> getAsMap(Class<T> type, String key, String regex, String entryRegex) {
        try {
            return target.getAsMap(type, key, regex, entryRegex);
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
