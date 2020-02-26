import mybatisPlugin.dao.UserMapperPlugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class JDKProxyInvocation implements InvocationHandler {
    public Object o ;
    public String str = "hahaha";

    public Object getProxy(Object obj){
        this.o = obj;
        Object o1 = Proxy.newProxyInstance(o.getClass().getClassLoader(),new Class[]{}, this);
        return o1;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理对象开始执行");
        System.out.println(str);
        return null;
    }
}
