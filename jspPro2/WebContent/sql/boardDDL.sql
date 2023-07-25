-- 사용할 데이터베이스 지정
use board;

-- board테이블삭제
drop table board;

-- board테이블생성
create table IF NOT EXISTS board(
 boardno  int   AUTO_INCREMENT NOT NULL PRIMARY KEY comment '글번호',
 writerid   varchar(50) not null comment '작성자id',
 title       varchar(255) not null comment '제목',
 content     text 	 null comment '내용',
 writedate   datetime     not null comment '작성일',
 imageFileName   varchar(100) comment '이미지파일명'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '업로드기능게시판';

-- board테이블
insert into board(writerid,title,content,writedate)
values('adminid','게시판사용안내','게시판!!','2023-01-01');


insert into board(writerid,title,content,writedate)
values('hongid','게시판1','1게시판!!','2023-01-01'),
('kimid','게시판2','2게시판!!','2023-01-01'),
('leeid','게시판3','3게시판!!','2023-01-01'),
('uid','게시판4','4게시판!!','2023-01-01');

-- commit;

-- 목록조회
select boardno,writerid,title,content,writedate
from board
order by articleno desc;

-- 상세조회
select boardno,writerid,title,content,writedate,imageFileName
from board
where articleno=1;

-- 여기에서는 1,3,5번만  파일을 업로드했다고 가정하자
update board
set imageFileName='pic.jpg'
where boardno in(1,3,5);

-- 참고 컬럼명변경
/*alter table 테이블명
change 옛컬럼명 새컬럼명 원래컬럼타입*/

-- 참고 컬럼타입변경
/*alter table 테이블명
change 예컬럼명 새컬럼명 새컬럼타입*/

-- board테이블의 articleno컬럼명을 boardno라는 컬럼명으로 변경
alter table board
change articleno boardno int;

-- 수정
update board
set title=?, content=?, imageFileName=?
where boardno=?;

-- 삭제
delete from board
where boardno=?;