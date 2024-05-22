package su.member.service;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import su.login.dao.LoginDAO;
import su.member.dao.MemberDAO;
import su.member.dto.MemberDTO;

@Service
public class MemberServiceImp implements MemberService{
	
	@Autowired
	private final MemberDAO memberDAO;
	
	@Autowired
	private final LoginDAO loginDAO;
	
	public MemberServiceImp(@Qualifier("memberDAOImp")MemberDAO memberDAO, @Qualifier("loginDAOImp")LoginDAO loginDAO) {
		this.memberDAO = memberDAO;
		this.loginDAO = loginDAO;
	}

	@Override
	public List<MemberDTO> memberSelectAll() {
		// TODO Auto-generated method stub
		return memberDAO.selectAll();
	}

	@Override
	public MemberDTO memberSelect(int member_number) {
		// TODO Auto-generated method stub
		System.out.println("member select 실행!!");
		return memberDAO.select(member_number);
	}

	@Override
	public void memberInsert(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		memberDAO.insert(memberDTO);
	}

	@Override
	public void memberUpdate(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
		System.out.println("member update 실행!!");
		memberDAO.update(memberDTO);
	}

	@Override
	public void memberDelete(int member_number) {
		// TODO Auto-generated method stub
		
		memberDAO.delete(member_number);
	}

	
	
	// ==================== login ======================= //
	
	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
		MemberDTO result = new MemberDTO();
		
		result = loginDAO.memberLogin(memberDTO);
		
		if(result != null) {

			if(result.getMember_password().equals(memberDTO.getMember_password())) {
				memberDTO.setMember_password(result.getMember_password());
			} else {
				result.setMember_password("");
			}

		} else {
			memberDTO.setMember_id("");
		}
		
		
		return result;
	}

	@Override
	public MemberDTO memberSearchId(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
		//loginDAO.memberSearchId(memberDTO);
		
		
		return loginDAO.memberSearchId(memberDTO);
	}

	@Override
	public MemberDTO memberSearchPassword(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
		//loginDAO.memberSearchPassword(memberDTO);
		
		return loginDAO.memberSearchPassword(memberDTO);
	}

	@Override
	public int memberIdCheck(String member_id) {
		// TODO Auto-generated method stub
		
		int idCheck = 0;
		
		MemberDTO result = null;

		result = loginDAO.idCheck(member_id);
		
		if(result != null || member_id.equals("")) { // member_id 를 가지고 있는 MemberDTO 가 있거나 입력받은 member_id 자체가 빈 문자열이면 1을 반환한다
			idCheck = 1;
		} else {
			idCheck = 0;
		}
		
		
		return idCheck;
	}

}
