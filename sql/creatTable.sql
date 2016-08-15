drop table if exists question;

drop table if exists user;

/*==============================================================*/
/* Table: question                                              */
/*==============================================================*/
create table question
(
   id                   int not null,
   titile               varchar(10),
   content              varchar(100),
   createdDate          Data,
   userId               int,
   commentCount         int,
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null,
   name                 varchar(10),
   password             varchar(10),
   salt                 varchar(10),
   headUrl              varchar(10),
   primary key (id)
);
