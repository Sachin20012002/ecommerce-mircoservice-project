package com.sachin.categorymicroservice.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditableAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Rithika");
    }
}
