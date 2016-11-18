package uci.develops.wiraenergimobile.response;


import java.util.List;

import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.RoleModel;

/**
 * Created by ArahitoPC .
 */
public class CustomerAddressResponse {
    private String status;
    private String code;
    private String info;
    private List<CustomerAddressModel> data;

    public CustomerAddressResponse(String status, String code, String info, List<CustomerAddressModel> data) {
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

    public List<CustomerAddressModel> getData() {
        return data;
    }

    public void setData(List<CustomerAddressModel> data) {
        this.data = data;
    }
}


