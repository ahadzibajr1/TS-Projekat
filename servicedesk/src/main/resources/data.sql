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







