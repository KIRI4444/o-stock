package com.optimagrowth.license.service.client;

import com.optimagrowth.license.model.Organization;
import com.optimagrowth.license.repository.OrganizationRedisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class OrganizationCacheService {

    private final OrganizationRedisRepository organizationRedisRepository;

    public Optional<Organization> getOrganization(String organizationId) {
        Optional<Organization> organization = organizationRedisRepository.findById(organizationId);

        // Логирование для теста, Можно опустить
        if (organization.isPresent()) {
            log.debug("Found organization {} in cache", organizationId);
        } else {
            log.debug("Organization {} not found in cache", organizationId);
        }

        return organization;
    }

    public void cacheOrganization(Organization organization) {
       organizationRedisRepository.save(organization);

       // Логи
       log.debug("Cached organization {}", organization.getId());
    }
}
