package org.walkerljl.configuration.client.impl;

import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.exception.resouce.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.exception.resouce.CannotInitResourceException;

/**
 * 抽象的配置器提供者
 *
 * @author: lijunlin
 */
public abstract class AbstractConfiguratorProvider extends AbstractResource implements ConfiguratorProvider {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 默认构造函数
     */
    public AbstractConfiguratorProvider() {}

    /**
     * 包装Key,统一转换成小写的Key且trim前后空格
     *
     * @param key
     * @return
     */
    protected String wrapKey(String key) {
        return key == null ? null : key.trim().toLowerCase();
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public void del(String key) {

    }

    @Override
    public String getName() {
        return getId();
    }

    @Override
    public String getGroup() {
        return "orgwalkerljl-configurator-provider";
    }

    @Override
    public void processInit() throws CannotInitResourceException {

    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {

    }
}