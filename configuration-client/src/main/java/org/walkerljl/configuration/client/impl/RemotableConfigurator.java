package org.walkerljl.configuration.client.impl;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * RemotableConfigurator
 *
 * @author: lijunlin
 */
public interface RemotableConfigurator extends Remote {

    /**
     * Get id
     * @return
     */
    String getId() throws RemoteException;

    /**
     * Get name
     * @return
     */
    String getName() throws RemoteException;

    /**
     * Get groud
     * @return
     */
    String getGroup() throws RemoteException;

    /**
     * Init
     *
     * @throws AppException
     */
    void init() throws AppException, RemoteException;

    /**
     * Destroy
     */
    void destroy() throws RemoteException;

    /**
     * Get instance id
     * @return
     */
    String getInstanceId() throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return Byte value
     * @throws RemoteException
     */
    Byte getAsByte(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Byte Object
     * @throws RemoteException
     */
    Byte getAsByte(String key, Byte defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return Short object
     * @throws RemoteException
     */
    Short getAsShort(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Short object
     * @throws RemoteException
     */
    Short getAsShort(String key, Short defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return Integer object
     * @throws RemoteException
     */
    Integer getAsInteger(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Integer object
     * @throws RemoteException
     */
    Integer getAsInteger(String key, Integer defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return Long object
     * @throws RemoteException
     */
    Long getAsLong(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Long object
     * @throws RemoteException
     */
    Long getAsLong(String key, Long defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return Float object
     * @throws RemoteException
     */
    Float getAsFloat(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Float object
     * @throws RemoteException
     */
    Float getAsFloat(String key, Float defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return Double object
     * @throws RemoteException
     */
    Double getAsDouble(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default vlaue
     * @return Double object
     * @throws RemoteException
     */
    Double getAsDouble(String key, Double defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return Character object
     * @throws RemoteException
     */
    Character getAsChar(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Character object
     * @throws RemoteException
     */
    Character getAsChar(String key, Character defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return Boolean object
     * @throws RemoteException
     */
    Boolean getAsBoolean(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return Boolean object
     * @throws RemoteException
     */
    Boolean getAsBoolean(String key, Boolean defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key key
     * @return String object
     * @throws RemoteException
     */
    String getAsString(String key) throws RemoteException;

    /**
     * Get property
     *
     * @param key          key
     * @param defaultValue Default value
     * @return String object
     * @throws RemoteException
     */
    String getAsString(String key, String defaultValue) throws RemoteException;

    /**
     * Get property
     *
     * @param key   key
     * @param regex regex
     * @return String array
     * @throws RemoteException
     */
    String[] getAsStringArray(String key, String regex) throws RemoteException;

    /**
     * Get property
     *
     * @param type  Data type
     * @param key   key
     * @param regex regex
     * @return List<String> object
     * @throws RemoteException
     */
    <T> List<T> getAsList(Class<T> type, String key, String regex) throws RemoteException;

    /**
     * Get property
     *
     * @param type  Data type
     * @param key   key
     * @param regex regex
     * @return Set<String> object
     * @throws RemoteException
     */
    <T> Set<T> getAsSet(Class<T> type, String key, String regex) throws RemoteException;

    /**
     * Get property
     *
     * @param type  Object type
     * @param key   key
     * @param regex regex
     * @return Map<String, String> object
     * @throws RemoteException
     */
    <T> Map<String, T> getAsMap(Class<T> type, String key, String regex, String entryRegex)
            throws RemoteException;

    /**
     * Set property<br>
     * Replace the old value if the value exists
     *
     * @param key
     * @param value
     * @throws RemoteException
     */
    void set(String key, String value) throws RemoteException;

    /**
     * Del property by the key
     *
     * @param key
     * @throws RemoteException
     */
    void del(String key) throws RemoteException;
}