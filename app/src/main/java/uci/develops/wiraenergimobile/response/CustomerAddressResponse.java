package uci.develops.wiraenergimobile.response;


import java.util.List;

import uci.develops.wiraenergimobile.model.CustomerAddressModel;

/**
 * Created by ArahitoPC .
 */
public class CustomerAddressResponse {
    private String status;
    private String code;
    private String info;
    private CustomerAddressModel data;

    public CustomerAddressResponse(){}

    public CustomerAddressResponse(CustomerAddressModel data, String status, String code, String info) {
        this.data = data;
        this.status = status;
        this.code = code;
        this.info = info;
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

    public CustomerAddressModel getData() {
        return data;
    }

    public void setData(CustomerAddressModel data) {
        this.data = data;
    }
}


