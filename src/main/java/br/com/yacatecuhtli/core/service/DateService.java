
package br.com.yacatecuhtli.core.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class DateService {

    public Date getNow() {
        return Date.from(Instant.now());
    }

    public Date addSecondsToDate(Date date, long secondsToAdd) {
        return new Date(date.getTime() + (secondsToAdd * 1000));
    }

}
