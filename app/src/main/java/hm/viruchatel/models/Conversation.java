package hm.viruchatel.models;

import java.util.Date;

/**
 * Created by Дмитрий on 11.06.2015.
 */
public class Conversation {
    public static final int STATUS_SENDING = 0;
    public static final int STATUS_SENT = 1;
    public static final int STATUS_FAILED = 2;

    private String msg;
    private int status = STATUS_SENT;
    private Date date;
    private String sender;
    private boolean send;

    public Conversation(String msg, Date date, String sender) {
        this.msg = msg;
        this.date = date;
        this.sender = sender;
    }

    public Conversation() {
        msg = "Новое сообщение";
        date = new Date();
        sender = "Маргарита Иванова";
        send = true;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public boolean isSent() {
        //return UserList.user.getUsername().equals(sender);
        return send;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}



