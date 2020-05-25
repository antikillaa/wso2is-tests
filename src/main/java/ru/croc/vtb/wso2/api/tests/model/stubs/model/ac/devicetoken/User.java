package ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * <b>Выходная ДТО ответа авторизации по девайс-токену</b>
 *
 * @author Logvin I. N.
 */
public class User {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("domain")
    private String domain = "master";

    @JsonProperty("username")
    private String username = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("homePhone")
    private String homePhone = null;

    @JsonProperty("mobile")
    private String mobile = null;

    @JsonProperty("accountLock")
    private Boolean accountLock = null;

    @JsonProperty("disable_smsotp")
    private Boolean disableSmsotp = null;

    public User id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User domain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Get domain
     *
     * @return domain
     **/
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Get username
     *
     * @return username
     **/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     **/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User homePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    /**
     * Get homePhone
     *
     * @return homePhone
     **/
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public User mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /**
     * Get mobile
     *
     * @return mobile
     **/
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User accountLock(Boolean accountLock) {
        this.accountLock = accountLock;
        return this;
    }

    /**
     * Get accountLock
     *
     * @return accountLock
     **/
    public Boolean isAccountLock() {
        return accountLock;
    }

    public void setAccountLock(Boolean accountLock) {
        this.accountLock = accountLock;
    }

    public User disableSmsotp(Boolean disableSmsotp) {
        this.disableSmsotp = disableSmsotp;
        return this;
    }

    /**
     * Get disableSmsotp
     *
     * @return disableSmsotp
     **/
    public Boolean isDisableSmsotp() {
        return disableSmsotp;
    }

    public void setDisableSmsotp(Boolean disableSmsotp) {
        this.disableSmsotp = disableSmsotp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.id, user.id) &&
                Objects.equals(this.domain, user.domain) &&
                Objects.equals(this.username, user.username) &&
                Objects.equals(this.email, user.email) &&
                Objects.equals(this.homePhone, user.homePhone) &&
                Objects.equals(this.mobile, user.mobile) &&
                Objects.equals(this.accountLock, user.accountLock) &&
                Objects.equals(this.disableSmsotp, user.disableSmsotp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, domain, username, email, homePhone, mobile, accountLock, disableSmsotp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    domain: ").append(toIndentedString(domain)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    homePhone: ").append(toIndentedString(homePhone)).append("\n");
        sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
        sb.append("    accountLock: ").append(toIndentedString(accountLock)).append("\n");
        sb.append("    disableSmsotp: ").append(toIndentedString(disableSmsotp)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
