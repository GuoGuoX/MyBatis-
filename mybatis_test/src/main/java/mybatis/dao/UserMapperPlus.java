package mybatis.dao;

import mybatis.bean.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserMapperPlus {


    public User selectUserById(Integer id);
    public User selectUserMap(Integer id);
    public User selectUserMapStep(Integer id);
    public User selectUserByDid(Integer did);
    public User selectUserByIdDis(Integer id);
}
