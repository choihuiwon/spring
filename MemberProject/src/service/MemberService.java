package service;

import java.util.ArrayList;

import vo.MemberVO;

public class MemberService {
	private MemberService instance = new MemberService();

	private MemberService() {
	}
	public MemberService getInstance() {
		if(instance == null)
			instance = new MemberService();
		return instance;
	}
	public void insertMemberVO(MemberVO vo) {}
	public void findMemberVO() {}
	public void updatePass(String id, String pass) {}
	public MemberVO login(String id, String pass) {return null;}
	public MemberVO selectMemberVO(String id) {return null;}
	public void updateMemberVO(MemberVO vo) {}
	public boolean updateManageMemberVO(MemberVO vo) {return false;}
	public ArrayList<MemberVO> selectAllMember(){return null;}
	public ArrayList<MemberVO> searchMember(String id, String pass){return null;}
}
