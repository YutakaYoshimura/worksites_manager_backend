package com.example.worksitesmanager.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorksitesRequest {
    private String name;

    private String sub_name;

    private String type;

    private String staff_name;

	@Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;
    
    private String address;

    private String status;

    private Date start_at;

    private Date end_at;
}
