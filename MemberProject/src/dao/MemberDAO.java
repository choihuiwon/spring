package dao;

import java.util.ArrayList;

import vo.MemberVO;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();

	private MemberDAO() {
	}
	public MemberDAO getInstance() {
		if(instance == null)
			instance = new MemberDAO();
		return instance;
	}
	public MemberVO selectMemberVO(String id) {return null;}
	public int insertMemberVO(MemberVO vo) {return 0;}
	public void updatePass(String id, String pass) {}
	public MemberVO login(String id, String pass) {return null;}
	public int updateMember(MemberVO vo) {return 1;}
	public ArrayList<MemberVO> selectAllMemberVO(){return null;}
	public ArrayList<MemberVO> searchMember(String id, String pass){return null;}
	public boolean updateManageMember(MemberVO vo) {return false;}
	public boolean deleteMember(String id) {return false;}
	
}
