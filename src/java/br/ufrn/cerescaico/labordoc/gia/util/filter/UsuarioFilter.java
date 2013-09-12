/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.util.filter;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rummenigge
 */
public class UsuarioFilter implements Filter {

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
        Usuario logado = (Usuario) req.getSession()
                .getAttribute(Consts.USUARIO_LOGADO);
        if (logado != null && logado.getRole().equals(Usuario.ROLE_USER)) {
            String reqUrl = req.getRequestURL().toString();
            if (!reqUrl.matches(".*/gia/testes_site/usuario/.*")) {
                resp.sendRedirect("/gia/testes_site/usuario/home.jsf");
            }
        } else {
            resp.sendRedirect("/gia/testes_site/visitante/login.jsf");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
