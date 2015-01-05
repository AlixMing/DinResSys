package com.txws.util;

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

public class UserFilter implements Filter {

        private FilterConfig filterConfig = null;
        
        public UserFilter() {
        }        

        /**
         *
         * @param request The servlet request we are processing
         * @param response The servlet response we are creating
         * @param chain The filter chain we are processing
         *
         * @exception IOException if an input/output error occurs
         * @exception ServletException if a servlet error occurs
         */
        @Override
		public void doFilter(ServletRequest request, ServletResponse response,
                FilterChain chain)
                throws IOException, ServletException {

        	HttpServletRequest req = ((HttpServletRequest) request);
            HttpServletResponse resp = ((HttpServletResponse) response);
            HttpSession session = req.getSession();
            
            Object o = session.getAttribute("user");
            
            if (o != null || !req.getRequestURI().endsWith("login.html") || req.getRequestURI().contains("/admin/")) {
                    chain.doFilter(req, resp);
            } else {
                    resp.sendRedirect(req.getContextPath() + "/index.html");
            }
        }

        /**
         * Return the filter configuration object for this filter.
         */
        public FilterConfig getFilterConfig() {
                return (this.filterConfig);
        }

        /**
         * Set the filter configuration object for this filter.
         *
         * @param filterConfig The filter configuration object
         */
        public void setFilterConfig(FilterConfig filterConfig) {
                this.filterConfig = filterConfig;
        }

        /**
         * Destroy method for this filter
         */
        @Override
		public void destroy() {                
        }

        /**
         * Init method for this filter
         */
        @Override
		public void init(FilterConfig filterConfig) {                
                this.filterConfig = filterConfig;
        }

        /**
         * Return a String representation of this object.
         */
        @Override
        public String toString() {
                if (filterConfig == null) {
                        return ("UserFilter()");
                }
                StringBuffer sb = new StringBuffer("UserFilter(");
                sb.append(filterConfig);
                sb.append(")");
                return (sb.toString());
        } 
}
