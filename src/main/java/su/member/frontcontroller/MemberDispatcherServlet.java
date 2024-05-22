package su.member.frontcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import su.member.dto.MemberDTO;
import su.member.service.MemberServiceImp;

@Controller
@RequiredArgsConstructor
public class MemberDispatcherServlet {
	private static Logger logger = LoggerFactory.getLogger(MemberDispatcherServlet.class);
	
	@Autowired
	private final MemberServiceImp memberServiceImp;
	
	
	@GetMapping("/MemberSelect")
	public String memberSelect(Model model) {
		

		model.addAttribute("arrayList",memberServiceImp.memberSelectAll());
		
		return "/member/member_select_view";
	}
	
	@GetMapping("/MemberSelectDetail")
	public String memberSelectDetail(@RequestParam("member_number")int member_number, Model model) {
		
		model.addAttribute("memberDTO", memberServiceImp.memberSelect(member_number));
		
		return "/member/member_select_detail_view";
	}
	
	@GetMapping("/MemberInsertView")
	public String memberInsertView() {

		return "/member/member_insert_view";
	}
	
	@PostMapping("/MemberInsert")
	public String memberInsert(MemberDTO memberDTO) {
		
		memberServiceImp.memberInsert(memberDTO);
		
		return "/member/member_insert";
	}
	
	@GetMapping("/MemberUpdateView")
	public String memberUpdateView(MemberDTO memberDTO, Model model) {
		
		model.addAttribute("memberDTO", memberServiceImp.memberSelect(memberDTO.getMember_number()));

		return "/member/member_update_view";
	}
	
	@PostMapping("/MemberUpdate")
	public String memberUpdate(MemberDTO memberDTO, Model model) {
		
		System.out.println("update member number = " + memberDTO.getMember_number());
		
		memberServiceImp.memberUpdate(memberDTO); // update
		
		MemberDTO updatedMemberDTO = memberServiceImp.memberSelect(memberDTO.getMember_number());
		
		model.addAttribute("memberDTO", updatedMemberDTO);
		
		return "/member/member_select_detail_view";
	}
	
	@GetMapping("/MemberDeleteView")
	public String memberDeleteView() {

		return "/member/member_delete_view";
	}
	
	@PostMapping("/MemberDelete")
	public String memberDelete(@RequestParam("member_number") int member_number, HttpServletRequest request) {

		memberServiceImp.memberDelete(member_number);
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "./login/logout";
	}
	
	// =========== login ==================// 
	
	@GetMapping("/IdCheck")
	@ResponseBody
	public String idCheck(@RequestParam("member_id") String member_id, Model model) {
		
		int result = memberServiceImp.memberIdCheck(member_id);
		
		if(result == 1) {
			logger.info("사용할 수 없는 아이디");
		} else {
			logger.info("사용할 수 있는 아이디");
		}
		
		return result+"";
	}
	
	@GetMapping("/LoginView")
	public String loginView() {

		return "/login/login_view";
	}
	
	@PostMapping("/Login")
	public String login(MemberDTO memberDTO, Model model, HttpServletRequest request) {

		MemberDTO result = memberServiceImp.memberLogin(memberDTO);

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("memberDTO", result);
		
		model.addAttribute("memberDTO", result);
		
		return "/login/login_check";
	}
	
	@GetMapping("/IdSearchView")
	public String idSearchView() {
		return "/login/id_search_view";
	}
	
	@PostMapping("/IdSearch")
	public String idSearch(MemberDTO memberDTO, Model model) {

		//memberServiceImp.memberSearchId(memberDTO);
		model.addAttribute("memberDTO", memberServiceImp.memberSearchId(memberDTO));
		
		return "/login/id_search";
	}
	
	@GetMapping("/PasswordSearchView")
	public String passwordSearchView() {
		return "/login/password_search_view";
	}
	
	@PostMapping("/PasswordSearch")
	public String passwordSearch(MemberDTO memberDTO, Model model) {

		//memberServiceImp.memberSearchPassword(memberDTO);
		
		model.addAttribute("memberDTO", memberServiceImp.memberSearchPassword(memberDTO));
		
		return "/login/password_search";
	}
	
	@GetMapping("/Logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		
		return "/login/logout";
	}

}



// ======================= Front-Controller 패턴 기존 코드 ======================== // 

