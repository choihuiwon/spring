select * from member;
select * from employee;
select * from POSITION_LIST;  
select e.eno, e.name, e.department, p.name from employee e, position_list p where e.position = p.pno;
select * from EMPLOYEE_SALARY;

select e.eno, e.name, e.department, e.position, s.salary from employee e, employee_salary s where e.eno = s.eno;