Create table Usuarios(
UUID varchar2(50) not null,
nombre_user VARCHAR2(30) not null unique ,
contra_user VARCHAR2(270) NOT NULL,

cONSTRAINT PK_USERARIOS PRIMARY KEY (UUID)
);

cREATE TABLE Tickets (
UUID VARCHAR2 (50) NOT NULL, 
numero_Ticket VARCHAR2(5)NOT NULL ,
titulo_ticket  VARCHAR2(30)NOT NULL,
Descrip_ticket  VARCHAR2(75) NOT NULL,
autor_ticket VARCHAR2(10)NOT NULL,
email_ticket VARCHAR2(50)NOT NULL,
estado_ticket VARCHAR2(10)NOT NULL,

CONSTRAINT PK_TICKETS PRIMARY KEY(UUID)


);
