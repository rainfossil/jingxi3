set character_set_database=utf8;
set character_set_server=utf8;
set character_set_connection=utf8;
set character_set_client=utf8;
set character_set_results=utf8;

set collation_connection=utf8_bin ; 
set collation_database=utf8_bin ; 
set collation_server=utf8_bin;

#删除数据库
drop database if exists jx;
#创建数据库
create database if not exists jx default character set 'utf8' default collate 'utf8_general_ci';

use jx;

#创建表user
create table user(id varchar(13) not null,
					name varchar(13) not null,
					pass varchar(13) not null,
					email varchar(33) not null,
					rightx int(3) not null,
					constraint pk_id primary key(id)); 
desc user;

#创建表ware
create table ware(number varchar(23) not null,
					name varchar(23) not null,
					description varchar(233),
					dayTime date,
					price decimal(7,2) not null,
					address1 varchar(53),
					address2 varchar(53),
					address3 varchar(53),
					constraint pk_number primary key(number));
					
#创建表收藏、购物车、历史订单
create table cch(flag int(7) auto_increment,
					orderNumber varchar(23) not null,
					userId varchar(13),
					wareNumber varchar(23),
					wareCount int(13),
					status int(1),
					dateTime date,
					constraint pk_flag primary key(flag),
					constraint fk_on foreign key(userId) references user(id) on delete cascade,
					constraint fk_wn foreign key(wareNumber) references ware(number) on delete cascade);
#创建账户表account
create table account(flag int(7) auto_increment,
						userId varchar(13),
						income decimal(9,2),
						pay decimal(9,2),
						dateTime datetime,
						constraint pk_account_flag primary key(flag),
						constraint fk_account_ui foreign key(userId) references user(id) on delete cascade);
#insert into account values(null,'3335332',10000,33,now());
alter table cch add index cch_on(orderNumber);
#创建订单详细orderform
create table orderform(flag int(7) auto_increment,
						formNumber varchar(23) not null,
						cargo_receiver varchar(103),
						consignee varchar(13),
						phone varchar(15),
						company varchar(23),
						dateTime datetime,
						constraint pk_orderform_flag primary key(flag),
						constraint fk_orderform_fn foreign key(formNumber) references cch(orderNumber) on delete cascade);
alter table orderform add column zip_code varchar(7) after cargo_receiver;
alter table orderform add column(receipt varchar(3),express varchar(13),paymethod varchar(13));
#创建论坛表
create table forum(flag int auto_increment,
					contentnumber varchar(13),
					id varchar(13),
					content varchar(255),
					dateTime datetime,
					constraint pk_forum_fg primary key(flag),
					constraint fk_forum_id foreign key(id) references user(id) on delete cascade
					);
#alter table forum modify flag int auto_increment;
#alter table forum drop flag;
#alter table forum add flag int identity(1,1);
alter table forum add index contentn_n(contentnumber);
#创建评论表
create table comment(flag int auto_increment,
					cn varchar(13),
					commentary varchar(255),
					dateTime datetime,
					constraint pk_comment_flag primary key(flag),
					constraint fk_comment_cn foreign key(cn) references forum(contentnumber) on delete cascade
					);
alter table comment add column commentator varchar(13) references user(id) after commentary;
#

