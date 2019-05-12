package br.com.bv.juliocesar.spring;

import org.springframework.util.Assert;

public class TenantContextHolder {
	private static final ThreadLocal<TenantId> contextHolder = new ThreadLocal<TenantId>();

	public static void setTenantId(TenantId tenantId) {
		Assert.notNull(tenantId, "tenantId cannot be null");
		contextHolder.set(tenantId);
	}

	public static TenantId getTenantId() {
		return contextHolder.get();
	}

	public static void clearTenantId() {
		contextHolder.remove();
	}
}
