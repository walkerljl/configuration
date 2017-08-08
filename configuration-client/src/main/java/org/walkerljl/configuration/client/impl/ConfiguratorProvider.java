package org.walkerljl.configuration.client.impl;

import org.walkerljl.toolkit.standard.Resource;

/**
 * Configurator provider
 *
 * @author: lijunlin
 */
public interface ConfiguratorProvider extends Resource {

    /**
     * Get
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * Set
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * Del
     *
     * @param key
     */
    void del(String key);
}