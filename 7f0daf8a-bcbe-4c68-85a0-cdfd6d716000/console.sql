create database demo2;
use demo2;
create table student(
                        student_id  int primary key auto_increment,
                        name varchar(100) not null,
                        dob date not null,
                        email varchar(100) not null unique,
                        sex bit not null,
                        phone varchar(20) null ,
                        create_at date default(curdate())
);

create table account(
                        account_id int primary key auto_increment,
                        email varchar(50) not null unique,
                        password varchar(255) not null,
                        role enum('admin','student') not null default 'student',
                        student_id int null ,
                        foreign key(student_id) references student(student_id)
);
insert into account(email,password,role,student_id)
values('admin@gmail.com',1,'admin',null);
insert into student(name, dob, email, sex, phone, create_at)
values
    ('Alice Johnson', '1999-05-15', 'alice.johnson@gmail.com', 1, '0912345678', '2023-01-01'),
    ('Bob Smith', '2000-08-22', 'bob.smith@gmail.com', 0, '0923456789', '2023-01-02'),
    ('Charlie Brown', '1998-12-10', 'charlie.brown@gmail.com', 1, '0934567890', '2023-01-03'),
    ('Diana Prince', '2001-03-18', 'diana.prince@gmail.com', 1, '0945678901', '2023-01-04'),
    ('Ethan Hunt', '1997-07-25', 'ethan.hunt@gmail.com', 0, '0956789012', '2023-01-05'),
    ('Fiona Gallagher', '1996-11-12', 'fiona.gallagher@gmail.com', 1, '0967890123', '2023-01-06'),
    ('George Miller', '1995-09-30', 'george.miller@gmail.com', 0, '0978901234', '2023-01-07'),
    ('Hannah Baker', '2002-04-25', 'hannah.baker@gmail.com', 1, '0989012345', '2023-01-08'),
    ('Ian Wright', '1994-06-15', 'ian.wright@gmail.com', 0, '0990123456', '2023-01-09'),
    ('Jane Doe', '1993-03-10', 'jane.doe@gmail.com', 1, '0901234567', '2023-01-10'),
    ('Kevin Hart', '1992-12-20', 'kevin.hart@gmail.com', 0, '0912345679', '2023-01-11'),
    ('Laura Palmer', '1991-08-05', 'laura.palmer@gmail.com', 1, '0923456780', '2023-01-12'),
    ('Michael Scott', '1990-01-15', 'michael.scott@gmail.com', 0, '0934567891', '2023-01-13'),
    ('Nancy Drew', '1989-07-25', 'nancy.drew@gmail.com', 1, '0945678902', '2023-01-14'),
    ('Oscar Wilde', '1988-10-30', 'oscar.wilde@gmail.com', 0, '0956789013', '2023-01-15');



insert into account(email,password,role,student_id)
values ('alice.johnson@gmail.com', 'alice123', 'student', 1),
       ('bob.smith@gmail.com', 'bob123', 'student', 2),
       ('charlie.brown@gmail.com', 'charlie123', 'student', 3),
       ('diana.prince@gmail.com', 'diana123', 'student', 4),
       ('ethan.hunt@gmail.com', 'ethan123', 'student', 5),
       ('fiona.gallagher@gmail.com', 'fiona123', 'student', 6),
       ('george.miller@gmail.com', 'george123', 'student', 7),
       ('hannah.baker@gmail.com', 'hannah123', 'student', 8),
       ('ian.wright@gmail.com', 'ian123', 'student', 9),
       ('jane.doe@gmail.com', 'jane123', 'student', 10),
       ('kevin.hart@gmail.com', 'kevin123', 'student', 11),
       ('laura.palmer@gmail.com', 'laura123', 'student', 12),
       ('michael.scott@gmail.com', 'michael123', 'student', 13),
       ('nancy.drew@gmail.com', 'nancy123', 'student', 14),
       ('oscar.wilde@gmail.com', 'oscar123', 'student', 15);



create table course(
                       id int primary key auto_increment,
                       name varchar(100) not null,
                       duration int not null,
                       instructor varchar(100) not null,
                       create_date date default(curdate())
);


