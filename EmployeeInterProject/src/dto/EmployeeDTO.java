package dto;

public class EmployeeDTO {
	private String eno;
	private String name;
	private int department;
	private int position;
	public EmployeeDTO(String eno, String name, int department, int position) {
		super();
		this.eno = eno;
		this.name = name;
		this.department = department;
		this.position = position;
	}
	@Override
	public String toString() {
		return "EmployeeDTO [eno=" + eno + ", name=" + name + ", department=" + department + ", position=" + position
				+ "]";
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
