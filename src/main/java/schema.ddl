
    create table jboss_as_scrum.ITEM (
        ITEM_ID  serial not null,
        DESCRICAO varchar(255) not null,
        PRIORIDADE int4 not null,
        SPRINT_ID int4,
        primary key (ITEM_ID)
    );

    create table jboss_as_scrum.SPRINT (
        SPRINT_ID  serial not null,
        DATA_FIM date not null,
        DATA_INICIO timestamp not null,
        NOME varchar(60) not null,
        PROJETO_ID int4,
        primary key (SPRINT_ID)
    );

    create table jboss_as_scrum.TASK (
        TAREFA_ID  serial not null,
        LOCAL varchar(60) not null,
        NOME varchar(60) not null,
        RESOURCE int4 not null,
        STATUS varchar(15),
        ITEM_ID int4,
        primary key (TAREFA_ID)
    );

    create table jboss_as_scrum.USUARIO (
        id  serial not null,
        CIDADE varchar(60),
        RUA varchar(60),
        UF varchar(2),
        LOGIN varchar(20) not null unique,
        NOME varchar(100) not null,
        role varchar(10),
        SENHA varchar(15) not null,
        primary key (id),
        unique (NOME)
    );

    create table jboss_as_scrum.project (
        PROJETO_ID  serial not null,
        DATA_ENTREGA timestamp not null,
        DESCRICAO varchar(255) not null,
        EMPRESA varchar(60) not null,
        NOME varchar(60) not null unique,
        primary key (PROJETO_ID),
        unique (NOME)
    );

    alter table jboss_as_scrum.ITEM 
        add constraint FK2273139B52DD7A 
        foreign key (SPRINT_ID) 
        references jboss_as_scrum.SPRINT;

    alter table jboss_as_scrum.SPRINT 
        add constraint FK922FF61A6D309ED0 
        foreign key (PROJETO_ID) 
        references jboss_as_scrum.project;

    alter table jboss_as_scrum.TASK 
        add constraint FK272D85873F8EDA 
        foreign key (ITEM_ID) 
        references jboss_as_scrum.ITEM;
