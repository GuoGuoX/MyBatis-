import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mybatisExtension.bean.Empinfo;
import mybatisExtension.bean.User;
import mybatisExtension.dao.UserMapperExtension;
import mybatisExtension.util.EmpStatus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MybatisExtensionTest {

    private static String driver = "oracle.jdbc.OracleDriver";
    private static String url = "jdbc:oracle:thin:@192.168.154.128:1521/orcl";
    private static String user = "scott";
    private static String password = "tiger";

    static{
        try {
            //注册驱动
            //DriverManager.registerDriver(driver) 此方法会导致驱动注册两次,且还会依赖jar包
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("初始化驱动出错");
        }
    }
    public static Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }
    public static void close(Connection conn,Statement st,ResultSet rs){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                conn=null;//为什么要指向null值,把该对象迅速作为java垃圾回收的对象,为了把它占用的资源释放
            }
        }

        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                st=null;

            }
        }

        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                rs=null;

            }
        }
    }

    @Test
    public void test01() throws ClassNotFoundException, SQLException {

        Connection connection = getConnection();
        System.out.println(connection);
        PreparedStatement preparedStatement = connection.prepareStatement("select *from emp");
        boolean execute = preparedStatement.execute();
        System.out.println(execute);
        close(connection,preparedStatement,null);
    }

    @Test
    public void test02() throws IOException {
        String conf = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(conf);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        try {
            UserMapperExtension mapper = sqlSession.getMapper(UserMapperExtension.class);
            Page<Object> page = PageHelper.startPage(4, 1);

            List<User> users = mapper.selectUsers();
            for (User user : users){
                System.out.println(user);
            }
            /*System.out.println("当前页码page.getPageNum()="+page.getPageNum());
            System.out.println("总页码数page.getPages()="+page.getPages());
            System.out.println("总记录数page.getTotal()="+page.getTotal());
            System.out.println("当前页显示记录数page.getPageSize()="+page.getPageSize());*/
            //也可利用pageInfo来获取分页信息
            PageInfo<User> pageInfo = new PageInfo<User>(users,5);
            System.out.println("当前页码page.getPageNum()="+pageInfo.getPageNum());
            System.out.println("总页码数page.getPages()="+pageInfo.getPages());
            System.out.println("总记录数page.getTotal()="+pageInfo.getTotal());
            System.out.println("当前页显示记录数page.getPageSize()="+pageInfo.getPageSize());
            System.out.println("当前页是否第一页page.isIsFirstPage()="+pageInfo.isIsFirstPage());
            System.out.println("当前页是否最后一页page.isIsLastPage()="+pageInfo.isIsLastPage());
            System.out.println("连续显示的页码");
            int[] navigatepageNums = pageInfo.getNavigatepageNums();
            for(int i : navigatepageNums){
                System.out.print(i+" ");
            }
            System.out.println();

            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    //批量操作
    @Test
    public void test03() throws IOException {
        String conf = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(conf);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //SqlSession sqlSession = build.openSession();
        //获取具有批量操作的sql会话
        SqlSession sqlSession = build.openSession(ExecutorType.BATCH);
        try {
            UserMapperExtension mapper = sqlSession.getMapper(UserMapperExtension.class);
            int start = 20;
            int end = 30;
            int start1 = 1;
            int end1 = 2;
            String i1 = String.valueOf(new Random().nextInt(2));//取0到1的随机数
            long l = System.currentTimeMillis();

            for(int i=1;i<100;i++){
                int randomValue = start+(int)(Math.random()*(end-start+1));//取20到30之间的随机数
                String randomValue1 = String.valueOf(start1+(int)(Math.random()*(end1-start1+1)));//取1到2之间的随机数
                mapper.addUser(new User(null,UUID.randomUUID().toString().substring(1,5),randomValue,i1,randomValue1,null));
//                System.out.println(randomValue1);
            }
            sqlSession.commit();
            long l1 = System.currentTimeMillis();//开启批量操作,用时868毫秒   未开启批量操作,用时1018毫秒
            System.out.println(l1-l);
        } finally {
            sqlSession.close();
        }
    }


    //用mybatis调用oracle的存储过程
    @Test
    public void test04() throws IOException {
        String conf = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(conf);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        try {
            UserMapperExtension mapper = sqlSession.getMapper(UserMapperExtension.class);
            /**
             * 对应Oracle中的存储过程

             create or replace procedure
             --创建一个具有分页功能的存储过程
             hello_procedure(
                p_start in int,p_end in int,p_count out int,p_emps out sys_refcursor
             )as
             begin
                select count(*) into p_count from empinfo;
             open p_emps for
                select * from (select rownum rn,e.* from empinfo e where rownum<=p_end)
                where rn>=p_start;
             end hello_procedure;
             */
            //取oracle表中第一行到第五行的数据
            mybatisExtension.dao.Page page = new mybatisExtension.dao.Page(1, 5);
            mapper.getUserByOracleProcedure(page);
            for(Empinfo emp:page.getEmps()){
                System.out.println(emp);
            }
            System.out.println("总记录数："+page.getCount());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }

    //测试修改指定枚举类处理器
    @Test
    public void test05() throws IOException {
        String conf = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(conf);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        try {
            UserMapperExtension mapper = sqlSession.getMapper(UserMapperExtension.class);
            User user = new User(null,"mybatisEnumTypeHandler2",22,"0","2",null);
            boolean b = mapper.addUser(user);

            System.out.println(b+"userId="+user.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testEnum(){
        EmpStatus empStatus = EmpStatus.LOGIN;
        System.out.println("枚举的名称:"+empStatus.name());
        System.out.println("枚举的索引:"+empStatus.ordinal());
        System.out.println("枚举的状态码:"+empStatus.getCode());
        System.out.println("枚举的提示信息:"+empStatus.getStatus());
    }

    @Test
    public void test06() throws IOException {
        String conf = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(conf);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        try {
            UserMapperExtension mapper = sqlSession.getMapper(UserMapperExtension.class);
            //使用了自定义类型处理器处理枚举类映射
            User user = mapper.selectUserById(722);
            //使用自定义类型处理器执行添加
            /*User user = new User(null,"mybatisEnumTypeHandler3",32,"1","1",null);
            user.setEmpStatus(EmpStatus.REMOVE);
            mapper.addUser(user);
            sqlSession.commit();
            System.out.println("userId="+user.getId());*/
            System.out.println("user"+user);
            System.out.println("enum"+user.getEmpStatus());
            /**
             * userUser{id=722, userName='mybatisEnumTypeHandler3', age=32, gender='1', dId='1', dept=null, empStatus=REMOVE}
             * enumREMOVE
             */
        } finally {
            sqlSession.close();
        }
    }
}
