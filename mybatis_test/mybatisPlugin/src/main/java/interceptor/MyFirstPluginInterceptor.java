package interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;
@Intercepts({@Signature(type = StatementHandler.class, method ="parameterize" , args = Statement.class)})
public class MyFirstPluginInterceptor implements Interceptor {
    //执行目标方法前拦截
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println(this+"即将调用目标方法"+invocation.getMethod());
        /**
         * 需求:查询id为1的数据,通过拦截器将id改为其他值,从而查询其他id的数据
         * 使用了MyBatis的工具类SystemMetaObject和MetaObject类
         */
        MetaObject metaObject = SystemMetaObject.forObject(invocation.getTarget());
        //当前的拦截器签名是StatementHandler,MetaObject获取值是通过属性名称获取值
        //StatementHandler==>parameterHandler==>parameterObject
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("预编译参数值是:"+value);
        //设置改变预编译参数
        metaObject.setValue("parameterHandler.parameterObject","15");
        Object proceed = invocation.proceed();
        //返回目标方法执行后返回的参数
        return proceed;
    }

    //包装目标对象的,包装:为目标对象创建一个代理对象
    public Object plugin(Object target) {
        System.out.println("即将要包装的对象MyFirstPlugin...plugin"+target);
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }
    //参数赋值
    public void setProperties(Properties properties) {
    }
}
