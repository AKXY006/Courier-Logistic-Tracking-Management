package com.logistic.courier.entity;

public enum TrackingStatus {
       
    SHIPMENT_CREATED,
    PICKED_UP,
    IN_TRANSIT,
    ARRIVED_AT_WAREHOUSE,
    OUT_FOR_DELIVERY,
    DELIVERED,
    DELIVERY_FAILED,
    RETURNED,
    CANCELLED

}
