package cn.edu.neu.onlineoa.bean;

public enum UserIdentity {
    ADMIN("系统管理员", 0),       //0:系统管理员
    STUDENT("学生", 1),          //1:学生
    TEACHER("课程主讲教师", 2),    //2:课程主讲教师
    HEAD_TEACHER("主管教师", 3);  //3:主管教师

    private String name;
    private int id;

    private UserIdentity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getNameByIndex(int id){
        UserIdentity[] identities = UserIdentity.values();
        for ( UserIdentity i : identities) {
            if( i.getId() == id )
                return i.name;
        }
        return null;
    }
}
