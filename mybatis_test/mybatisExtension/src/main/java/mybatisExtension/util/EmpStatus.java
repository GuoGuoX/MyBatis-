package mybatisExtension.util;


/**
 * 使用枚举类映射数据库
 */
public enum  EmpStatus {
    LOGIN(100,"用户登录"),LOGINOUT(200,"用户登出"),REMOVE(300,"用户不存在");

    private Integer code;
    private String status;

    EmpStatus(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static EmpStatus getEmpStatusByCode(Integer code){
        switch (code){
            case 100:
                return LOGIN;
            case 200:
                return LOGINOUT;
            case 300:
                return REMOVE;
            default:
                return LOGINOUT;
        }
    }
}
