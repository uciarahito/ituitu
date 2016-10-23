package uci.develops.wiraenergimobile.response;


import java.util.List;

import uci.develops.wiraenergimobile.model.CustomerModel;

/**
 * Created by ArahitoPC .
 */
public class RequestListCustomerResponse {
    private String status;
    private String code;
    private String info;
    private List<CustomerModel> data;

    public RequestListCustomerResponse(String status, String code, String info, List<CustomerModel> data) {
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

    public List<CustomerModel> getData() {
        return data;
    }

    public void setData(List<CustomerModel> data) {
        this.data = data;
    }
}


