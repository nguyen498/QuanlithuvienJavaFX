CREATE TABLE Account (
   id INT PRIMARY KEY AUTO_INCREMENT
 , password NVARCHAR(100) NOT NULL
 , name NVARCHAR(100) NOT NULL
 , gender NVARCHAR(100) NOT NULL
 , birthdate DATETIME NOT NULL
 , accountType INT NOT NULL  DEFAULT 0 
 -- 0: TEACHER, 1: OFFICER, 2: STUDENT
);

CREATE TABLE LibraryCard (
 cardNumber INT PRIMARY KEY AUTO_INCREMENT
 , issuedAt DATETIME NOT NULL
 , active INT NOT NULL DEFAULT 0
 , account_id int UNIQUE
 , FOREIGN KEY (account_id) REFERENCES Account(id)
);


CREATE TABLE Book (
   id INT PRIMARY KEY AUTO_INCREMENT
 , name NVARCHAR(100) NOT NULL
 , description NVARCHAR(200) NOT NULL
 , price FLOAT NOT NULL DEFAULT 0
 , dateOfPurcharse DATETIME NOT NULL
 , publicationPlace NVARCHAR(100) NOT NULL
 , status INT NOT NULL  DEFAULT 0
);


CREATE TABLE Author (
   id INT PRIMARY KEY AUTO_INCREMENT
 , name NVARCHAR(100) NOT NULL
);

CREATE TABLE author_book (
    PRIMARY KEY (authorID, bookID)
  , authorID INT NOT NULL
  , bookID   INT NOT NULL 
  , FOREIGN KEY (authorID) REFERENCES Author(id)
  , FOREIGN KEY (bookID) REFERENCES Book(id)
);


CREATE TABLE Category (
   id INT PRIMARY KEY AUTO_INCREMENT
 , name NVARCHAR(100) NOT NULL
);

CREATE TABLE category_book (
    PRIMARY KEY (categoryID, bookID)
  , categoryID INT NOT NULL
  , bookID   INT NOT NULL
  , FOREIGN KEY (categoryID) REFERENCES Category(id)
  , FOREIGN KEY (bookID) REFERENCES Book(id)
);

CREATE TABLE ReservationTicket (
   id INT PRIMARY KEY AUTO_INCREMENT
 , createdDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
 , status INT NOT NULL DEFAULT 0
 , totalBookReserved INT NOT NULL DEFAULT 0
 , accountID INT NOT NULL
 , FOREIGN KEY (accountID) REFERENCES Account(id)
);

CREATE TABLE reservation_detail (
    PRIMARY KEY (bookID, reservationID)
  , dueDate DATETIME NOT NULL
  , bookID INT NOT NULL
  , reservationID INT NOT NULL
  , FOREIGN KEY (bookID) REFERENCES Book(id)
  , FOREIGN KEY (reservationID) REFERENCES ReservationTicket(id)
);


CREATE TABLE LendingTicket (
   id INT PRIMARY KEY AUTO_INCREMENT
 , createdDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
 , totalBookLended INT NOT NULL DEFAULT 0
 , accountID INT NOT NULL
 , FOREIGN KEY (accountID) REFERENCES Account(id)
);


CREATE TABLE lending_detail (
    PRIMARY KEY (bookID, lendingID)
  , dueDate DATETIME NOT NULL
  , ammount FLOAT NOT NULL DEFAULT 0
  , bookID INT NOT NULL
  , lendingID INT NOT NULL
  , FOREIGN KEY (bookID) REFERENCES Book(id)
  , FOREIGN KEY (lendingID) REFERENCES LendingTicket(id)
);


CREATE TABLE Payment (
   id INT PRIMARY KEY AUTO_INCREMENT
 , ammount FLOAT NOT NULL DEFAULT 0
 , fine FLOAT NOT NULL DEFAULT 0
 , status INT NOT NULL  DEFAULT 0
 , createdDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
 , accountID INT NOT NULL
 , lendingID INT NOT NULL
 , FOREIGN KEY (accountID) REFERENCES Account(id)
 , FOREIGN KEY (lendingID) REFERENCES LendingTicket(id)
);

