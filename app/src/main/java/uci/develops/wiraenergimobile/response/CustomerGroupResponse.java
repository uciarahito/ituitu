package uci.develops.wiraenergimobile.response;

import java.util.List;

import uci.develops.wiraenergimobile.model.CustomerGroupModel;

/**
 * Created by user on 11/1/2016.
 */

public class CustomerGroupResponse {
    private String status;
    private String code;
    private String info;
    private CustomerGroupModel data;

    public CustomerGroupResponse(String status, String code, String info, CustomerGroupModel data) {
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

    public CustomerGroupModel getData() {
        return data;
    }

    public void setData(CustomerGroupModel data) {
        this.data = data;
    }
}
