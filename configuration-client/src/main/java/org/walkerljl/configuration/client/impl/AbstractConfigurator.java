package org.walkerljl.configuration.client.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * AbstractConfigurator
 *
 * @author lijunlin
 */
public abstract class AbstractConfigurator implements RemotableConfigurator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConfigurator.class);
    protected ConfiguratorResources resources;

    public AbstractConfigurator(ConfiguratorResources resources) {
        this.resources = resources;
    }

    /**
     * Get property
     * @param key
     * @return
     */
    private Object get(String key) {
        ConfiguratorProviderRepository repository = ConfiguratorProviderRepository
                .getInstance();
        Collection<ConfiguratorProvider> providers = repository.lookupAll();
        if (providers == null || providers.isEmpty()) {
            return null;
        }
        for (ConfiguratorProvider provider : providers) {
            Object value = provider.get(key);
            try {
                provider.init();
            } catch (Throwable e) {
                throw new AppException(e);
            }
            if (value != null) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(String.format("Using configurator provider:%s,config key:%s,config value:%s.",
                            provider.getId(), key, value));
                }
                return value;
            }
        }
        return null;
    }

    @Override
    public Byte getAsByte(String key) throws RemoteException {
        return getAsByte(key, null);
    }

    @Override
    public Byte getAsByte(String key, Byte defaultValue) throws RemoteException {
        String value = getAsString(key);
        return value == null ? defaultValue : Byte.valueOf(value);
    }

    @Override
    public Short getAsShort(String key) throws RemoteException {
        return getAsShort(key, null);
    }

    @Override
    public Short getAsShort(String key, Short defaultValue) throws RemoteException {
        String value = getAsString(key);
        return value == null ? defaultValue : Short.valueOf(value);
    }

    @Override
    public Integer getAsInteger(String key) throws RemoteException {
        return getAsInteger(key, null);
    }

    @Override
    public Integer getAsInteger(String key, Integer defaultValue) throws RemoteException {
        String value = getAsString(key);
        return value == null ? defaultValue : Integer.valueOf(value);
    }

    @Override
    public Long getAsLong(String key) throws RemoteException {
        return getAsLong(key, null);
    }

    @Override
    public Long getAsLong(String key, Long defaultValue) throws RemoteException {
        String value = getAsString(key);
        return value == null ? defaultValue : Long.valueOf(value);
    }

    @Override
    public Float getAsFloat(String key) throws RemoteException {
        return getAsFloat(key, null);
    }

    @Override
    public Float getAsFloat(String key, Float defaultValue) throws RemoteException {
        String value = getAsString(key);
        return value == null ? defaultValue : Float.valueOf(value);
    }

    @Override
    public Double getAsDouble(String key) throws RemoteException {
        return getAsDouble(key, null);
    }

    @Override
    public Double getAsDouble(String key, Double defaultValue) throws RemoteException {
        String value = getAsString(key);
        return value == null ? defaultValue : Double.valueOf(value);
    }

    @Override
    public Character getAsChar(String key) throws RemoteException {
        return getAsChar(key, null);
    }

    @Override
    public Character getAsChar(String key, Character defaultValue) throws RemoteException {
        String value = getAsString(key);
        return value == null ? defaultValue : Character.valueOf(value.charAt(0));
    }

    @Override
    public Boolean getAsBoolean(String key) throws RemoteException {
        return getAsBoolean(key, false);
    }

    @Override
    public Boolean getAsBoolean(String key, Boolean defaultValue) throws RemoteException {
        String value = getAsString(key);
        return value == null ? defaultValue : Boolean.valueOf(value);
    }

    @Override
    public String getAsString(String key) throws RemoteException {
        return getAsString(key, null);
    }

    @Override
    public String getAsString(String key, String defaultValue) throws RemoteException {
        Object value = get(key);
        return value == null ? defaultValue : value.toString();
    }

    @Override
    public String[] getAsStringArray(String key, String regex) throws RemoteException {
        String value = getAsString(key);
        return value == null ? new String[0] : value.split(regex);
    }

    @Override
    public <T> List<T> getAsList(Class<T> type, String key, String regex) throws RemoteException {
        String value = getAsString(key);
        List<T> items = new ArrayList<T>();
        if (value != null) {
            String[] array = value.split(regex);
            addElementToCollection(items, type, array);
        }
        return items;
    }

    @Override
    public <T> Set<T> getAsSet(Class<T> type, String key, String regex) throws RemoteException {
        String value = getAsString(key);
        Set<T> items = new HashSet<T>();
        if (value != null) {
            String[] array = value.split(regex);
            addElementToCollection(items, type, array);
        }
        return items;
    }

    /**
     * 添加元素到集合
     *
     * @param collection
     * @param elementType
     * @param elements
     * @param <T>
     */
    private <T> void addElementToCollection(Collection<T> collection, Class<T> elementType, String... elements) {
        if (elements != null && elements.length != 0) {
            for (String element : elements) {
                collection.add(valueOf(elementType, element));
            }
        }
    }

    @Override
    public <T> Map<String, T> getAsMap(Class<T> type, String key, String regex, String entryRegex)
            throws RemoteException {
        String value = getAsString(key);
        Map<String, T> map = new HashMap<String, T>();
        if (value != null) {
            String[] array = value.split(regex);
            if (array != null && array.length != 0) {
                for (String item : array) {
                    String[] entry = item.split(entryRegex);
                    if (entry != null && entry.length > 1) {
                        map.put(entry[0], valueOf(type, entry[1]));
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获取值
     *
     * @param type
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T valueOf(Class<T> type, String value) {
        if (value == null || "".equals(value) || value.length() == 0) {
            return null;
        } else if (type == Byte.class) {
            return (T) Byte.valueOf(value);
        } else if (type == Short.class) {
            return (T) Short.valueOf(value);
        } else if (type == Character.class) {
            return (T) Character.valueOf(value.charAt(0));
        } else if (type == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (type == Long.class) {
            return (T) Long.valueOf(value);
        } else if (type == Float.class) {
            return (T) Float.valueOf(value);
        } else if (type == Double.class) {
            return (T) Double.valueOf(value);
        } else if (type == Boolean.class) {
            return (T) Boolean.valueOf(value);
        } else if (type == String.class) {
            return (T) value;
        } else {
            throw new AppException("Can not support type:" + type);
        }
    }

    @Override
    public void set(String key, String value) throws RemoteException {
        throw new AppException("Does not support this method");
    }

    @Override
    public void del(String key) throws RemoteException {
        throw new AppException("Does not support this method");
    }
}
