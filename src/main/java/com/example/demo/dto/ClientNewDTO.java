package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.demo.services.validadation.ClientStore;

@ClientStore
public class ClientNewDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min=5, max=120, message = "O tamanho precisa ser entre 5 e 120 caracteres!")
    private String name;
    
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String CPFOrCNPJ;
    private Integer clientType;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String number;

    private String complement;
    private String district;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String zipcode;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;

    private Integer cityId;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String password;


    public ClientNewDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPFOrCNPJ() {
        return CPFOrCNPJ;
    }

    public void setCPFOrCNPJ(String cPFOrCNPJ) {
        CPFOrCNPJ = cPFOrCNPJ;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
