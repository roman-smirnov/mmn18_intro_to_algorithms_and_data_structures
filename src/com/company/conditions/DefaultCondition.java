package com.company.conditions;

/**
 * a default condition that always returns true
 */
public class DefaultCondition<K> implements Condition<K>{
    @Override
    public boolean isConditionMet(K key1) {
        return true;
    }
}
