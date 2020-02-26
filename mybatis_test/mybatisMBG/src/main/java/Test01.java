import mybatisMBG.bean.User;
import mybatisMBG.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test01 {
    @Test
    public void test0() throws IOException, XMLParserException, SQLException, InterruptedException, InvalidConfigurationException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void test02() throws IOException {
        String conf = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(conf);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectByPrimaryKey(1);
            sqlSession.commit();
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }
}
