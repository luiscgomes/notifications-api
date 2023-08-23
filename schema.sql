drop table if exists Categories;
drop table if exists Users;
drop table if exists Channels;
drop table if exists UserCategories;
drop table if exists UserChannels;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE Categories (
    Id integer primary key,
    Created_At timestamp not null DEFAULT now(),
    Name varchar(100) not null
);

CREATE TABLE Channels (
    Id integer primary key,
    Created_At timestamp not null DEFAULT now(),
    Name varchar(100) not null
);

CREATE TABLE Users (
    Id uuid primary key DEFAULT uuid_generate_v4(),
    Created_At timestamp not null DEFAULT now(),
    Name varchar(150) not null,
    Email varchar(150) not null,
    Phone varchar(30) not null
);

CREATE TABLE UserCategories (
    UserId uuid,
    CategoryId integer,
    Created_At timestamp not null DEFAULT now(),
    PRIMARY KEY(UserId, CategoryId),
    CONSTRAINT fk_user
          FOREIGN KEY(UserId)
    	  REFERENCES Users(Id),
    CONSTRAINT fk_category
          FOREIGN KEY(CategoryId)
      	  REFERENCES Categories(Id)
);

CREATE TABLE UserChannels (
    UserId uuid,
    ChannelId integer,
    Created_At timestamp not null DEFAULT now(),
    PRIMARY KEY(UserId, ChannelId),
    CONSTRAINT fk_user
          FOREIGN KEY(UserId)
    	  REFERENCES Users(Id),
    CONSTRAINT fk_channel
          FOREIGN KEY(ChannelId)
      	  REFERENCES Channels(Id)
);

CREATE TABLE Notifications (
    Id uuid primary key DEFAULT uuid_generate_v4(),
    Notified_At timestamp not null,
    UserId uuid not null,
    CategoryId integer not null,
    ChannelId integer not null,
    Message varchar(255) not null,
    CONSTRAINT fk_user
          FOREIGN KEY(UserId)
          REFERENCES Users(Id),
    CONSTRAINT fk_channel
          FOREIGN KEY(ChannelId)
          REFERENCES Channels(Id),
    CONSTRAINT fk_category
          FOREIGN KEY(CategoryId)
          REFERENCES Categories(Id)
);

INSERT INTO Categories (Id, Name, Created_At) VALUES (1, 'Sports', now());
INSERT INTO Categories (Id, Name, Created_At) VALUES (2, 'Finance', now());
INSERT INTO Categories (Id, Name, Created_At) VALUES (3, 'Films', now());

INSERT INTO Channels (Id, Name, Created_At) VALUES (1, 'SMS', now());
INSERT INTO Channels (Id, Name, Created_At) VALUES (2, 'Email', now());
INSERT INTO Channels (Id, Name, Created_At) VALUES (3, 'PushNotification', now());

INSERT INTO Users (Id, Created_At, Name, Email, Phone) VALUES('f84f42f8-36a5-4abf-a4b6-ce50206dfbca', now(), 'Mary H. Halligan', 'mary.halligan@notification', '561-354-2334');
INSERT INTO Users (Id, Created_At, Name, Email, Phone) VALUES('32593dd9-92de-4f62-b095-847ed5591709', now(), 'Diana M. Chandler', 'diana.chandler@notification', '406-742-9004');
INSERT INTO Users (Id, Created_At, Name, Email, Phone) VALUES('4431f409-636a-45a6-b274-9310d39d5246', now(), 'Elaina K. McCormick', 'elaina.mccormick@notification', '801-841-3646');

INSERT INTO UserChannels (UserId, ChannelId) VALUES ('f84f42f8-36a5-4abf-a4b6-ce50206dfbca', 1);
INSERT INTO UserChannels (UserId, ChannelId) VALUES ('f84f42f8-36a5-4abf-a4b6-ce50206dfbca', 2);
INSERT INTO UserChannels (UserId, ChannelId) VALUES ('32593dd9-92de-4f62-b095-847ed5591709', 2);
INSERT INTO UserChannels (UserId, ChannelId) VALUES ('32593dd9-92de-4f62-b095-847ed5591709', 3);
INSERT INTO UserChannels (UserId, ChannelId) VALUES ('4431f409-636a-45a6-b274-9310d39d5246', 3);

INSERT INTO UserCategories (UserId, CategoryId) VALUES ('f84f42f8-36a5-4abf-a4b6-ce50206dfbca', 2);
INSERT INTO UserCategories (UserId, CategoryId) VALUES ('f84f42f8-36a5-4abf-a4b6-ce50206dfbca', 3);
INSERT INTO UserCategories (UserId, CategoryId) VALUES ('32593dd9-92de-4f62-b095-847ed5591709', 3);
INSERT INTO UserCategories (UserId, CategoryId) VALUES ('32593dd9-92de-4f62-b095-847ed5591709', 1);
INSERT INTO UserCategories (UserId, CategoryId) VALUES ('4431f409-636a-45a6-b274-9310d39d5246', 2);
