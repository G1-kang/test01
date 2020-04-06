package com.test.dashboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.dashboard.common.util.Util;
import com.test.dashboard.model.biz.MemberBiz;
import com.test.dashboard.model.dto.KakaoMemberDto;
import com.test.dashboard.model.dto.MemberDto;

@RestController
public class UtilController {
	
	@Autowired
	private MemberBiz memberBiz;
	private Logger logger = LoggerFactory.getLogger(UtilController.class);
	
	@GetMapping("smscheck")
	public String getSmsCheck(MemberDto memberDto) {
		
		logger.info("[ INFO ] : UtilController > getSmsCheck [path : /smscheck]");
		logger.info("[ INFO ] : UtilController > phone > " + memberDto.getMphone());
//		return "1";
		return new Util().smsCheckFun(memberDto.getMphone(), "웹에서 온 인증번호 입니다. 확인해주세요.");
	}
	
	@GetMapping("userload")
	public MemberDto getUserLoad(HttpSession session) {
		
		return (MemberDto)session.getAttribute("user");
	}

	@GetMapping("nickcheck")
	public MemberDto getNickSearch(MemberDto memberDto) {
		
		logger.info("[ INFO ] : UtilController > getNickSearch [path : /nickcheck]");
		logger.info("[ INFO ] : SearchNick > " + memberDto.getMnick());
		
		MemberDto user = memberBiz.selectByNick(memberDto.getMnick());
		
		logger.info("[ INFO ] : USER > " + user );
		
		if(user != null) {
			return user;
		} else {
			return null;
		}
	}
	
	@GetMapping("namesearch")
	public boolean getNameSearch(MemberDto memberDto) {
		logger.info("[ INFO ] : UtilController > postIdSearch [path : /namesearch]");
		logger.info("[ INFO ] : SearchName > " + memberDto.getMname());
		
		int res = memberBiz.selectByName(memberDto.getMname());
		
		if(res > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	@GetMapping("idcheck")
	public boolean getIdCheck(MemberDto memberDto) {
		
		logger.info("[ INFO ] : UtilController > getIdCheck [path : /idcheck]");
		logger.info("[ INFO ] : SearchID > " + memberDto.getMid());
		
		MemberDto user = memberBiz.selectById(memberDto.getMid());
		
		logger.info("[ INFO ] : USER > " + user );
		
		if(user != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@GetMapping("emailAuthCheck")
	public String getSignUpCheck(MemberDto memberDto) {
		logger.info("[ INFO ] : UtilController > getSignUpCheck [path : /signupcheck]");
		logger.info("[ INFO ] : UtilController > email > " + memberDto.getMemail());
		return new Util().signUpEmail(memberDto.getMemail());
	}
	
	
	@GetMapping("kakaologin")
	public String kakaologin(KakaoMemberDto kakaoMemberDto, HttpSession session) {
		
		logger.info("[ INFO ] : UtilController > kakaologin [path : /kakaologin]");
		logger.info("[ INFO ] : UtilController > kakaoMemberDto > " + kakaoMemberDto);
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("Authorization", "Bearer "+kakaoMemberDto.getAccess_token());
		params.put("Content-Length", "property_keys=[\"kakao_account.email\"]");
		
		String res = Util.restRequest("POST", "https://kapi.kakao.com/v2/user/me", "application/x-www-form-urlencoded;charset=utf-8", params);
		
		
		JSONParser json = new JSONParser();
		
		JSONObject jObj = null;
		
		MemberDto memberDto = null;
		
		session.setAttribute("platformInfo", kakaoMemberDto);
		
		try {
			
			
			jObj = (JSONObject) json.parse(res);
			
			String id = String.valueOf(jObj.get("id"));
			
			memberDto = memberBiz.selectById(id+"_kakao");
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(memberDto == null) {
			return "{\"signcheck\" : \"false\", "+ "\"info\":" + res+"}";
		} else {
			return "{\"signcheck\" : \"true\", "+ "\"info\":" + res+"}";
		}
		
	}
	
	@GetMapping("kakaologout")
	public String kakaologout(HttpSession session) {
		
		logger.info("[ INFO ] : UtilController > kakaologout [path : /kakaologout]");
		
		KakaoMemberDto kakaoMemberDto = (KakaoMemberDto) session.getAttribute("platformInfo");
		
		logger.info("[ INFO ] : UtilController > kakaoMemberDto > " + kakaoMemberDto);
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("Authorization", "Bearer "+ kakaoMemberDto.getAccess_token());
		
		session.invalidate();
		
		return Util.restRequest("POST", "https://kapi.kakao.com/v1/user/logout", null, params);
	}
	
	@GetMapping("kakaoout")
	public String kakaoout(HttpSession session) {
		
		logger.info("[ INFO ] : UtilController > kakaoout [path : /kakaoout]");
		
		KakaoMemberDto kakaoMemberDto = (KakaoMemberDto) session.getAttribute("platformInfo");
		
		logger.info("[ INFO ] : UtilController > kakaoMemberDto > " + kakaoMemberDto);
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("Authorization", "Bearer "+ kakaoMemberDto.getAccess_token());
		
		session.invalidate();
		
		return Util.restRequest("POST", "https://kapi.kakao.com/v1/user/unlink", null, params);
	}
	
	@GetMapping( value = "fileread" , produces = "application/text; charset=utf8")
	public String fileread(@RequestParam(name = "path") String path, HttpServletRequest req) {
		
		logger.info("[ INFO ] : UtilController > fileread [path :" + path + "]");
		
		return Util.fileRead(req.getServletContext().getRealPath("/")+path.split("dashboard")[1]);
		
	}
	
	@GetMapping("notpermission")
	public String notPermission() {
		return null;
	}
	
}
