package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 10/21/2016.
 */
public class CustomerModel {
    public int id;
    public int user_id;
    private String decode;
    private String code;
    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String province;
    private String postcode;
    private String ktp;
    private String gender;
    private String birthplace;
    private String birthday;
    private String mobile;
    private String phone;
    private String fax;
    private String email;
    private String website;
    private String type;
    private String group;
    private String detail_payment;
    private String term;
    private String npwp;
    private String tax;
    private String valuta;
    private String note;
    private String admin_note;
    private String map;
    private String detail_shipping;
    private String remember_token;
    private String created_at;
    private String updated_at;
    private String register_at;
    private String created_by;
    private String updated_by;
    private int active;
    private int approve;
    private String detail;
    private String deleted_at;
    private String name1;
    private String phone1;
    private String mobile1;
    private String email1;
    private String jabatan1;
    private String name2;
    private String phone2;
    private String mobile2;
    private String email2;
    private String jabatan2;
    private String name3;
    private String phone3;
    private String mobile3;
    private String email3;
    private String jabatan3;

    public CustomerModel(){}

    public CustomerModel(int id, int user_id, String decode, String code, String first_name, String last_name, String address, String city, String province, String postcode, String ktp, String gender, String birthplace, String birthday, String mobile, String phone, String fax, String email, String website, String type, String group, String detail_payment, String term, String npwp, String tax, String valuta, String note, String admin_note, String map, String detail_shipping, String remember_token, String created_at, String updated_at, String register_at, String created_by, String updated_by, int active, int approve, String detail, String deleted_at, String name1, String phone1, String mobile1, String email1, String jabatan1, String name2, String phone2, String mobile2, String email2, String jabatan2, String name3, String phone3, String mobile3, String email3, String jabatan3) {
        this.id = id;
        this.user_id = user_id;
        this.decode = decode;
        this.code = code;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postcode = postcode;
        this.ktp = ktp;
        this.gender = gender;
        this.birthplace = birthplace;
        this.birthday = birthday;
        this.mobile = mobile;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.website = website;
        this.type = type;
        this.group = group;
        this.detail_payment = detail_payment;
        this.term = term;
        this.npwp = npwp;
        this.tax = tax;
        this.valuta = valuta;
        this.note = note;
        this.admin_note = admin_note;
        this.map = map;
        this.detail_shipping = detail_shipping;
        this.remember_token = remember_token;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.register_at = register_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.active = active;
        this.approve = approve;
        this.detail = detail;
        this.deleted_at = deleted_at;
        this.name1 = name1;
        this.phone1 = phone1;
        this.mobile1 = mobile1;
        this.email1 = email1;
        this.jabatan1 = jabatan1;
        this.name2 = name2;
        this.phone2 = phone2;
        this.mobile2 = mobile2;
        this.email2 = email2;
        this.jabatan2 = jabatan2;
        this.name3 = name3;
        this.phone3 = phone3;
        this.mobile3 = mobile3;
        this.email3 = email3;
        this.jabatan3 = jabatan3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDecode() {
        return decode;
    }

    public void setDecode(String decode) {
        this.decode = decode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDetail_payment() {
        return detail_payment;
    }

    public void setDetail_payment(String detail_payment) {
        this.detail_payment = detail_payment;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAdmin_note() {
        return admin_note;
    }

    public void setAdmin_note(String admin_note) {
        this.admin_note = admin_note;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getDetail_shipping() {
        return detail_shipping;
    }

    public void setDetail_shipping(String detail_shipping) {
        this.detail_shipping = detail_shipping;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
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

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
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

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getJabatan1() {
        return jabatan1;
    }

    public void setJabatan1(String jabatan1) {
        this.jabatan1 = jabatan1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getJabatan2() {
        return jabatan2;
    }

    public void setJabatan2(String jabatan2) {
        this.jabatan2 = jabatan2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getMobile3() {
        return mobile3;
    }

    public void setMobile3(String mobile3) {
        this.mobile3 = mobile3;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getJabatan3() {
        return jabatan3;
    }

    public void setJabatan3(String jabatan3) {
        this.jabatan3 = jabatan3;
    }
}
