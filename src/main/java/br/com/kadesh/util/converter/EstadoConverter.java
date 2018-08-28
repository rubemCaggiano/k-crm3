package br.com.kadesh.util.converter;

import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.model.Estado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            EstadoDao estadoDAO = new EstadoDao();
            Estado estado = estadoDAO.find(id);
            return estado;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Estado estado = (Estado) value;
        if (estado != null) {
            return String.valueOf(estado.getId());
        } else {
            return null;
        }
    }

}
