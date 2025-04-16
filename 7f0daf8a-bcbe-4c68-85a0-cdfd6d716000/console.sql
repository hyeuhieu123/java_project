create database demo2;
use demo2;
create table admin(
                      id int primary key auto_increment,
                      username varchar(50) not null unique,
                      password varchar(255) not null
);
create table student(
                        id int primary key auto_increment,
                        name varchar(100) not null,
                        dob date not null,
                        email varchar(100) not null unique,
                        sex bit not null,
                        phone varchar(20) null ,
                        password varchar(255) not null,
                        create_at date default(curdate())
);
create table course(
                       id int primary key auto_increment,
                       name varchar(100) not null,
                       duration int not null,
                       instructor varchar(100) not null,
                       create_date date default(curdate())
);

create table enrollment(
                           id int primary key auto_increment,
                           student_id int not null,
                           course_id int not null,
                           registered_at datetime default(current_timestamp()) on update current_timestamp ,
                           status enum('WAITING','DENIED','CANCLE','CONFIRM') default 'WAITING',
                           foreign key(student_id) references student(id),
                           foreign key(course_id) references course(id)
);
insert into enrollment(student_id,course_id)values(1,1);
select * from enrollment;

insert into course(name,duration,instructor) values('b',12,'a');
update enrollment
set course_id =2
where id = 1;
