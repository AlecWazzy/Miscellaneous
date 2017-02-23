DROP DATABASE IF EXISTS NotesDB;
CREATE DATABASE NotesDB;
USE NotesDB;

CREATE TABLE `user` (
  `UserName` varchar(25) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `UserPassword` varchar(25) NOT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `user`(`UserName`,`FirstName`,`LastName`,`Email`,`UserPassword`) values ('abe','Abe','Aberly','abe@cprg352.com','password'),('admin','Admin','Admin','admin@cprg352.com','password'),('bob','Bob','Barker','bob@cprg352.com','password');

CREATE TABLE `note` (
  `NoteID` int(11) NOT NULL AUTO_INCREMENT,
  `DateCreated` datetime NOT NULL,
  `Contents` varchar(10000) NOT NULL,
  `Owner` varchar(25) NOT NULL,
  PRIMARY KEY (`NoteID`),
  KEY `note_owner` (`Owner`),
  CONSTRAINT `note_owner` FOREIGN KEY (`Owner`) REFERENCES `user` (`UserName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert  into `note`(`NoteID`,`DateCreated`,`Contents`,`Owner`) values (1,'2015-10-28 22:52:47','test','abe'),(2,'2015-10-28 22:52:48','test2','abe');

CREATE TABLE `notecollaborator` (
  `NoteId` int(11) NOT NULL,
  `UserName` varchar(25) NOT NULL,
  PRIMARY KEY (`NoteId`,`UserName`),
  KEY `fk_NoteCollaborator_user1_idx` (`UserName`),
  CONSTRAINT `fk_NoteCollaborator_note1` FOREIGN KEY (`NoteId`) REFERENCES `note` (`NoteID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_NoteCollaborator_user1` FOREIGN KEY (`UserName`) REFERENCES `user` (`UserName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `notecollaborator`(`NoteId`,`UserName`) values (1,'bob');

CREATE TABLE `role` (
  `RoleId` int(11) NOT NULL,
  `RoleName` varchar(25) NOT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `role`(`RoleId`,`RoleName`) values (1,'admin');

CREATE TABLE `userrole` (
  `UserName` varchar(25) NOT NULL,
  `RoleId` int(11) NOT NULL,
  PRIMARY KEY (`UserName`,`RoleId`),
  KEY `userrole_role_idx` (`RoleId`),
  CONSTRAINT `userrole_role` FOREIGN KEY (`RoleId`) REFERENCES `role` (`RoleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userrole_user` FOREIGN KEY (`UserName`) REFERENCES `user` (`UserName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `userrole`(`UserName`,`RoleId`) values ('admin',1);
