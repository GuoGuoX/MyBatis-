import mybatisPlugin.dao.UserImpl;
import mybatisPlugin.dao.UserMapperPlugin;
import mybatisPlugin.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.List;

public class MybatisPluginTest {
    @Test
    public void test01() throws IOException {
        String cong = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(cong);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        try {
            UserMapperPlugin mapper = sqlSession.getMapper(UserMapperPlugin.class);
            //从第一页开始,获取第一行数据


            sqlSession.commit();
            System.out.println();
        } finally {
            sqlSession.close();
        }


    }

    @Test
    public void test02() {
        System.out.println("---");
        Object target = UserMapperPlugin.class;
       // UserMapperPlugin proxy = (UserMapperPlugin) new JDKProxyInvocation().getProxy(target);
        UserMapperPlugin o = (UserMapperPlugin)Proxy.newProxyInstance(UserMapperPlugin.class.getClassLoader(), new Class[]{UserMapperPlugin.class}, new JDKProxyInvocation());
        o.selectUserById(1);
        System.out.println("===");
       // User user = proxy.selectUserById(1);
       // System.out.println(user);
        System.out.println("===");
    }
}
