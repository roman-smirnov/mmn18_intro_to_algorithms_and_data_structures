package com.company.dataobjects;

/**
 * Created by roman on 2/24/17.
 */
public class Customer implements Comparable<Customer> {
    private final String mName;
    private final int mId;
    private final int mCustomerId;
    private final int mBalance;

    public Customer(String name, int id, int customerId, int balance) {
        mName = name;
        //the id is unique
        mId = id;
        //customer id is unique
        mCustomerId = customerId;
        mBalance = balance;
    }

    public int getBalance() {
        return mBalance;
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }

    public int getCustomerId() {
        return mCustomerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer && getCustomerId() == ((Customer) obj).getCustomerId()) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return mCustomerId;
    }

    @Override
    public int compareTo(Customer customer) {
        return mCustomerId - customer.getCustomerId();
    }

}
