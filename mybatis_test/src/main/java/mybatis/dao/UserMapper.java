package mybatis.dao;

import mybatis.bean.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public User selectUserById(Integer id);
    public List<User> selectUserListByLastName(String lastName);
    //告诉mybatis封装map的时候用哪个属性作为key
    @MapKey("id")
    public Map<Integer,User> selectUserMapLikeByLastName(String lastName);
    //返回一条map：key就是列明，value就是对应的值
    public Map<String,Object> selectUserMapByLastName(String lastName);

    public User selectUserByIdAndLastName(Integer id,String lastName);
    public Boolean deleteUserById(Integer id);
    public Boolean updateUserById(User user);
    public Integer insertUser(User user);
}
