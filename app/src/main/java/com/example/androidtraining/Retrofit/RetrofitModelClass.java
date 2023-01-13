package com.example.androidtraining.Retrofit;

public class RetrofitModelClass {
    int userID, ID;
    String tittle, body;

    public RetrofitModelClass(int userID, int ID, String tittle, String body) {
        this.userID = userID;
        this.ID = ID;
        this.tittle = tittle;
        this.body = body;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
