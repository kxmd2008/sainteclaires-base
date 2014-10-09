package org.luis.sainteclaires.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.luis.basic.util.SpringContextFactory;
import org.luis.sainteclaires.base.INameSpace;
import org.luis.sainteclaires.base.bean.Account;
import org.luis.sainteclaires.base.bean.Order;
import org.luis.sainteclaires.base.bean.OrderItem;
import org.luis.sainteclaires.base.bean.service.AccountService;
import org.luis.sainteclaires.base.bean.service.OrderService;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class MySavedRequestAwareAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        session.setAttribute("userName", userDetails.getUsername());
        Account dbUser = accountService.getAccount(userDetails.getUsername());  
        session.setAttribute("custAccount", dbUser);
        
        Order bag = (Order) session.getAttribute(INameSpace.KEY_SESSION_ORDER);
        OrderService orderService = SpringContextFactory.getSpringBean(OrderService.class);
        Order order = orderService.findUnpayOrder(dbUser.getLoginName());
        if(bag == null){
        	session.setAttribute(INameSpace.KEY_SESSION_ORDER, order);
        } else {
        	if(order != null){
        		order.setAmount(order.getAmount().add(bag.getAmount()));
        		for(OrderItem item : bag.getItems()){
        			order.getItems().add(item);
        		}
        	}
        }
        String locale = (String) BaseUtil.getSessionAttr(request, INameSpace.KEY_SESSION_LOCALE);
        if(locale == null){
        	BaseUtil.setSessionAttr(request, INameSpace.KEY_SESSION_LOCALE, "zh_CN");
        }
        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
    
    @Autowired
    private AccountService accountService;
	
}
