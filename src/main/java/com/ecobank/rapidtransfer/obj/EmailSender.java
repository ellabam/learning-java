
package com.ecobank.rapidtransfer.obj;

import java.util.List;

/**
 *
 * @author emmanuellabamgbose
 */
public class EmailSender {
    private String subject;
    private List<String> emails;
    private String senderDisplayName;
    private String senderAddress;
    private String message;
    private String tr_ref;
    private List<String> CC;
    private List<String> BCC;
    private List<String> input;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getSenderDisplayName() {
        return senderDisplayName;
    }

    public void setSenderDisplayName(String senderDisplayName) {
        this.senderDisplayName = senderDisplayName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTr_ref() {
        return tr_ref;
    }

    public void setTr_ref(String tr_ref) {
        this.tr_ref = tr_ref;
    }

    public List<String> getCC() {
        return CC;
    }

    public void setCC(List<String> CC) {
        this.CC = CC;
    }

    public List<String> getBCC() {
        return BCC;
    }

    public void setBCC(List<String> BCC) {
        this.BCC = BCC;
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }
    
    
}
