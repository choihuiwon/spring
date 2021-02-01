select * from member;
select * from GRADE_LIST;
select id, passwd, name, age, grade_name from MEMBER, GRADE_LIST where grade = grade_no;
select id, name, age from member where id like #{id};
