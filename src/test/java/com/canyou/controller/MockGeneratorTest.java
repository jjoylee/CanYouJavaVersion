package com.canyou.controller;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.canyou.model.LectureCategory.LectureCategoryVO;

public class MockGeneratorTest {

	@Test
	public void setMockNonPrimitiveTest() throws IllegalArgumentException, IllegalAccessException {
		NonPrimitiveClass obj = new NonPrimitiveClass();
		MockGenerator.setMock(obj);
		assertNotNull(obj.theList);
		assertNotNull(obj.theMap);
	}
	
	class NonPrimitiveClass{
		public List<LectureCategoryVO> theList ;
		public Map<String,String> theMap;
		
	}
}
