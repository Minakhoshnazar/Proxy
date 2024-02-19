package Security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AccessControl {
	
	private Map<String, List<AccessInfo>> accessMap = new HashMap<>();
	private static AccessControl instance;
	
	private AccessControl() {
		
	}
	
	public synchronized static AccessControl getInstance() {
		if (instance == null) {
			instance = new AccessControl();
		}
		return instance;
	}
	
	public synchronized static void reset() {
		instance = null;
	}
	
	
	public void addUser(String username) {
		  if (accessMap.containsKey(username)) {
		        throw new RuntimeException("User already exists");
		    }
		    accessMap.put(username, new ArrayList<>());
		}
		
	
	public void addAccess(String username, Class clazz, String methodName) {
		List<AccessInfo> access = accessMap.get(username);
		if (access != null)
			access.add(new AccessInfo(clazz, methodName));
		else
			throw new RuntimeException("User does not exist");
	}
	
	public boolean verifyAccess(String username, Class clazz, String methodName) {
		List<AccessInfo> access = accessMap.get(username);
		if (access != null) {
			return access.contains(new AccessInfo(clazz, methodName));
		}
		else
		   return false;
		
	}
    
	class AccessInfo {
		private Class clazz;
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Objects.hash(clazz, methodName);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AccessInfo other = (AccessInfo) obj;
			return Objects.equals(clazz, other.clazz) && Objects.equals(methodName, other.methodName);
		}

		private String methodName;
		
		public AccessInfo(Class clazz, String methodName) {
			super();
			this.clazz = clazz;
			this.methodName = methodName;
		}

		
		
		
		
	}



}
