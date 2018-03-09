package com.joiest.jpf.common.util;



import java.lang.reflect.Method;


@SuppressWarnings("unused")
public class GenerateCls {

	public static void main(String[] args) throws Exception {
		//generateByCls(PayTradeOrder.class,"orderRecord");
	}

	/**
	 * @param cls
	 */
	public static void generateByCls(Class<?> cls,String value) {
		/*
		 * 得到类中的方法
		 */
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith("set")) {
				System.out.print(value + "." + method.getName() + "("+value+".get"
						+ firstToUpper(method.getName().substring(3)) + "());\n");
			}
		}
	}

	/**
	 * @param val
	 * @return
	 */
	public static String firstToUpper(String val) {
		return val.substring(0, 1).toUpperCase() + val.substring(1);
	}

}
