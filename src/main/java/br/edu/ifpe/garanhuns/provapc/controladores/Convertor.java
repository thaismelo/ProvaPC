/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.ifpe.garanhuns.provapc.model.Turma;

/**
 *
 * @author Thais
 */
@FacesConverter("convertor")
public class Convertor implements Converter{

    
    public Turma getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
               ControladorTurma service = (ControladorTurma) fc.getExternalContext().getApplicationMap().get("ControladorTurma");
                return service.recuperar(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic,Object turma) {
        if(turma != null) {
            String resultado = ((Turma)turma).getTurma();
            return  resultado;
        }
        else {
            return null;
        }
    }
 
    
       
    
   
    
}
