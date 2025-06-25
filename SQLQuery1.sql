USE db_springboot_crud;
GO

CREATE TABLE products (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(255) NOT NULL,
    price DECIMAL(10, 2)
);
GO