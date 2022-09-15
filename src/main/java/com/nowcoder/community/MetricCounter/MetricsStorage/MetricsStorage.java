package com.nowcoder.community.MetricCounter.MetricsStorage;

import com.nowcoder.community.MetricCounter.RequestInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 数据存储
 */
public interface MetricsStorage {
    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
