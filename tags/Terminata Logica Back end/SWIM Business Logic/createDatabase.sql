-- Creates the necessary database.
CREATE
	DATABASE
	IF NOT EXISTS
	swimdb;

-- Creates the user that the application uses to act on the data
-- [*] "localhost" MUST be replaced with the host identifier of the
-- network position where the Application Server will be installed
-- [*] "swimpsw" MUST be replaced with the specific secret password 
-- you prefer.
CREATE
	USER 'swimadmin'@'localhost'
	IDENTIFIED BY 'swimpsw'; 	

-- Grants all privileges, except for the ability to grant them to 
-- other users, on the just created database.
GRANT
	ALL PRIVILEGES
	ON swimdb.*
	TO 'swimadmin'@'localhost';
