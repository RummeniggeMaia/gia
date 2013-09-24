/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.filter;

import java.io.IOException;
import javax.servlet.*;

/**
 *
 * @author Rummenigge
 */

public class CharsetFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) 
            throws ServletException {
    }

    @Override
    public void doFilter(
            ServletRequest request, 
            ServletResponse response, 
            FilterChain chain) throws IOException, ServletException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
