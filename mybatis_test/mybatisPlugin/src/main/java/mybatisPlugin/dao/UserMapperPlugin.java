package mybatisPlugin.dao;

import com.github.pagehelper.PageHelper;
import mybatisPlugin.bean.User;

import java.util.List;

public interface UserMapperPlugin {
    public User selectUserById(Integer id);
    public List<User> selectUsers();


}
