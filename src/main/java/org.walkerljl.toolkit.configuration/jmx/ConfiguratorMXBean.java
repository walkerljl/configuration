package org.walkerljl.toolkit.configuration.jmx;

import org.walkerljl.toolkit.standard.Resource;

/**
 * 配置器MBean
 *
 * @author: lijunlin
 */
public interface ConfiguratorMXBean extends Resource {

    /**
     * Get property
     *
     * @param key key
     * @return String object
     */
    String getAsString(String key);

    /**
     * Set property<br>
     * Replace the old value if the value exists
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * Remove property by the key
     *
     * @param key
     */
    void del(String key);
}
