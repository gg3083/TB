package cn.gg3083.tb.domain;

import cn.gg3083.tb.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "log")
@Data
public class Log extends BaseEntity {
    @Id
    @Column(name = "pk_log_id")
    private String pkLogId;

    private String ip;

    private String oper;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "fk_user_id")
    private String fkUserId;

    @Column(name = "user_name")
    private String userName;

}