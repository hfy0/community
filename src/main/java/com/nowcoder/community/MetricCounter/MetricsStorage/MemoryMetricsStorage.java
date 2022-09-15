package com.nowcoder.community.MetricCounter.MetricsStorage;

import com.nowcoder.community.MetricCounter.Aggregator;
import com.nowcoder.community.MetricCounter.Reporter.ConsoleReporter;
import com.nowcoder.community.MetricCounter.RequestInfo;
import com.nowcoder.community.MetricCounter.Viewer.ConsoleViewer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemoryMetricsStorage implements MetricsStorage {

    Map<String, List<RequestInfo>> requestInfos = new HashMap<>();

    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        requestInfos.putIfAbsent(requestInfo.getApiName(), new ArrayList<>());
        List<RequestInfo> requestInfoList = requestInfos.get(requestInfo.getApiName());
        requestInfoList.add(requestInfo);
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        List<RequestInfo> requestInfoList = requestInfos.get(apiName);
        ArrayList<RequestInfo> result = new ArrayList<>();
        for (RequestInfo info : requestInfoList) {
            if (info.getTimestamp() >= startTimeInMillis && info.getTimestamp() <= endTimeInMillis) {
                result.add(info);
            }
        }
        return result;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        HashMap<String, List<RequestInfo>> resultMap = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> apiEntry : requestInfos.entrySet()) {
            ArrayList<RequestInfo> result = new ArrayList<>();
            for (RequestInfo requestInfo : apiEntry.getValue()) {
                if (requestInfo.getTimestamp() >= startTimeInMillis && requestInfo.getTimestamp() <= endTimeInMillis) {
                    result.add(requestInfo);
                }
            }
            resultMap.put(apiEntry.getKey(), result);
        }
        return resultMap;
    }
}
