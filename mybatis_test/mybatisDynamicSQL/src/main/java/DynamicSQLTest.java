import SSM.bean.User;
import SSM.dao.UserMapperDynamicSQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DynamicSQLTest {

    public SqlSessionFactory getSqlSessionFactory(){
        String conf = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
    @Test
    public void test1(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapperDynamicSQL mapper = sqlSession.getMapper(UserMapperDynamicSQL.class);
            List<User> list = new ArrayList<User>();
            User user = new User(null,"tony",16,"0","2",null);
            User user1 = new User(null,"tony",16,"0","2",null);

            User user2 = new User(null,"jerry",22,"1","1",null);
            list.add(user);
            list.add(user2);
            //if/where/choose
            /*List<User> users = mapper.selectUserByUser(user);
            for (User u:users){
                System.out.println(u);
            }*/
            //set
            /*Boolean aBoolean = mapper.updateUserByUser(user);
            System.out.println(aBoolean);*/
            //foreach标签
            //List<User> users = mapper.selectUserByUserByForeach(Arrays.asList(1, 16, 15, 17));
            /*for (User u:users){
                System.out.println(u);
            }*/

            Boolean aBoolean = mapper.insertListUser(list);
            System.out.println(aBoolean);
            sqlSession.commit();
        } finally {
            sqlSession.close();

        }

    }

    @Test
    public void test00(){
        User user = new User(null,"tony",16,"0","2",null);
        User user1 = new User(null,"tony",16,"0","2",null);
        System.out.println(user==user1);
    }
}
