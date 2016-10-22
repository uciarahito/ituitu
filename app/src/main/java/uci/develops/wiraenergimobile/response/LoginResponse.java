package uci.develops.wiraenergimobile.response;

/**
 * Created by user on 10/22/2016.
 */
public class LoginResponse {
    private String status;
    private String code;
    private String info;
    private String token;
    private boolean activated;
    private String role;

    public LoginResponse(String status, String code, String info, String token, boolean activated, String role) {
        this.status = status;
        this.code = code;
        this.info = info;
        this.token = token;
        this.activated = activated;
        this.role = role;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
