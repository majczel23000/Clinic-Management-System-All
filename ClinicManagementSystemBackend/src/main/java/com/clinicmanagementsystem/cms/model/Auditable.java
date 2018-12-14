package com.clinicmanagementsystem.cms.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class Auditable {

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

}
