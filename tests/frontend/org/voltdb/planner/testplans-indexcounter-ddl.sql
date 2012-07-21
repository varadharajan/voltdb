CREATE TABLE T1 (
	ID INTEGER NOT NULL,
	POINTS INTEGER NOT NULL,
	AGE TINYINT NOT NULL,
	PRIMARY KEY (ID)
);
create index idx_1_tree on T1 (POINTS);

CREATE TABLE T2 (
	ID INTEGER NOT NULL,
	USERNAME VARCHAR(10) NOT NULL,
	POINTS INTEGER NOT NULL,
	AGE TINYINT NOT NULL,
	PRIMARY KEY (ID)
);
create unique index idx_2_tree on T2 (USERNAME,POINTS);