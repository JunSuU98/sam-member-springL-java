package su.login.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import su.member.dto.MemberDTO;

@Repository
@RequiredArgsConstructor
public class LoginDAOImp implements LoginDAO {
	
	@Autowired
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public MemberDTO idCheck(String member_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("su.spring.login.idCheck", member_id);
	}

	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("su.spring.login.login", memberDTO);
	}

	@Override
	public MemberDTO memberSearchId(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("su.spring.login.idSearch", memberDTO);
	}

	@Override
	public MemberDTO memberSearchPassword(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("su.spring.login.passwordSearch", memberDTO);
	}



}
