package com.nowcoder.community.MetricCounter;

import com.nowcoder.community.MetricCounter.MetricsStorage.MetricsStorage;
import com.nowcoder.community.MetricCounter.Reporter.ConsoleReporter;
import com.nowcoder.community.MetricCounter.Viewer.ConsoleViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner{

    @Autowired
    ConsoleReporter consoleReporter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 定时触发统计并将结果显示到终端
        consoleReporter.startRepeatedReport(10, 10);
    }

}
