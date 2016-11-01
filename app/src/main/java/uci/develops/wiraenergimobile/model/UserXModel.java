package uci.develops.wiraenergimobile.model;

/**
 * Created by user on 11/1/2016.
 */

public class UserXModel {
    private int id;
    private String user_id;
    private String registration_key;
    private String customer_code;
    private String name;
    private String email;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String updated_by;
    private boolean activated;
    private String detail;
    private String deleted_at;

    public UserXModel(int id, String user_id, String registration_key, String customer_code, String name, String email, String created_at, String updated_at, String created_by, String updated_by, boolean activated, String detail, String deleted_at) {
        this.id = id;
        this.user_id = user_id;
        this.registration_key = registration_key;
        this.customer_code = customer_code;
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.activated = activated;
        this.detail = detail;
        this.deleted_at = deleted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRegistration_key() {
        return registration_key;
    }

    public void setRegistration_key(String registration_key) {
        this.registration_key = registration_key;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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
