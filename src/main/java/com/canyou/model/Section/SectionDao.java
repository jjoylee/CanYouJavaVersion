package com.canyou.model.Section;

import java.util.List;

public interface SectionDao {
    List<SectionVO> findAll();
    List<SectionVO> findByTypeId(int typeId);
}
