package com.gg3083.tb.domain.note;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "note")
@Data
public class Note {
    @Id
    @Column(name = "pk_note_id")
    private String pkNoteId;

    @Column(name = "fk_user_id")
    private String fkUserId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "is_stick")
    private Integer isStick;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "note_desc")
    private String noteDesc;

    @Column(name = "note_desc_short")
    private String noteDescShort;

    @Column(name = "note_title")
    private String noteTitle;

}