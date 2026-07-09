package com.logistic.courier.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracking_history_id")
    private Integer trackingHistoryId;

    @Column(name = "location")
    @NotBlank(message = "Location is required")
    private String location;

    @Column(name = "remarks")
    @NotBlank(message = "Remarks are required")
    private String remarks;

    @Column(name = "tracking_status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tracking status is required")
    private TrackingStatus trackingStatus;

    @CreationTimestamp
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;
    
    
}