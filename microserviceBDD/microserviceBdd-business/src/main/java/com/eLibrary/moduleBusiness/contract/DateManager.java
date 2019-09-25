package com.eLibrary.moduleBusiness.contract;

import com.eLibrary.moduleBusiness.enums.ComparisonDate;
import org.springframework.stereotype.Service;

@Service
public interface DateManager {
    String todayDate();
    ComparisonDate compareDateWithToday(String pDate);
}
