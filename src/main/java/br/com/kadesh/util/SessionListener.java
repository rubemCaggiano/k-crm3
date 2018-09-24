
package br.com.kadesh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Sessão criada " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date(se.getSession().getLastAccessedTime()));
        System.out.println("Sessão expirada " + se.getSession().getId() + ". Ultimo Acesso = " + ultimoAcesso);
    }

}
