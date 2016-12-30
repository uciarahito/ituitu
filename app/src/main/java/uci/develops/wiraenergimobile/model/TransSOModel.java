package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 12/28/2016.
 */

public class TransSOModel {
    private int id;
    private String decode;
    private String qt_decode;
    private String shipping_address_decode;
    private String shipping_address_name;
    private String shipping_address;
    private String shipping_pic;
    private String shipping_phone;
    private String shipping_handphone;
    private String payment_address_decode;
    private String payment_address_name;
    private String payment_address;
    private String payment_pic;
    private String payment_pic_position;
    private String payment_phone;
    private String payment_handphone;
    private String payment_email;
    private String term;
    private String customer_decode;
    private String customer_name;
    private String customer_po;
    private String project_decode;
    private String project_name;
    private String group_decode;
    private String group_name;
    private String bruto;
    private String discount;
    private String discount_amount;
    private String ppn;
    private String ppn_amount;
    private String othercost;
    private String othercost_note;
    private String total;
    private String note;
    private String note_replay;
    private String total_item;
    private String so_date;
    private String so_date_approve;
    private String do_date_delivery;
    private String do_date;
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

    public TransSOModel(int id, String decode, String qt_decode, String shipping_address_decode, String shipping_address_name, String shipping_address, String shipping_pic, String shipping_phone, String shipping_handphone, String payment_address_decode, String payment_address_name, String payment_address, String payment_pic, String payment_pic_position, String payment_phone, String payment_handphone, String payment_email, String term, String customer_decode, String customer_name, String customer_po, String project_decode, String project_name, String group_decode, String group_name, String bruto, String discount, String discount_amount, String ppn, String ppn_amount, String othercost, String othercost_note, String total, String note, String note_replay, String total_item, String so_date, String so_date_approve, String do_date_delivery, String do_date, int priority, String created_at, String updated_at, String register_at, String created_by, String updated_by, int active, int status, String detail, String deleted_at) {
        this.id = id;
        this.decode = decode;
        this.qt_decode = qt_decode;
        this.shipping_address_decode = shipping_address_decode;
        this.shipping_address_name = shipping_address_name;
        this.shipping_address = shipping_address;
        this.shipping_pic = shipping_pic;
        this.shipping_phone = shipping_phone;
        this.shipping_handphone = shipping_handphone;
        this.payment_address_decode = payment_address_decode;
        this.payment_address_name = payment_address_name;
        this.payment_address = payment_address;
        this.payment_pic = payment_pic;
        this.payment_pic_position = payment_pic_position;
        this.payment_phone = payment_phone;
        this.payment_handphone = payment_handphone;
        this.payment_email = payment_email;
        this.term = term;
        this.customer_decode = customer_decode;
        this.customer_name = customer_name;
        this.customer_po = customer_po;
        this.project_decode = project_decode;
        this.project_name = project_name;
        this.group_decode = group_decode;
        this.group_name = group_name;
        this.bruto = bruto;
        this.discount = discount;
        this.discount_amount = discount_amount;
        this.ppn = ppn;
        this.ppn_amount = ppn_amount;
        this.othercost = othercost;
        this.othercost_note = othercost_note;
        this.total = total;
        this.note = note;
        this.note_replay = note_replay;
        this.total_item = total_item;
        this.so_date = so_date;
        this.so_date_approve = so_date_approve;
        this.do_date_delivery = do_date_delivery;
        this.do_date = do_date;
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

    public String getQt_decode() {
        return qt_decode;
    }

    public void setQt_decode(String qt_decode) {
        this.qt_decode = qt_decode;
    }

    public String getShipping_address_decode() {
        return shipping_address_decode;
    }

    public void setShipping_address_decode(String shipping_address_decode) {
        this.shipping_address_decode = shipping_address_decode;
    }

    public String getShipping_address_name() {
        return shipping_address_name;
    }

    public void setShipping_address_name(String shipping_address_name) {
        this.shipping_address_name = shipping_address_name;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getShipping_pic() {
        return shipping_pic;
    }

    public void setShipping_pic(String shipping_pic) {
        this.shipping_pic = shipping_pic;
    }

    public String getShipping_phone() {
        return shipping_phone;
    }

    public void setShipping_phone(String shipping_phone) {
        this.shipping_phone = shipping_phone;
    }

    public String getShipping_handphone() {
        return shipping_handphone;
    }

    public void setShipping_handphone(String shipping_handphone) {
        this.shipping_handphone = shipping_handphone;
    }

    public String getPayment_address_decode() {
        return payment_address_decode;
    }

    public void setPayment_address_decode(String payment_address_decode) {
        this.payment_address_decode = payment_address_decode;
    }

    public String getPayment_address_name() {
        return payment_address_name;
    }

    public void setPayment_address_name(String payment_address_name) {
        this.payment_address_name = payment_address_name;
    }

    public String getPayment_address() {
        return payment_address;
    }

    public void setPayment_address(String payment_address) {
        this.payment_address = payment_address;
    }

    public String getPayment_pic() {
        return payment_pic;
    }

    public void setPayment_pic(String payment_pic) {
        this.payment_pic = payment_pic;
    }

    public String getPayment_pic_position() {
        return payment_pic_position;
    }

    public void setPayment_pic_position(String payment_pic_position) {
        this.payment_pic_position = payment_pic_position;
    }

    public String getPayment_phone() {
        return payment_phone;
    }

    public void setPayment_phone(String payment_phone) {
        this.payment_phone = payment_phone;
    }

    public String getPayment_handphone() {
        return payment_handphone;
    }

    public void setPayment_handphone(String payment_handphone) {
        this.payment_handphone = payment_handphone;
    }

    public String getPayment_email() {
        return payment_email;
    }

    public void setPayment_email(String payment_email) {
        this.payment_email = payment_email;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCustomer_decode() {
        return customer_decode;
    }

    public void setCustomer_decode(String customer_decode) {
        this.customer_decode = customer_decode;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_po() {
        return customer_po;
    }

    public void setCustomer_po(String customer_po) {
        this.customer_po = customer_po;
    }

    public String getProject_decode() {
        return project_decode;
    }

    public void setProject_decode(String project_decode) {
        this.project_decode = project_decode;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getGroup_decode() {
        return group_decode;
    }

    public void setGroup_decode(String group_decode) {
        this.group_decode = group_decode;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
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

    public String getPpn() {
        return ppn;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public String getPpn_amount() {
        return ppn_amount;
    }

    public void setPpn_amount(String ppn_amount) {
        this.ppn_amount = ppn_amount;
    }

    public String getOthercost() {
        return othercost;
    }

    public void setOthercost(String othercost) {
        this.othercost = othercost;
    }

    public String getOthercost_note() {
        return othercost_note;
    }

    public void setOthercost_note(String othercost_note) {
        this.othercost_note = othercost_note;
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

    public String getNote_replay() {
        return note_replay;
    }

    public void setNote_replay(String note_replay) {
        this.note_replay = note_replay;
    }

    public String getTotal_item() {
        return total_item;
    }

    public void setTotal_item(String total_item) {
        this.total_item = total_item;
    }

    public String getSo_date() {
        return so_date;
    }

    public void setSo_date(String so_date) {
        this.so_date = so_date;
    }

    public String getSo_date_approve() {
        return so_date_approve;
    }

    public void setSo_date_approve(String so_date_approve) {
        this.so_date_approve = so_date_approve;
    }

    public String getDo_date_delivery() {
        return do_date_delivery;
    }

    public void setDo_date_delivery(String do_date_delivery) {
        this.do_date_delivery = do_date_delivery;
    }

    public String getDo_date() {
        return do_date;
    }

    public void setDo_date(String do_date) {
        this.do_date = do_date;
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
