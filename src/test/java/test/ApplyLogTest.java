package test;

import cn.edu.neu.onlineoa.service.ApplyLogService;
import org.junit.Test;

public class ApplyLogTest {

    @Test
    public void testFindLogByAid() {
        ApplyLogService applyLogService = new ApplyLogService();
        System.out.println(applyLogService.findApplyLogByAid(1));
    }
}
