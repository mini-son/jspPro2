-- jsp교재 p365참고
-- 데이터베이스 생성
-- 문법> create database  데이터베이스명  default character set utf8;

-- p581
-- board라는 이름의 데이터베이스 생성
create database  board  default character set utf8;
 
-- 데이터베이스 조회
show databases;

-- 유저생성 및 권한부여
/* create user '계정명'@'호스트'  identified by '비번';
   grant 권한1,권한n  on 데이터베이스명.대상 to '계정명'@'호스트';  	
*/

-- 호스트가 localhost인 경우, localhost에서 jspexam으로 접근
create user 'jspexam'@'localhost'  identified by 'jsppw';
grant all privileges  on board.* to 'jspexam'@'localhost';

-- 호스트가 %인 경우, 모든 호스트에서 jspexam으로 접근
create user 'jspexam'@'%'  identified by 'jsppw';
grant all privileges  on board.* to 'jspexam'@'%';


-- 사용할 데이터베이스 지정
use board;

-- 테이블생성
/* 문법&예문>
create table [IF NOT EXISTS] 테이블명(
 컬럼명 데이터타입(크기) 제약조건
 컬럼명 데이터타입(크기) NOT NULL AUTO_INCREMENT,
 컬럼명 데이터타입(크기) AUTO_INCREMENT NOT NULL PRIMARY KEY,
 컬럼명 데이터타입(크기) NOT NULL DEFAULT '',
 컬럼명 데이터타입(크기) DEFAULT 0,
 PRIMARY KEY (컬럼명),
 CONSTRAINT 제약조건명 FOREIGN KEY(자식테이블의 컬럼명) 
			REFERENCES 부모테이블명(컬럼명)
            
 -- 참조되는 부모레코드가 삭제되면  참조하는 자식record 삭제
 CONSTRAINT 제약조건명 FOREIGN KEY(자식테이블의 컬럼명) 
			REFERENCES 부모테이블명(컬럼명)
            ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/

-- 테이블변경
-- fk제약조건추가:참조되는 부모레코드가 삭제되면  참조하는 자식record 삭제
/*alter table 테이블명
ADD CONSTRAINT 제약조건명 FOREIGN KEY(테이블의 컬럼명) 
			REFERENCES 부모테이블명(컬럼명)
            [ON DELETE CASCADE];*/



-- p591
-- 회원테이블삭제
drop  table member;

