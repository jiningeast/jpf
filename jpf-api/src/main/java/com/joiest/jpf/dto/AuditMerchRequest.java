package com.joiest.jpf.dto;

public class AuditMerchRequest {

    private Long id;

    /**
     * 企业认证：0:未认证；1:已认证
     */
    private Boolean attestation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAttestation() {
        return attestation;
    }

    public void setAttestation(Boolean attestation) {
        this.attestation = attestation;
    }
}
