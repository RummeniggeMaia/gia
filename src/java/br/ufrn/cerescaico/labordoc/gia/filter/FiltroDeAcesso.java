/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.filter;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rummenigge
 */
public class FiltroDeAcesso implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

//        Usuario logado = (Usuario) req.getSession()
//                .getAttribute(Consts.USUARIO_LOGADO);
//        String reqUrl = req.getRequestURL().toString();
//        if (!reqUrl.matches(".*/gia/javax.faces.resource.*")) {
//            if (logado != null) {
//                if (logado.getRole().equals(Usuario.ROLE_USER)) {
//                    if (!reqUrl.matches(".*/gia/testes_site/usuario/.*")) {
//                        resp.sendRedirect("/gia/testes_site/usuario/home.jsf");
//                    }
//                } else if (logado.getRole().equals(Usuario.ROLE_ADMIN)) {
//                    if (!reqUrl.matches(".*/gia/testes_site/admin/.*")) {
//                        resp.sendRedirect("/gia/testes_site/admin/home.jsf");
//                    }
//                }
//            } else {
//                if (!reqUrl.endsWith("/gia/testes_site/visitante/login.jsf")
//                        && !reqUrl.endsWith("/gia/")) {
//                    resp.sendRedirect("/gia/testes_site/visitante/login.jsf");
//                }
//            }
//        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
