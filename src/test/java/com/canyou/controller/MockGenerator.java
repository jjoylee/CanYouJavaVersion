package com.canyou.controller;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;

public class MockGenerator {
	public static void setMock(Object object) throws IllegalArgumentException, IllegalAccessException{
		Class cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		
		for(Field field : fields){
			if(field.getType().isInterface()){
				Class fieldClass= field.getType();
				Object mockObj = mock(field.getType());
				field.set(object, mockObj);
			}
//			if(field.isAnnotationPresent(Autowired.class)){
//				Class fieldClass= field.getType();
//				Object mockObj = mock(field.getType());
//				field.set(object, mockObj);
//				System.out.println(mockObj.toString());
//			}
		}
	}
}
