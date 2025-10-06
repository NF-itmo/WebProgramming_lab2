package org.web2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean {
    private double x;
    private double y;
    private double r;
    
    private boolean hit;
    private Date time;
    private long elapsedTimeNs;
}
