package com.kwetter.interceptor;

import com.kwetter.dao.interfaces.AuthDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.UUID;

@Stateless
public class AuthInterceptor {
    @Inject
    private AuthDAO authDAO;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception{
        if (authDAO.findTokenForUser((UUID) context.getParameters()[0]) != null){
            return context.proceed();
        }
        throw new IllegalAccessException();
    }
}
