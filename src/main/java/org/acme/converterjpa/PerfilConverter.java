package org.acme.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.acme.model.Perfil;

@Converter(autoApply = true)
public class PerfilConverter implements AttributeConverter< Perfil, String>{

    @Override
    public String convertToDatabaseColumn(Perfil perfil) {
        return perfil == null ? null : perfil.getLabel();
    }

    @Override
    public Perfil convertToEntityAttribute(String label) {
        return label == null ? null : Perfil.valueOf(label.toUpperCase());
    }
    
}
