package uci.develops.wiraenergimobile.response;

import uci.develops.wiraenergimobile.model.UserXModel;

/**
 * Created by user on 11/1/2016.
 */

public class UserResponse {
    private String status;
    private String code;
    private String info;
    private UserXModel data;

    public UserResponse(String status, String code, String info, UserXModel data) {
        this.status = status;
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public UserXModel getData() {
        return data;
    }

    public void setData(UserXModel data) {
        this.data = data;
    }
}
