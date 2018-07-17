package com.gg3083.tb.domain.role;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "role")
public class Role {
    /**
     * 角色编号
     */
    @Id
    @Column(name = "pk_role_id")
    private Integer pkRoleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 是否已经删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取角色编号
     *
     * @return pk_role_id - 角色编号
     */
    public Integer getPkRoleId() {
        return pkRoleId;
    }

    /**
     * 设置角色编号
     *
     * @param pkRoleId 角色编号
     */
    public void setPkRoleId(Integer pkRoleId) {
        this.pkRoleId = pkRoleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取是否已经删除
     *
     * @return is_delete - 是否已经删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否已经删除
     *
     * @param isDelete 是否已经删除
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
     * 获取最后修改时间
     *
     * @return update_time - 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}