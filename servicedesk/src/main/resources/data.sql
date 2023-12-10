-- Check if the table is empty
SELECT COUNT(*) FROM department INTO @rowCount;

-- If the table is empty, insert values
INSERT INTO department (id, name)
SELECT 1, 'other'
WHERE @rowCount = 0;

INSERT INTO department (id, name)
SELECT 2, 'it_security'
WHERE @rowCount = 0;

INSERT INTO department (id, name)
SELECT 3, 'it_network'
WHERE @rowCount = 0;

INSERT INTO department (id, name)
SELECT 4, 'it_administration'
WHERE @rowCount = 0;

INSERT INTO department (id, name)
SELECT 5, 'it_supply'
WHERE @rowCount = 0;

SELECT COUNT(*) FROM role INTO @rowCount;

INSERT INTO role (id, name)
SELECT 1, 'sd_user'
WHERE @rowCount = 0;

INSERT INTO role (id, name)
SELECT 2, 'sd_agent'
WHERE @rowCount = 0;


--INSERT INTO user
--VALUES (3,'Saša', 'Mrdović', 'sasamrdovic@gmail.com',`$2a$12$8aCjoFVN/tZQ6bfGYIyPx.UDoNBVOT2WqR/wjXIj0JH3aEit4b7sW`,1,1)

--INSERT INTO user
--VALUES (4,'Samra', 'Behić', 'sbehic@gmail.com','$2a$12$BgWc3PCbWMRxYGC9/6OoP.iIrmP/gRD3apQPaVQnums5CCIio5O.i',2,2)

--INSERT INTO user
--VALUES (5,'Elma', 'Polutan', 'pelma@gmail.com',' $2a$12$acu6c/zD36KQu726VJlekelQYn7OsBI2zSs/el1yXXjWogyxvuqYy ',3,2)

--INSERT INTO user
--VALUES (6,'Amila', 'Hadzibajramovic', 'ahadzibajr@gmail.com','$2a$12$dB4IPLaqbfPup7V2wKvHbeoRXoEiby1dTwz8sGh8GT9cUZYTgSfl.',4,2)

--INSERT INTO user
--VALUES (7,'Agent', 'Agent', 'agent007@gmail.com','$2a$12$jcsMd/V2TpGYWUq00oF0cuosaxD9oTeUjJXrs7vyTNDXUOnBmXHMi',5,2)





