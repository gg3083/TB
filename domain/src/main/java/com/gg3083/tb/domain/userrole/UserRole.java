package com.gg3083.tb.domain.userrole;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_role")
public class UserRole {
    /**
     * 用户角色表编号
     */
    @Id
    @Column(name = "pk_user_role_id")
    private Integer pkUserRoleId;

    /**
     * 角色id（关联角色表pk_role_id字段）
     */
    @Column(name = "fk_role_id")
    private Integer fkRoleId;

    /**
     * 用户id（关联用户信息表pk_user_id字段）
     */
    @Column(name = "fk_user_id")
    private Integer fkUserId;

    /**
     * 是否已经删除(0表示删除，1表示没删除)
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间(自动更新)
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取用户角色表编号
     *
     * @return pk_user_role_id - 用户角色表编号
     */
    public Integer getPkUserRoleId() {
        return pkUserRoleId;
    }

    /**
     * 设置用户角色表编号
     *
     * @param pkUserRoleId 用户角色表编号
     */
    public void setPkUserRoleId(Integer pkUserRoleId) {
        this.pkUserRoleId = pkUserRoleId;
    }

    /**
     * 获取角色id（关联角色表pk_role_id字段）
     *
     * @return fk_role_id - 角色id（关联角色表pk_role_id字段）
     */
    public Integer getFkRoleId() {
        return fkRoleId;
    }

    /**
     * 设置角色id（关联角色表pk_role_id字段）
     *
     * @param fkRoleId 角色id（关联角色表pk_role_id字段）
     */
    public void setFkRoleId(Integer fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    /**
     * 获取用户id（关联用户信息表pk_user_id字段）
     *
     * @return fk_user_id - 用户id（关联用户信息表pk_user_id字段）
     */
    public Integer getFkUserId() {
        return fkUserId;
    }

    /**
     * 设置用户id（关联用户信息表pk_user_id字段）
     *
     * @param fkUserId 用户id（关联用户信息表pk_user_id字段）
     */
    public void setFkUserId(Integer fkUserId) {
        this.fkUserId = fkUserId;
    }

    /**
     * 获取是否已经删除(0表示删除，1表示没删除)
     *
     * @return is_delete - 是否已经删除(0表示删除，1表示没删除)
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否已经删除(0表示删除，1表示没删除)
     *
     * @param isDelete 是否已经删除(0表示删除，1表示没删除)
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改时间(自动更新)
     *
     * @return update_time - 最后修改时间(自动更新)
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间(自动更新)
     *
     * @param updateTime 最后修改时间(自动更新)
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}