package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/5/23 20:27
 * Created by: wei.chen
 */
public class WeightedRoundRobin {

    private final List<Node> nodes;

    public WeightedRoundRobin(Map<Invoker, Integer> invokersWeight) {
        if (invokersWeight == null || invokersWeight.isEmpty()) {
            this.nodes = null;
        } else {
            nodes = new ArrayList<>(invokersWeight.size());
            invokersWeight.forEach(((invoker, weight) -> {
                nodes.add(new Node(invoker, weight));
            }));
        }
    }

    public Invoker select() {
        if (!checkNodes()) {
            return null;
        } else if (nodes.size() == 1) {
            Node node = nodes.get(0);
            return node.invoker.isAvailable()
                    ? node.invoker : null;
        }
        int totalWeight = 0;
        for (Node node : nodes) {
            totalWeight += node.effectiveWeight;
            node.currentWeight += node.effectiveWeight;
            
        }
    }

    private boolean checkNodes() {
        return (nodes != null && nodes.size() > 0);
    }

    public interface Invoker {
        boolean isAvailable();

        String getId();
    }

    static class Node implements Comparable<Node> {
        final Invoker invoker;
        final int weight;
        int effectiveWeight;
        int currentWeight;

        public Node(Invoker invoker, int weight) {
            this.invoker = invoker;
            this.weight = weight;
            this.effectiveWeight = weight;
            this.currentWeight = 0;
        }

        @Override
        public int compareTo(Node o) {
            return currentWeight >= o.currentWeight ? 1 : 0;
        }

        public Invoker getInvoker() {
            return invoker;
        }

        public int getWeight() {
            return weight;
        }

        public int getEffectiveWeight() {
            return effectiveWeight;
        }

        public void setEffectiveWeight(int effectiveWeight) {
            this.effectiveWeight = effectiveWeight;
        }

        public int getCurrentWeight() {
            return currentWeight;
        }

        public void setCurrentWeight(int currentWeight) {
            this.currentWeight = currentWeight;
        }
    }
}
