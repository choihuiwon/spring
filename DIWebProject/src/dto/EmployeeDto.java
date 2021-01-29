package dto;

public class EmployeeDto {
	private String eno;
	private String name;
	private String department;
	private String position;
	private int salary;
	
	public EmployeeDto() {
	}
	
	public void init(String eno, String name, String department, String position, int salary) {
		this.eno = eno;
		this.name = name;
		this.department = department;
		this.position = position;
		this.salary = salary;
	}
	// 조회용
	public EmployeeDto(String eno, String name, String department, String position, int salary) {
		super();
		this.eno = eno;
		this.name = name;
		this.department = department;
		this.position = position;
		this.salary = salary;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	// 등록용
	public EmployeeDto(String eno, String name, String department, String position) {
		super();
		this.eno = eno;
		this.name = name;
		this.department = department;
		this.position = position;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "EmployeeDto [eno=" + eno + ", name=" + name + ", department=" + department + ", position=" + position
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((eno == null) ? 0 : eno.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + salary;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("EmployeeDto equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (eno == null) {
			if (other.eno != null)
				return false;
		} else if (!eno.equals(other.eno))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}
	
	
}
