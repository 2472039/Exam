;             
CREATE USER IF NOT EXISTS SA SALT '8a122e18e060f2d6' HASH '1fb594177c1a0be95766ccf50ba1954976fc83dfd304c5346cbb09a217d6b59a' ADMIN;           
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_6D532E13_BBAD_4AA7_B04C_1FC748FA4636 START WITH 477 BELONGS_TO_TABLE;  
CREATE CACHED TABLE PUBLIC.CLASS_NUM(
    SCHOOL_CD CHAR(3) NOT NULL,
    CLASS_NUM VARCHAR(5) NOT NULL
); 
ALTER TABLE PUBLIC.CLASS_NUM ADD CONSTRAINT PUBLIC.CONSTRAINT_A PRIMARY KEY(SCHOOL_CD, CLASS_NUM);            
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.CLASS_NUM;               
INSERT INTO PUBLIC.CLASS_NUM(SCHOOL_CD, CLASS_NUM) VALUES
('tes', '101'),
('tes', '102'),
('tes', '201'),
('tes', '202'); 
CREATE CACHED TABLE PUBLIC.TEST(
    STUDENT_NO VARCHAR(10) NOT NULL,
    SUBJECT_CD CHAR(3) NOT NULL,
    SCHOOL_CD CHAR(10) NOT NULL,
    NO INTEGER NOT NULL,
    POINT INTEGER,
    CLASS_NUM VARCHAR(5)
);        
ALTER TABLE PUBLIC.TEST ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO);
-- 33 +/- SELECT COUNT(*) FROM PUBLIC.TEST;   
INSERT INTO PUBLIC.TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES
('124', 'MAT', 'tes', 2, 90, '102'),
('124', 'SCI', 'tes', 1, 88, '102'),
('124', 'SCI', 'tes', 2, 92, '102'),
('124', 'ENG', 'tes', 1, 78, '102'),
('124', 'ENG', 'tes', 2, 80, '102'),
('124', 'JPN', 'tes', 2, 96, '102'),
('124', 'SOC', 'tes', 1, 85, '102'),
('124', 'SOC', 'tes', 2, 87, '102'),
('125', 'MAT', 'tes', 1, 88, '201'),
('125', 'MAT', 'tes', 2, 91, '201'),
('125', 'SCI', 'tes', 1, 84, '201'),
('125', 'SCI', 'tes', 2, 86, '201'),
('125', 'ENG', 'tes', 1, 79, '201'),
('125', 'ENG', 'tes', 2, 81, '201'),
('125', 'JPN', 'tes', 1, 94, '201'),
('125', 'JPN', 'tes', 2, 95, '201'),
('125', 'SOC', 'tes', 1, 89, '201'),
('125', 'SOC', 'tes', 2, 90, '201'),
('126', 'MAT', 'tes', 1, 87, '201'),
('126', 'MAT', 'tes', 2, 89, '201'),
('126', 'SCI', 'tes', 1, 82, '201'),
('126', 'SCI', 'tes', 2, 85, '201'),
('126', 'ENG', 'tes', 1, 77, '201'),
('126', 'ENG', 'tes', 2, 78, '201'),
('126', 'JPN', 'tes', 1, 93, '201'),
('126', 'JPN', 'tes', 2, 94, '201'),
('126', 'SOC', 'tes', 1, 88, '201'),
('126', 'SOC', 'tes', 2, 89, '201'),
('124', 'JPN', 'tes', 1, 95, '102'),
('444', 'MAT', 'tes', 1, 44, '101'),
('444', 'MAT', 'tes', 2, 44, '101'),
('124', 'MAT', 'tes', 1, 10, '102'),
('123', 'MAT', 'tes', 1, 20, '102'); 
CREATE CACHED TABLE PUBLIC.SCHOOL(
    CD CHAR(3) NOT NULL,
    NAME VARCHAR(20)
);        
ALTER TABLE PUBLIC.SCHOOL ADD CONSTRAINT PUBLIC.CONSTRAINT_9 PRIMARY KEY(CD); 
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.SCHOOL;  
INSERT INTO PUBLIC.SCHOOL(CD, NAME) VALUES
('tes', STRINGDECODE('\u30c6\u30b9\u30c8\u6821'));
CREATE CACHED TABLE PUBLIC.TEACHER(
    ID VARCHAR(10) NOT NULL,
    PASSWORD VARCHAR(30),
    NAME VARCHAR(10),
    SCHOOL_CD CHAR(3)
);
ALTER TABLE PUBLIC.TEACHER ADD CONSTRAINT PUBLIC.CONSTRAINT_D PRIMARY KEY(ID);
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.TEACHER; 
INSERT INTO PUBLIC.TEACHER(ID, PASSWORD, NAME, SCHOOL_CD) VALUES
('admin1', 'password', STRINGDECODE('\u7ba1\u7406\u80051'), 'tes');         
CREATE CACHED TABLE PUBLIC.STUDENT(
    NO VARCHAR(10) DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_6D532E13_BBAD_4AA7_B04C_1FC748FA4636) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_6D532E13_BBAD_4AA7_B04C_1FC748FA4636,
    NAME VARCHAR(10),
    ENT_YEAR INTEGER,
    CLASS_NUM CHAR(3),
    IS_ATTEND BOOLEAN,
    SCHOOL_CD CHAR(3)
);          
ALTER TABLE PUBLIC.STUDENT ADD CONSTRAINT PUBLIC.CONSTRAINT_B PRIMARY KEY(NO);
-- 25 +/- SELECT COUNT(*) FROM PUBLIC.STUDENT;
INSERT INTO PUBLIC.STUDENT(NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES
('124', 'bbb', 2024, '102', FALSE, 'tes'),
('125', 'ccc', 2025, '201', TRUE, 'tes'),
('126', 'ddd', 2025, '202', FALSE, 'tes'),
('127', STRINGDECODE('\u5927\u539f\u592a\u90ce'), 2025, '202', FALSE, 'tes'),
('128', 'eeee', 2023, '101', TRUE, 'tes'),
('129', 'ffff', 2023, '102', TRUE, 'tes'),
('130', 'gggg', 2024, '201', FALSE, 'tes'),
('131', 'hhhh', 2024, '202', TRUE, 'tes'),
('132', 'iiii', 2025, '101', FALSE, 'tes'),
('133', 'jjjj', 2025, '102', TRUE, 'tes'),
('134', 'kkkk', 2023, '201', FALSE, 'tes'),
('135', 'llll', 2023, '202', TRUE, 'tes'),
('136', 'mmmm', 2024, '101', FALSE, 'tes'),
('137', 'nnnn', 2024, '102', TRUE, 'tes'),
('138', 'oooo', 2025, '201', FALSE, 'tes'),
('139', 'pppp', 2025, '202', TRUE, 'tes'),
('140', 'qqqq', 2023, '101', FALSE, 'tes'),
('141', 'rrrr', 2023, '102', TRUE, 'tes'),
('142', 'ssss', 2024, '201', TRUE, 'tes'),
('143', 'tttt', 2024, '202', FALSE, 'tes'),
('445', 'b', 2024, '101', TRUE, 'tes'),
('446', 'b', 2024, '101', TRUE, 'tes'),
('444', 'hara', 2015, '101', TRUE, 'tes'),
('447', 'oohara', 2015, '101', FALSE, 'tes'),
('123', 'aaa', 2024, '102', FALSE, 'tes');      
CREATE CACHED TABLE PUBLIC.SUBJECT(
    SCHOOL_CD CHAR(3) NOT NULL,
    CD CHAR(3) NOT NULL,
    NAME VARCHAR(20)
);      
ALTER TABLE PUBLIC.SUBJECT ADD CONSTRAINT PUBLIC.CONSTRAINT_BB PRIMARY KEY(SCHOOL_CD, CD);    
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.SUBJECT; 
INSERT INTO PUBLIC.SUBJECT(SCHOOL_CD, CD, NAME) VALUES
('tes', 'MAT', STRINGDECODE('\u6570\u5b66')),
('tes', 'SCI', STRINGDECODE('\u79d1\u5b66')),
('tes', 'JPN', STRINGDECODE('\u56fd\u8a9e')),
('tes', 'SOC', STRINGDECODE('\u793e\u4f1a')),
('tes', 'tes', 'tes'),
('tes', 'ENG', STRINGDECODE('\u82f1\u8a9e'));     
