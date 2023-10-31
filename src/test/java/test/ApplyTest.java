package test;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.service.ApplyService;
import org.junit.Test;

import java.util.List;

public class ApplyTest {

    @Test
    public void testFindAll() {
        ApplyService applyService = new ApplyService();
        List<Apply> list = applyService.findAllApply();
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindAllConfirmed() {
        ApplyService applyService = new ApplyService();
        List<Apply> list = applyService.findAllConfirmedApply("20210001");
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindPassed() {
        ApplyService applyService = new ApplyService();
        List<Apply> list = applyService.findAllPassedApply();
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindByAid() {
        ApplyService applyService = new ApplyService();
        System.out.println(applyService.findApplyByAid(4));
    }

    @Test
    public void testFindByStu() {
        ApplyService applyService = new ApplyService();
        List<Apply> list = applyService.findApplyByStuId("20210001");
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindByTea1() {
        ApplyService applyService = new ApplyService();
        List<Apply> list = applyService.findApplyByTea1Id("00210001");
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindByTea2Id() {
        ApplyService applyService = new ApplyService();
        List<Apply> list = applyService.findApplyByTea2Id("00210003");
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindApplyToApproveByTea2Id() {
        ApplyService applyService = new ApplyService();
        List<Apply> list = applyService.findApplyToApproveByTea2Id("00210003");
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

    @Test
    public void testDelete() {
        ApplyService applyService = new ApplyService();
        System.out.println(applyService.deleteApplyByAid(18));
    }

    @Test
    public void testAdd() {
        ApplyService applyService = new ApplyService();
        Apply apply = new Apply(0, 1, "20210001", "zhangsan", "A00006", "计算机网络", "test-test");
        System.out.println(applyService.addApply(apply));
        System.out.println(apply.getAid());
    }

    @Test
    public void testUpdate() {
        ApplyService applyService = new ApplyService();
        Apply apply = new Apply(14, 1, "20210001", "zhangsan", "A00010", "test1", "需要补选");
        System.out.println(applyService.updateApply(apply));
    }

    @Test
    public void testFindApplyWithMultiCondition() {
        ApplyService applyService = new ApplyService();
        Apply apply = new Apply();
        apply.setStatus(1);
        List<Apply> list = applyService.findApplyWithMultiCondition(apply,"00210001", null);
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindHistory() {
        ApplyService applyService = new ApplyService();
        List<Apply> list = applyService.findApplyHistory("00210003", 3, null);
        for ( Apply a : list ) {
            System.out.println(a);
        }
    }

}
