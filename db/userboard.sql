-- 강사님과 컬럼명 같고 ,추가한것 없음.


-- USER 

-- user
desc user;

-- insert
insert into user values(null, '관리자', 'admin@mysite.com', '1234', 'male', now());

-- select1(login)
select no, name from user where email='admin@mysite.com' and password='1234'; 

-- select2(update profile)

select * from user;

-- update
update user set name='...', gender='....' where no=10;
update user set name='...', gender='....', password='....' where no=10;





-- BOARD

-- board
-- colums -> no , title , contents ,hit , g_no , o_no, depth , reg_date, user_no ( 강사님과 동일 )

-- select all
select a.*,b.name from board as a join user as b on a.user_no = b.no order by g_no desc, o_no asc;
-- 검색은 where like 'keyword' 포함

-- insert
Insert into board values ( null , '정말' , '졸려요' , 0, (select ifnull(max(g_no)+1,1) from board b) , 1 , 1 ,now(), 3);

Insert into board values ( null , ? , ? , 0,(select ifnull(max(g_no)+1,1) from board b) , 1 , 1 ,now(), ?);

-- 답글 insert
Insert into board values ( null , '정말' , '졸려요'  , 0,(select ifnull(max(g_no)+1,1) from board b) , 2 , 2 ,now(), 4);

Insert into board values(null ,?,?,0, ? , ? , ?, now() , ?);

-- 삭제
delete from board where no = 22;

delete from board where no = ?;

-- findbyNo
 select title, contents,  hit, g_no, o_no, depth, a.reg_date ,user_no, b.name from board as a join user as 
 b on a.user_no = b.no where a.no = 14 order by g_no desc, o_no asc ;
 select title, contents,  hit, g_no, o_no, depth, a.reg_date ,user_no, b.name from board as a join user as 
 b on a.user_no = b.no where a.no = ? order by g_no desc, o_no asc ;
 
 -- hit ( 조회수 )
 update board set hit = 3 where no = 14;
 update board set hit = ? where no = ?;
 
 -- update(답글 번호 수정)
 -- update board set o_no = (o_no + 1) where o_no > (order.no) and g_no = (group.no)
 update board set o_no = (o_no + 1) where o_no > ? and g_no = ?;
 
 -- update ( 글 수정 )
update board set title = '하하' , contents = '하하하' where no = 20;
update board set title = ? , contents = ? where no = ?;
