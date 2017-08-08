package org.walkerljl.configuration.client.impl.readonly;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.walkerljl.configuration.client.impl.AbstractConfiguratorProvider;
import org.walkerljl.configuration.client.impl.ConfiguratorProvider;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * 基于API的只读配置器<br>
 * <li>可按照固定频率更新配置数据,删除老数据
 *
 * @author lijunlin
 */
public class ApiConfiguratorProvider extends AbstractConfiguratorProvider implements ConfiguratorProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiConfiguratorProvider.class);

    /**
     * 分页加载数据,每页大小
     */
    private static final int      PAGE_SIZE             = 100;
    /**
     * 时间单位
     */
    private static final TimeUnit UNIT                  = TimeUnit.SECONDS;
    /**
     * 默认延迟初始化时间
     */
    private static final long     DEFAULT_INITIAL_DELAY = 10;
    /**
     * 默认更新周期
     */
    private static final long     DEFAULT_PERIOD        = 5 * 60;

    /**
     * 配置信息容器
     */
    private Map<String, Object> propertiesMap = null;
    /**
     * 加载器
     */
    private ConfigInfoLoader configInfoLoader;

    /**
     * 构造函数
     * <li>默认延迟10秒钟初始化
     * <li>每五分钟更新一次
     *
     * @param configInfoLoader
     */
    public ApiConfiguratorProvider(ConfigInfoLoader configInfoLoader) {
        this(configInfoLoader, DEFAULT_INITIAL_DELAY, DEFAULT_PERIOD);
    }

    /**
     * 构造函数
     *
     * @param configInfoLoader 配置信息加载器
     * @param initialDelay     初始延迟时间
     * @param period           执行周期
     */
    public ApiConfiguratorProvider(ConfigInfoLoader configInfoLoader, long initialDelay, long period) {
        propertiesMap = new ConcurrentHashMap<String, Object>();
        this.configInfoLoader = configInfoLoader;
        initialDelay = (initialDelay <= 0 ? DEFAULT_INITIAL_DELAY : initialDelay);
        period = (period <= 0 ? DEFAULT_PERIOD : period);
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(
                new ConfigInfoLoaderThread(this.configInfoLoader), initialDelay, period, UNIT);
    }

    @Override
    public Object get(String key) {
        return propertiesMap.get(key);
    }

    @Override
    public String getId() {
        return "ApiConfiguratorProvider";
    }

    @Override
    public String getName() {
        return getId();
    }

    /**
     * 配置信息加载线程
     *
     * @author lijunlin
     */
    private class ConfigInfoLoaderThread implements Runnable {
        /**
         * 配置加载器
         */
        private ConfigInfoLoader configInfoLoader;

        /**
         * 构造函数
         *
         * @param configInfoLoader
         */
        public ConfigInfoLoaderThread(ConfigInfoLoader configInfoLoader) {
            this.configInfoLoader = configInfoLoader;
        }

        @Override
        public void run() {
            try {
                long totalCount = configInfoLoader.getConfigInfoCount();
                if (totalCount <= 0) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(String.format("%s没有加载到配置信息", ApiConfiguratorProvider.class));
                    }
                    return;
                }
                for (long startIndex = 0; startIndex < totalCount; startIndex += PAGE_SIZE) {
                    List<ConfigInfo> configInfos = configInfoLoader.getConfigInfo(startIndex, PAGE_SIZE);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(String.format("%s加载配置信息, totalCount:%s, startIndex:%s, pageSize:%s", ApiConfiguratorProvider.class,
                                totalCount, startIndex, PAGE_SIZE));
                    }
                    for (ConfigInfo configInfo : configInfos) {
                        if (configInfo.isValid()) {
                            propertiesMap.put(wrapKey(configInfo.getKey()), configInfo.getValue());
                            if (LOGGER.isDebugEnabled()) {
                                LOGGER.debug(String.format("%s覆盖配置, Key:%s, Value:%s", ApiConfiguratorProvider.class, configInfo.getKey(),
                                        configInfo.getValue()));
                            }
                        } else {
                            propertiesMap.remove(wrapKey(configInfo.getKey()));
                            if (LOGGER.isDebugEnabled()) {
                                LOGGER.debug(String.format("%s移除配置, Key:%s, Value:%s", ApiConfiguratorProvider.class, configInfo.getKey(),
                                        configInfo.getValue()));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error(String.format("%s更新配置信息出错", ApiConfiguratorProvider.class), e);
            }
        }
    }
}