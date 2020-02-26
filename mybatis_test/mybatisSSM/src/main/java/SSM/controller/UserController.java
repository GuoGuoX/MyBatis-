package SSM.controller;

import SSM.bean.User;
import SSM.dao.UserMapperSSM;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Controller
public class UserController {
//    @Autowired
//    private SqlSession sqlSession;
    @Autowired
    private UserMapperSSM userMapperSSM;
    /*
    @RequestMapping("/selectAll")
    public String selectAll(Map<String,Object> map){
        List<User> list = userMapperSSM.selectUserAll();
        map.put("usersList",list);
        return "mybatis_ssm";
    }*/
    @RequestMapping("/addUser")
    public String addUser(String flag){
        flag = "true";
        try {
            int start = 20;
            int end = 30;
            int start1 = 1;
            int end1 = 2;
            String i1 = String.valueOf(new Random().nextInt(2));//取0到1的随机数
            long l = System.currentTimeMillis();

            for(int i=1;i<100;i++){
                int randomValue = start+(int)(Math.random()*(end-start+1));//取20到30之间的随机数
                String randomValue1 = String.valueOf(start1+(int)(Math.random()*(end1-start1+1)));//取1到2之间的随机数
                userMapperSSM.addUser(new User(null,UUID.randomUUID().toString().substring(1,5),randomValue,i1,randomValue1,null));
//                System.out.println(randomValue1);
            }
            long l1 = System.currentTimeMillis();//开启批量操作,用时868毫秒   未开启批量操作,用时1018毫秒
            System.out.println(l1-l);//spring开启批量操作用时1114   //779直接接口注入
        } catch (Exception e) {
            e.printStackTrace();
            flag = "false";
        }
        return "mybatis_ssm";
    }
}
