package sample;

import java.io.Serializable;
import java.util.List;

public class RespondCountryCount implements Serializable {
    private String msg;
    private List<Country> countries;

    public RespondCountryCount(String msg, List<Country> countries) {
        this.msg = msg;
        this.countries = countries;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
