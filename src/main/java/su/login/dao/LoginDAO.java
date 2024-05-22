package su.login.dao;

import su.member.dto.MemberDTO;

public interface LoginDAO {

	public MemberDTO idCheck(String member_id);
	
	public MemberDTO memberLogin(MemberDTO memberDTO);
	
	public MemberDTO memberSearchId(MemberDTO memberDTO);
	
	public MemberDTO memberSearchPassword(MemberDTO memberDTO);
}
