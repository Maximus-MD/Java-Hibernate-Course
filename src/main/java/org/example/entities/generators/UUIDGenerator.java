package org.example.entities.generators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.UUID;

public class UUIDGenerator implements IdentifierGenerator {
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object owner){
        return UUID.randomUUID().toString();
    }
}
