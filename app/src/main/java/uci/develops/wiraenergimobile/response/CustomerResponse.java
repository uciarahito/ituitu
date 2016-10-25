package uci.develops.wiraenergimobile.response;

import uci.develops.wiraenergimobile.model.CustomerModel;

/**
 * Created by user on 10/26/2016.
 */

public class CustomerResponse {
    private String status;
    private String code;
    private String info;
    private CustomerModel data;

    public CustomerResponse(String status, String code, String info, CustomerModel data) {
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

    public CustomerModel getData() {
        return data;
    }

    public void setData(CustomerModel data) {
        this.data = data;
    }
}
