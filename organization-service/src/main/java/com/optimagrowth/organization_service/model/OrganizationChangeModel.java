package com.optimagrowth.organization_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrganizationChangeModel {
    private String type;
    private String action;
    private String organizationId;
}