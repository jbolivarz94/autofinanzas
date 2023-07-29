CREATE TABLE IF NOT EXISTS usuario (
    id INT NOT NULL AUTO_INCREMENT,
    identificacion VARCHAR2(50),
    nombre VARCHAR2(150),
    apellido VARCHAR2(150),
    email VARCHAR2(200),
    telefono VARCHAR2(50),
    PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS cuenta (
    id INT NOT NULL AUTO_INCREMENT,
    id_usuario INT,
    numero VARCHAR2(150),
    tipo VARCHAR2(100),
    monto_inicial NUMERIC(8,2),
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id));

CREATE TABLE IF NOT EXISTS categoria(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR2(100),
    descripcion VARCHAR2(200),
    PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS transaccion(
    id INT NOT NULL AUTO_INCREMENT,
    id_cuenta INT,
    id_categoria INT,
    fecha DATE,
    descripcion VARCHAR2(200),
    monto NUMERIC(8,2),
    FOREIGN KEY (id_cuenta) REFERENCES cuenta(id),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id));