-- 회원테이블생성
create table IF NOT EXISTS member(
 no	int   AUTO_INCREMENT NOT NULL PRIMARY KEY,
 memberid varchar(50) not null unique,
 password varchar(20) not null,
 name	  varchar(50) not null,
 regdate  datetime	  not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 기존테이블에 새 컬럼을 추가
/*ALTER TABLE 테이블명
  ADD 추가할컬럼명  타입  제약조건;
*/

-- member테이블에  isshow  'Y'(노출.기본상태) 'N'(노출X.삭제회원)
ALTER TABLE member
ADD isshow  varchar(1) check(isshow in('Y','N'));

select * from member;
/*입력
insert into 테이블명[(컬럼명,..컬럼명)]
values(값,..,값);*/

-- 기존 회원들의 isshow컬럼의 값을 'Y'로 변경
update member
set isshow='Y';

-- 회원삭제(UPDATE용) 
update member
set isshow='N'
where memberid=?;  또는 where no=?;

/*-- 다중입력
insert into 테이블명[(컬럼명,..컬럼명)]
values(값,..,값),(값,..,값),(값,..,값);
*/


select now(); -- 현재 날짜와시간  --출력결과 2023-07-05 10:43:10


-- dummy data입력
-- 관리자
insert into member(memberid,password,name,regdate)
values('adminid','1234','관리자',now());

-- 일반 회원
insert into member(memberid,password,name,regdate)
values('hongid','1234','홍GD','2015-07-01 10:43:10'),
('kimid','1234','김9','2016-06-06 06:10:15'),
('leeid','1234','이순신','2018-08-08 12:21:30'),
('uid','1234','유관순','2020-10-25 23:49:59');


-- 모든 회원목록 조회
select no,memberid,password,name,regdate 
from member;
-- order by no desc limit 0,3;

 
-- id로 회원정보가져오기-id중복체크(p592 20라인)
-- 로그인처리   
-- 회원상세정보가져오기, 비번찾기 등등
-- memberid를 입력받아 특정 회원정보 조회
select no,memberid,password,name,regdate 
from member
where memberid='hongid';

-- id중복체크
select count(*)
from member
where memberid='hongid';

/*수정
update 테이블명
set    컬럼명=새값,컬럼명=새값
[where 조건]      */


-- 비번변경(p620) 포함한 회원정보변경  memberid,password,name,regdate 
update member
set    name=?,password=?
where  memberid=?;



/*삭제
delete from 테이블명
[where 조건]
*/
delete from member where no=?;

-- ----------------------------------------------------------------
-- 게시판 테이블(p629)
drop table article;

create table IF NOT EXISTS article(
 article_no	 int   AUTO_INCREMENT NOT NULL PRIMARY KEY,
 writer_id   varchar(50) not null,
 writer_name varchar(50) not null,
 title       varchar(255) not null,
 regdate     datetime	  not null,
 moddate     datetime	  not null,
 read_cnt    int
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 내용 데이터 테이블(p629)
drop table article_content;

create table IF NOT EXISTS `article_content` (
  `article_no` int NOT NULL COMMENT '글번호',
  `content` text COMMENT '내용',
  PRIMARY KEY (`article_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='게시판 글번호와 내용';

-- fk제약조건추가:참조되는 부모레코드가 삭제되면  참조하는 자식record 삭제
alter table article_content
ADD CONSTRAINT articleCon_articleno_fk FOREIGN KEY(article_no) 
			REFERENCES article(article_no)
            ON DELETE CASCADE;

-- article테이블에 입력
insert into article(writer_id,writer_name,title,regdate,moddate,read_cnt)
values('adminid','관리자','게시판사용안내','2023-01-01','2023-01-01',100);

insert into article(writer_id,writer_name,title,regdate,moddate,read_cnt)
values('hongid','홍GD','제목2','2023-02-01','2023-01-01',0),
('hongid','홍GD','제목3','2023-02-02','2023-02-02',0),
('hongid','홍GD','제목4','2023-02-02','2023-02-02',0),
('hongid','홍GD','제목5','2023-02-02','2023-02-02',0),
('hongid','홍GD','제목6','2023-02-02','2023-02-02',0),
('kimid','김9','제목7','2023-03-03','2023-03-03',0),
('kimid','김9','제목8','2023-03-04','2023-03-04',0),
('kimid','김9','제목9','2023-04-03','2023-04-03',0),
('kimid','김9','제목10','2023-04-04','2023-04-04',0),
('leeid','이순신','제목11','2023-05-05','2023-05-05',0),
('leeid','이순신','제목12','2023-05-05','2023-05-05',0),
('leeid','이순신','제목13','2023-05-05','2023-05-05',0);
commit;


-- article_content테이블에 입력
insert into article_content(article_no,content)
values(1,'청정 게시판을 만들어보아요~~ ^-^');

insert into article_content(article_no,content)
values(2,'내용2'),
(3,'내용3'),
(4,'내용4'),
(5,'내용5'),
(6,'내용6'),
(7,'내용7'),
(8,'내용8'),
(9,'내용9'),
(10,'내용10'),
(11,'내용11'),
(12,'내용12'),
(13,'내용13');

commit;

-- 페이징처리를 위한 총 게시물수 조회-646
select count(*) from article;


-- 페이징처리를 반영한  게시판전체목록조회(모든 컬럼 조회) -p647참고
select a.article_no, a.writer_id, a.writer_name, a.title, 
       a.regdate,    a.moddate,   a.read_cnt,
       ac.content
from article a,  article_content ac
where a.article_no=ac.article_no
order by a.article_no desc    limit 12,3;

-- 삭제  참고...
delete from article         where article_no=13; -- 부모T
select * from article;
select * from article_content;


-- 페이징처리를 반영한  article목록조회 -p647
select article_no, writer_id, writer_name, title, 
       regdate,    moddate,   read_cnt
from article 
order by article_no desc;  limit 0,13; -- 1page페이지의 경우
-- 1페이지당 게시글수가 3인 경우(여기에서는 총게시물수가 13건) 
-- =>1page이면 limit 0,3   13 12 11
-- =>2page이면 limit 3,3   10 9 8
-- =>3page이면 limit 6,3   7 6 5 
-- =>4page이면 limit 9,3   4 3 2 
-- =>5page이면 limit 12,3  1 


-- 조회수 증가 - p656 
update article
set read_cnt=read_cnt+1
where article_no=1;

-- article테이블에서  특정글 상세조회 - p655
select article_no, writer_id, writer_name, title, 
       regdate,    moddate,   read_cnt
from article 
where article_no=13;

-- article_content테이블에서  특정글 상세조회 - p656
select article_no, content
from article_content
where article_no=1;

-- 특정글 상세조회(article테이블과  article_content테이블을 조인)
select a.article_no, a.writer_id, a.writer_name, a.title, 
       a.regdate,    a.moddate,   a.read_cnt,
       ac.content
from article a,  article_content ac
where a.article_no=ac.article_no
	  and
      a.article_no=13;

select * from article where  article_no=16;
--  (article)수정
update article
set title='새title', regdate=now()
where article_no=16;

select * from article_content where article_no=16;
--  (article_content)수정
update article_content
set content='새내용'
where article_no=16;

select * from  article;   -- 16존재->삭제성공
select * from  article_content; -- 16존재->삭제성공
delete from article where article_no=16;
-- 우리는 article삭제시 
-- article_content테이블에서도 해당 글번호가 연쇄적으로 삭제하게끔.

--  -------------------------------------------
-- 기존테이블에 새 컬럼을 추가
/*ALTER TABLE 테이블명
  ADD 추가할컬럼명  타입  제약조건;
*/

-- article테이블에  isshow  'Y'(노출.기본상태) 'N'(노출X.삭제회원)
ALTER TABLE article
ADD isshow  varchar(1) check(isshow in('Y','N'));

select * from article;
/*입력
insert into 테이블명[(컬럼명,..컬럼명)]
values(값,..,값);*/

-- 기존 게시글의 isshow컬럼의 값을 'Y'로 변경
update article
set isshow='Y';
commit;

-- 글삭제(UPDATE용) 
update article
set isshow='N'
where  article_no=? and wrtiter_id=?;

--  -------------------------------------------
-- 기존테이블에 새 컬럼을 추가
/*ALTER TABLE 테이블명
  ADD 추가할컬럼명  타입  제약조건;
*/

-- article테이블에 　orifile(원래 파일명), savedfile(저장된 파일명)
ALTER TABLE article
ADD orifile varchar(200);
ALTER TABLE article
ADD savedfile varchar(200);
