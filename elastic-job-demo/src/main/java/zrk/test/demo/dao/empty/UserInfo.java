package zrk.test.demo.dao.empty;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_info
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class UserInfo {
    //用户id
    private Integer userId;

    //用户名
    private String userName;

    //密码
    private String password;

    //手机号
    private String phone;

    //分数
    private String score;

    /**
     * 获取:用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置:用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取:用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置:用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取:密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置:密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取:手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置:手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取:分数
     */
    public String getScore() {
        return score;
    }

    /**
     * 设置:分数
     */
    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }
}