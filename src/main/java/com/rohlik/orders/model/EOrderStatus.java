package com.rohlik.orders.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EOrderStatus
{
    CREATED,
    PAYED,
    INVALID,
    CANCELED;
}
