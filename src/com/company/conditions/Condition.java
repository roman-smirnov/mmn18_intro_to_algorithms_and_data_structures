package com.company.conditions;

/**
 * Created by bioel on 13-Mar-17.
 */
public interface Condition<K> {
    boolean isConditionMet(K key1);
}

