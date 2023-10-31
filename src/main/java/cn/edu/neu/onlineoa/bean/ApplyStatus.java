package cn.edu.neu.onlineoa.bean;

public enum ApplyStatus {
    SUBMITTED("申请已提交", 0),                //0:申请已提交
    TEACHER_APPROVING("课程主讲教师审批中", 1),  //1:课程主讲教师审批中
    HEAD_APPROVING("主管教师审批中", 2),        //2:主管教师审批中
    SUCCESS("申请成功", 3),                    //3:申请成功
    REJECT("申请被驳回", 4);                   //4:申请被驳回

    private String name;
    private int id;

    ApplyStatus(String name, int id) {
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
        ApplyStatus[] statuses = ApplyStatus.values();
        for ( ApplyStatus s : statuses) {
            if( s.getId() == id )
                return s.name;
        }
        return null;
    }
}
