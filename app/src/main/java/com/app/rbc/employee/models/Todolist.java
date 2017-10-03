
package com.app.rbc.employee.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Todolist {

    @SerializedName("data")
    private List<Task> mData;
    @SerializedName("meta")
    private Meta mMeta;

    @SerializedName("data1")
    private List<Task> mData1;


    public List<Task> getData1() {
        return mData1;
    }

    public void setData1(List<Task> data) {
        mData1 = data;
    }

    public List<Task> getData() {
        return mData;
    }

    public void setData(List<Task> data) {
        mData = data;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta) {
        mMeta = meta;
    }

    public class Meta {

        @SerializedName("message")
        private String mMessage;
        @SerializedName("status")
        private String mStatus;

        public String getMessage() {
            return mMessage;
        }

        public void setMessage(String message) {
            mMessage = message;
        }

        public String getStatus() {
            return mStatus;
        }

        public void setStatus(String status) {
            mStatus = status;
        }

    }

    public class Data {

        @SerializedName("deadline")
        private String mDeadline;
        @SerializedName("from_user")
        private String mFromUser;
        @SerializedName("status")
        private String mStatus;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("to_user")
        private String mToUser;
        @SerializedName("task_id")
        private String mTask_id;

        @SerializedName("type")
        private String mTask_type;

        @SerializedName("unread_count")
        private Long mUnread_count;

        public Long getUnread_count() {
            return mUnread_count;
        }

        public void setUnread_count(Long mUnread_count) {
            this.mUnread_count = mUnread_count;
        }

        public String getTask_type() {
            return mTask_type;
        }

        public void setTask_type(String mTask_type) {
            this.mTask_type = mTask_type;
        }

        public String getTask_id() {
            return mTask_id;
        }

        public void setTask_id(String mTask_id) {
            this.mTask_id = mTask_id;
        }

        public String getDeadline() {
            return mDeadline;
        }

        public void setDeadline(String deadline) {
            mDeadline = deadline;
        }

        public String getFromUser() {
            return mFromUser;
        }

        public void setFromUser(String fromUser) {
            mFromUser = fromUser;
        }

        public String getStatus() {
            return mStatus;
        }

        public void setStatus(String status) {
            mStatus = status;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getToUser() {
            return mToUser;
        }

        public void setToUser(String toUser) {
            mToUser = toUser;
        }

    }


    public class Data1 {

        @SerializedName("deadline")
        private String mDeadline;
        @SerializedName("from_user")
        private String mFromUser;
        @SerializedName("status")
        private String mStatus;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("to_user")
        private String mToUser;

        @SerializedName("task_id")
        private String mTask_id;

        @SerializedName("type")
        private String mTask_type;

        @SerializedName("unread_count")
        private Long mUnread_count;

        public Long getUnread_count() {
            return mUnread_count;
        }

        public void setUnread_count(Long mUnread_count) {
            this.mUnread_count = mUnread_count;
        }

        public String getTask_type() {
            return mTask_type;
        }

        public String getTask_id() {
            return mTask_id;
        }

        public void setTask_id(String mTask_id) {
            this.mTask_id = mTask_id;
        }

        public String getDeadline() {
            return mDeadline;
        }

        public void setDeadline(String deadline) {
            mDeadline = deadline;
        }

        public String getFromUser() {
            return mFromUser;
        }

        public void setFromUser(String fromUser) {
            mFromUser = fromUser;
        }

        public String getStatus() {
            return mStatus;
        }

        public void setStatus(String status) {
            mStatus = status;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getToUser() {
            return mToUser;
        }

        public void setToUser(String toUser) {
            mToUser = toUser;
        }

    }

}
