package com.nonstop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nonstop.Tools.CharsetTool;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "管理員資料實體類")
public class Manager implements Serializable {
    @JsonIgnore
    CharsetTool cstool = new CharsetTool();
    @JsonProperty(value = "manID")
    @Schema(description = "管理員ID")
    Long manID;
    @JsonProperty(value = "manAccount")
    @Schema(description = "帳號")
    String manAccount;
    @JsonProperty(value = "manPassword")
    @Schema(description = "密碼")
    String manPassword;
    @JsonProperty(value = "manSalt")
    @Schema(description = "加密鹽值")
    String manSalt;
    @JsonProperty(value = "manName")
    @Schema(description = "姓名")
    String manName;
    @JsonProperty(value = "manPhone")
    @Schema(description = "電話")
    String manPhone;
    @JsonProperty(value = "manEmail")
    @Schema(description = "信箱")
    String manEmail;
    @JsonProperty(value = "manAddress")
    @Schema(description = "通訊地址")
    String manAddress;
    @JsonProperty(value = "manMsg")
    @Schema(description = "備註訊息")
    String manMsg;

    public Long getManID() {
        return manID;
    }

    public void setManID(Long manID) {
        this.manID = manID;
    }

    public String getManAccount() {
        return manAccount;
    }

    public void setManAccount(String manAccount) {
        if (cstool.isEncoding(manAccount, "ISO-8859-1"))
            this.manAccount = cstool.iso2utf8(manAccount);
        else
            this.manAccount = manAccount;
    }

    public String getManPassword() {
        return manPassword;
    }

    public void setManPassword(String manPassword) {
        if (cstool.isEncoding(manPassword, "ISO-8859-1"))
            this.manPassword = cstool.iso2utf8(manPassword);
        else
            this.manPassword = manPassword;
    }

    public String getManSalt() {
        return manSalt;
    }

    public void setManSalt(String manSalt) {
        if (cstool.isEncoding(manSalt, "ISO-8859-1"))
            this.manSalt = cstool.iso2utf8(manSalt);
        else
            this.manSalt = manSalt;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        if (cstool.isEncoding(manName, "ISO-8859-1"))
            this.manName = cstool.iso2utf8(manName);
        else
            this.manName = manName;
    }

    public String getManPhone() {
        return manPhone;
    }

    public void setManPhone(String manPhone) {
        if (cstool.isEncoding(manPhone, "ISO-8859-1"))
            this.manPhone = cstool.iso2utf8(manPhone);
        else
            this.manPhone = manPhone;
    }

    public String getManEmail() {
        return manEmail;
    }

    public void setManEmail(String manEmail) {
        if (cstool.isEncoding(manEmail, "ISO-8859-1"))
            this.manEmail = cstool.iso2utf8(manEmail);
        else
            this.manEmail = manEmail;
    }

    public String getManAddress() {
        return manAddress;
    }

    public void setManAddress(String manAddress) {
        if (cstool.isEncoding(manAddress, "ISO-8859-1"))
            this.manAddress = cstool.iso2utf8(manAddress);
        else
            this.manAddress = manAddress;
    }

    public String getManMsg() {
        return manMsg;
    }

    public void setManMsg(String manMsg) {
        if (cstool.isEncoding(manMsg, "ISO-8859-1"))
            this.manMsg = cstool.iso2utf8(manMsg);
        else
            this.manMsg = manMsg;
    }

    public Manager() {
        this.manID = 0L;
        this.manAccount = "";
        this.manPassword = "";
        this.manSalt = "";
        this.manName = "";
        this.manPhone = "";
        this.manEmail = "";
        this.manAddress = "";
        this.manMsg = "";
    }

    public Manager(Long manID, String manAccount, String manPassword, String manSalt, String manName, String manPhone, String manEmail, String manAddress, String manMsg) {
        this.manID = manID;
        setManAccount(manAccount);
        setManPassword(manPassword);
        setManSalt(manSalt);
        setManName(manName);
        setManPhone(manPhone);
        setManEmail(manEmail);
        setManAddress(manAddress);
        setManMsg(manMsg);
    }
}
