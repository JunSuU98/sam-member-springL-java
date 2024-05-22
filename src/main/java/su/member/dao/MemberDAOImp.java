package su.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import su.member.dto.MemberDTO;

@Repository
@RequiredArgsConstructor
public class MemberDAOImp implements MemberDAO{
	
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<MemberDTO> selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("su.spring.member.selectAll");
	}

	@Override
	public MemberDTO select(int member_number) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("su.spring.member.select", member_number);
	}

	@Override
	public void insert(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
		sqlSessionTemplate.insert("su.spring.member.insert", memberDTO);
		
	}

	@Override
	public void update(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
		sqlSessionTemplate.update("su.spring.member.update", memberDTO);
		
	}

	@Override
	public void delete(int member_number) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete("su.spring.member.delete", member_number);
	}
	
	
	@Override
	public MemberDTO idCheck(String member_id) {
		
		return sqlSessionTemplate.selectOne("su.spring.member.idCheck", member_id);
	}

}
