package br.com.fk.util;

public class TenantUtil {
	
	private static String tenant = "";
	
	private TenantUtil() {
		
	}
	
	public static String getTenant(){
		return TenantUtil.tenant;
	}
	
	public static void setTenant(String tenant){
		TenantUtil.tenant = tenant;
	}

}
