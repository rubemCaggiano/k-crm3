package br.com.kadesh.util.converter;

import br.com.kadesh.dao.impl.FamiliaDao;
import br.com.kadesh.model.Familia;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("familiaConverter")
public class FamiliaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            FamiliaDao familiaDao = new FamiliaDao();
            Familia familia = familiaDao.find(id);
            return familia;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Familia familia = (Familia) value;
        if (familia != null) {
            return String.valueOf(familia.getId());
        } else {
            return null;
        }
    }

}
