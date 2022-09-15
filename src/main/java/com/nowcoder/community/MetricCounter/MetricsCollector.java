package com.nowcoder.community.MetricCounter;

import com.nowcoder.community.MetricCounter.MetricsStorage.MetricsStorage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据采集
 */
@Component
public class MetricsCollector {
    @Autowired
    private MetricsStorage metricsStorage;

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
