package org.jumao.bi.entites;

import java.io.Serializable;

public class ParamBean implements Serializable {
    

    private static final long serialVersionUID = 909766822573450024L;
    private String platform;
    private String startDate;
    private String endDate;
    
    public ParamBean(String platform, String startDate, String endDate) {
        this.platform = platform;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
    

}
