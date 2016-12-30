package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 12/28/2016.
 */

public class TransDOTrackingModel {
    private int id;
    private String decode;
    private String do_decode;
    private String driver_decode;
    private String vehicle_decode;
    private String longitude;
    private String tracking_date;
    private int priority;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private String detail;
    private String deleted_at;

    public TransDOTrackingModel(String deleted_at, int id, String decode, String do_decode, String driver_decode, String vehicle_decode, String longitude, String tracking_date, int priority, String created_at, String updated_at, String register_at, String created_by, String updated_by, String detail) {
        this.deleted_at = deleted_at;
        this.id = id;
        this.decode = decode;
        this.do_decode = do_decode;
        this.driver_decode = driver_decode;
        this.vehicle_decode = vehicle_decode;
        this.longitude = longitude;
        this.tracking_date = tracking_date;
        this.priority = priority;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.detail = detail;
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

    public String getDo_decode() {
        return do_decode;
    }

    public void setDo_decode(String do_decode) {
        this.do_decode = do_decode;
    }

    public String getDriver_decode() {
        return driver_decode;
    }

    public void setDriver_decode(String driver_decode) {
        this.driver_decode = driver_decode;
    }

    public String getVehicle_decode() {
        return vehicle_decode;
    }

    public void setVehicle_decode(String vehicle_decode) {
        this.vehicle_decode = vehicle_decode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTracking_date() {
        return tracking_date;
    }

    public void setTracking_date(String tracking_date) {
        this.tracking_date = tracking_date;
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
