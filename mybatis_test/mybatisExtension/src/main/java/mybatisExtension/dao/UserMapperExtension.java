package mybatisExtension.dao;

import mybatisExtension.bean.Empinfo;
import mybatisExtension.bean.User;

import java.util.List;

public interface UserMapperExtension {
    public User selectUserById(Integer id);
    public List<User> selectUsers();
    public boolean addUser(User user);


    List<Empinfo> getUserByOracleProcedure(Page page);
}
