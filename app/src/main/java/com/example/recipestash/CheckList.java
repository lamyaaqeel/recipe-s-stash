package com.example.recipestash;

public class CheckList {
    private int id;
    private String check;
    private int isChecked;

    public CheckList(int id, String check, int isChecked) {
        this.id = id;
        this.check = check;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}
