create schema ` application `;

create schema `quartz`;
use `quartz`;

create table QRTZ_JOB_DETAILS (
  SCHED_NAME        varchar(120) not null,
  JOB_NAME          varchar(190) not null,
  JOB_GROUP         varchar(190) not null,
  DESCRIPTION       varchar(250) null,
  JOB_CLASS_NAME    varchar(250) not null,
  IS_DURABLE        varchar(1)   not null,
  IS_NONCONCURRENT  varchar(1)   not null,
  IS_UPDATE_DATA    varchar(1)   not null,
  REQUESTS_RECOVERY varchar(1)   not null,
  JOB_DATA          blob         null,
  primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
  ENGINE = InnoDB;

create table QRTZ_TRIGGERS (
  SCHED_NAME     varchar(120) not null,
  TRIGGER_NAME   varchar(190) not null,
  TRIGGER_GROUP  varchar(190) not null,
  JOB_NAME       varchar(190) not null,
  JOB_GROUP      varchar(190) not null,
  DESCRIPTION    varchar(250) null,
  NEXT_FIRE_TIME bigint (13) null,
  PREV_FIRE_TIME bigint (13) null,
  PRIORITY       integer      null,
  TRIGGER_STATE  varchar(16)  not null,
  TRIGGER_TYPE   varchar(8)   not null,
  START_TIME     bigint (13) not null,
  END_TIME       bigint (13) null,
  CALENDAR_NAME  varchar(190) null,
  MISFIRE_INSTR  smallint (2) null,
  JOB_DATA       blob         null,
  primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  foreign key (SCHED_NAME, JOB_NAME, JOB_GROUP)
  references QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
  ENGINE = InnoDB;

create table QRTZ_SIMPLE_TRIGGERS (
  SCHED_NAME      varchar(120) not null,
  TRIGGER_NAME    varchar(190) not null,
  TRIGGER_GROUP   varchar(190) not null,
  REPEAT_COUNT    bigint (7) not null,
  REPEAT_INTERVAL bigint (12) not null,
  TIMES_TRIGGERED bigint (10) not null,
  primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
  ENGINE = InnoDB;

create table QRTZ_CRON_TRIGGERS (
  SCHED_NAME      varchar(120) not null,
  TRIGGER_NAME    varchar(190) not null,
  TRIGGER_GROUP   varchar(190) not null,
  CRON_EXPRESSION varchar(120) not null,
  TIME_ZONE_ID    varchar(80),
  primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
  ENGINE = InnoDB;

create table QRTZ_SIMPROP_TRIGGERS
(
  SCHED_NAME    varchar(120)   not null,
  TRIGGER_NAME  varchar(190)   not null,
  TRIGGER_GROUP varchar(190)   not null,
  STR_PROP_1    varchar(512)   null,
  STR_PROP_2    varchar(512)   null,
  STR_PROP_3    varchar(512)   null,
  INT_PROP_1    int            null,
  INT_PROP_2    int            null,
  LONG_PROP_1   bigint         null,
  LONG_PROP_2   bigint         null,
  DEC_PROP_1    numeric(13, 4) null,
  DEC_PROP_2    numeric(13, 4) null,
  BOOL_PROP_1   varchar(1)     null,
  BOOL_PROP_2   varchar(1)     null,
  primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
  ENGINE = InnoDB;

create table QRTZ_BLOB_TRIGGERS (
  SCHED_NAME    varchar(120) not null,
  TRIGGER_NAME  varchar(190) not null,
  TRIGGER_GROUP varchar(190) not null,
  BLOB_DATA     blob         null,
  primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  INDEX (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
  ENGINE = InnoDB;

create table QRTZ_CALENDARS (
  SCHED_NAME    varchar(120) not null,
  CALENDAR_NAME varchar(190) not null,
  CALENDAR      blob         not null,
  primary key (SCHED_NAME, CALENDAR_NAME)
)
  ENGINE = InnoDB;

create table QRTZ_PAUSED_TRIGGER_GRPS (
  SCHED_NAME    varchar(120) not null,
  TRIGGER_GROUP varchar(190) not null,
  primary key (SCHED_NAME, TRIGGER_GROUP)
)
  ENGINE = InnoDB;

create table QRTZ_FIRED_TRIGGERS (
  SCHED_NAME        varchar(120) not null,
  ENTRY_ID          varchar(95)  not null,
  TRIGGER_NAME      varchar(190) not null,
  TRIGGER_GROUP     varchar(190) not null,
  INSTANCE_NAME     varchar(190) not null,
  FIRED_TIME        bigint (13) not null,
  SCHED_TIME        bigint (13) not null,
  PRIORITY          integer      not null,
  STATE             varchar(16)  not null,
  JOB_NAME          varchar(190) null,
  JOB_GROUP         varchar(190) null,
  IS_NONCONCURRENT  varchar(1)   null,
  REQUESTS_RECOVERY varchar(1)   null,
  primary key (SCHED_NAME, ENTRY_ID)
)
  ENGINE = InnoDB;

create table QRTZ_SCHEDULER_STATE (
  SCHED_NAME        varchar(120) not null,
  INSTANCE_NAME     varchar(190) not null,
  LAST_CHECKIN_TIME bigint (13) not null,
  CHECKIN_INTERVAL  bigint (13) not null,
  primary key (SCHED_NAME, INSTANCE_NAME)
)
  ENGINE = InnoDB;

create table QRTZ_LOCKS (
  SCHED_NAME varchar(120) not null,
  LOCK_NAME  varchar(40)  not null,
  primary key (SCHED_NAME, LOCK_NAME)
)
  ENGINE = InnoDB;

create index IDX_QRTZ_J_REQ_RECOVERY on QRTZ_JOB_DETAILS (SCHED_NAME, REQUESTS_RECOVERY);
create index IDX_QRTZ_J_GRP on QRTZ_JOB_DETAILS (SCHED_NAME, JOB_GROUP);

create index IDX_QRTZ_T_J on QRTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IDX_QRTZ_T_JG on QRTZ_TRIGGERS (SCHED_NAME, JOB_GROUP);
create index IDX_QRTZ_T_C on QRTZ_TRIGGERS (SCHED_NAME, CALENDAR_NAME);
create index IDX_QRTZ_T_G on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IDX_QRTZ_T_STATE on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE);
create index IDX_QRTZ_T_N_STATE on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IDX_QRTZ_T_N_G_STATE on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IDX_QRTZ_T_NEXT_FIRE_TIME on QRTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME);
create index IDX_QRTZ_T_NFT_ST on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE, NEXT_FIRE_TIME);
create index IDX_QRTZ_T_NFT_MISFIRE on QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME);
create index IDX_QRTZ_T_NFT_ST_MISFIRE on QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_STATE);
create index IDX_QRTZ_T_NFT_ST_MISFIRE_GRP on QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_GROUP, TRIGGER_STATE);

create index IDX_QRTZ_FT_TRIG_INST_NAME on QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME);
create index IDX_QRTZ_FT_INST_JOB_REQ_RCVRY on QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY);
create index IDX_QRTZ_FT_J_G on QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IDX_QRTZ_FT_JG on QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_GROUP);
create index IDX_QRTZ_FT_T_G on QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
create index IDX_QRTZ_FT_TG on QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);

commit;

