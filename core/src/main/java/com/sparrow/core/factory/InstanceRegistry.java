package com.sparrow.core.factory;

import cn.hutool.core.util.IdUtil;
import com.sparrow.common.entity.Instance;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实例
 *
 * @author 985492783@qq.com
 * @date 2023/10/12 23:08
 */
@Service
public class InstanceRegistry {
    
    private final Map<Instance, Instance> instanceMap = new ConcurrentHashMap<>();
    
    public String register(Instance instance) {
        Instance service = instanceMap.computeIfAbsent(instance, this::buildInstance);
        return service.getId();
    }
    
    public Instance buildInstance(Instance instance) {
        instance.setId(IdUtil.getSnowflakeNextIdStr());
        return instance;
    }
}
