package mybatisExtension.bean;

/**
 * ------ ------------ -------- ------- --------
 * EMPNO  NUMBER(4)    Y
 * ENAME  VARCHAR2(10) Y
 * SAL    NUMBER(7,2)  Y
 * ANNSAL NUMBER       Y
 * DNAME  VARCHAR2(14) Y
 */
public class Empinfo {
    private Integer empNo;
    private String eName;
    private Integer sal;
    private Integer annsal;
    private String dName;

    @Override
    public String toString() {
        return "Empinfo{" +
                "empNo=" + empNo +
                ", eName='" + eName + '\'' +
                ", sal=" + sal +
                ", annsal=" + annsal +
                ", dName='" + dName + '\'' +
                '}';
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getAnnsal() {
        return annsal;
    }

    public void setAnnsal(Integer annsal) {
        this.annsal = annsal;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
}
