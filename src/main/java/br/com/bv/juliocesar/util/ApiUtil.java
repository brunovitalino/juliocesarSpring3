package br.com.bv.juliocesar.util;

import java.util.EnumMap;
import java.util.Map;

import br.com.bv.juliocesar.spring.TenantId;

public class ApiUtil {

	public static Map<TenantId, String> getIdClientes() {
		Map<TenantId, String> configs = new EnumMap<TenantId, String>(TenantId.class);

		configs.put(TenantId.DEVELOPMENT, "12347654");

		configs.put(TenantId.COMERCIAL, "12347654");

		return configs;
	}

	public static String getIdCliente(TenantId tenant) {
		Map<TenantId, String> configs = getIdClientes();

		return configs.get(tenant);
	}

}
