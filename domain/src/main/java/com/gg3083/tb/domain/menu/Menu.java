package com.gg3083.tb.domain.menu;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "menu")
public class Menu {
    /**
     * 权限表编号
     */
    @Id
    @Column(name = "pk_menu_id")
    private Integer pkMenuId;

    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 栏目的url
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 栏目名称
     */
    @Column(name = "menu_name")
    private String menuName;

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
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 获取权限表编号
     *
     * @return pk_menu_id - 权限表编号
     */
    public Integer getPkMenuId() {
        return pkMenuId;
    }

    /**
     * 设置权限表编号
     *
     * @param pkMenuId 权限表编号
     */
    public void setPkMenuId(Integer pkMenuId) {
        this.pkMenuId = pkMenuId;
    }

    /**
     * @return menu_id
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取栏目的url
     *
     * @return menu_url - 栏目的url
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置栏目的url
     *
     * @param menuUrl 栏目的url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 获取栏目名称
     *
     * @return menu_name - 栏目名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置栏目名称
     *
     * @param menuName 栏目名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
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

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}