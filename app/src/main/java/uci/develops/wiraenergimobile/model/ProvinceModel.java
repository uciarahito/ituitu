package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 10/21/2016.
 */
public class ProvinceModel {
    private String province_id;
    private String province;

    public ProvinceModel(){}

    public ProvinceModel(String province_id, String province) {
        this.province_id = province_id;
        this.province = province;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
