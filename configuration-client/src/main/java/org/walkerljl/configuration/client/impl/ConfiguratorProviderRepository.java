package org.walkerljl.configuration.client.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.walkerljl.configuration.client.ConfiguratorException;

/**
 * 配置器提供者仓库
 *
 * @author: lijunlin
 */
public class ConfiguratorProviderRepository {

    private static ConfiguratorProviderRepository    instance;
    private        Map<String, ConfiguratorProvider> providers;

    private ConfiguratorProviderRepository() {
        providers = new HashMap<String, ConfiguratorProvider>();
    }

    public static synchronized ConfiguratorProviderRepository getInstance() {
        if (instance == null) {
            instance = new ConfiguratorProviderRepository();
        }
        return instance;
    }

    public synchronized void bind(ConfiguratorProvider provider) throws ConfiguratorException {
        if (providers.get(provider.getId()) != null) {
            throw new ConfiguratorException("ConfiguratorProvider with id '"
                    + provider.getId() + "' already exists.");
        }
        providers.put(provider.getId(), provider);
    }

    public synchronized boolean unbind(String id) {
        return (providers.remove(id) != null);
    }

    public synchronized ConfiguratorProvider lookup(String id) {
        return providers.get(id);
    }

    public synchronized Collection<ConfiguratorProvider> lookupAll() {
        return java.util.Collections
                .unmodifiableCollection(providers.values());
    }

}
