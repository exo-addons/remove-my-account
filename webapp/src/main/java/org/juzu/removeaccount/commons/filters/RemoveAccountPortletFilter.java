package org.juzu.removeaccount.commons.filters;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.webui.Utils;

import javax.portlet.*;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;
import java.io.IOException;

/**
 * Created by exoplatform on 14/01/15.
 */
public class RemoveAccountPortletFilter implements RenderFilter {
  private final static Log log = ExoLogger.getLogger(RemoveAccountPortletFilter.class);

  @Override
  public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException, PortletException {
    log.info("start append remove account btn for "+    Utils.getOwnerRemoteId()+" - "+Utils.getViewerRemoteId());
    chain.doFilter(request,response);
  }

  @Override
  public void init(FilterConfig filterConfig) throws PortletException {
    log.info("juzu filter init");
  }

  @Override
  public void destroy() {

  }
}
