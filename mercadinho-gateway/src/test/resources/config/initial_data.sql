CREATE TABLE usuario_perm (id INTEGER NOT NULL, email VARCHAR(1000) NOT NULL, role VARCHAR(50) NOT NULL, CONSTRAINT pc_usuario_perm_pk PRIMARY KEY (id));
CREATE TABLE usuario ( id INTEGER NOT NULL, fl_habilitado BOOLEAN NOT NULL, nome VARCHAR(255) NOT NULL,  email VARCHAR(1000) NOT NULL, senha VARCHAR(255) NOT NULL, CONSTRAINT pc_usuario_pk PRIMARY KEY (id));
INSERT INTO usuario(id, nome, senha, email, fl_habilitado) VALUES (1, 'marcelo', 'marcelo', 'marcelo@com', true);
INSERT INTO usuario_perm(id, email, role) VALUES (1, 'marcelo@com', 'USER');
