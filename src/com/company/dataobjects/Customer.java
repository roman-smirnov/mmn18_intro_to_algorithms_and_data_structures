package com.company.dataobjects;

/**
 * Created by roman on 2/24/17.
 * The customer class - an immutable instance of a customer
 */
public final class Customer {
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
    public String toString() {
        return "customer: " +
                "first_name='" + mFirstName + '\'' +
                ", last_name='" + mLastName + '\'' +
                ", id=" + mId +
                ", customer_id=" + mCustomerId +
                ", balance=" + mBalance;
    }
}
