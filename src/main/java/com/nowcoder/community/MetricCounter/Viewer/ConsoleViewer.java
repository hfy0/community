package com.nowcoder.community.MetricCounter.Viewer;

import com.google.gson.Gson;
import com.nowcoder.community.MetricCounter.RequestStat;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConsoleViewer implements StatViewer {
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}
