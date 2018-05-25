package com.model.buser;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bbs_user")
public class Buser {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_sex")
    private String userSex;

    @Column(name = "user_postNum")
    private String userPostnum;

    private String zt;

    @Column(name = "user_joinDate")
    private Date userJoindate;

    @Column(name = "user_lastLogin")
    private Date userLastlogin;

    @Column(name = "user_logins")
    private Integer userLogins;

    @Column(name = "user_birthday")
    private Date userBirthday;

    @Column(name = "user_question")
    private String userQuestion;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "user_photo")
    private String userPhoto;

    @Column(name = "user_description")
    private String userDescription;

    /**
     * 中国
     */
    @Column(name = "user_address")
    private String userAddress;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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

    /**
     * @return user_password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return user_email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return user_sex
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * @param userSex
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * @return user_postNum
     */
    public String getUserPostnum() {
        return userPostnum;
    }

    /**
     * @param userPostnum
     */
    public void setUserPostnum(String userPostnum) {
        this.userPostnum = userPostnum;
    }

    /**
     * @return zt
     */
    public String getZt() {
        return zt;
    }

    /**
     * @param zt
     */
    public void setZt(String zt) {
        this.zt = zt;
    }

    /**
     * @return user_joinDate
     */
    public Date getUserJoindate() {
        return userJoindate;
    }

    /**
     * @param userJoindate
     */
    public void setUserJoindate(Date userJoindate) {
        this.userJoindate = userJoindate;
    }

    /**
     * @return user_lastLogin
     */
    public Date getUserLastlogin() {
        return userLastlogin;
    }

    /**
     * @param userLastlogin
     */
    public void setUserLastlogin(Date userLastlogin) {
        this.userLastlogin = userLastlogin;
    }

    /**
     * @return user_logins
     */
    public Integer getUserLogins() {
        return userLogins;
    }

    /**
     * @param userLogins
     */
    public void setUserLogins(Integer userLogins) {
        this.userLogins = userLogins;
    }

    /**
     * @return user_birthday
     */
    public Date getUserBirthday() {
        return userBirthday;
    }

    /**
     * @param userBirthday
     */
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    /**
     * @return user_question
     */
    public String getUserQuestion() {
        return userQuestion;
    }

    /**
     * @param userQuestion
     */
    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion;
    }

    /**
     * @return user_answer
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * @param userAnswer
     */
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * @return user_photo
     */
    public String getUserPhoto() {
        return userPhoto;
    }

    /**
     * @param userPhoto
     */
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    /**
     * @return user_description
     */
    public String getUserDescription() {
        return userDescription;
    }

    /**
     * @param userDescription
     */
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    /**
     * 获取中国
     *
     * @return user_address - 中国
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 设置中国
     *
     * @param userAddress 中国
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}