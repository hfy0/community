package com.nowcoder.community.MetricCounter.Reporter;

import com.nowcoder.community.MetricCounter.Aggregator;
import com.nowcoder.community.MetricCounter.MetricsStorage.MetricsStorage;
import com.nowcoder.community.MetricCounter.RequestInfo;
import com.nowcoder.community.MetricCounter.RequestStat;
import com.nowcoder.community.MetricCounter.Viewer.StatViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ConsoleReporter {
    @Autowired
    private MetricsStorage metricsStorage;
    @Autowired
    private Aggregator aggregator;
    @Autowired
    private StatViewer viewer;
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();;

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("定时任务");
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMillis);
                viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
            }
        }, 0L, periodInSeconds, TimeUnit.SECONDS);
    }
}