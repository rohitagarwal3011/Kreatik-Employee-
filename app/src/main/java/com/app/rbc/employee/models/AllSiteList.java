
package com.app.rbc.employee.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AllSiteList {

    @SerializedName("meta")
    private Meta mMeta;
    @SerializedName("site_list")
    private List<SiteDetail> mSiteList;

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta) {
        mMeta = meta;
    }

    public List<SiteDetail> getSiteList() {
        return mSiteList;
    }

    public void setSiteList(List<SiteDetail> siteList) {
        mSiteList = siteList;
    }

}
