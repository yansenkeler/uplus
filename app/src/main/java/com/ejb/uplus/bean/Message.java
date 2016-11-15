package com.ejb.uplus.bean;

import android.os.Parcel;

import java.util.Date;

/**
 * Created by John on 11/1/2016.
 */

public class Message
{
    private long messageId;
    private String title;
    private String content;
    private Date date;
    private boolean isRead = false;

    public Message()
    {
    }

    public Message(String title, String content, Date date)
    {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Message(long messageId, String title, String content, Date date)
    {
        this.messageId = messageId;
        this.title = title;
        this.content = content;

        this.date = date;
    }

    protected Message(Parcel in)
    {
        messageId = in.readLong();
        title = in.readString();
        content = in.readString();
        isRead = in.readByte() != 0;
    }

    public boolean isRead()
    {
        return isRead;
    }

    public void setRead(boolean read)
    {
        isRead = read;
    }

    public long getMessageId()
    {
        return messageId;
    }

    public void setMessageId(long messageId)
    {
        this.messageId = messageId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
