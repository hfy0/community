package com.nowcoder.community.MetricCounter;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Aggregator {
    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis / 1000));
        return requestStat;
    }

    private double max(List<Double> dataset) {
        double result = -1;
        for (Double aDouble : dataset) {
            result = Math.max(result, aDouble);
        }
        return result;
    }

    private double min(List<Double> dataset) {
        double result = Integer.MAX_VALUE;
        for (Double aDouble : dataset) {
            result = Math.min(aDouble, result);
        }
        return result;
    }

    private double avg(List<Double> dataset) {
        double result = -1;
        if (!dataset.isEmpty()) {
            double sum = 0;
            for (Double aDouble : dataset) {
                sum += aDouble;
            }
            result = sum / dataset.size();
        }
        return result;
    }

    private double tps(int count, double duration) {
        if (duration == 0) {
            return 0;
        }
        return count * 1.0 / duration;
    }
}
