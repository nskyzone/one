package com.lcw.one.workflow.service;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.workflow.dao.FlowTaskInfoEODao;
import com.lcw.one.workflow.entity.FlowTaskInfoEO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程节点信息县官服务
 */
@Service
public class FlowTaskInfoEOService extends CrudService<FlowTaskInfoEODao, FlowTaskInfoEO, String> {

    public FlowTaskInfoEO getFlowTaskInfoEOAndValid(String processKey, String taskKey) {
        FlowTaskInfoEO flowTaskInfoEO = dao.getFlowTaskInfoEO(processKey, taskKey);
        validProcessTaskInfo(flowTaskInfoEO);
        return flowTaskInfoEO;
    }

    public List<FlowTaskInfoEO> listFlowTaskInfoEOByProcessKey(String processKey) {
        return dao.listFlowTaskInfoEOByProcessKey(processKey);
    }

    public void deleteByProcessKey(String processKey) {
        dao.deleteByProcessKey(processKey);
    }

    public void deleteByTaskKeyNotExist(String processKey, List<String> taskKeyList) {
        dao.deleteByTaskKeyNotExist(processKey, taskKeyList);
    }

    public static void validProcessTaskInfo(FlowTaskInfoEO taskInfoEO) {
        if (taskInfoEO == null) {
            throw new OneBaseException("流程未配置角色和表单");
        }
        if (StringUtils.isEmpty(taskInfoEO.getBindForm()) || StringUtils.isEmpty(taskInfoEO.getBindRole())) {
            throw new OneBaseException("流程[" + taskInfoEO.getProcessKey() + "]的节点[" + taskInfoEO.getTaskKey() + "]绑定的角色或表单为空");
        }
    }

}