#cch加入数据
#insert into cch values(null,'jx00859878799','3335333','p103351',3,0,localtime()),
#						(null,'jx00993702561','3335332','p103351',3,2,sysdate()),
#						(null,'jx00993702561','3335332','p103353',3,2,sysdate());
#ware加入数据
insert into ware values('p103351','katarina','ap',now(),7800,'image/lol/katarina1.jpg','image/lol/katarina2.jpg','image/lol/katarina3.jpg'),
						('p103352','jianji','ad',now(),7800,'image/lol/jianji1.jpg','image/lol/jianji2.jpg','image/lol/jianji3.jpg'),
						('p103353','shuguang','ap',now(),7800,'image/lol/shuguang1.jpg','image/lol/shuguang2.jpg','image/lol/shuguang3.jpg'),
						('p103354','niutou','ap',now(),7800,'image/lol/niutou1.jpg','image/lol/niutou2.jpg','image/lol/niutou3.jpg'),
						('p103355','anni','ap',now(),7800,'image/lol/anni1.jpg','image/lol/anni2.jpg','image/lol/anni3.jpg'),
						('p103356','moganna','ap',now(),7800,'image/lol/moganna1.jpg','image/lol/moganna2.jpg','image/lol/moganna3.jpg'),
						('p103357','guafu','ap',now(),7800,'image/lol/guafu1.jpg','image/lol/guafu2.jpg','image/lol/guafu3.jpg'),
						('p103358','bobi','ap',now(),7800,'image/lol/bobi1.jpg','image/lol/bobi2.jpg','image/lol/bobi3.jpg'),
						('p103359','fengnv','ap',now(),7800,'image/lol/fengnv1.jpg','image/lol/fengnv2.jpg','image/lol/fengnv3.jpg'),
						('p103360','baoshi','ap',now(),7800,'image/lol/baoshi1.jpg','image/lol/baoshi2.jpg','image/lol/baoshi3.jpg'),
						('p103361','daocao','ap',now(),7800,'image/lol/daocao1.jpg','image/lol/daocao2.jpg','image/lol/daocao3.jpg'),
						('p103362','goutou','ap',now(),7800,'image/lol/goutou1.jpg','image/lol/goutou2.jpg','image/lol/goutou3.jpg'),
						('p103363','longgui','ap',now(),7800,'image/lol/longgui1.jpg','image/lol/longgui2.jpg','image/lol/longgui3.jpg'),
						('p103364','xiaochou','ap',now(),7800,'image/lol/xiaochou1.jpg','image/lol/xiaochou2.jpg','image/lol/xiaochou3.jpg'),
						('p103365','xiweier','ap',now(),7800,'image/lol/xiweier1.jpg','image/lol/xiweier2.jpg','image/lol/xiweier3.jpg'),
						('p103366','ruizi','ap',now(),7800,'image/lol/ruizi1.jpg','image/lol/ruizi2.jpg','image/lol/ruizi3.jpg'),
						('p103367','xin','ap',now(),7800,'image/lol/xin1.jpg','image/lol/xin2.jpg','image/lol/xin3.jpg'),
						('p103368','shen','ap',now(),7800,'image/lol/shen1.jpg','image/lol/shen2.jpg','image/lol/shen3.jpg'),
						('p103369','xiaopao','ap',now(),7800,'image/lol/xiaopao1.jpg','image/lol/xiaopao2.jpg','image/lol/xiaopao3.jpg'),
						('p103370','jiqi','ap',now(),7800,'image/lol/jiqi1.jpg','image/lol/jiqi2.jpg','image/lol/jiqi3.jpg'),
						('p103371','hanbing','ap',now(),7800,'image/lol/hanbing1.jpg','image/lol/hanbing2.jpg','image/lol/hanbing3.jpg'),
						('p103372','dashi','ap',now(),7800,'image/lol/dashi1.jpg','image/lol/dashi2.jpg','image/lol/dashi3.jpg'),
						('p103373','jiaoji','ap',now(),7800,'image/lol/jiaoji1.jpg','image/lol/jiaoji2.jpg','image/lol/jiaoji3.jpg'),
						('p103374','manzi','ap',now(),7800,'image/lol/manzi1.jpg','image/lol/manzi2.jpg','image/lol/manzi3.jpg'),
						('p103375','bingniao','ap',now(),7800,'image/lol/bingniao1.jpg','image/lol/bingniao2.jpg','image/lol/bingniao3.jpg'),
						('p103376','mengduo','ap',now(),7800,'image/lol/mengduo1.jpg','image/lol/mengduo2.jpg','image/lol/mengduo3.jpg'),
						('p103377','chuanzhang','ap',now(),7800,'image/lol/chuanzhang1.jpg','image/lol/chuanzhang2.jpg','image/lol/chuanzhang3.jpg'),
						('p103378','huli','ap',now(),7800,'image/lol/huli1.jpg','image/lol/huli2.jpg','image/lol/huli3.jpg'),
						('p103379','shiguang','ap',now(),7800,'image/lol/shiguang1.jpg','image/lol/shiguang2.jpg','image/lol/shiguang3.jpg');
						
insert into ware values('m103331','寂寞在唱歌','阿桑专辑',now(),13353,'image/music/jimo1.jpg','image/music/jimo2.jpg','image/music/jimo3.jpg'),
						('m103332','受了点伤','阿桑专辑',now(),13353,'image/music/shoushang1.jpg','image/music/shoushang2.jpg','image/music/shoushang3.jpg');





