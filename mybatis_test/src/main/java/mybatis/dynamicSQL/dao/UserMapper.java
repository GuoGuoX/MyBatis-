package mybatis.dynamicSQL.dao;

import mybatis.dynamicSQL.bean.User;

import java.util.List;

public interface UserMapper {
    public User selectUserById(Integer id);

    public List<User> selectUserByUser1(User user);
    public Integer insertUser(User user);
    public Integer insertListUser(List<User> users);
}
