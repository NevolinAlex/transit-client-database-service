package ru.renue.fts.asktt.client.entities;

import lombok.Getter;

import lombok.Setter;
import ru.renue.fts.asktt.client.enums.DocumentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by disap on 21.07.2017.
 */

@Getter
@Setter

@Entity
@Table(name = "message_information")
public class MessageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "data")
    @NotNull
    private byte[] data;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DocumentStatus documentStatus;

    @Column(name = "custom_id")
    @NotNull
    private long customId;

    @Column(name = "date_time")
    @NotNull
    private Date dateTime;

    public MessageInfo() {
    }

    public MessageInfo(final byte[] data,final  DocumentStatus documentStatus,final long customId,final Date dateTime) {
        this.data = data.clone();
        this.documentStatus = documentStatus;
        this.customId = customId;
        this.dateTime = (Date) dateTime.clone();
    }

    public MessageInfo(final byte[] data,final DocumentStatus documentStatus,final long customId) {
        this.data = data.clone();
        this.documentStatus = documentStatus;
        this.customId = customId;
        this.dateTime = new Date();
    }

    public byte[] getData(){
        return data.clone();
    }
    public Date getDateTime(){
        return (Date) dateTime.clone();
    }

    public void setDateTime(final Date dateTime1) {
        this.dateTime = (Date) dateTime1.clone();
    }

    public void setData(final byte[] data1) {
        this.data = data1.clone();
    }

    @Override
    public String toString() {
        return " {Id: " + this.id+
                "\n Data: " + this.data.toString()+
                "\n Document status:" + this.documentStatus+
                "\n Custom Id : " + this.customId+
                "\n Date: " + this.dateTime +"}\n";
    }
}
