package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 11/28/2016.
 */

public class SalesmanModel {
    private int id;
    private String decode;
    private String name;
    private String type;
    private String address;
    private String phone;
    private String mobile;
    private String email;
    private int priority;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private int active;
    private String detail;
    private String deleted_at;

    public SalesmanModel(int id, String decode, String name, String type, String address, String phone, String mobile, String email, int priority, String created_at, String updated_at, String register_at, String created_by, String updated_by, int active, String detail, String deleted_at) {
        this.id = id;
        this.decode = decode;
        this.name = name;
        this.type = type;
        this.address = address;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.priority = priority;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.active = active;
        this.detail = detail;
        this.deleted_at = deleted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecode() {
        return decode;
    }

    public void setDecode(String decode) {
        this.decode = decode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getRegister_at() {
        return register_at;
    }

    public void setRegister_at(String register_at) {
        this.register_at = register_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
