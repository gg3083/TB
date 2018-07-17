package com.gg3083.tb.domain.log;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "log")
public class Log {
    @Id
    @Column(name = "pk_log_id")
    private String pkLogId;

    private String ip;

    /**
     * 干了啥
     */
    private String oper;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "fk_user_id")
    private String fkUserId;

    @Column(name = "user_name")
    private String userName;

    /**
     * @return pk_log_id
     */
    public String getPkLogId() {
        return pkLogId;
    }

    /**
     * @param pkLogId
     */
    public void setPkLogId(String pkLogId) {
        this.pkLogId = pkLogId;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取干了啥
     *
     * @return oper - 干了啥
     */
    public String getOper() {
        return oper;
    }

    /**
     * 设置干了啥
     *
     * @param oper 干了啥
     */
    public void setOper(String oper) {
        this.oper = oper;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return is_delete
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return fk_user_id
     */
    public String getFkUserId() {
        return fkUserId;
    }

    /**
     * @param fkUserId
     */
    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}