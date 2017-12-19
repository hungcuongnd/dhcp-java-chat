/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author Tran Cuong
 */
public class UserSimple {

    private String user;
    private String fullName;
    private boolean online;

    public UserSimple() {
    }

    public UserSimple(String user, String fullName, boolean online) {
        this.user = user;
        this.fullName = fullName;
        this.online = online;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return fullName + " - " + user;
    }

}
