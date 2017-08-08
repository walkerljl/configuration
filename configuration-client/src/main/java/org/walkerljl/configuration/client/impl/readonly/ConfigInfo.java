package org.walkerljl.configuration.client.impl.readonly;

import java.io.Serializable;

/**
 * 配置信息
 *
 * @author lijunlin
 */
public class ConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Key
     */
    private String key;
    /**
     * Value
     */
    private String value;
    /**
     * 是否有效,默认有效
     */
    private boolean valid = true;

    /**
     * 默认构造函数
     */
    public ConfigInfo() {
    }

    /**
     * 构造函数
     *
     * @param key
     * @param value
     */
    public ConfigInfo(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 构造函数
     *
     * @param key
     * @param value
     * @param valid
     */
    public ConfigInfo(String key, String value, boolean valid) {
        this.key = key;
        this.value = value;
        this.valid = valid;
    }

    /**
     * 获取配置Key
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置配置Key
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取配置值
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置配置值
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 判断配置信息是否有效
     *
     * @return
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * 设置配置信息是否有效
     *
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "ConfigInfo [key=" + key + ", value=" + value + ", valid="
                + valid + "]";
    }
}