package SSM.dao;

import SSM.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapperDynamicSQL {
    public User selectUserById(Integer id);

    public List<User> selectUserByUser(User user);
    public List<User> selectUserByUserByForeach(@Param("integerList") List<Integer> integerList);
    public Integer insertUser(User user);
    public Boolean insertListUser(@Param("users") List<User> users);
    public Boolean updateUserByUser(User user);
}
