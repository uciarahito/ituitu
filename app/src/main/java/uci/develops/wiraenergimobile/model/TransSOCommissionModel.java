package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 12/28/2016.
 */

public class TransSOCommissionModel {
    private int id;
    private String so_decode;
    private String salesman_decode;
    private String barang_decode;
    private String barang_name;
    private String unit_decode;
    private String unit_name;
    private String commission;
    private String note;
    private int priority;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private int active;
    private int status;
    private String detail;
    private String deleted_at;

    public TransSOCommissionModel(int id, String so_decode, String salesman_decode, String barang_decode, String barang_name, String unit_decode, String unit_name, String commission, String note, int priority, String created_at, String updated_at, String register_at, String created_by, String updated_by, int active, int status, String detail, String deleted_at) {
        this.id = id;
        this.so_decode = so_decode;
        this.salesman_decode = salesman_decode;
        this.barang_decode = barang_decode;
        this.barang_name = barang_name;
        this.unit_decode = unit_decode;
        this.unit_name = unit_name;
        this.commission = commission;
        this.note = note;
        this.priority = priority;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.active = active;
        this.status = status;
        this.detail = detail;
        this.deleted_at = deleted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSo_decode() {
        return so_decode;
    }

    public void setSo_decode(String so_decode) {
        this.so_decode = so_decode;
    }

    public String getSalesman_decode() {
        return salesman_decode;
    }

    public void setSalesman_decode(String salesman_decode) {
        this.salesman_decode = salesman_decode;
    }

    public String getBarang_decode() {
        return barang_decode;
    }

    public void setBarang_decode(String barang_decode) {
        this.barang_decode = barang_decode;
    }

    public String getBarang_name() {
        return barang_name;
    }

    public void setBarang_name(String barang_name) {
        this.barang_name = barang_name;
    }

    public String getUnit_decode() {
        return unit_decode;
    }

    public void setUnit_decode(String unit_decode) {
        this.unit_decode = unit_decode;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
