package board.dto;

public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private int age;
	private String grade;
	
	//조회용
	public MemberDTO(String id, String passwd, String name, int age, String grade) {
		super();
		this.id = id;
		this.pass = passwd;
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return pass;
	}

	public void setPasswd(String passwd) {
		this.pass = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
