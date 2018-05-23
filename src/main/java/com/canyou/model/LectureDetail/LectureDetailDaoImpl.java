package com.canyou.model.LectureDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
	
public class LectureDetailDaoImpl implements LectureDetailDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(LectureDetailVO vo) {
		StringBuilder query = new StringBuilder();
		query.append("Insert into LectureDetail(accountId,lectureCategoryId,lectureTypeId,sectionId,");
		query.append("name,credit,score) VALUES(?,?,?,?,?,?,?)");
		Object[] obj = {vo.getAccountId(),vo.getLectureCategoryId(),vo.getLectureTypeId(),vo.getSectionId(),vo.getName(),vo.getCredit(),vo.getScore()};
        return jdbcTemplate.update(query.toString(), obj);
	}

	@Override
	public int update(LectureDetailVO vo) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE LectureDetail SET accountId = ?, lectureCategoryId = ?, ");
		query.append("lectureTypeId = ?, name = ?, score = ?, sectionId=?, credit = ? where id = ?");
        Object[] obj = {vo.getAccountId(),vo.getLectureCategoryId(),vo.getLectureTypeId(),vo.getName(),vo.getScore(),vo.getSectionId(),vo.getCredit(),vo.getId()};
        return jdbcTemplate.update(query.toString(), obj);
	}

	@Override
	public int delete(int id) {
		String query = "delete from LectureDetail where id= ?";
        return jdbcTemplate.update(query, id);
	}

	@Override
	public List<LectureDetailVO> findByAccountId(int accountId) {
		StringBuilder query = new StringBuilder();
		query.append("select a.*, ifnull(b.name ,'구분없음') lectureTypeName ,");
		query.append("c.name lectureCategoryName, ifnull(d.name ,'구분없음') sectionName ");
		query.append("from LectureDetail a left outer join LectureType b on a.lectureTypeId=b.id ");
		query.append("inner join LectureCategory c on a.lectureCategoryId = c.id ");
		query.append("left outer join Section d on a.sectionId = d.id where accountId=?");
		query.append("order by lectureCategoryId, lectureTypeId, sectionId");
        return jdbcTemplate.query(query.toString(), new Object[]{accountId}, new LectureDetailMapper());
	}

	@Override
	public List<LectureDetailVO> findAll() {
		StringBuilder query = new StringBuilder();
        query.append("select a.*, ifnull(b.name ,'구분없음') lectureTypeName ,");
        query.append("c.name lectureCategoryName, ifnull(d.name,'구분없음') sectionName ");
        query.append("from LectureDetail a left outer join LectureType b on a.lectureTypeId=b.id ");
        query.append("inner join LectureCategory c on a.lectureCategoryId = c.id ");
        query.append("left outer join Section d on a.sectionId = d.id order by lectureCategoryId, ");
        query.append("lectureTypeId, sectionId");
        return jdbcTemplate.query(query.toString(), new LectureDetailMapper());
	}

	@Override
	public LectureDetailVO findById(int id) {
		StringBuilder query = new StringBuilder();
        query.append("select a.*, ifnull(b.name ,'구분없음') lectureTypeName ,c.name lectureCategoryName,");
        query.append("ifnull(d.name,'구분없음') sectionName from LectureDetail a left outer join LectureType b on a.lectureTypeId=b.id ");
        query.append("inner join LectureCategory c on a.lectureCategoryId = c.id ");
        query.append("left outer join Section d on a.sectionId = d.id where ");
        query.append("a.id = ? order by lectureCategoryId, lectureTypeId, sectionId");
        List<LectureDetailVO> list = jdbcTemplate.query(query.toString(), new Object[]{id}, new LectureDetailMapper());
        return (list.size()==0) ? null : list.get(0);
	}

	@Override
	public LectureDetailVO findByAccountAndTitle(int accountId, String title) {
		StringBuilder query = new StringBuilder();
		query.append("select a.*, ifnull(b.name ,N'구분없음') lectureTypeName ,c.name lectureCategoryName,");
		query.append("ifnull(d.name,N'구분없음') sectionName from LectureDetail a left outer join ");
		query.append("LectureType b on a.lectureTypeId=b.id inner join LectureCategory c on ");
		query.append("a.lectureCategoryId = c.id left outer join Section d on a.sectionId = d.id ");
		query.append("where a.accountId = ? and a.Name = ? order by lectureCategoryId, lectureTypeId, sectionId");
		List<LectureDetailVO> list = jdbcTemplate.query(query.toString(), new Object[]{accountId, title}, new LectureDetailMapper());
        return (list.size()==0) ? null : list.get(0);
	}

}
