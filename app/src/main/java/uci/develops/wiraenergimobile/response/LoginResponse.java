package uci.develops.wiraenergimobile.response;

import java.util.List;

import uci.develops.wiraenergimobile.model.RoleModel;

/**
 * Created by user on 10/22/2016.
 */
public class LoginResponse {
    private String status;
    private String code;
    private String info;
    private String token;
    private int user_id;
    private boolean activated;
    private List<Integer> roles;
    private String customer_decode;
    private int active;
    private int approve;

    public LoginResponse(String status, String code, String info, String token, int user_id, boolean activated, List<Integer> roles, String customer_decode, int active, int approve) {
        this.status = status;
        this.code = code;
        this.info = info;
        this.token = token;
        this.user_id = user_id;
        this.activated = activated;
        this.roles = roles;
        this.customer_decode = customer_decode;
        this.active = active;
        this.approve = approve;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public String getCustomer_decode() {
        return customer_decode;
    }

    public void setCustomer_decode(String customer_decode) {
        this.customer_decode = customer_decode;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }
}
