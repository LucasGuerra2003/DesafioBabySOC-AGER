CREATE TABLE exame (rowid bigint auto_increment, nm_exame VARCHAR(255));
ALTER TABLE exame ADD PRIMARY KEY (rowid);
CREATE TABLE funcionario (rowid bigint auto_increment, nm_funcionario VARCHAR(255));
ALTER TABLE funcionario ADD PRIMARY KEY (rowid);
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');
INSERT INTO funcionario (nm_funcionario) VALUES ('José da Silva'), ('Roberta Tarantina'), ('Lucas Duarte'), ('Gabriel Pato'),('Jackeline Guerra');
CREATE TABLE funcionario_exame (
    rowid bigint auto_increment,
    funcionario_id bigint,
    exame_id bigint,
    data_realizacao DATE,
    PRIMARY KEY (rowid),
    FOREIGN KEY (funcionario_id) REFERENCES funcionario(rowid) ON DELETE CASCADE,
    FOREIGN KEY (exame_id) REFERENCES exame(rowid),
    UNIQUE (funcionario_id, exame_id, data_realizacao)
);
INSERT INTO funcionario_exame (funcionario_id, exame_id, data_realizacao) 
	VALUES (1,1,'2024-01-08'),(2,3,'2024-05-08'),(4,2,'2024-04-08'),(3,4,'2024-02-08')
	,(2,1,'2024-03-08'),(2,4,'2024-02-08'),(2,2,'2024-06-08');

