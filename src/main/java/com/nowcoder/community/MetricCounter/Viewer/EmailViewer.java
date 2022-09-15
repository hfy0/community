package com.nowcoder.community.MetricCounter.Viewer;

import com.nowcoder.community.MetricCounter.RequestStat;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailViewer implements StatViewer {
    private JavaMailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}
