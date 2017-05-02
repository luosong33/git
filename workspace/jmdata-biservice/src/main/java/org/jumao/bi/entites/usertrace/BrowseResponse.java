package org.jumao.bi.entites.usertrace;

import java.io.Serializable;
import java.util.List;

import org.jumao.bi.entites.CommonResponse;

public class BrowseResponse extends CommonResponse implements Serializable{
    

    private static final long serialVersionUID = -8044745529836098163L;
    private List<BrowseBean> browses;

    public List<BrowseBean> getBrowses() {
        return browses;
    }

    public void setBrowses(List<BrowseBean> browses) {
        this.browses = browses;
    }
    

}
