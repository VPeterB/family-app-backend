create table users
(
    ID             VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    email          varchar(255) not null,
    password       varchar(255) not null,
    phonenumber    varchar(255),
    firstname      varchar(255),
    lastname       varchar(255),
    username       varchar(255),
    category       varchar(255),
    profilepicture varchar(255),
    familyID       VARCHAR(60),
    primary key (ID)
)

create table familys
(
    ID             VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    profilepicture varchar(255),
    primary key (ID)
)

create table invites
(
    id       VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    userID   varchar(60) not null,
    familyID varchar(60) not null,
    primary key (ID)
)

create table shoppingitems
(
    ID             VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    name           varchar(255) not null,
    shoppinglistID varchar(60)  not null,
    done           int,
    primary key (ID)
)

create table shoppinglists
(
    ID   VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    name varchar(255) not null,
    primary key (ID)
)

create table list_user
(
    userID         VARCHAR(60) not null,
    shoppinglistID varchar(60) not null,
    primary key (userID, shoppinglistID)
)

alter table list_user
    add constraint XR87uo43xteb9ye72lgq9zcj0wt
        foreign key (userID)
            references users (ID)
alter table list_user
    add constraint OJ26uo36pjeb4ye18ayc5zcj7zj
        foreign key (shoppinglistID)
            references shoppinglists (ID)

alter table shoppingitems
    add constraint ZH83uo82pjeb9ye72pyc1zcj0xt
        foreign key (shoppinglistID)
            references shoppinglists (ID)

alter table invites
    add constraint PQ54uo82jnoy7ye38pyc8kcj2lj
        foreign key (familyID)
            references familys (ID)
alter table invites
    add constraint JL21uo82jzhy7ye98vyc8kcj7lj
        foreign key (userID)
            references users (ID)

alter table users
    add constraint FK74uo82jnot7ye32pyc8dcj2eh
        foreign key (familyID)
            references familys (ID)