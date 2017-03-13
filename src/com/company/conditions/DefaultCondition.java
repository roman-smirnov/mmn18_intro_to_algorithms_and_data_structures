package com.company.conditions;

/**
 * Created by bioel on 13-Mar-17.
 */
public class DefaultCondition<K> implements Condition<K>{
    @Override
    public boolean isConditionMet(K key1) {
        return true;
    }
}
