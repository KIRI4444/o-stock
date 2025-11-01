package com.optimagrowth.organization_service.service;

import com.optimagrowth.organization_service.events.source.SimpleSourceBean;
import com.optimagrowth.organization_service.model.Organization;
import com.optimagrowth.organization_service.repository.OrganizationRepository;
import com.optimagrowth.organization_service.util.ActionEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrganizationService {

    private OrganizationRepository repository;
    private SimpleSourceBean simpleSourceBean;

    public Organization findById(String organizationId) {
        Optional<Organization> opt = repository.findById(organizationId);
        simpleSourceBean.publishOrganizationChange(ActionEnum.GET, organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organization create(Organization organization){
        organization.setId( UUID.randomUUID().toString());
        organization = repository.save(organization);
        simpleSourceBean.publishOrganizationChange(ActionEnum.CREATED, organization.getId());
        return organization;

    }

    public void update(Organization organization){
        repository.save(organization);
        simpleSourceBean.publishOrganizationChange(ActionEnum.UPDATED, organization.getId());
    }

    public void delete(String organizationId){
        repository.deleteById(organizationId);
        simpleSourceBean.publishOrganizationChange(ActionEnum.DELETED, organizationId);
    }
}