INSERT INTO course (name, duration, instructor) VALUES    ('Introduction to Programming', 60, 'Dr. Emily Carter'),
                                                          ('Data Structures & Algorithms', 90, 'Prof. John Doe'),
                                                          ('Database Management Systems', 75, 'Dr. Sarah Lee' ),
                                                          ('Web Development Basics', 45, 'Mr. Michael Scott' ),
                                                          ('Machine Learning', 120, 'Dr. Alan Turing'),
                                                          ('Cloud Computing Essentials', 100, 'Dr. Grace Hopper'),
                                                          ('Cybersecurity Basics', 80, 'Mr. Edward Snowden'),
                                                          ('Artificial Intelligence', 150, 'Dr. Andrew Ng');

create table enrollment(
                           id int primary key auto_increment,
                           student_id int not null,
                           course_id int not null,
                           registered_at datetime default(current_timestamp()) on update current_timestamp ,
                           status enum('WAITING','DENIED','CANCLE','CONFIRM') default 'WAITING',
                           foreign key(student_id) references student(student_id),
                           foreign key(course_id) references course(id)
);

INSERT INTO enrollment (student_id, course_id, registered_at, status)
VALUES
    (1, 1, '2023-02-01 10:00:00', 'CONFIRM'),
    (2, 1, '2023-02-02 11:00:00', 'CONFIRM'),
    (3, 1, '2023-02-03 12:00:00', 'CONFIRM'),
    (4, 1, '2023-02-04 13:00:00', 'CONFIRM'),
    (5, 1, '2023-02-05 14:00:00', 'CONFIRM'),
    (6, 1, '2023-02-06 09:15:00', 'CONFIRM'),
    (7, 1, '2023-02-07 10:30:00', 'CONFIRM'),
    (8, 1, '2023-02-08 08:45:00', 'CONFIRM'),
    (9, 1, '2023-02-09 07:25:00', 'CONFIRM'),
    (10, 1, '2023-02-10 12:10:00', 'CONFIRM'),
    (11, 1, '2023-02-11 14:20:00', 'CONFIRM'),
    (12, 2, '2023-02-12 15:30:00', 'WAITING'),
    (13, 2, '2023-02-13 16:40:00', 'WAITING'),
    (14, 3, '2023-02-14 17:50:00', 'DENIED'),
    (15, 3, '2023-02-15 18:00:00', 'CANCLE'),
    (1, 4, '2023-02-16 19:10:00', 'CONFIRM'),
    (2, 4, '2023-02-17 20:20:00', 'WAITING'),
    (3, 5, '2023-02-18 21:30:00', 'CONFIRM'),
    (4, 5, '2023-02-19 22:40:00', 'WAITING'),
    (5, 6, '2023-02-20 23:50:00', 'CONFIRM'),
    (6, 6, '2023-02-21 10:00:00', 'WAITING'),
    (7, 7, '2023-02-22 11:10:00', 'CONFIRM'),
    (8, 7, '2023-02-23 12:20:00', 'WAITING'),
    (9, 8, '2023-02-24 13:30:00', 'CONFIRM'),
    (10, 8, '2023-02-25 14:40:00', 'WAITING'),
    (11, 8, '2023-02-26 15:50:00', 'CONFIRM'),
    (12, 8, '2023-02-27 16:00:00', 'WAITING'),
    (13, 8, '2023-02-28 17:10:00', 'CONFIRM'),
    (14, 8, '2023-03-01 18:20:00', 'WAITING');




delimiter //
CREATE PROCEDURE insert_student_and_account(
    IN p_name VARCHAR(100),
    IN p_dob DATE,
    IN p_email VARCHAR(100),
    IN p_sex BIT,
    IN p_phone VARCHAR(20)
)
BEGIN
    DECLARE new_id INT;

INSERT INTO student(name, dob, email, sex, phone)
VALUES (p_name, p_dob, p_email, p_sex, p_phone);

SET new_id = LAST_INSERT_ID();

INSERT INTO account(email, password, role, student_id)
VALUES (p_email, 1, 'student', new_id);
END//

create procedure delete_course(
    in course_id_in int,
    out code int
)begin
    declare  enrollment_count int;
    if not exists(select * from course where id = course_id_in) then
        set code = 0;
