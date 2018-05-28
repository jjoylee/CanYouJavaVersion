package com.canyou.service.Section;

import java.util.List;

import com.canyou.model.Section.SectionVO;

public interface SectionService {
	List<SectionVO> findAll();
    List<SectionVO> findByTypeId(int typeId);
}
