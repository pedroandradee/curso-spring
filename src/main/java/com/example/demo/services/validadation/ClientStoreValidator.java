package com.example.demo.services.validadation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Client;
import com.example.demo.dto.ClientNewDTO;
import com.example.demo.enums.ClientType;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.resourses.exceptions.FieldMessage;
import com.example.demo.services.validadation.utils.BR;

public class ClientStoreValidator implements ConstraintValidator<ClientStore, ClientNewDTO> {
    
    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(ClientStore ann) {}

    @Override
    public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
        
        List<FieldMessage> list = new ArrayList<>();

        if (objDTO.getClientType().equals(ClientType.FISICPERSON.getCod()) && !BR.isValidCPF(objDTO.getCPFOrCNPJ())) {
            list.add(new FieldMessage("CPFOrCNPJ", "Invalid cpf!"));
        }

        if (objDTO.getClientType().equals(ClientType.JURIDICPERSON.getCod()) && !BR.isValidCNPJ(objDTO.getCPFOrCNPJ())) {
            list.add(new FieldMessage("CPFOrCNPJ", "Invalid cnpj!"));
        }

        Client aux = repo.findByEmail(objDTO.getEmail());

        if (aux != null) {
            list.add(new FieldMessage("email", "Email already exists!"));
        }

        for (FieldMessage e: list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                .addPropertyNode(e.getFieldName())
                .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
