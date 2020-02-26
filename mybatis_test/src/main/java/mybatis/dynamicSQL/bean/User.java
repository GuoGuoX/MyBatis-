package mybatis.dynamicSQL.bean;


import mybatis.bean.Dept;

public class User {
    private Integer id;
    private String userName;
    private Integer age;
    private Character gender;
    private String dId;
    private Dept dept;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", dId='" + dId + '\'' +
                '}';
    }

    public User(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public User(Integer id, String userName, Integer age, Character gender, String dId, Dept dept) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.dId = dId;
        this.dept = dept;
    }
}
