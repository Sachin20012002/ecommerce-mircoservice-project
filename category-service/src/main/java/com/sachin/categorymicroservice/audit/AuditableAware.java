package com.sachin.categorymicroservice.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditableAware implements AuditorAware<String> {

    @SuppressWarnings("NullableProblems")
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Rithika");
    }
}
