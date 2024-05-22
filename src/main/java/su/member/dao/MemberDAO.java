package su.member.dao;

import java.util.List;

import su.member.dto.MemberDTO;

public interface MemberDAO {
	
	public List<MemberDTO> selectAll();
	public MemberDTO select(int member_number);
	public void insert(MemberDTO memberDTO);
	public void update(MemberDTO memberDTO);
	public void delete(int Member_number);

	public MemberDTO idCheck(String member_id);
}
