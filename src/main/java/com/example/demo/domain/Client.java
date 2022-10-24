package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.enums.ClientType;
import com.example.demo.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique=true)
    private String email;
    private String CPFOrCNPJ;
    private Integer clientType;

    @JsonIgnore
    private String password;


    @OneToMany(mappedBy = "client", cascade=CascadeType.ALL)
    private List<Address> adresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="CONTACTS")
    private Set<String> contacts = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="USERTYPE")
    private Set<Integer> types = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orderes = new ArrayList<>();

    public Client() {
        setUserType(UserType.CLIENT);
    }
    
    public Client(Integer id, String name, String email, String password, String CPFOrCNPJ, ClientType clientType) {
        super();
        this.id = id;
        this.name = name;
        setUserType(UserType.CLIENT);
        this.email = email;
        this.password = password;
        this.CPFOrCNPJ = CPFOrCNPJ;
        this.clientType = ( clientType == null) ? null : clientType.getCod();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public ClientType getClientType() {
        return ClientType.toEnum(clientType);
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType.getCod();
    }

    public List<Address> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Address> adresses) {
        this.adresses = adresses;
    }

    public Set<String> getContacts() {
        return contacts;
    }

    public void setContacts(Set<String> contacts) {
        this.contacts = contacts;
    }

    public Set<UserType> getUserTypes() {
        return types.stream().map(x -> UserType.toEnum(x)).collect(Collectors.toSet());
    }

    public void setUserType(UserType ut) {
        types.add(ut.getCod());
    }

    public List<Order> getOrderes() {
        return orderes;
    }

    public void setOrderes(List<Order> orderes) {
        this.orderes = orderes;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}