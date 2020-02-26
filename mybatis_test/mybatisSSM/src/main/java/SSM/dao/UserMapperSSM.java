package SSM.dao;

import SSM.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapperSSM {
    public List<User> selectUserAll();
    public void addUser(User user);


}
