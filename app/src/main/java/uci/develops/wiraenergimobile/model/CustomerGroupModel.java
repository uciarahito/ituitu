package uci.develops.wiraenergimobile.model;

/**
 * Created by ArahitoPC on 10/21/2016.
 */
public class CustomerGroupModel {
    private String decode;
    private String name;

    public CustomerGroupModel(String decode, String name) {
        this.decode = decode;
        this.name = name;
    }

    public String getDecode() {
        return decode;
    }

    public void setDecode(String decode) {
        this.decode = decode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
