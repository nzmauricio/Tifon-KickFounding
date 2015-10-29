
create table Usuario(
    id int not null auto_increment,
    password varchar(30) not null,
    nombre varchar(39) not null,
    email varchar(30) not null,
    primary key (id)
);

create table Pais(
    id int not null auto_increment,
    nombre varchar(30) not null,
    idUsuario int not null,
    primary key(id)
);

create table Proyecto(
    id int not null auto_increment,
    titulo varchar(40) not null,
    imagen varchar(100),
    descripcion varchar(200) not null,
    plazoFinanciamiento date not null,
    meta int not null,
    rutaVideo varchar(300),
    estado int not null,
    idCategoria int not null,
    idUsuario int not null,
    primary key (id)
);

create table Categoria(
    id int not null auto_increment,
    nombre varchar(30) not null,
    descripcion varchar(300) not null,
    primary key(id)
);

create table Evaluacion(
    id int not null auto_increment,
    puntaje int not null,
    idUduario int not null,
    idProyecto int not null,
    primary key(id)
);

create table Donacion(
    id int not null auto_increment,
    monto real not null,
    idProyecto int not null,
    idusuario int not null,
    primary key(id)
);