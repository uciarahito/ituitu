package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 12/28/2016.
 */

public class TransPOModel {
    private int id;
    private String decode;
    private String decode_WE;
    private String po_date;
    private String supplier_decode;
    private String supplier_name;
    private String warehouse_decode;
    private String shipping_address;
    private String shipping_date;
    private int term;
    private String initial;
    private int revisi;
    private String revisi_date;
    private String valuta;
    private String payment_address;
    private String discount;
    private String discount_amount;
    private String bruto;
    private String ppn;
    private String pph_amount;
    private String pph;
    private String ppn_amount;
    private String pbbkb;
    private String pbbkb_amount;
    private String other_cost;
    private String other_cost_note;
    private String total;
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

    public TransPOModel(int id, String decode, String decode_WE, String po_date, String supplier_decode, String supplier_name, String warehouse_decode, String shipping_address, String shipping_date, int term, String initial, int revisi, String revisi_date, String valuta, String payment_address, String discount, String discount_amount, String bruto, String ppn, String pph_amount, String pph, String ppn_amount, String pbbkb, String pbbkb_amount, String other_cost, String other_cost_note, String total, String note, int priority, String created_at, String updated_at, String register_at, String created_by, String updated_by, int active, int status, String detail, String deleted_at) {
        this.id = id;
        this.decode = decode;
        this.decode_WE = decode_WE;
        this.po_date = po_date;
        this.supplier_decode = supplier_decode;
        this.supplier_name = supplier_name;
        this.warehouse_decode = warehouse_decode;
        this.shipping_address = shipping_address;
        this.shipping_date = shipping_date;
        this.term = term;
        this.initial = initial;
        this.revisi = revisi;
        this.revisi_date = revisi_date;
        this.valuta = valuta;
        this.payment_address = payment_address;
        this.discount = discount;
        this.discount_amount = discount_amount;
        this.bruto = bruto;
        this.ppn = ppn;
        this.pph_amount = pph_amount;
        this.pph = pph;
        this.ppn_amount = ppn_amount;
        this.pbbkb = pbbkb;
        this.pbbkb_amount = pbbkb_amount;
        this.other_cost = other_cost;
        this.other_cost_note = other_cost_note;
        this.total = total;
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

    public String getDecode() {
        return decode;
    }

    public void setDecode(String decode) {
        this.decode = decode;
    }

    public String getDecode_WE() {
        return decode_WE;
    }

    public void setDecode_WE(String decode_WE) {
        this.decode_WE = decode_WE;
    }

    public String getPo_date() {
        return po_date;
    }

    public void setPo_date(String po_date) {
        this.po_date = po_date;
    }

    public String getSupplier_decode() {
        return supplier_decode;
    }

    public void setSupplier_decode(String supplier_decode) {
        this.supplier_decode = supplier_decode;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getWarehouse_decode() {
        return warehouse_decode;
    }

    public void setWarehouse_decode(String warehouse_decode) {
        this.warehouse_decode = warehouse_decode;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getShipping_date() {
        return shipping_date;
    }

    public void setShipping_date(String shipping_date) {
        this.shipping_date = shipping_date;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public int getRevisi() {
        return revisi;
    }

    public void setRevisi(int revisi) {
        this.revisi = revisi;
    }

    public String getRevisi_date() {
        return revisi_date;
    }

    public void setRevisi_date(String revisi_date) {
        this.revisi_date = revisi_date;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getPayment_address() {
        return payment_address;
    }

    public void setPayment_address(String payment_address) {
        this.payment_address = payment_address;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
    }

    public String getPpn() {
        return ppn;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public String getPph_amount() {
        return pph_amount;
    }

    public void setPph_amount(String pph_amount) {
        this.pph_amount = pph_amount;
    }

    public String getPph() {
        return pph;
    }

    public void setPph(String pph) {
        this.pph = pph;
    }

    public String getPpn_amount() {
        return ppn_amount;
    }

    public void setPpn_amount(String ppn_amount) {
        this.ppn_amount = ppn_amount;
    }

    public String getPbbkb() {
        return pbbkb;
    }

    public void setPbbkb(String pbbkb) {
        this.pbbkb = pbbkb;
    }

    public String getPbbkb_amount() {
        return pbbkb_amount;
    }

    public void setPbbkb_amount(String pbbkb_amount) {
        this.pbbkb_amount = pbbkb_amount;
    }

    public String getOther_cost() {
        return other_cost;
    }

    public void setOther_cost(String other_cost) {
        this.other_cost = other_cost;
    }

    public String getOther_cost_note() {
        return other_cost_note;
    }

    public void setOther_cost_note(String other_cost_note) {
        this.other_cost_note = other_cost_note;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
