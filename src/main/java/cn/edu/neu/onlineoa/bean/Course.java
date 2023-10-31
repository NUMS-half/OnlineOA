package cn.edu.neu.onlineoa.bean;

public class Course {
    private String cid;          //课程编号
    private String cname;        //课程名称
    private float credit;        //学分
    private String teacherId;    //主讲老师编号
    private String teacherName;  //主讲老师姓名
    private String takeTime;     //上课时间
    private String note = "";    //备注

    public Course() {}

    public Course(String cid, String cname, float credit, String teacherId, String teacherName, String takeTime, String note) {
        this.cid = cid;
        this.cname = cname;
        this.credit = credit;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.takeTime = takeTime;
        this.note = note;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", credit=" + credit +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", takeTime='" + takeTime + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
