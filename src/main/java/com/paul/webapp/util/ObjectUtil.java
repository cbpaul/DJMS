package com.paul.webapp.util;

public class ObjectUtil {

	public static boolean isPrimitive(Object obj) {
		if (obj instanceof Integer || obj instanceof String
				|| obj instanceof Long || obj instanceof Float
				|| obj instanceof Double || obj instanceof Character) {
			return true;
		}
		return false;
	}
}
