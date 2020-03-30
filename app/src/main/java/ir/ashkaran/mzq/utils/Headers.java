package ir.ashkaran.mzq.utils;

public class Headers {









    public String getUserID() {
        return Storage.get().getInt("userID" , -1)  + "";
    }

    public String getSession() {
        return Storage.get().getString("session" , "") ;
    }

    public String getPerson() {
        return Storage.get().getInt("person" , -1)  + "";
    }

    public String getSal() {
        return Storage.get().getInt("sal" , -1)  + "";
    }
    public String getShobe() {
        return Storage.get().getInt("shobe" , -1)  + "";
    }




    public String getStudentID() {
        return Storage.get().getInt("studentID" , -1)  + "";
    }

    public String getUserAgent() {
        return "androidUserAgent";
    }



    public String getClient() {
        return app.main.CLIENT ;
    }








}
