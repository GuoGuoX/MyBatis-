package mybatisExtension.dao;

import mybatisExtension.bean.Empinfo;
import mybatisExtension.bean.User;

import java.util.List;

public class UserImpl implements UserMapperExtension {
    public User selectUserById(Integer id) {
        System.out.println("user实现类执行");
        return null;
    }

    public List<User> selectUsers() {
        return null;
    }

    public boolean addUser(User user) {
        return false;
    }

    public List<Empinfo> getUserByOracleProcedure(Page page) {
        return null;
    }
}