//import java.io.IOException;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import su.login.controller.IdCheckController;
//import su.login.controller.IdSearchController;
//import su.login.controller.LoginController;
//import su.login.controller.LogoutController;
//import su.login.controller.PasswordSearchController;
//import su.member.control.Controller;
//import su.member.controller.MemberDeleteController;
//import su.member.controller.MemberInsertController;
//import su.member.controller.MemberSelectController;
//import su.member.controller.MemberSelectDetailController;
//import su.member.controller.MemberUpdateController;
//import su.member.controller.MemberUpdateViewController;
//import su.member.handler.MemberHandlerAdapter;
//
//public class MemberDispatcherServlet extends HttpServlet {
//	private static Log log = LogFactory.getLog(MemberDispatcherServlet.class);
//
//	private static final long serialVersionUID = 1L;
//       
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String requestURI = request.getRequestURI();
//		String contextPath = request.getContextPath();
//		String pathURL = requestURI.substring(contextPath.length());
//		
//		log.info("매핑명 조회 - " + pathURL);
//		
//		MemberHandlerAdapter memberHandlerAdapter = null;
//		
//		Controller controller = null;
//		
//		if(pathURL.equals("/MemberSelect.me")){
//			controller = new MemberSelectController();
//			memberHandlerAdapter = controller.execute(request, response);			
//			
//			log.info("회원 전체 조회 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/MemberSelectDetail.me")) {
//			controller = new MemberSelectDetailController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("회원 상세 조회 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/MemberInsertView.me")) {
//			memberHandlerAdapter = new MemberHandlerAdapter();
//			memberHandlerAdapter.setPath("/WEB-INF/view/member/member_insert_view.jsp");
//
//			log.info("회원가입 화면 뷰 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/MemberInsert.me")) {
//			controller = new MemberInsertController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("회원 가입 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/MemberUpdateView.me")) {
//			controller = new MemberUpdateViewController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("정보 수정 뷰 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/MemberUpdate.me")) {
//			controller = new MemberUpdateController();
//			memberHandlerAdapter = controller.execute(request, response);
//					
//			log.info("정보 수정 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/MemberDeleteView.me")) {
//			memberHandlerAdapter = new MemberHandlerAdapter();
//			memberHandlerAdapter.setPath("/WEB-INF/view/member/member_delete_view.jsp");
//			
//			log.info("회원 삭제 화면 뷰 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/MemberDelete.me")) {
//			controller = new MemberDeleteController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("회원 삭제 확인 - " + memberHandlerAdapter);
//			
//		}
//		
//		else if(pathURL.equals("/LoginView.me")) {
//			memberHandlerAdapter = new MemberHandlerAdapter();
//			memberHandlerAdapter.setPath("/WEB-INF/view/login/login_view.jsp");
//			
//			log.info("로그인 화면 뷰 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/Login.me")) {
//			controller = new LoginController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("로그인 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/Logout.me")) {
//			controller = new LogoutController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("로그아웃 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/IdSearchView.me")) {
//			memberHandlerAdapter = new MemberHandlerAdapter();
//			memberHandlerAdapter.setPath("/WEB-INF/view/login/id_search_view.jsp");
//			
//			log.info("아이디 찾기 뷰 확인 - " + memberHandlerAdapter);
//		}
//		
//		else if(pathURL.equals("/IdSearch.me")) {
//			controller = new IdSearchController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("아이디 찾기 확인 - " + memberHandlerAdapter);
//			
//		}
//		
//		else if(pathURL.equals("/PasswordSearchView.me")) {
//			memberHandlerAdapter = new MemberHandlerAdapter();
//			memberHandlerAdapter.setPath("/WEB-INF/view/login/password_search_view.jsp");
//			
//			log.info("비밀번호 찾기 뷰 확인 - " + memberHandlerAdapter);
//			
//		}
//		
//		else if(pathURL.equals("/PasswordSearch.me")) {
//			controller = new PasswordSearchController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("비밀번호 찾기 - " + memberHandlerAdapter);
//			
//		}
//		
//		else if(pathURL.equals("/IdCheck.me")){
//			controller = new IdCheckController();
//			memberHandlerAdapter = controller.execute(request, response);
//			
//			log.info("아이디 중복 확인 - " + memberHandlerAdapter);
//		}
//		
//		
//		if(memberHandlerAdapter != null) {
//			if(memberHandlerAdapter.isRedirect()) {
//				response.sendRedirect(memberHandlerAdapter.getPath());
//			} else {
//				RequestDispatcher dispatcher = request.getRequestDispatcher(memberHandlerAdapter.getPath());
//				dispatcher.forward(request, response);
//			}
//		}
//
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		service(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		service(request, response);
//	}
//
//}
