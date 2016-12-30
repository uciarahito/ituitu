package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 11/28/2016.
 */

public class TransDOModel {
    private int id;
    private String decode;
    private String decode_WE;
    private String do_date;
    private int revisi;
    private String revisi_date;
    private String initial;
    private String customer_code;
    private String customer_name;
    private String so_number;
    private String qt_number;
    private String send_date;
    private String shipping_address_decode;
    private String shipping_address_name;
    private String shipping_address;
    private String shipping_pic;
    private String shipping_phone;
    private String shipping_handphone;
    private String shipping_coordinate;
    private String warehouse_code;
    private String warehouse_name;
    private String warehouse_type;
    private String warehouse_address;
    private String warehouse_phone;
    private String warehouse_mobile;
    private String warehouse_email;
    private String warehouse_coordinate;
    private String supplier_code;
    private String supplier_name;
    private String supplier_address;
    private String supplier_city;
    private String supplier_province;
    private String supplier_postcode;
    private String supplier_mobile;
    private String supplier_phone;
    private String supplier_email;
    private String supplier_vehicle_decode;
    private String supplier_vehicle_name;
    private String supplier_vehicle_capacity;
    private String supplier_vehicle_gpsid;
    private String supplier_driver_code;
    private String supplier_driver_name;
    private String supplier_driver_address;
    private String supplier_driver_phone;
    private String supplier_driver_mobile;
    private String supplier_driver_email;
    private String supplier_shipping_code;
    private String supplier_shipping_codearea;
    private String supplier_shipping_unitprice;
    private String supplier_shipping_quantity;
    private String item_code;
    private String item_name;
    private String item_unit;
    private String unit_decode;
    private String item_qty;
    private String item_qty_delivery;
    private String item_unit_price;
    private String date_delivery;
    private String date_delivered;
    private String hpp;
    private String komisi;
    private String harga_jual;
    private int status;
    private String invoice_decode;
    private String invoice_date;
    private String invoice_term;
    private String invoice_duedate;
    private String invoice_amount;
    private String payment_date;
    private String payment_amount;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private boolean activated;
    private String detail;
    private String deleted_at;
    private String seal_number;

