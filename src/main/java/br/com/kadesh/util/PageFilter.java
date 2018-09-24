package br.com.kadesh.util;

import br.com.kadesh.model.Usuario;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageFilter implements Filter {

//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//             User user = null;
//             HttpSession sess = ((HttpServletRequest) request).getSession(false);
//              
//             if (sess != null){
//                   user = (User) sess.getAttribute("usuarioLogado");
//             }      
//  
//                   if (user == null) {
//                            String contextPath = ((HttpServletRequest) request)
//                                               .getContextPath();
//                            ((HttpServletResponse) response).sendRedirect(contextPath
//                                               + "/security/form_login.xhtml");
//                   } else {
//                            chain.doFilter(request, response);
//                   }
//  
//         }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Usuario usuario = null;
        HttpSession sess = ((HttpServletRequest) request).getSession(false);

        if (sess != null) {
            usuario = (Usuario) sess.getAttribute("usuarioLogado");
        }
        if (usuario == null) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }

//        String newCurrentPage = ((HttpServletRequest) request).getServletPath();
//
//        if (sess.getAttribute("currentPage") == null) {
//            sess.setAttribute("lastPage", newCurrentPage);
//            sess.setAttribute("currentPage", newCurrentPage);
//        } else {
//
//            String oldCurrentPage = sess.getAttribute("currentPage").toString();
//            if (!oldCurrentPage.equals(newCurrentPage)) {
//                sess.setAttribute("lastPage", oldCurrentPage);
//                sess.setAttribute("currentPage", newCurrentPage);
//            }
//        }
//
//        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
