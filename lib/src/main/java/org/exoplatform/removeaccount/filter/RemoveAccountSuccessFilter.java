package org.exoplatform.removeaccount.filter;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by exoplatform on 19/01/15.
 */
public class RemoveAccountSuccessFilter implements org.exoplatform.web.filter.Filter  {
  private final static Log log = ExoLogger.getLogger(RemoveAccountSuccessFilter.class);
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse res = (HttpServletResponse) servletResponse;
    HttpSession session = req.getSession();
    session.invalidate();
    res.sendRedirect("/portal");
    return;
  }

}
