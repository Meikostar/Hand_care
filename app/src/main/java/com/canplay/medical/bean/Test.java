package com.canplay.medical.bean;

/**
 * Created by Administrator on 2018/4/16.
 */

public class Test {

    /**
     * patientDeviceId : e6192688-0b05-e711-b9e4-2c44fd9333fc
     * userId : ccda9cf7-d871-41a5-8548-77022c54f1bf
     * userName : zhangj
     * serialNo : 9999031842
     * deviceType : Blood pressure monitor
     * deviceTypeDisplayName : 慧医-爱奥乐血压计
     * deviceUserId : 0
     * status : Active
     * createdDateTime : 1489093277677
     * lastModified : 1489093277677
     */

    private String patientDeviceId;
    private String userId;
    private String displayName;
    private String serialNo;
    private String deviceType;
    private String deviceTypeDisplayName;
    private String deviceUserId;
    private String status;
    private String createdDateTime;
    private String lastModified;

    public String getPatientDeviceId() {
        return patientDeviceId;
    }

    public void setPatientDeviceId(String patientDeviceId) {
        this.patientDeviceId = patientDeviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return displayName;
    }

    public void setUserName(String userName) {
        this.displayName = userName;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceTypeDisplayName() {
        return deviceTypeDisplayName;
    }

    public void setDeviceTypeDisplayName(String deviceTypeDisplayName) {
        this.deviceTypeDisplayName = deviceTypeDisplayName;
    }

    public String getDeviceUserId() {
        return deviceUserId;
    }

    public void setDeviceUserId(String deviceUserId) {
        this.deviceUserId = deviceUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
}
