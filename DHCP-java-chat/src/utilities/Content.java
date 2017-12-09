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
public class Content {
    private String content;
    private Object sas;

    public Content() {
    }

    public Content(String content, Object sas) {
        this.content = content;
        this.sas = sas;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getSas() {
        return sas;
    }

    public void setSas(Object sas) {
        this.sas = sas;
    }
}
