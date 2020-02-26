import mybatis.bean.Dept;
import mybatis.bean.User;
import mybatis.dao.DeptMapper;
import mybatis.dao.UserMapperPlus;
import mybatis.dynamicSQL.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    public SqlSessionFactory getSqlSessionFactory(){
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
    /**
     * 通过非接口式操作数据库
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Object selectUser = sqlSession.selectOne("selectUser", 1);
            System.out.println(selectUser);
        } finally {
            sqlSession.close();
    }
    }

    @Test
    public void test02(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();//参数为true时自动提交
        User user = new User(null, "王五", 23, '0',null,null );

        mybatis.dao.UserMapper mapper = sqlSession.getMapper(mybatis.dao.UserMapper.class);
        //插入数据库
        //Integer aBoolean = mapper.insertUser(user);
        //删除数据库
        //Boolean aBoolean = mapper.deleteUserById(14);
        //修改数据库
       //Boolean aBoolean = mapper.updateUserById(user);
        //查询数据库
        //User user1 = mapper.selectUserById(15);
        //mybatis如何处理多个参数
        User user1 = mapper.selectUserByIdAndLastName(15, "王五");
        sqlSession.commit();
        //System.out.println(aBoolean);
        System.out.println(user1);
        System.out.println(user.getId());//获取插入数据后的自增主键值
        /**
         * mybatis处理多个参数转换源码
         public Object getNamedParams(Object[] args) {
         int paramCount = this.names.size();
         if (args != null && paramCount != 0) {
         //判断是否添加了@Param注解
         if (!this.hasParamAnnotation && paramCount == 1) {
         return args[(Integer)this.names.firstKey()];
         } else {
         //初始化一个map用于封装参数名称
         Map<String, Object> param = new ParamMap();
         int i = 0;
         //循环遍历外部传入的查询参数名称map:id,userName
         for(Iterator var5 = this.names.entrySet().iterator(); var5.hasNext(); ++i) {
         Entry<Integer, String> entry = (Entry)var5.next();
         //将map中参数名称的value作为key,args[0]的值作为map的值
         param.put((String)entry.getValue(), args[(Integer)entry.getKey()]);
         //提供另一种获取参数名的方式param1.param2...
         String genericParamName = "param" + (i + 1);
         if (!this.names.containsValue(genericParamName)) {
         param.put(genericParamName, args[(Integer)entry.getKey()]);
         }
         }

         return param;
         }
         } else {
         return null;
         }
         }
         */
    }

    @Test
    public void test03(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            mybatis.dao.UserMapper mapper = sqlSession.getMapper(mybatis.dao.UserMapper.class);
            //查询结果list
            //List<User> user= mapper.selectUserByLastName("%五");
            //查询结果map 字段名为key，字段值为value  {gender=0, user_name=张三, id=1, age=19}
            //Map<String, Object> integerUserMap = mapper.selectUserMapByLastName("张三");
            //查询结果map 指定字段名为key，User为value  {16=User{id=16, userName='王五', age=23, gender=0},
            Map<Integer, User> integerUserMap = mapper.selectUserMapLikeByLastName("%五");
            sqlSession.commit();
            //System.out.println(user);
            System.out.println(integerUserMap);
        } finally {
            sqlSession.close();
        }
    }

    //关联映射
    @Test
    public void test04(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapperPlus mapper = sqlSession.getMapper(UserMapperPlus.class);
            //User user = mapper.selectUserById(1);
            //User user = mapper.selectUserMap(17);//association标签配置结果集
            User user = mapper.selectUserMapStep(17);//association标签实现分步查询
/**
 [13 23:46:37,071 DEBUG] [main] UserMapperPlus.selectUserMapStep - ==>  Preparing: select * from mybatis where id = ?
 [13 23:46:37,127 DEBUG] [main] UserMapperPlus.selectUserMapStep - ==> Parameters: 17(Integer)
 [13 23:46:37,148 DEBUG] [main] DeptMapper.selectDeptById - ====>  Preparing: select * from dept where id = ?
 [13 23:46:37,148 DEBUG] [main] DeptMapper.selectDeptById - ====> Parameters: 2(Integer)
 [13 23:46:37,150 DEBUG] [main] DeptMapper.selectDeptById - <====      Total: 1
 [13 23:46:37,150 DEBUG] [main] UserMapperPlus.selectUserMapStep - <==      Total: 1
 User{id=17, userName='王五', age=23, gender=0, dId='2'}
 Dept{id=2, deptName='营销部'}
 */

            sqlSession.commit();
            System.out.println(user);
            //System.out.println(user.getDept());
        } finally {
            sqlSession.close();
        }

    }


    @Test
    public void test05(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
            //Dept dept = mapper.selectDeptAndUsers(1);
            //collection分步查询
            Dept dept = mapper.selectDeptAndUsersStep(2);
            sqlSession.commit();
            System.out.println(dept);
            //System.out.println(dept.getUsers());

        } finally {
            sqlSession.close();
        }


    }

    @Test
    public void test06(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapperPlus mapper = sqlSession.getMapper(UserMapperPlus.class);
            User user = mapper.selectUserByIdDis(1);
            System.out.println(user);
            System.out.println(user.getDept());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void dynamicSQL(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<mybatis.dynamicSQL.bean.User> users = mapper.selectUserByUser1(new mybatis.dynamicSQL.bean.User(1,null,null,null,null,null));
            sqlSession.commit();
            System.out.println(users);
        } finally {
            sqlSession.close();

        }
    }
}
