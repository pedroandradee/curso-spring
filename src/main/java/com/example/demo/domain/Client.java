package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String CPFOrCNPJ;
    private Integer clientType;

    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    private List<Address> adresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="CONTACTS")
    private Set<String> contacts = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<Order> orderes = new ArrayList<>();

    public Client() {}

    public Client(Integer id, String name, String email, String CPFOrCNPJ, ClientType clientType) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.CPFOrCNPJ = CPFOrCNPJ;
        this.clientType = clientType.getCod();
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

    public List<Order> getOrderes() {
        return orderes;
    }

    public void setOrderes(List<Order> orderes) {
        this.orderes = orderes;
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