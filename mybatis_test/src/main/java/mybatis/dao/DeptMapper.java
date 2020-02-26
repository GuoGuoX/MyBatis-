package mybatis.dao;

import mybatis.bean.Dept;

public interface DeptMapper {
    public Dept selectDeptById(Integer id);
    public Dept selectDeptAndUsers(Integer id);
    public Dept selectDeptAndUsersStep(Integer id);
}
