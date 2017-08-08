package org.walkerljl.configuration.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.walkerljl.toolkit.standard.Resource;

/**
 * Configurator<br>
 * <li>Ignore case,trim the blank of before and after
 *
 * @author lijunlin
 */
public interface Configurator extends Resource {

    /**
     * Get property
     *
     * @param key key
     * @return Byte value
     */
    Byte getAsByte(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Byte Object
     */
    Byte getAsByte(String key, Byte defaultValue);

    /**
     * Get property
     *
     * @param key key
     * @return Short object
     */
    Short getAsShort(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Short object
     */
    Short getAsShort(String key, Short defaultValue);

    /**
     * Get property
     *
     * @param key key
     * @return Integer object
     */
    Integer getAsInteger(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Integer object
     */
    Integer getAsInteger(String key, Integer defaultValue);

    /**
     * Get property
     *
     * @param key key
     * @return Long object
     */
    Long getAsLong(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Long object
     */
    Long getAsLong(String key, Long defaultValue);

    /**
     * Get property
     *
     * @param key key
     * @return Float object
     */
    Float getAsFloat(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Float object
     */
    Float getAsFloat(String key, Float defaultValue);

    /**
     * Get property
     *
     * @param key key
     * @return Double object
     */
    Double getAsDouble(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default vlaue
     * @return Double object
     */
    Double getAsDouble(String key, Double defaultValue);

    /**
     * Get property
     *
     * @param key key
     * @return Character object
     */
    Character getAsChar(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Character object
     */
    Character getAsChar(String key, Character defaultValue);

    /**
     * Get property
     *
     * @param key key
     * @return Boolean object
     */
    Boolean getAsBoolean(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Boolean object
     */
    Boolean getAsBoolean(String key, Boolean defaultValue);

    /**
     * Get property
     *
     * @param key key
     * @return String object
     */
    String getAsString(String key);

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return String object
     */
    String getAsString(String key, String defaultValue);

    /**
     * Get property
     *
     * @param key   key
     * @param regex regex
     * @return String array
     */
    String[] getAsStringArray(String key, String regex);

    /**
     * Get property
     *
     * @param type  Data type
     * @param key   key
     * @param regex regex
     * @return List<String> object
     */
    <T> List<T> getAsList(Class<T> type, String key, String regex);

    /**
     * Get property
     *
     * @param type Data type
     * @param key  key
     * @param regex regex
     * @return Set<String> object
     */
    <T> Set<T> getAsSet(Class<T> type, String key, String regex);

    /**
     * Get property
     *
     * @param type  Object type
     * @param key   key
     * @param regex regex
     * @return Map<String, String> object
     */
    <T> Map<String, T> getAsMap(Class<T> type, String key, String regex, String entryRegex);

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