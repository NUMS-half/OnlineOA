package cn.edu.neu.onlineoa.bean;

public class Apply {
    private int aid;
    private int status = -1;
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private String applyReason;
    private String reasonFilePath;
    private String applyTime;
    private String firstTeacherId;
    private String firstApproveTeaId;
    private String firstApproveTime;
    private String secondTeacherId;
    private String secondApproveTeaId;
    private String secondApproveTime;
    private String rejectReason;
    private boolean confirm;

    public Apply() {}

    public Apply(int aid, int status, String studentId, String studentName, String courseId, String courseName, String applyReason) {
        this.aid = aid;
        this.status = status;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.applyReason = applyReason;
    }

    public Apply(int aid, int status, String studentId, String studentName, String courseId, String courseName, String applyReason, String firstTeacherId, String secondTeacherId, String rejectReason, boolean confirm) {
        this.aid = aid;
        this.status = status;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.applyReason = applyReason;
        this.firstTeacherId = firstTeacherId;
        this.secondTeacherId = secondTeacherId;
        this.rejectReason = rejectReason;
        this.confirm = confirm;
    }

    public Apply(int aid, int status, String studentId, String studentName, String courseId, String courseName, String applyReason, String applyTime, String firstTeacherId, String firstApproveTime, String secondTeacherId, String secondApproveTime, String rejectReason, boolean confirm) {
        this.aid = aid;
        this.status = status;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.applyReason = applyReason;
        this.applyTime = applyTime;
        this.firstTeacherId = firstTeacherId;
        this.firstApproveTime = firstApproveTime;
        this.secondTeacherId = secondTeacherId;
        this.secondApproveTime = secondApproveTime;
        this.rejectReason = rejectReason;
        this.confirm = confirm;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getFirstTeacherId() {
        return firstTeacherId;
    }

    public void setFirstTeacherId(String firstTeacherId) {
        this.firstTeacherId = firstTeacherId;
    }

    public String getSecondTeacherId() {
        return secondTeacherId;
    }

    public void setSecondTeacherId(String secondTeacherId) {
        this.secondTeacherId = secondTeacherId;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getFirstApproveTeaId() {
        return firstApproveTeaId;
    }

    public void setFirstApproveTeaId(String firstApproveTeaId) {
        this.firstApproveTeaId = firstApproveTeaId;
    }

    public String getFirstApproveTime() {
        return firstApproveTime;
    }

    public void setFirstApproveTime(String firstApproveTime) {
        this.firstApproveTime = firstApproveTime;
    }

    public String getSecondApproveTeaId() {
        return secondApproveTeaId;
    }

    public void setSecondApproveTeaId(String secondApproveTeaId) {
        this.secondApproveTeaId = secondApproveTeaId;
    }

    public String getSecondApproveTime() {
        return secondApproveTime;
    }

    public void setSecondApproveTime(String secondApproveTime) {
        this.secondApproveTime = secondApproveTime;
    }

    public String getReasonFilePath() {
        return reasonFilePath;
    }

    public void setReasonFilePath(String reasonFilePath) {
        this.reasonFilePath = reasonFilePath;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "aid=" + aid +
                ", status=" + status +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", applyReason='" + applyReason + '\'' +
                ", reasonFilePath='" + reasonFilePath + '\'' +
                ", applyTime='" + applyTime + '\'' +
                ", firstTeacherId='" + firstTeacherId + '\'' +
                ", firstApproveTeaId='" + firstApproveTeaId + '\'' +
                ", firstApproveTime='" + firstApproveTime + '\'' +
                ", secondTeacherId='" + secondTeacherId + '\'' +
                ", secondApproveTeaId='" + secondApproveTeaId + '\'' +
                ", secondApproveTime='" + secondApproveTime + '\'' +
                ", rejectReason='" + rejectReason + '\'' +
                ", confirm=" + confirm +
                '}';
    }
}
