package su.member.service;

import java.util.List;

import su.member.dto.MemberDTO;

public interface MemberService {
	public List<MemberDTO> memberSelectAll();
	
	public MemberDTO memberSelect(int member_number);
	
	public void memberInsert(MemberDTO memberDTO);
	
	public void memberUpdate(MemberDTO memberDTO);
	
	public void memberDelete(int member_number);
	
	public MemberDTO memberLogin(MemberDTO memberDTO);
	
	public MemberDTO memberSearchId(MemberDTO memberDTO);
	
	public MemberDTO memberSearchPassword(MemberDTO memberDTO);
	
	public int memberIdCheck(String member_id);
}
