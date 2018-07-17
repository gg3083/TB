package com.gg3083.tb.domain.user;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user")
public class User {
    /**
     * 用户编号
     */
    @Id
    @Column(name = "pk_user_id")
    private String pkUserId;

    /**
     * 登录姓名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户密码
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_img")
    private String userImg;

    /**
     * 邮件
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 电话
     */
    @Column(name = "user_phone")
    private String userPhone;

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
     * 获取用户编号
     *
     * @return pk_user_id - 用户编号
     */
    public String getPkUserId() {
        return pkUserId;
    }

    /**
     * 设置用户编号
     *
     * @param pkUserId 用户编号
     */
    public void setPkUserId(String pkUserId) {
        this.pkUserId = pkUserId;
    }

    /**
     * 获取登录姓名
     *
     * @return login_name - 登录姓名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录姓名
     *
     * @param loginName 登录姓名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取用户密码
     *
     * @return login_pwd - 用户密码
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * 设置用户密码
     *
     * @param loginPwd 用户密码
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return user_img
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * @param userImg
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * 获取邮件
     *
     * @return user_email - 邮件
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置邮件
     *
     * @param userEmail 邮件
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取电话
     *
     * @return user_phone - 电话
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置电话
     *
     * @param userPhone 电话
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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