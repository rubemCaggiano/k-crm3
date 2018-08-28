package br.com.kadesh.util.converter;

import br.com.kadesh.dao.impl.ProdutoDao;
import br.com.kadesh.model.Produto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            ProdutoDao produtoDao = new ProdutoDao();
            Produto produto = produtoDao.find(id);
            return produto;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Produto produto = (Produto) value;
        if (produto != null) {
            return String.valueOf(produto.getId());
        } else {
            return null;
        }
    }

}
