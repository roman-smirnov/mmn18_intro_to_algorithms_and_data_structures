package com.company.conditions;

/**
 * a condition to compare objects against
 * @param <K>
 */
public interface Condition<K> {
    boolean isConditionMet(K key1);
}

