package mybatisPlugin.dao;

import mybatisPlugin.bean.User;

import java.util.List;

public class UserImpl implements UserMapperPlugin {
    public User selectUserById(Integer id) {
        System.out.println("user实现类执行");
        return null;
    }

    public List<User> selectUsers() {
        return null;
    }
}