else
select count(id) into enrollment_count from enrollment where course_id = course_id_in;
if enrollment_count > 0 then
            set code = 2;
else
delete from course where id = course_id_in;
set code = 1;
end if;
end if;
end //

create procedure update_student(
    id_in int,
    name_in varchar(100),
    dob_in date,
    email_in varchar(100),
    sex_in bit,
    phone_in varchar(20)

)
begin
    declare email varchar(1000) default email_in;
update student
set name =name_in,
    dob = dob_in,
    email=email_in,
    sex = sex_in,
    phone = phone_in
where student_id = id_in;
update account
set email = email_in
where student_id = id_in;


end //

create  procedure delete_student_account(
    in student_id_in int

)
begin
delete from account where student_id = student_id_in;
delete from student where student_id = student_id_in;

end //
create procedure check_student_email(
    email_in varchar(255),
    out code int
)
begin
    declare exit handler for SQLEXCEPTION
begin
            set code =0;
end;
    set code =  1;


insert into account( email, password)
values (email_in,1);

end //

create procedure confirm_enrollment(
    enrollment_id_in int,
    out code int
)
begin
        declare c_status varchar(20);
select status into c_status from enrollment where id=enrollment_id_in;
if(c_status ='DENIED' or c_status ='CANCLE') then
        set code =0;
        elseif (c_status ='CONFIRM') then
            set code = 2;
        elseif (c_status='WAITING') then
update enrollment
set status = 'CONFIRM'
where id =enrollment_id_in;
set code = 1;

end if;

end //
create procedure deny_enrollment(
    enrollment_id_in int,
    out code int

)
begin
    declare c_status varchar(20);
select status into c_status from enrollment where id=enrollment_id_in;
if(c_status ='DENIED' or c_status ='CANCLE') then
        set code =0;
end if;
    if (c_status ='CONFIRM') then
        set code = 2;
end if ;
    if (c_status='WAITING') then
update enrollment
set status = 'DENIED'
where id =enrollment_id_in;
set code = 1;
end if;
end //

create procedure assign_course(
    in student_id_in int,
    in course_id_in int,
    out code int
)
begin
    proc: begin
        if  exists(select * from enrollment where student_id = student_id_in and course_id=course_id_in) then
            set code = 0;
else
            insert into enrollment(student_id, course_id)
            values(student_id_in, course_id_in);
            set code = 1;
end if;

end ;
end //

create procedure cancle_enrollment(
    student_id_in int,
    enrollment_id_in int,
    out code int
)
begin
    declare  e_status varchar(20);
select status into e_status from enrollment where student_id=student_id_in and  id =enrollment_id_in;
proc :
begin
        if e_status is null then
            set code = -1;
            leave proc;
end if;
        if e_status ='CONFIRM' then
            set code =3;
            leave proc;
end if;
        if e_status = 'DENIED' or e_status='CANCLE' then
            set code =2;
            leave proc;
end if ;
update enrollment
set status ='CANCLE'
where id=enrollment_id_in;
set code=1;

end ;



end //
delimiter //



##test here
# call cancle_enrollment(16,30,@code);
# select @code;
#



#
#    call assign_course(1,1,@code1);
#     select @code1;
# call deny_enrollment(17,@code);
# select @code;

# call confirm_enrollment(10,@code);
# select @code;

# call delete_course(11,@code);
# select @code;
#
# //
#
# //
# call check_student_email('student1@gmail.com',@code);
# select @code;

# select * from enrollment
                    # where student_id =1 and status !='CONFIRM' ;


# select DISTINCT  *  from enrollment
                               # where student_id =27
      #

# select not exists(select * from student where student_id = 1);
#
#     select c.name, count(e.student_id) as total from
    #     enrollment e join course c on e.course_id = c.id
          #     join student s on s.student_id = e.student_id
          #     group by  c.name
          #
          #
          # ;
#
# select c.name as name, count(e.student_id) as total
      # from enrollment e join
             #     course c on e.course_id = c.id
      #     join student s on s.student_id = e.student_id
      # where e.status ='CONFIRM'
      # group by c.name
      # order by count(e.student_id) desc
      #  limit 5;


