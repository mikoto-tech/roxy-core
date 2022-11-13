package net.mikoto.roxy.core.algorithm.impl;

import net.mikoto.roxy.core.algorithm.ContainerType;
import net.mikoto.roxy.core.algorithm.HttpTargetAlgorithm;
import net.mikoto.roxy.core.annotation.AlgorithmImpl;
import net.mikoto.roxy.core.model.network.resource.HttpTarget;
import net.mikoto.roxy.core.model.network.weight.CurrentWeighted;

import java.util.Iterator;
import java.util.List;

/**
 * @author mikoto
 * @date 2022/10/22
 * Create for core
 */
@AlgorithmImpl(
        value = "RoxySmoothWeightedServerAlgorithm",
        constructorParamsContainer = ContainerType.LIST
)
public class SmoothWeightedHttpTargetAlgorithm implements HttpTargetAlgorithm {
    private final List<? extends CurrentWeighted> SERVER_LIST;

    public SmoothWeightedHttpTargetAlgorithm(List<? extends CurrentWeighted> list) {
        if (list != null && !list.isEmpty() && list.get(0) instanceof HttpTarget) {
            this.SERVER_LIST = list;
        } else {
            throw new RuntimeException("Unknown type");
        }
    }

    @Override
    public HttpTarget run(Object... objects) {
        if (!SERVER_LIST.isEmpty()) {
            Iterator<? extends CurrentWeighted> sourceSetIterator = SERVER_LIST.iterator();

            CurrentWeighted resultServer = sourceSetIterator.next();
            resultServer.setCurrentWeight(resultServer.getWeight() + resultServer.getCurrentWeight());
            int currentWeightSum = resultServer.getCurrentWeight();

            while (sourceSetIterator.hasNext()) {
                CurrentWeighted server = sourceSetIterator.next();
                server.setCurrentWeight(server.getWeight() + server.getCurrentWeight());
                if (server.getCurrentWeight() > resultServer.getCurrentWeight()) {
                    resultServer = server;
                }
                currentWeightSum += server.getCurrentWeight();
            }

            resultServer.setCurrentWeight(resultServer.getCurrentWeight() - currentWeightSum);

            return (HttpTarget) resultServer;
        } else {
            return null;
        }
    }
}