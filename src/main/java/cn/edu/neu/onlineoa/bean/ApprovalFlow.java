package cn.edu.neu.onlineoa.bean;

public class ApprovalFlow {
    private String cid;
    private String firstTeacherId;
    private String secondTeacherId;

    public ApprovalFlow() {}

    public ApprovalFlow(String cid, String firstTeacherId, String secondTeacherId) {
        this.cid = cid;
        this.firstTeacherId = firstTeacherId;
        this.secondTeacherId = secondTeacherId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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
}