    public TransDOModel(int id, String decode, String decode_WE, String do_date, int revisi, String revisi_date, String initial, String customer_code, String customer_name, String so_number, String qt_number, String send_date, String shipping_address_decode, String shipping_address_name, String shipping_address, String shipping_pic, String shipping_phone, String shipping_handphone, String shipping_coordinate, String warehouse_code, String warehouse_name, String warehouse_type, String warehouse_address, String warehouse_phone, String warehouse_mobile, String warehouse_email, String warehouse_coordinate, String supplier_code, String supplier_name, String supplier_address, String supplier_city, String supplier_province, String supplier_postcode, String supplier_mobile, String supplier_phone, String supplier_email, String supplier_vehicle_decode, String supplier_vehicle_name, String supplier_vehicle_capacity, String supplier_vehicle_gpsid, String supplier_driver_code, String supplier_driver_name, String supplier_driver_address, String supplier_driver_phone, String supplier_driver_mobile, String supplier_driver_email, String supplier_shipping_code, String supplier_shipping_codearea, String supplier_shipping_unitprice, String supplier_shipping_quantity, String item_code, String item_name, String item_unit, String unit_decode, String item_qty, String item_qty_delivery, String item_unit_price, String date_delivery, String date_delivered, String hpp, String komisi, String harga_jual, int status, String invoice_decode, String invoice_date, String invoice_term, String invoice_duedate, String invoice_amount, String payment_date, String payment_amount, String created_at, String updated_at, String register_at, String created_by, String updated_by, boolean activated, String detail, String deleted_at, String seal_number) {
        this.id = id;
        this.decode = decode;
        this.decode_WE = decode_WE;
        this.do_date = do_date;
        this.revisi = revisi;
        this.revisi_date = revisi_date;
        this.initial = initial;
        this.customer_code = customer_code;
        this.customer_name = customer_name;
        this.so_number = so_number;
        this.qt_number = qt_number;
        this.send_date = send_date;
        this.shipping_address_decode = shipping_address_decode;
        this.shipping_address_name = shipping_address_name;
        this.shipping_address = shipping_address;
        this.shipping_pic = shipping_pic;
        this.shipping_phone = shipping_phone;
        this.shipping_handphone = shipping_handphone;
        this.shipping_coordinate = shipping_coordinate;
        this.warehouse_code = warehouse_code;
        this.warehouse_name = warehouse_name;
        this.warehouse_type = warehouse_type;
        this.warehouse_address = warehouse_address;
        this.warehouse_phone = warehouse_phone;
        this.warehouse_mobile = warehouse_mobile;
        this.warehouse_email = warehouse_email;
        this.warehouse_coordinate = warehouse_coordinate;
        this.supplier_code = supplier_code;
        this.supplier_name = supplier_name;
        this.supplier_address = supplier_address;
        this.supplier_city = supplier_city;
        this.supplier_province = supplier_province;
        this.supplier_postcode = supplier_postcode;
        this.supplier_mobile = supplier_mobile;
        this.supplier_phone = supplier_phone;
        this.supplier_email = supplier_email;
        this.supplier_vehicle_decode = supplier_vehicle_decode;
        this.supplier_vehicle_name = supplier_vehicle_name;
        this.supplier_vehicle_capacity = supplier_vehicle_capacity;
        this.supplier_vehicle_gpsid = supplier_vehicle_gpsid;
        this.supplier_driver_code = supplier_driver_code;
        this.supplier_driver_name = supplier_driver_name;
        this.supplier_driver_address = supplier_driver_address;
        this.supplier_driver_phone = supplier_driver_phone;
        this.supplier_driver_mobile = supplier_driver_mobile;
        this.supplier_driver_email = supplier_driver_email;
        this.supplier_shipping_code = supplier_shipping_code;
        this.supplier_shipping_codearea = supplier_shipping_codearea;
        this.supplier_shipping_unitprice = supplier_shipping_unitprice;
        this.supplier_shipping_quantity = supplier_shipping_quantity;
        this.item_code = item_code;
        this.item_name = item_name;
        this.item_unit = item_unit;
        this.unit_decode = unit_decode;
        this.item_qty = item_qty;
        this.item_qty_delivery = item_qty_delivery;
        this.item_unit_price = item_unit_price;
        this.date_delivery = date_delivery;
        this.date_delivered = date_delivered;
        this.hpp = hpp;
        this.komisi = komisi;
        this.harga_jual = harga_jual;
        this.status = status;
        this.invoice_decode = invoice_decode;
        this.invoice_date = invoice_date;
        this.invoice_term = invoice_term;
        this.invoice_duedate = invoice_duedate;
        this.invoice_amount = invoice_amount;
        this.payment_date = payment_date;
        this.payment_amount = payment_amount;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.activated = activated;
        this.detail = detail;
        this.deleted_at = deleted_at;
        this.seal_number = seal_number;
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

    public String getDo_date() {
        return do_date;
    }

    public void setDo_date(String do_date) {
        this.do_date = do_date;
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

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getSo_number() {
        return so_number;
    }

    public void setSo_number(String so_number) {
        this.so_number = so_number;
    }

    public String getQt_number() {
        return qt_number;
    }

    public void setQt_number(String qt_number) {
        this.qt_number = qt_number;
    }

    public String getSend_date() {
        return send_date;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
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

    public String getShipping_coordinate() {
        return shipping_coordinate;
    }

    public void setShipping_coordinate(String shipping_coordinate) {
        this.shipping_coordinate = shipping_coordinate;
    }

    public String getWarehouse_code() {
        return warehouse_code;
    }

    public void setWarehouse_code(String warehouse_code) {
        this.warehouse_code = warehouse_code;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getWarehouse_type() {
        return warehouse_type;
    }

    public void setWarehouse_type(String warehouse_type) {
        this.warehouse_type = warehouse_type;
    }

    public String getWarehouse_address() {
        return warehouse_address;
    }

    public void setWarehouse_address(String warehouse_address) {
        this.warehouse_address = warehouse_address;
    }

    public String getWarehouse_phone() {
        return warehouse_phone;
    }

    public void setWarehouse_phone(String warehouse_phone) {
        this.warehouse_phone = warehouse_phone;
    }

    public String getWarehouse_mobile() {
        return warehouse_mobile;
    }

    public void setWarehouse_mobile(String warehouse_mobile) {
        this.warehouse_mobile = warehouse_mobile;
    }

    public String getWarehouse_email() {
        return warehouse_email;
    }

    public void setWarehouse_email(String warehouse_email) {
        this.warehouse_email = warehouse_email;
    }

    public String getWarehouse_coordinate() {
        return warehouse_coordinate;
    }

    public void setWarehouse_coordinate(String warehouse_coordinate) {
        this.warehouse_coordinate = warehouse_coordinate;
    }

    public String getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public String getSupplier_city() {
        return supplier_city;
    }

    public void setSupplier_city(String supplier_city) {
        this.supplier_city = supplier_city;
    }

    public String getSupplier_province() {
        return supplier_province;
    }

    public void setSupplier_province(String supplier_province) {
        this.supplier_province = supplier_province;
    }

    public String getSupplier_postcode() {
        return supplier_postcode;
    }

    public void setSupplier_postcode(String supplier_postcode) {
        this.supplier_postcode = supplier_postcode;
    }

    public String getSupplier_mobile() {
        return supplier_mobile;
    }

    public void setSupplier_mobile(String supplier_mobile) {
        this.supplier_mobile = supplier_mobile;
    }

    public String getSupplier_phone() {
        return supplier_phone;
    }

    public void setSupplier_phone(String supplier_phone) {
        this.supplier_phone = supplier_phone;
    }

    public String getSupplier_email() {
        return supplier_email;
    }

    public void setSupplier_email(String supplier_email) {
        this.supplier_email = supplier_email;
    }

    public String getSupplier_vehicle_decode() {
        return supplier_vehicle_decode;
    }

    public void setSupplier_vehicle_decode(String supplier_vehicle_decode) {
        this.supplier_vehicle_decode = supplier_vehicle_decode;
    }

    public String getSupplier_vehicle_name() {
        return supplier_vehicle_name;
    }

    public void setSupplier_vehicle_name(String supplier_vehicle_name) {
        this.supplier_vehicle_name = supplier_vehicle_name;
    }

    public String getSupplier_vehicle_capacity() {
        return supplier_vehicle_capacity;
    }

    public void setSupplier_vehicle_capacity(String supplier_vehicle_capacity) {
        this.supplier_vehicle_capacity = supplier_vehicle_capacity;
    }

    public String getSupplier_vehicle_gpsid() {
        return supplier_vehicle_gpsid;
    }

    public void setSupplier_vehicle_gpsid(String supplier_vehicle_gpsid) {
        this.supplier_vehicle_gpsid = supplier_vehicle_gpsid;
    }

    public String getSupplier_driver_code() {
        return supplier_driver_code;
    }

    public void setSupplier_driver_code(String supplier_driver_code) {
        this.supplier_driver_code = supplier_driver_code;
    }

    public String getSupplier_driver_name() {
        return supplier_driver_name;
    }

    public void setSupplier_driver_name(String supplier_driver_name) {
        this.supplier_driver_name = supplier_driver_name;
    }

    public String getSupplier_driver_address() {
        return supplier_driver_address;
    }

    public void setSupplier_driver_address(String supplier_driver_address) {
        this.supplier_driver_address = supplier_driver_address;
    }

    public String getSupplier_driver_phone() {
        return supplier_driver_phone;
    }

    public void setSupplier_driver_phone(String supplier_driver_phone) {
        this.supplier_driver_phone = supplier_driver_phone;
    }

    public String getSupplier_driver_mobile() {
        return supplier_driver_mobile;
    }

    public void setSupplier_driver_mobile(String supplier_driver_mobile) {
        this.supplier_driver_mobile = supplier_driver_mobile;
    }

    public String getSupplier_driver_email() {
        return supplier_driver_email;
    }

    public void setSupplier_driver_email(String supplier_driver_email) {
        this.supplier_driver_email = supplier_driver_email;
    }

    public String getSupplier_shipping_code() {
        return supplier_shipping_code;
    }

    public void setSupplier_shipping_code(String supplier_shipping_code) {
        this.supplier_shipping_code = supplier_shipping_code;
    }

    public String getSupplier_shipping_codearea() {
        return supplier_shipping_codearea;
    }

    public void setSupplier_shipping_codearea(String supplier_shipping_codearea) {
        this.supplier_shipping_codearea = supplier_shipping_codearea;
    }

    public String getSupplier_shipping_unitprice() {
        return supplier_shipping_unitprice;
    }

    public void setSupplier_shipping_unitprice(String supplier_shipping_unitprice) {
        this.supplier_shipping_unitprice = supplier_shipping_unitprice;
    }

    public String getSupplier_shipping_quantity() {
        return supplier_shipping_quantity;
    }

    public void setSupplier_shipping_quantity(String supplier_shipping_quantity) {
        this.supplier_shipping_quantity = supplier_shipping_quantity;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_unit() {
        return item_unit;
    }

    public void setItem_unit(String item_unit) {
        this.item_unit = item_unit;
    }

    public String getUnit_decode() {
        return unit_decode;
    }

    public void setUnit_decode(String unit_decode) {
        this.unit_decode = unit_decode;
    }

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public String getItem_qty_delivery() {
        return item_qty_delivery;
    }

    public void setItem_qty_delivery(String item_qty_delivery) {
        this.item_qty_delivery = item_qty_delivery;
    }

    public String getItem_unit_price() {
        return item_unit_price;
    }

    public void setItem_unit_price(String item_unit_price) {
        this.item_unit_price = item_unit_price;
    }

    public String getDate_delivery() {
        return date_delivery;
    }

    public void setDate_delivery(String date_delivery) {
        this.date_delivery = date_delivery;
    }

    public String getDate_delivered() {
        return date_delivered;
    }

    public void setDate_delivered(String date_delivered) {
        this.date_delivered = date_delivered;
    }

    public String getHpp() {
        return hpp;
    }

    public void setHpp(String hpp) {
        this.hpp = hpp;
    }

    public String getKomisi() {
        return komisi;
    }

    public void setKomisi(String komisi) {
        this.komisi = komisi;
    }

    public String getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(String harga_jual) {
        this.harga_jual = harga_jual;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInvoice_decode() {
        return invoice_decode;
    }

    public void setInvoice_decode(String invoice_decode) {
        this.invoice_decode = invoice_decode;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getInvoice_term() {
        return invoice_term;
    }

    public void setInvoice_term(String invoice_term) {
        this.invoice_term = invoice_term;
    }

    public String getInvoice_duedate() {
        return invoice_duedate;
    }

    public void setInvoice_duedate(String invoice_duedate) {
        this.invoice_duedate = invoice_duedate;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
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

    public String getSeal_number() {
        return seal_number;
    }

    public void setSeal_number(String seal_number) {
        this.seal_number = seal_number;
    }
}
