package com.canplay.medical.bean;

/**
 * Created by mykar on 17/4/26.
 */
public class USER {
//    "id": "535",
//            "name": "爱尔巴桑",
//            "parent_id": "534",
//            "type": "city"
//{  
//   "access_token":"dCS5qY_jvKbm6vzYi7YdjgY…",
//   "token_type":"bearer",
//   "expires_in":1209599,
//   "userId":"562e0bb4-c686-468e-ab09-2dcb8e7dfa85",
//   "userName":"gaog",
//   "firstName":"罡",

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

//   "lastName":"高",
//   ".issued":"Sun, 29 Jan 2017 20:44:47 GMT",
//   ".expires":"Sun, 12 Feb 2017 20:44:47 GMT"

    public String access_token;
    public String token_type;
    public String expires_in;
    public String userId;
    public String displayName;
    public String firstName;
    public String lastName;

    public String issued;
    public String expires;

}
