create database demo2;
use demo2;
create table admin(
                      id int primary key auto_increment,
                      username varchar(50) not null unique,
                      password varchar(255) not null
);
insert into admin(username,password) values(1,1);
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

INSERT INTO student (name, dob, email, sex, phone, password) VALUES
                                                                 ('Student 1', '2000-01-01', '1', 1, '1234567890', '1'),
                                                                 ('Student 2', '2000-02-02', 'student2@example.com', 0, '1234567891', 'password2'),
                                                                 ('Student 3', '2000-03-03', 'student3@example.com', 1, '1234567892', 'password3'),
                                                                 ('Student 4', '2000-04-04', 'student4@example.com', 0, '1234567893', 'password4'),
                                                                 ('Student 5', '2000-05-05', 'student5@example.com', 1, '1234567894', 'password5'),
                                                                 ('Student 6', '2000-06-06', 'student6@example.com', 0, '1234567895', 'password6'),
                                                                 ('Student 7', '2000-07-07', 'student7@example.com', 1, '1234567896', 'password7'),
                                                                 ('Student 8', '2000-08-08', 'student8@example.com', 0, '1234567897', 'password8'),
                                                                 ('Student 9', '2000-09-09', 'student9@example.com', 1, '1234567898', 'password9'),
                                                                 ('Student 10', '2000-10-10', 'student10@example.com', 0, '1234567899', 'password10'),
                                                                 ('Student 11', '2000-11-11', 'student11@example.com', 1, '1234567800', 'password11'),
                                                                 ('Student 12', '2000-12-12', 'student12@example.com', 0, '1234567801', 'password12'),
                                                                 ('Student 13', '2001-01-01', 'student13@example.com', 1, '1234567802', 'password13'),
                                                                 ('Student 14', '2001-02-02', 'student14@example.com', 0, '1234567803', 'password14'),
                                                                 ('Student 15', '2001-03-03', 'student15@example.com', 1, '1234567804', 'password15'),
                                                                 ('Student 16', '2001-04-04', 'student16@example.com', 0, '1234567805', 'password16'),
                                                                 ('Student 17', '2001-05-05', 'student17@example.com', 1, '1234567806', 'password17'),
                                                                 ('Student 18', '2001-06-06', 'student18@example.com', 0, '1234567807', 'password18'),
                                                                 ('Student 19', '2001-07-07', 'student19@example.com', 1, '1234567808', 'password19'),
                                                                 ('Student 20', '2001-08-08', 'student20@example.com', 0, '1234567809', 'password20');
create table course(
                       id int primary key auto_increment,
                       name varchar(100) not null,
                       duration int not null,
                       instructor varchar(100) not null,
                       create_date date default(curdate())
);

INSERT INTO course (name, duration, instructor, create_date) VALUES
                                                                 ('Course 1', 30, 'Instructor 1', '2023-01-01'),
                                                                 ('Course 2', 45, 'Instructor 2', '2023-01-02'),
                                                                 ('Course 3', 60, 'Instructor 3', '2023-01-03'),
                                                                 ('Course 4', 90, 'Instructor 4', '2023-01-04'),
                                                                 ('Course 5', 120, 'Instructor 5', '2023-01-05'),
                                                                 ('Course 6', 30, 'Instructor 6', '2023-01-06'),
                                                                 ('Course 7', 45, 'Instructor 7', '2023-01-07'),
                                                                 ('Course 8', 60, 'Instructor 8', '2023-01-08'),
                                                                 ('Course 9', 90, 'Instructor 9', '2023-01-09'),
                                                                 ('Course 10', 120, 'Instructor 10', '2023-01-10'),
                                                                 ('Course 11', 30, 'Instructor 11', '2023-01-11'),
                                                                 ('Course 12', 45, 'Instructor 12', '2023-01-12'),
                                                                 ('Course 13', 60, 'Instructor 13', '2023-01-13'),
                                                                 ('Course 14', 90, 'Instructor 14', '2023-01-14'),
                                                                 ('Course 15', 120, 'Instructor 15', '2023-01-15'),
                                                                 ('Course 16', 30, 'Instructor 16', '2023-01-16'),
                                                                 ('Course 17', 45, 'Instructor 17', '2023-01-17'),
                                                                 ('Course 18', 60, 'Instructor 18', '2023-01-18'),
                                                                 ('Course 19', 90, 'Instructor 19', '2023-01-19'),
                                                                 ('Course 20', 120, 'Instructor 20', '2023-01-20');
create table enrollment(
                           id int primary key auto_increment,
                           student_id int not null,
                           course_id int not null,
                           registered_at datetime default(current_timestamp()) on update current_timestamp ,
                           status enum('WAITING','DENIED','CANCLE','CONFIRM') default 'WAITING',
                           foreign key(student_id) references student(id),
                           foreign key(course_id) references course(id)
);





# test here
