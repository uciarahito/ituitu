package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 11/28/2016.
 */

public class BarangModel {
    private  int id;
    private String decode;
    private String upc;
    private String name;
    private String type_decode;
    private String category_decode;
    private String family_decode;
    private String brand_decode;
    private String group_decode;
    private String unit1_decode;
    private String unit1_name;
    private String unit1_val;
    private String unit2_decode;
    private String unit2_name;
    private String unit2_val;
    private String unit3_decode;
    private String unit3_name;
    private String unit3_val;
    private String inventory_type_decode;
    private String supplier_decode;
    private String supplier_sku;
    private String supplier_item_name;
    private String supplier_last_price;
    private String supplier_lead_time;
    private int tax;
    private String stock_min;
    private String stock_max;
    private String hpp;
    private String customer_price;
    private String customer_standar_cost;
    private String harga_jual_satuan;
    private String harga_jual_paket;
    private int production_prepare_by_day;
    private String pic;
    private int duration;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private int active;
    private String note;
    private String customer_note;
    private String detail;
    private String deleted_at;

    public BarangModel(int id, String decode, String upc, String name, String type_decode, String category_decode, String family_decode, String brand_decode, String group_decode, String unit1_decode, String unit1_name, String unit1_val, String unit2_decode, String unit2_name, String unit2_val, String unit3_decode, String unit3_name, String unit3_val, String inventory_type_decode, String supplier_decode, String supplier_sku, String supplier_item_name, String supplier_last_price, String supplier_lead_time, int tax, String stock_min, String stock_max, String hpp, String customer_price, String customer_standar_cost, String harga_jual_satuan, String harga_jual_paket, int production_prepare_by_day, String pic, int duration, String created_at, String updated_at, String register_at, String created_by, String updated_by, int active, String note, String customer_note, String detail, String deleted_at) {
        this.id = id;
        this.decode = decode;
        this.upc = upc;
        this.name = name;
        this.type_decode = type_decode;
        this.category_decode = category_decode;
        this.family_decode = family_decode;
        this.brand_decode = brand_decode;
        this.group_decode = group_decode;
        this.unit1_decode = unit1_decode;
        this.unit1_name = unit1_name;
        this.unit1_val = unit1_val;
        this.unit2_decode = unit2_decode;
        this.unit2_name = unit2_name;
        this.unit2_val = unit2_val;
        this.unit3_decode = unit3_decode;
        this.unit3_name = unit3_name;
        this.unit3_val = unit3_val;
        this.inventory_type_decode = inventory_type_decode;
        this.supplier_decode = supplier_decode;
        this.supplier_sku = supplier_sku;
        this.supplier_item_name = supplier_item_name;
        this.supplier_last_price = supplier_last_price;
        this.supplier_lead_time = supplier_lead_time;
        this.tax = tax;
        this.stock_min = stock_min;
        this.stock_max = stock_max;
        this.hpp = hpp;
        this.customer_price = customer_price;
        this.customer_standar_cost = customer_standar_cost;
        this.harga_jual_satuan = harga_jual_satuan;
        this.harga_jual_paket = harga_jual_paket;
        this.production_prepare_by_day = production_prepare_by_day;
        this.pic = pic;
        this.duration = duration;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.active = active;
        this.note = note;
        this.customer_note = customer_note;
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

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_decode() {
        return type_decode;
    }

    public void setType_decode(String type_decode) {
        this.type_decode = type_decode;
    }

    public String getCategory_decode() {
        return category_decode;
    }

    public void setCategory_decode(String category_decode) {
        this.category_decode = category_decode;
    }

    public String getFamily_decode() {
        return family_decode;
    }

    public void setFamily_decode(String family_decode) {
        this.family_decode = family_decode;
    }

    public String getBrand_decode() {
        return brand_decode;
    }

    public void setBrand_decode(String brand_decode) {
        this.brand_decode = brand_decode;
    }

    public String getGroup_decode() {
        return group_decode;
    }

    public void setGroup_decode(String group_decode) {
        this.group_decode = group_decode;
    }

    public String getUnit1_decode() {
        return unit1_decode;
    }

    public void setUnit1_decode(String unit1_decode) {
        this.unit1_decode = unit1_decode;
    }

    public String getUnit1_name() {
        return unit1_name;
    }

    public void setUnit1_name(String unit1_name) {
        this.unit1_name = unit1_name;
    }

    public String getUnit1_val() {
        return unit1_val;
    }

    public void setUnit1_val(String unit1_val) {
        this.unit1_val = unit1_val;
    }

    public String getUnit2_decode() {
        return unit2_decode;
    }

    public void setUnit2_decode(String unit2_decode) {
        this.unit2_decode = unit2_decode;
    }

    public String getUnit2_name() {
        return unit2_name;
    }

    public void setUnit2_name(String unit2_name) {
        this.unit2_name = unit2_name;
    }

    public String getUnit2_val() {
        return unit2_val;
    }

    public void setUnit2_val(String unit2_val) {
        this.unit2_val = unit2_val;
    }

    public String getUnit3_decode() {
        return unit3_decode;
    }

    public void setUnit3_decode(String unit3_decode) {
        this.unit3_decode = unit3_decode;
    }

    public String getUnit3_name() {
        return unit3_name;
    }

    public void setUnit3_name(String unit3_name) {
        this.unit3_name = unit3_name;
    }

    public String getUnit3_val() {
        return unit3_val;
    }

    public void setUnit3_val(String unit3_val) {
        this.unit3_val = unit3_val;
    }

    public String getInventory_type_decode() {
        return inventory_type_decode;
    }

    public void setInventory_type_decode(String inventory_type_decode) {
        this.inventory_type_decode = inventory_type_decode;
    }

    public String getSupplier_decode() {
        return supplier_decode;
    }

    public void setSupplier_decode(String supplier_decode) {
        this.supplier_decode = supplier_decode;
    }

    public String getSupplier_sku() {
        return supplier_sku;
    }

    public void setSupplier_sku(String supplier_sku) {
        this.supplier_sku = supplier_sku;
    }

    public String getSupplier_item_name() {
        return supplier_item_name;
    }

    public void setSupplier_item_name(String supplier_item_name) {
        this.supplier_item_name = supplier_item_name;
    }

    public String getSupplier_last_price() {
        return supplier_last_price;
    }

    public void setSupplier_last_price(String supplier_last_price) {
        this.supplier_last_price = supplier_last_price;
    }

    public String getSupplier_lead_time() {
        return supplier_lead_time;
    }

    public void setSupplier_lead_time(String supplier_lead_time) {
        this.supplier_lead_time = supplier_lead_time;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getStock_min() {
        return stock_min;
    }

    public void setStock_min(String stock_min) {
        this.stock_min = stock_min;
    }

    public String getStock_max() {
        return stock_max;
    }

    public void setStock_max(String stock_max) {
        this.stock_max = stock_max;
    }

    public String getHpp() {
        return hpp;
    }

    public void setHpp(String hpp) {
        this.hpp = hpp;
    }

    public String getCustomer_price() {
        return customer_price;
    }

    public void setCustomer_price(String customer_price) {
        this.customer_price = customer_price;
    }

    public String getCustomer_standar_cost() {
        return customer_standar_cost;
    }

    public void setCustomer_standar_cost(String customer_standar_cost) {
        this.customer_standar_cost = customer_standar_cost;
    }

    public String getHarga_jual_satuan() {
        return harga_jual_satuan;
    }

    public void setHarga_jual_satuan(String harga_jual_satuan) {
        this.harga_jual_satuan = harga_jual_satuan;
    }

    public String getHarga_jual_paket() {
        return harga_jual_paket;
    }

    public void setHarga_jual_paket(String harga_jual_paket) {
        this.harga_jual_paket = harga_jual_paket;
    }

    public int getProduction_prepare_by_day() {
        return production_prepare_by_day;
    }

    public void setProduction_prepare_by_day(int production_prepare_by_day) {
        this.production_prepare_by_day = production_prepare_by_day;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCustomer_note() {
        return customer_note;
    }

    public void setCustomer_note(String customer_note) {
        this.customer_note = customer_note;
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
