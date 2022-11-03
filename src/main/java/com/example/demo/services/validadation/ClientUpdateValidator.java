package com.example.demo.services.validadation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.example.demo.domain.Client;
import com.example.demo.dto.ClientDTO;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
    
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(ClientUpdate ann) {}

    @Override
    public boolean isValid(ClientDTO objDTO, ConstraintValidatorContext context) {
        
        List<FieldMessage> list = new ArrayList<>();

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        Client aux = repo.findByEmail(objDTO.getEmail());

        if (aux != null && !aux.getId().equals(uriId)) {
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
