package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 10/21/2016.
 */
public class CustomerAddressModel {
    private int id;
    private String name;
    private String address;
    private String pic;
    private String phone;
    private String mobile;
    private String map;

    public CustomerAddressModel(){}

    public CustomerAddressModel(int id, String name, String address, String pic, String phone, String mobile, String map) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.pic = pic;
        this.phone = phone;
        this.mobile = mobile;
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
