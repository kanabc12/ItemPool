-- Create table
create table SA_USER
(
  USER_ID              VARCHAR2(40) not null,
  USER_NAME            VARCHAR2(40),
  PASSWORD             VARCHAR2(60),
  REAL_NAME            VARCHAR2(200),
  IS_LOCK              VARCHAR2(1) default 0,
  IS_DELETE            VARCHAR2(1) default 0,
  TELEPHONE            VARCHAR2(18),
  PASSWORD_UPDATE_TIME DATE,
  LOCK_TIME            DATE,
  ATTR1                VARCHAR2(100),
  ATTR2                VARCHAR2(100),
  ATTR3                VARCHAR2(100),
  ATTR4                VARCHAR2(100),
  MAIL                 VARCHAR2(100),
  GENDER               VARCHAR2(1),
  USER_IP              VARCHAR2(60),
  IS_MANAGER           VARCHAR2(1) default 0,
  USER_TYPE            VARCHAR2(1),
  HIS_PASSWORD1        VARCHAR2(60),
  HIS_PASSWORD2        VARCHAR2(60),
  HIS_PASSWORD3        VARCHAR2(60),
  HIS_PASSWORD4        VARCHAR2(60),
  HIS_PASSWORD5        VARCHAR2(60),
  HIS_PASSWORD6        VARCHAR2(60),
  CREATE_TIME          DATE
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table SA_USER
  add constraint PK_USER_ID primary key (USER_ID)
  using index 
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index IDX_USER_REAL_NAME on SA_USER (REAL_NAME)
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create unique index INX_SA_USER on SA_USER (USER_NAME)
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create unique index PK_SA_USER on SA_USER (USER_ID)
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
-- Create sequence 
create sequence SEQ_USER_ID
minvalue 0
maxvalue 9999999999999999999999999
start with 10000
increment by 1
cache 20;
