CREATE DATABASE testTemp;

-- 部门表
# ************************************************
CREATE TABLE dept(
	deptno INT,
	dname VARCHAR(20),
	loc VARCHAR(13)
)

-- 员工表
# ************************************************
CREATE TABLE emp(
	empno INT,
	ename VARCHAR(20),
	job VARCHAR(9),
	mgr INT,
	hiredate DATE,
	sal DOUBLE(7,2),
	comm DOUBLE(7,2),
	deptno INT
)

-- 创建索引（先不要执行）
ALTER TABLE emp ADD PRIMARY KEY (empno);
CREATE INDEX i_emp_ename ON emp(ename);

EXPLAIN SELECT * FROM emp WHERE ename LIKE 'fx%';
SELECT COUNT(*) FROM emp WHERE ename LIKE '%fx%';

SHOW INDEX FROM emp;

-- 工资级别表
# ************************************************
CREATE TABLE salgrade(
	grade INT,
	losal DOUBLE(17,2),
	hisal DOUBLE(17,2)
)

INSERT INTO salgrade VALUES (1,700,1200);
INSERT INTO salgrade VALUES (2,1201,1400);
INSERT INTO salgrade VALUES (3,1401,2000);
INSERT INTO salgrade VALUES (4,2001,3000);
INSERT INTO salgrade VALUES (5,3001,9999);
SHOW GLOBAL STATUS LIKE 'com_insert';

-- 定义一个随机产生字符串的存储函数
# ************************************************
DELIMITER $$
CREATE FUNCTION random_str(n INT) 
RETURNS VARCHAR(100) 
BEGIN 
	DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	DECLARE return_str VARCHAR(100) DEFAULT '';
	DECLARE i INT DEFAULT 0;
	WHILE i<n DO
		SET return_str = CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));
		SET i=i+1;
	END WHILE;
	RETURN return_str;
END$$
DELIMITER ;

SELECT random_str(10);

-- 随机产生部门编号
# ************************************************
DELIMITER$$

CREATE FUNCTION rand_num()
RETURNS INT(5)
BEGIN
	DECLARE i INT DEFAULT 0;
	SET i = FLOOR(10+RAND()*500);
	RETURN i;
END$$
DELIMITER ;

SELECT FLOOR(10+RAND()*500);
SELECT rand_num();

-- 向emp表中插入大量数据
# ************************************************
DELIMITER $$
CREATE PROCEDURE insert_emp(IN START INT(10),IN max_num INT(10))
BEGIN
	DECLARE i INT DEFAULT 0;
	REPEAT
		SET i = i+1;
		INSERT INTO emp VALUES((START+i),random_str(6),'SALESMEN',0001,CURDATE(),2000,400,rand_num());
		UNTIL i =max_num
	END REPEAT;
	COMMIT;
END$$
DELIMITER ;

CALL insert_emp(100001,4000000);

SELECT COUNT(*) FROM emp;

-- 向dept表中插入数据
# ************************************************
DELIMITER $$

CREATE PROCEDURE insert_dept(IN START INT(10),IN max_num INT(10))
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0;
WHILE i<START DO
	INSERT INTO dept VALUES ((START+i),random_str(10),random_str(8));
	SET i = i+1;
END WHILE;
COMMIT;
END $$

DELIMITER ;

CALL insert_dept(100,10);

SELECT * FROM dept;

-- 设置主键
ALTER TABLE dept ADD PRIMARY KEY (deptno);

SHOW KEYS FROM dept;
-- 设置联合索引
ALTER TABLE dept ADD INDEX dept_unite_keys (dname,loc);

SHOW INDEXES FROM dept;

# 索引
-- 创建测试表
CREATE TABLE aaa(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(32) NOT NULL DEFAULT '');
DESC aaa;

SHOW INDEX FROM emp;

SHOW KEYS FROM aaa;

CREATE INDEX aaa_name ON aaa(NAME);

-- 全文索引
-- 创建全文索引表
CREATE TABLE articles (
       id INT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
       title VARCHAR(200),
       body TEXT,
       FULLTEXT (title,body)
     ) ENGINE=MYISAM;
-- 初始化数据
INSERT INTO articles (title,body) VALUES
     ('MySQL Tutorial','DBMS stands for DataBase ...'),
     ('How To Use MySQL Well','After you went through a ...'),
     ('Optimizing MySQL','In this tutorial we will show ...'),
     ('1001 MySQL Tricks','1. Never run mysqld as root. 2. ...'),
     ('MySQL vs. YourSQL','In the following database comparison ...'),
     ('MySQL Security','When configured properly, MySQL ...');

-- 全文索引使用
-- 错误用法：
SELECT * FROM articles WHERE body LIKE '%database%';
-- 证明
EXPLAIN SELECT * FROM articles WHERE body LIKE '%database%';
-- 正确用法
EXPLAIN SELECT * FROM articles WHERE MATCH(title,body) AGAINST('database');

SELECT * FROM aaa;
INSERT INTO aaa VALUES (1,'aaa');
EXPLAIN SELECT * FROM aaa WHERE id = 1;
EXPLAIN SELECT * FROM aaa WHERE NAME = 'bbb';

