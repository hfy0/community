package com.nowcoder.community.MetricCounter.Viewer;

import com.nowcoder.community.MetricCounter.RequestStat;

import java.util.Map;

public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);
}
