package net.mikoto.roxy.core.model.assigner;

import net.mikoto.roxy.core.algorithm.Algorithm;
import net.mikoto.roxy.core.model.AbstractHasAHashMapClass;

import java.util.Map;

public class AlgorithmAssigner extends AbstractHasAHashMapClass<Algorithm<?>> implements Assigner {

    @Override
    public Map<Object, Object> assign(Map<Object, Object> inputValue) {
        for (Map.Entry<String, Algorithm<?>> entry :
                super.dataMap.entrySet()) {
            inputValue.put(entry.getKey(), entry.getValue().run());
        }
        return inputValue;
    }

    @Override
    public void put(Object k, Object v) {
        super.put((String) k, (Algorithm<?>) v);
    }
}
