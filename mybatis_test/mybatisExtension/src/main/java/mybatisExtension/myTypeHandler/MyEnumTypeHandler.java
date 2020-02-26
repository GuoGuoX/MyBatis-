package mybatisExtension.myTypeHandler;

import mybatisExtension.util.EmpStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyEnumTypeHandler implements TypeHandler<EmpStatus> {
    public void setParameter(PreparedStatement ps, int i, EmpStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getCode());
    }

    public EmpStatus getResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        //从数据库中获取的状态码
        EmpStatus empStatusByCode = EmpStatus.getEmpStatusByCode(code);
        return empStatusByCode;
    }

    public EmpStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        //从数据库中获取的状态码
        EmpStatus empStatusByCode = EmpStatus.getEmpStatusByCode(code);
        return empStatusByCode;
    }

    public EmpStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        //从数据库中获取的状态码
        EmpStatus empStatusByCode = EmpStatus.getEmpStatusByCode(code);
        return empStatusByCode;
    }
}
