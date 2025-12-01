-- =========================================================
-- 1. REINICIO Y CREACIÓN DE LA BASE DE DATOS
-- =========================================================
DROP DATABASE IF EXISTS sistema_ventas;
CREATE DATABASE sistema_ventas;
USE sistema_ventas;

-- =========================================================
-- 2. CREACIÓN DE TABLAS (ESTRUCTURA FINAL)
-- =========================================================

CREATE TABLE trabajadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ap_paterno VARCHAR(100) NOT NULL,
    ap_materno VARCHAR(100) NOT NULL,
    tipo_doc VARCHAR(20) NOT NULL,
    num_doc VARCHAR(20) UNIQUE NOT NULL, -- DNI único de 8 dígitos
    sueldo DOUBLE NOT NULL,
    rol VARCHAR(20) NOT NULL,            -- 'ADMIN' o 'VENDEDOR'
    horas_extra INT DEFAULT 0
);

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    total DOUBLE NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE CASCADE
);

-- =========================================================
-- 3. POBLADO DE DATOS: TRABAJADORES (TODOS)
-- =========================================================

INSERT INTO trabajadores (nombre, ap_paterno, ap_materno, tipo_doc, num_doc, sueldo, rol, horas_extra) 
VALUES 
-- --- NUEVOS AGREGADOS ---
('Jhonny Rafael', 'Alonzo', 'Rabanal', 'DNI', '76004079', 3500.00, 'ADMIN', 0),
('Jose Luis', 'Alcantara', 'Vergara De Los Santos', 'DNI', '48537114', 1200.00, 'VENDEDOR', 0),
('Dairon Adrian', 'Perez', 'Lopez', 'DNI', '74862345', 1200.00, 'VENDEDOR', 0),

-- --- ANTERIORES (MANTENIDOS) ---
('Carlos Alberto', 'Ruiz', 'Gomez', 'DNI', '45896321', 3000.00, 'ADMIN', 0),
('Ana Maria', 'Lopez', 'Torres', 'DNI', '74125896', 1200.00, 'VENDEDOR', 2),
('Luis Fernando', 'Quispe', 'Mamani', 'DNI', '15935748', 1200.00, 'VENDEDOR', 5),
('Sofia Alejandra', 'Diaz', 'Vega', 'DNI', '85245612', 600.00, 'VENDEDOR', 0);

-- =========================================================
-- 4. POBLADO DE DATOS: PRODUCTOS Y SERVICIOS
-- =========================================================

-- --- CATEGORÍA 1: SERVICIOS DE IMPRENTA Y DIGITAL ---
INSERT INTO productos (nombre, precio, stock) VALUES 
('Copia / Impresión B/N (A4)', 0.20, 5000),
('Copia / Impresión COLOR (A4)', 0.50, 2000),
('Copia / Impresión B/N (A3)', 0.50, 500),
('Copia / Impresión COLOR (A3)', 1.50, 300),
('Copia DNI (Ambas caras)', 0.50, 5000),
('Escaneo de Documento (por hoja)', 1.00, 9999);

-- --- CATEGORÍA 2: ACABADOS (ANILLADOS Y PLASTIFICADOS) ---
INSERT INTO productos (nombre, precio, stock) VALUES 
('Anillado A4 (1-50 hojas)', 3.00, 200),
('Anillado A4 (51-100 hojas)', 4.00, 150),
('Anillado A4 (100+ hojas)', 6.00, 100),
('Espiralado A4 Grueso', 8.00, 50),
('Plastificado Carnet / DNI', 1.50, 300),
('Plastificado A4', 3.00, 100);

-- --- CATEGORÍA 3: ÚTILES DE ESCRITORIO Y LIBRERÍA ---
INSERT INTO productos (nombre, precio, stock) VALUES 
('Lapicero Azul Faber Castell', 1.50, 100),
('Lapicero Rojo Faber Castell', 1.50, 80),
('Lapicero Negro Pilot Gel', 3.50, 40),
('Lápiz Mongol 2B', 1.00, 120),
('Borrador Blanco Grande', 1.00, 150),
('Tajador con depósito', 1.50, 60),
('Resaltador Amarillo', 2.50, 45),
('Resaltador Verde', 2.50, 40),
('Corrector Líquido Tipo Lapicero', 3.50, 30),
('Plumón Pizarra Negro', 3.00, 30),
('Plumón Pizarra Azul', 3.00, 25),
('Caja de Colores 12 unid.', 12.00, 20),
('Tijera Escolar Punta Roma', 2.50, 40),
('Goma en Barra 21g', 3.50, 30),
('Silicona Líquida 250ml', 6.00, 15),
('Cinta Embalaje Transparente', 4.00, 25),
('Grapas 26/6 Cajita', 2.00, 50);

-- --- CATEGORÍA 4: PAPELERÍA Y CUADERNOS ---
INSERT INTO productos (nombre, precio, stock) VALUES 
('Cuaderno A4 Stanford Cuadriculado', 6.50, 50),
('Cuaderno A4 Loro Rayado', 4.50, 60),
('Block Arcoiris (Hojas de color)', 3.00, 40),
('Folder Manilla A4', 0.50, 200),
('Sobre Manilla A4', 0.30, 200),
('Funda Plástica A4', 0.50, 300),
('Paquete Hojas Bond 500', 18.00, 15),
('Papel Lustre (Pliego)', 0.80, 100),
('Cartulina Blanca (Pliego)', 1.00, 80);

-- --- CATEGORÍA 5: BODEGA (BEBIDAS Y SNACKS) ---
INSERT INTO productos (nombre, precio, stock) VALUES 
('Agua San Mateo 600ml', 2.00, 48),
('Gaseosa Coca Cola 500ml', 3.00, 48),
('Gaseosa Inka Cola 500ml', 3.00, 48),
('Sporade', 2.50, 24),
('Galleta Oreo Paquete', 1.50, 50),
('Galleta Soda V', 1.00, 40),
('Sublime Clásico', 2.00, 30),
('Chizitos Bolsita', 1.20, 30),
('Piqueo Snax', 1.50, 20);

-- =========================================================
-- FIN DEL SCRIPT
-- =========================================================