package com.company.dataobjects;

/**
 * Created by roman on 2/24/17.
 */
public class Customer implements Comparable<Customer> {
    private final String mFirstName;
    private final String mLastName;
    private final int mId;
    private final int mCustomerId;
    private final int mBalance;

    public Customer(String firstName, String lastName, int id, int customerId, int balance) {
        mFirstName = firstName;
        mLastName = lastName;
        //the id is unique
        mId = id;
        //customer id is unique
        mCustomerId = customerId;
        mBalance = balance;
    }

    public int getBalance() {
        return mBalance;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public int getId() {
        return mId;
    }

    public int getCustomerId() {
        return mCustomerId;
    }

    public String getLastName() {
        return mLastName;
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

    @Override
    public String toString() {
        return "Customer: " +
                "FirstName'" + mFirstName + '\'' +
                ", LastName='" + mLastName + '\'' +
                ", Id=" + mId +
                ", CustomerId=" + mCustomerId +
                ", Balance=" + mBalance;
    }
}
