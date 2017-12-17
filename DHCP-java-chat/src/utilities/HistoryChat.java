/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author XPS14
 */
public class HistoryChat {
    private String userName;
    //private String userRecv;
    private String dateTime;
    private String content;
    private int status;
    private String sas;

    public HistoryChat(String userName, String content, int status, String sas) {
        this.userName = userName;
        //this.userRecv = userRecv;
        this.content = content;
        this.status = status;
        this.sas = sas;
    }

    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //public String getUserRecv() {
    //    return userRecv;
    //}

    //public void setUserRecv(String userRecv) {
    //    this.userRecv = userRecv;
    //}

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSas() {
        return sas;
    }

    public void setSas(String sas) {
        this.sas = sas;
    }
}
