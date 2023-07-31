-- 사용할 데이터베이스 지정
use board;

-- board 테이블
drop table comment;

create table IF NOT EXISTS comment(
 commentno	 int   AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '댓글번호',
 boardno     int   not null COMMENT '원글번호',
 cmt_writerid   varchar(50)   not null COMMENT '댓글작성자id',
 cmt_content	text 		  not null COMMENT '댓글내용',
 cmt_writedate  datetime	  COMMENT '댓글작성일',
 CONSTRAINT comment_boardno_fk FOREIGN KEY(boardno) REFERENCES board(boardno) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='댓글게시판';


-- board테이블에 입력
insert into comment(boardno,cmt_writerid,cmt_content,cmt_writedate)
values(1,'hongid','댓글내용~~~','2023-01-01');

insert into comment(boardno,cmt_writerid,cmt_content,cmt_writedate)
values(2,'hongid','댓글내용2~~~','2023-01-02'),
(2,'kimid','댓글내용방가 방가~^-^~','2023-02-02'),
(3,'hongid','댓글내용여름엔 빙수!','2023-02-05'),
(3,'kimid','댓글내용방가방가 ~^-^~','2023-02-06'),
(3,'hongid','댓글내용여름엔 빙수!','2023-03-07'),
(4,'kimid','댓글내용방가방가 ~^-^~','2023-03-09'),
(4,'hongid','댓글내용여름엔 빙수!','2023-03-10'),
(4,'kimid','댓글내용방가방가 ~^-^~','2023-03-11'),
(5,'hongid','댓글내용여름엔 빙수!','2023-04-15'),
(5,'kimid','댓글내용방가방가 ~^-^~','2023-04-19'),
(6,'hongid','댓글내용여름엔 빙수!','2023-04-20'),
(6,'kimid','댓글내용방가방가 ~^-^~','2023-05-21'),
(7,'hongid','댓글내용여름엔 빙수!','2023-05-22');

commit;

-- 목록조회
select commentno,boardno,cmt_writerid,cmt_content,cmt_writedate
from comment
order by boardno desc,commentno desc;

-- (특적원글에 등록된) 댓글목록조회
select commentno,boardno,cmt_writerid,cmt_content,cmt_writedate
from comment
where boardno=2;

-- 댓글 수정
update comment
set  cmt_content=?
where commentno=? and  cmt_writerid=?;             

-- 댓글 삭제
delete from comment
where commentno=?;
                
                


                
                