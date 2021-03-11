select * from member;
select * from GRADE_LIST;
select id, passwd, name, age, grade_name from MEMBER, GRADE_LIST where grade = grade_no;
select id, name, age from member where id like #{id};


create table memberlog(
	log_date date not null,
	code_number number not null,
	message varchar2(300 byte) not null
);

insert into memberlog values(to_date('2021/02/01 14:44:15','YYYY/MM/DD HH24:MI:SS'), 1000, 'TEST ERROR 메세지');

select * from memberlog;

select count(*) from board;

		select cno, content, cdate, writer, blike, bhate from board_comment where bno = 1 order by cdate desc;
		
select * from board_comment;
select * from board_comment where bno = 94 order by blike desc;

select * from qna;


select qno, title, content, wdate, writer, status, response from (select ceil(rownum / 5) as page, qno, title, content, wdate, writer, status, response from (select * from qna where writer =  order by qno desc)) where page = 1;

update qna set response = null where qno = 13;
update qna set status = 1 where qno = 13;