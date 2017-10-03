
package com.app.rbc.employee.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CatDesRequirementList {

    @SerializedName("meta")
    private Meta mMeta;
    @SerializedName("req_list")
    private List<RequirementList.ReqList> mReqList;

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta) {
        mMeta = meta;
    }

    public List<RequirementList.ReqList> getReqList() {
        return mReqList;
    }

    public void setReqList(List<RequirementList.ReqList> reqList) {
        mReqList = reqList;
    }




}
