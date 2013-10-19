
    create table CONFERENCE (
        id bigint not null auto_increment,
        description varchar(255),
        end datetime,
        name varchar(255),
        start datetime,
        primary key (id)
    );

    create table ROOM (
        id bigint not null auto_increment,
        capacity integer not null,
        name varchar(255),
        conference_id bigint,
        primary key (id)
    );

    create table SPEAKER (
        id bigint not null auto_increment,
        description varchar(255),
        name varchar(255),
        primary key (id)
    );

    create table SPEAKER_HAS_TALK (
        id bigint not null auto_increment,
        speakerId bigint not null,
        talkId bigint not null,
        primary key (id)
    );

    create table TALK (
        id bigint not null auto_increment,
        description varchar(255),
        duration integer not null,
        name varchar(255),
        conference_id bigint,
        room_id bigint,
        primary key (id)
    );

    alter table ROOM 
        add index FK2678DB37F32EF2 (conference_id), 
        add constraint FK2678DB37F32EF2 
        foreign key (conference_id) 
        references CONFERENCE (id);

    alter table TALK 
        add index FK272CAC153A8C92 (room_id), 
        add constraint FK272CAC153A8C92 
        foreign key (room_id) 
        references ROOM (id);

    alter table TALK 
        add index FK272CAC37F32EF2 (conference_id), 
        add constraint FK272CAC37F32EF2 
        foreign key (conference_id) 
        references CONFERENCE (id);
