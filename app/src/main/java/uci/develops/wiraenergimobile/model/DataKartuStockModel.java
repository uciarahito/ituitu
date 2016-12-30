package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 11/28/2016.
 */

public class DataKartuStockModel {
    private int id;
    private String decode;
    private String warehouse_decode;
    private String warehouse_name;
    private String barang_decode;
    private String barang_name;
    private String barang_sumber;
    private String bukti_decode;
    private String transaksi;
    private String mk;
    private String qty;
    private String hpp;
    private String harga;
    private String stock_date;
    private int priority;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private String detail;
    private String deleted_at;
    private String ongkir;
    private String komisi;
    private String pph;
    private String ppn;
    private String pbbkb;
    private String qtyoh_lama;
    private String hpp_lama;

    public DataKartuStockModel(int id, String decode, String warehouse_decode, String warehouse_name, String barang_decode, String barang_name, String barang_sumber, String bukti_decode, String transaksi, String mk, String qty, String hpp, String harga, String stock_date, int priority, String created_at, String updated_at, String register_at, String created_by, String updated_by, String detail, String deleted_at, String ongkir, String komisi, String pph, String ppn, String pbbkb, String qtyoh_lama, String hpp_lama) {
        this.id = id;
        this.decode = decode;
        this.warehouse_decode = warehouse_decode;
        this.warehouse_name = warehouse_name;
        this.barang_decode = barang_decode;
        this.barang_name = barang_name;
        this.barang_sumber = barang_sumber;
        this.bukti_decode = bukti_decode;
        this.transaksi = transaksi;
        this.mk = mk;
        this.qty = qty;
        this.hpp = hpp;
        this.harga = harga;
        this.stock_date = stock_date;
        this.priority = priority;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.detail = detail;
        this.deleted_at = deleted_at;
        this.ongkir = ongkir;
        this.komisi = komisi;
        this.pph = pph;
        this.ppn = ppn;
        this.pbbkb = pbbkb;
        this.qtyoh_lama = qtyoh_lama;
        this.hpp_lama = hpp_lama;
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

    public String getWarehouse_decode() {
        return warehouse_decode;
    }

    public void setWarehouse_decode(String warehouse_decode) {
        this.warehouse_decode = warehouse_decode;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
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

    public String getBarang_sumber() {
        return barang_sumber;
    }

    public void setBarang_sumber(String barang_sumber) {
        this.barang_sumber = barang_sumber;
    }

    public String getBukti_decode() {
        return bukti_decode;
    }

    public void setBukti_decode(String bukti_decode) {
        this.bukti_decode = bukti_decode;
    }

    public String getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(String transaksi) {
        this.transaksi = transaksi;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getHpp() {
        return hpp;
    }

    public void setHpp(String hpp) {
        this.hpp = hpp;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStock_date() {
        return stock_date;
    }

    public void setStock_date(String stock_date) {
        this.stock_date = stock_date;
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

    public String getOngkir() {
        return ongkir;
    }

    public void setOngkir(String ongkir) {
        this.ongkir = ongkir;
    }

    public String getKomisi() {
        return komisi;
    }

    public void setKomisi(String komisi) {
        this.komisi = komisi;
    }

    public String getPph() {
        return pph;
    }

    public void setPph(String pph) {
        this.pph = pph;
    }

    public String getPpn() {
        return ppn;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public String getPbbkb() {
        return pbbkb;
    }

    public void setPbbkb(String pbbkb) {
        this.pbbkb = pbbkb;
    }

    public String getQtyoh_lama() {
        return qtyoh_lama;
    }

    public void setQtyoh_lama(String qtyoh_lama) {
        this.qtyoh_lama = qtyoh_lama;
    }

    public String getHpp_lama() {
        return hpp_lama;
    }

    public void setHpp_lama(String hpp_lama) {
        this.hpp_lama = hpp_lama;
    }
}
