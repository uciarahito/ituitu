package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 11/28/2016.
 */

public class BarangStockModel {
    private int id;
    private String sku;
    private String nama;
    private String warehouse;
    private String warehouse_name;
    private int satuan;
    private String satuan_nama;
    private String qty_onhand;
    private String hpp;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private boolean activated;
    private String detail;
    private String deleted_at;

    public BarangStockModel(int id, String sku, String nama, String warehouse, String warehouse_name, int satuan, String satuan_nama, String qty_onhand, String hpp, String created_at, String updated_at, String register_at, String created_by, String updated_by, boolean activated, String detail, String deleted_at) {
        this.id = id;
        this.sku = sku;
        this.nama = nama;
        this.warehouse = warehouse;
        this.warehouse_name = warehouse_name;
        this.satuan = satuan;
        this.satuan_nama = satuan_nama;
        this.qty_onhand = qty_onhand;
        this.hpp = hpp;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public int getSatuan() {
        return satuan;
    }

    public void setSatuan(int satuan) {
        this.satuan = satuan;
    }

    public String getSatuan_nama() {
        return satuan_nama;
    }

    public void setSatuan_nama(String satuan_nama) {
        this.satuan_nama = satuan_nama;
    }

    public String getQty_onhand() {
        return qty_onhand;
    }

    public void setQty_onhand(String qty_onhand) {
        this.qty_onhand = qty_onhand;
    }

    public String getHpp() {
        return hpp;
    }

    public void setHpp(String hpp) {
        this.hpp = hpp;
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
