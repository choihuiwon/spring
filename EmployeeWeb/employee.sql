select * from EMPLOYEE;

select * from POSITION_LIST;

select e.eno, e.name, e.department, p.name from employee e, position_list p where e.position like p.pno and e.position > 3

select e.eno, e.name, e.department, p.name, s.salary from EMPLOYEE e, position_list p, employee_salary s where e.eno like s.eno and e.position = p.pno and e.eno like 'TQ98';

delete from employee_salary;
delete from employee;

