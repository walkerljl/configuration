package org.walkerljl.configuration.client.impl.readonly;

import java.util.List;

/**
 * 配置信息加载器
 *
 * @author lijunlin
 */
public interface ConfigInfoLoader {

    /**
     * 分页加载配置信息
     *
     * @param startIndex 开始索引
     * @param pageSize   每页大小
     * @return
     */
    List<ConfigInfo> getConfigInfo(long startIndex, int pageSize);

    /**
     * 获取配置信息总数
     *
     * @return
     */
    long getConfigInfoCount();
}