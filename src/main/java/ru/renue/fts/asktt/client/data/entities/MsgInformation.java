package ru.renue.fts.asktt.client.data.entities;

import lombok.Getter;

import lombok.Setter;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by disap on 21.07.2017.
 */

@Getter
@Setter

@Entity
@Table(name = "message_information")
public class MsgInformation {
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

    public MsgInformation() {
    }

    public MsgInformation(final byte[] data, final  DocumentStatus documentStatus, final long customId, final Date dateTime) {
        this.data = data.clone();
        this.documentStatus = documentStatus;
        this.customId = customId;
        this.dateTime = (Date) dateTime.clone();
    }

    public MsgInformation(final byte[] data, final DocumentStatus documentStatus, final long customId) {
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
                //"\n Data: " + this.data.clone().toString()+
                "\n Document status:" + this.documentStatus+
                "\n Custom Id : " + this.customId+
                "\n Date: " + this.dateTime +"}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MsgInformation)) return false;

        MsgInformation that = (MsgInformation) o;

        if (getId() != that.getId()) return false;
        if (getCustomId() != that.getCustomId()) return false;
        if (!Arrays.equals(getData(), that.getData())) return false;
        if (getDocumentStatus() != that.getDocumentStatus()) return false;
        return getDateTime().equals(that.getDateTime());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + Arrays.hashCode(getData());
        result = 31 * result + getDocumentStatus().hashCode();
        result = 31 * result + (int) (getCustomId() ^ (getCustomId() >>> 32));
        result = 31 * result + getDateTime().hashCode();
        return result;
    }
}
