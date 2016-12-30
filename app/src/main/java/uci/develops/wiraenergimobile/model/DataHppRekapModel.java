package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 11/28/2016.
 */

public class DataHppRekapModel {
    private int id;
    private String decode;
    private String barang_decode;
    private String barang_name;
    private String hpp;
    private String ref_decode;
    private String qty_oh;
    private String qty_po;
    private String qty_so;
    private String rekap_date;
    private int priority;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private String detail;
    private String deleted_at;

    public DataHppRekapModel(int id, String decode, String barang_decode, String barang_name, String hpp, String ref_decode, String qty_oh, String qty_po, String qty_so, String rekap_date, int priority, String created_at, String updated_at, String register_at, String created_by, String updated_by, String detail, String deleted_at) {
        this.id = id;
        this.decode = decode;
        this.barang_decode = barang_decode;
        this.barang_name = barang_name;
        this.hpp = hpp;
        this.ref_decode = ref_decode;
        this.qty_oh = qty_oh;
        this.qty_po = qty_po;
        this.qty_so = qty_so;
        this.rekap_date = rekap_date;
        this.priority = priority;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
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

    public String getHpp() {
        return hpp;
    }

    public void setHpp(String hpp) {
        this.hpp = hpp;
    }

    public String getRef_decode() {
        return ref_decode;
    }

    public void setRef_decode(String ref_decode) {
        this.ref_decode = ref_decode;
    }

    public String getQty_oh() {
        return qty_oh;
    }

    public void setQty_oh(String qty_oh) {
        this.qty_oh = qty_oh;
    }

    public String getQty_po() {
        return qty_po;
    }

    public void setQty_po(String qty_po) {
        this.qty_po = qty_po;
    }

    public String getQty_so() {
        return qty_so;
    }

    public void setQty_so(String qty_so) {
        this.qty_so = qty_so;
    }

    public String getRekap_date() {
        return rekap_date;
    }

    public void setRekap_date(String rekap_date) {
        this.rekap_date = rekap_date;
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
