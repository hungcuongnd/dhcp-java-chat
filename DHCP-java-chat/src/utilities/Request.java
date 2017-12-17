package utilities;

import database.Entities.Tbluser;
import java.util.ArrayList;
import java.util.List;
import utilities.Content;

public class Request {

    private int type;
    private String fromUser;
    private String password;
    private String toUser;
    private String toGroup;
    private Content content;
    private Tbluser user;
    private boolean isRegisterSuccess;   
    public Tbluser getUser() {
        return user;
    }

    public boolean getIsIsRegisterSuccess() {
        return isRegisterSuccess;
    }

    public void setIsRegisterSuccess(boolean isRegisterSuccess) {
        this.isRegisterSuccess = isRegisterSuccess;
    }

    public void setUser(Tbluser user) {
        this.user = user;
    }
    public String id;

    private boolean login = true;
    private String fullName;
    private ArrayList<UserSimple> listFriend;
    private List<HistoryChat> chatHistory;
    private String avatar = "";
    private String extension = "";
    private int loadMessageNum;

    public Request() {

    }

    public Request(int type, String fromUser, String toUser) {
        this.type = type;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToGroup() {
        return toGroup;
    }

    public void setToGroup(String toGroup) {
        this.toGroup = toGroup;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayList<UserSimple> getListFriend() {
        return listFriend;
    }

    public void setListFriend(ArrayList<UserSimple> listFriend) {
        this.listFriend = listFriend;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    public List<HistoryChat> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<HistoryChat> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public int getLoadMessageNum() {
        return loadMessageNum;
    }

    public void setLoadMessageNum(int loadMessageNum) {
        this.loadMessageNum = loadMessageNum;
    }

    @Override
    public String toString() {
        return "Request{" + "type=" + type + ", fromUser=" + fromUser + ", password=" + password + ", toUser=" + toUser + ", toGroup=" + toGroup + ", content=" + content + ", login=" + login + '}';
    }

}
