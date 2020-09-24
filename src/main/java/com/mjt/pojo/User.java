package com.mjt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name; //作者名

    @Column(name = "password")
    private String password;

    @Column(name = "realname")
    private String realname; //真实名字

    @Column(name = "cellNumber")
    private String cellNumber; //手机号码

    @Column(name = "emallAddress")
    private String emallAddress; //邮箱地址

    @Column(name = "address")
    private String address; //所在地址

    @Column(name = "postalCode")
    private String postalCode; //邮政编号

    @Column(name = "salt")
    private String salt;//加密用的盐

    @Transient
    private ProductImage fileImage;

    public ProductImage getFileImage() {
        return fileImage;
    }

    public void setFileImage(ProductImage fileImage) {
        this.fileImage = fileImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmallAddress() {
        return emallAddress;
    }

    public void setEmallAddress(String emallAddress) {
        this.emallAddress = emallAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
