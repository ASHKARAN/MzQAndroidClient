package ir.ashkaran.mzq.objects;

import java.util.List;

public class CachedCommutingObject {
    String token;
    List<CommutingObject> data;


    public CachedCommutingObject(String token, List<CommutingObject> data) {
        this.token = token;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<CommutingObject> getData() {
        return data;
    }

    public void setData(List<CommutingObject> data) {
        this.data = data;
    }
}
