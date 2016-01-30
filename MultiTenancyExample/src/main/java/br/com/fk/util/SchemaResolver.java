package br.com.fk.util;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class SchemaResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {

		return TenantUtil.getTenant();

	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}