package com.gg3083.tb.domain.rolemenu;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "role_menu")
public class RoleMenu {
    /**
     * 角色权限表编号
     */
    @Id
    @Column(name = "pk_role_perm_id")
    private Integer pkRolePermId;

    /**
     * 角色表id（关联角色表pk_role_id字段）
     */
    @Column(name = "fk_role_id")
    private Integer fkRoleId;

    /**
     * 菜单权限
     */
    @Column(name = "menu_rights")
    private String menuRights;

    /**
     * 增加的权限
     */
    @Column(name = "add_rights")
    private String addRights;

    /**
     * 删除的权限
     */
    @Column(name = "del_righs")
    private String delRighs;

    /**
     * 查看的权限
     */
    @Column(name = "select_rights")
    private String selectRights;

    /**
     * 修改的权限
     */
    @Column(name = "update_rights")
    private String updateRights;

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

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取角色权限表编号
     *
     * @return pk_role_perm_id - 角色权限表编号
     */
    public Integer getPkRolePermId() {
        return pkRolePermId;
    }

    /**
     * 设置角色权限表编号
     *
     * @param pkRolePermId 角色权限表编号
     */
    public void setPkRolePermId(Integer pkRolePermId) {
        this.pkRolePermId = pkRolePermId;
    }

    /**
     * 获取角色表id（关联角色表pk_role_id字段）
     *
     * @return fk_role_id - 角色表id（关联角色表pk_role_id字段）
     */
    public Integer getFkRoleId() {
        return fkRoleId;
    }

    /**
     * 设置角色表id（关联角色表pk_role_id字段）
     *
     * @param fkRoleId 角色表id（关联角色表pk_role_id字段）
     */
    public void setFkRoleId(Integer fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    /**
     * 获取菜单权限
     *
     * @return menu_rights - 菜单权限
     */
    public String getMenuRights() {
        return menuRights;
    }

    /**
     * 设置菜单权限
     *
     * @param menuRights 菜单权限
     */
    public void setMenuRights(String menuRights) {
        this.menuRights = menuRights;
    }

    /**
     * 获取增加的权限
     *
     * @return add_rights - 增加的权限
     */
    public String getAddRights() {
        return addRights;
    }

    /**
     * 设置增加的权限
     *
     * @param addRights 增加的权限
     */
    public void setAddRights(String addRights) {
        this.addRights = addRights;
    }

    /**
     * 获取删除的权限
     *
     * @return del_righs - 删除的权限
     */
    public String getDelRighs() {
        return delRighs;
    }

    /**
     * 设置删除的权限
     *
     * @param delRighs 删除的权限
     */
    public void setDelRighs(String delRighs) {
        this.delRighs = delRighs;
    }

    /**
     * 获取查看的权限
     *
     * @return select_rights - 查看的权限
     */
    public String getSelectRights() {
        return selectRights;
    }

    /**
     * 设置查看的权限
     *
     * @param selectRights 查看的权限
     */
    public void setSelectRights(String selectRights) {
        this.selectRights = selectRights;
    }

    /**
     * 获取修改的权限
     *
     * @return update_rights - 修改的权限
     */
    public String getUpdateRights() {
        return updateRights;
    }

    /**
     * 设置修改的权限
     *
     * @param updateRights 修改的权限
     */
    public void setUpdateRights(String updateRights) {
        this.updateRights = updateRights;
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}