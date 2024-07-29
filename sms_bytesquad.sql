-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: sms_bytesquad
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `branchs`
--

DROP TABLE IF EXISTS branchs;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE branchs (
  branch_id int NOT NULL AUTO_INCREMENT,
  branch_type varchar(10) NOT NULL,
  branch_name varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  manager_name varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  address varchar(255) NOT NULL,
  phone varchar(20) NOT NULL,
  email varchar(50) NOT NULL,
  branch_state int NOT NULL,
  username varchar(50) NOT NULL,
  PRIMARY KEY (branch_id),
  KEY fk_username (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES employees (username)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branchs`
--

LOCK TABLES branchs WRITE;
/*!40000 ALTER TABLE branchs DISABLE KEYS */;
INSERT INTO branchs VALUES (6,'Retail','Branch A','Alice Johnson','111 North St','111-222-3333','branchA@example.com',1,'john_doe'),(7,'Retail','DEMOOO1111111','Alice Johnson','111 North St','111-222-3333','branchA@example.com',1,'john_doe'),(8,'Retail','Branch C','Carol Williams','333 East St','333-444-5555','branchC@example.com',1,'michael_jones'),(9,'Warehouse','Branch D','David Brown','444 West St','444-555-6666','branchD@example.com',1,'emily_davis'),(10,'Retail','Branch E','Eve Davis','555 Central St','555-666-7777','branchE@example.com',1,'daniel_brown');
/*!40000 ALTER TABLE branchs ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS categories;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE categories (
  category_id int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` text,
  PRIMARY KEY (category_id)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES categories WRITE;
/*!40000 ALTER TABLE categories DISABLE KEYS */;
INSERT INTO categories VALUES (11,'Vegetables','Rau sạch và các loại hoa quả cung cấp vitamin và các chất dinh dưỡng cho cơ thể của bạn'),(12,'Fruits','Trái cây cung cấp các chất dinh dưỡng quan trọng cho sức khỏe và duy trì một cơ thể khỏe mạnh'),(13,'Juices','Bổ sung khẩu phần cho rau củ bị thiếu'),(14,'Dried','Trái cây sấy khô giàu kali, sắt, folate, canxi và magiê, đây là những chất dinh dưỡng thiết yếu giúp tăng cường khả năng miễn dịch');
/*!40000 ALTER TABLE categories ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS customers;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE customers (
  customer_id int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  email varchar(50) NOT NULL,
  phone varchar(20) DEFAULT NULL,
  address text,
  last_update date DEFAULT NULL,
  registration_date date DEFAULT NULL,
  PRIMARY KEY (customer_id),
  UNIQUE KEY email (email)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES customers WRITE;
/*!40000 ALTER TABLE customers DISABLE KEYS */;
INSERT INTO customers VALUES (1,'Customer One','password1','customer1@example.com','111-222-3333','Address 1','2024-07-01','2024-06-15'),(2,'Customer Two','password2','customer2@example.com','222-333-4444','Address 2','2024-07-01','2024-06-16'),(3,'Customer Three','password3','customer3@example.com','333-444-5555','Address 3','2024-07-01','2024-06-17'),(4,'Customer Four','password4','customer4@example.com','444-555-6666','Address 4','2024-07-01','2024-06-18'),(5,'Customer Five','password5','customer5@example.com','555-666-7777','Address 5','2024-07-01','2024-06-19');
/*!40000 ALTER TABLE customers ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_roles`
--

DROP TABLE IF EXISTS employee_roles;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE employee_roles (
  id int NOT NULL AUTO_INCREMENT,
  role_id varchar(20) NOT NULL,
  username varchar(50) NOT NULL,
  PRIMARY KEY (id),
  KEY role_id (role_id),
  KEY username (username),
  CONSTRAINT employee_roles_ibfk_1 FOREIGN KEY (role_id) REFERENCES roles (role_id),
  CONSTRAINT employee_roles_ibfk_2 FOREIGN KEY (username) REFERENCES employees (username)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_roles`
--

LOCK TABLES employee_roles WRITE;
/*!40000 ALTER TABLE employee_roles DISABLE KEYS */;
INSERT INTO employee_roles VALUES (1,'ROLE_ADMIN','john_doe'),(2,'ROLE_USER','jane_smith'),(3,'ROLE_MANAGER','michael_jones'),(4,'ROLE_USER','emily_davis'),(5,'ROLE_MANAGER','daniel_brown'),(6,'ROLE_ADMIN','olivia_wilson');
/*!40000 ALTER TABLE employee_roles ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS employees;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE employees (
  username varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  fullname varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  email varchar(50) NOT NULL,
  phone varchar(20) DEFAULT NULL,
  registration_date datetime(6) DEFAULT NULL,
  birth_date datetime(6) DEFAULT NULL,
  address varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  salary decimal(10,2) DEFAULT NULL,
  photo_path varchar(255) DEFAULT NULL,
  last_update timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (username),
  UNIQUE KEY email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES employees WRITE;
/*!40000 ALTER TABLE employees DISABLE KEYS */;
INSERT INTO employees VALUES ('daniel_brown','123','Daniel Brown','daniel.brown@example.com','567-890-1234','2024-07-01 00:00:00.000000','1991-05-10 00:00:00.000000','202 Maple St, Anytown',53000.00,'/photos/daniel_brown.jpg','2024-07-01 01:05:23'),('emily_davis','123','Emily Davis','emily.davis@example.com','456-789-0123','2024-07-01 00:00:00.000000','1988-04-30 00:00:00.000000','101 Pine St, Anytown',52000.00,'/photos/emily_davis.jpg','2024-07-01 01:05:23'),('jane_smith','123','Jane Smith','jane.smith@example.com','234-567-8901','2024-07-01 00:00:00.000000','1992-02-20 00:00:00.000000','456 Elm St, Anytown',55000.00,'/photos/jane_smith.jpg','2024-07-01 01:05:23'),('john_doe','123','John Doe','john.doe@example.com','123-456-7890','2024-07-01 00:00:00.000000','1990-01-15 00:00:00.000000','123 Main St, Anytown',50000.00,'/photos/john_doe.jpg','2024-07-01 01:05:23'),('michael_jones','123','Michael Jones','michael.jones@example.com','345-678-9012','2024-07-01 00:00:00.000000','1985-03-25 00:00:00.000000','789 Oak St, Anytown',60000.00,'/photos/michael_jones.jpg','2024-07-01 01:05:23'),('olivia_wilson','123','Olivia Wilson','olivia.wilson@example.com','678-901-2345','2024-07-01 00:00:00.000000','1993-06-15 00:00:00.000000','303 Cedar St, Anytown',56000.00,'/photos/olivia_wilson.jpg','2024-07-01 01:05:23');
/*!40000 ALTER TABLE employees ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_transactions`
--

DROP TABLE IF EXISTS inventory_transactions;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE inventory_transactions (
  lot_id int NOT NULL AUTO_INCREMENT,
  transaction_type varchar(10) NOT NULL,
  transaction_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  receiver varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  creator varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  quantity_received int NOT NULL,
  quantity_shipped int NOT NULL,
  receiving_location varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  inventory_shrinkage int NOT NULL,
  transaction_status int NOT NULL,
  note text,
  branch_id int DEFAULT NULL,
  PRIMARY KEY (lot_id),
  KEY branch_id (branch_id),
  CONSTRAINT inventory_transactions_ibfk_1 FOREIGN KEY (branch_id) REFERENCES branchs (branch_id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_transactions`
--

LOCK TABLES inventory_transactions WRITE;
/*!40000 ALTER TABLE inventory_transactions DISABLE KEYS */;
INSERT INTO inventory_transactions VALUES (11,'IN','2024-07-07 03:10:43','Receiver 1','Creator 1',100,50,'Location 1',0,1,'Note 1',6),(12,'OUT','2024-07-07 03:10:43','Receiver 2','Creator 2',200,100,'Location 2',1,1,'Note 2',7),(13,'IN','2024-07-07 03:10:43','Receiver 3','Creator 3',300,150,'Location 3',2,1,'Note 3',8),(14,'OUT','2024-07-07 03:10:43','Receiver 4','Creator 4',400,200,'Location 4',3,1,'Note 4',9),(15,'IN','2024-07-07 03:10:43','Receiver 5','Creator 5',500,250,'Location 5',4,1,'Note 5',10);
/*!40000 ALTER TABLE inventory_transactions ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS order_details;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE order_details (
  order_detail_id int NOT NULL AUTO_INCREMENT,
  quantity int NOT NULL,
  price decimal(10,2) NOT NULL,
  product_id int NOT NULL,
  order_id int NOT NULL,
  PRIMARY KEY (order_detail_id),
  KEY product_id (product_id),
  KEY order_id (order_id),
  CONSTRAINT order_details_ibfk_1 FOREIGN KEY (product_id) REFERENCES products (product_id),
  CONSTRAINT order_details_ibfk_2 FOREIGN KEY (order_id) REFERENCES orders (order_id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES order_details WRITE;
/*!40000 ALTER TABLE order_details DISABLE KEYS */;
INSERT INTO order_details VALUES (11,1,699.99,6,36),(12,1,89.99,7,37),(13,1,19.99,8,38),(14,1,14.99,9,39),(15,1,29.99,10,40);
/*!40000 ALTER TABLE order_details ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS orders;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE orders (
  order_id int NOT NULL AUTO_INCREMENT,
  order_type varchar(10) NOT NULL,
  seller varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  order_date date NOT NULL,
  required_date date DEFAULT NULL,
  total_price decimal(10,2) NOT NULL,
  ship_address varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  billing_address varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  order_status int NOT NULL,
  comments text,
  branch_id int NOT NULL,
  customer_id int NOT NULL,
  PRIMARY KEY (order_id),
  KEY fk_branch_id (branch_id),
  KEY fk_customer_id (customer_id),
  CONSTRAINT fk_branch_id FOREIGN KEY (branch_id) REFERENCES branchs (branch_id),
  CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES orders WRITE;
/*!40000 ALTER TABLE orders DISABLE KEYS */;
INSERT INTO orders VALUES (36,'Online','Seller A','2024-06-20','2024-06-25',799.99,'Ship Address 1','Bill Address 1',1,'Deliver by end of month.',6,1),(37,'In-store','Seller B','2024-06-21','2024-06-26',99.99,'Ship Address 2','Bill Address 2',1,'Pick up at store.',7,2),(38,'Online','Seller C','2024-06-22','2024-06-27',29.99,'Ship Address 3','Bill Address 3',1,'Handle with care.',8,3),(39,'In-store','Seller D','2024-06-23','2024-06-28',19.99,'Ship Address 4','Bill Address 4',1,'Gift wrap.',9,4),(40,'Online','Seller E','2024-06-24','2024-06-29',49.99,'Ship Address 5','Bill Address 5',1,'Express delivery.',10,5);
/*!40000 ALTER TABLE orders ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS payments;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE payments (
  payment_id int NOT NULL AUTO_INCREMENT,
  order_id int NOT NULL,
  payment_method varchar(50) NOT NULL,
  payment_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  amount decimal(10,2) NOT NULL,
  payment_status int NOT NULL,
  PRIMARY KEY (payment_id),
  KEY order_id (order_id),
  CONSTRAINT payments_ibfk_1 FOREIGN KEY (order_id) REFERENCES orders (order_id)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES payments WRITE;
/*!40000 ALTER TABLE payments DISABLE KEYS */;
INSERT INTO payments VALUES (26,36,'Credit Card','2024-07-07 03:06:52',799.99,1),(27,37,'Cash','2024-07-07 03:06:52',99.99,1),(28,38,'Credit Card','2024-07-07 03:06:52',29.99,1),(29,39,'Cash','2024-07-07 03:06:52',19.99,1),(30,40,'Credit Card','2024-07-07 03:06:52',49.99,1);
/*!40000 ALTER TABLE payments ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS product_images;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE product_images (
  id int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  product_id int NOT NULL,
  PRIMARY KEY (id),
  KEY product_id (product_id),
  CONSTRAINT product_images_ibfk_1 FOREIGN KEY (product_id) REFERENCES products (product_id)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES product_images WRITE;
/*!40000 ALTER TABLE product_images DISABLE KEYS */;
INSERT INTO product_images VALUES (28,'best-product-1.jpg',6),(29,'best-product-2.jpg',7),(30,'best-product-3.jpg',8),(31,'best-product-4.jpg',9),(32,'best-product-5.jpg',10),(33,'best-product-6.jpg',11),(34,'featur-1.jpg',12),(35,'featur-2.jpg',13),(36,'featur-3.jpg',14),(37,'fruite-item-1.jpg',8),(38,'fruite-item-2.jpg',9),(39,'fruite-item-3.jpg',10),(40,'fruite-item-4.jpg',11),(41,'fruite-item-5.jpg',12),(42,'fruite-item-6.jpg',6),(43,'single-item.jpg',7),(44,'vegetable-item-1.jpg',13),(45,'vegetable-item-2.jpg',14),(46,'vegetable-item-3.jpg',10),(47,'vegetable-item-4.jpg',11),(48,'vegetable-item-5.jpg',12),(49,'vegetable-item-6.jpg',6);
/*!40000 ALTER TABLE product_images ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS products;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE products (
  product_id int NOT NULL AUTO_INCREMENT,
  category_id int NOT NULL,
  supplier_id int NOT NULL,
  product_name varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  quantity int NOT NULL,
  unit_price decimal(10,2) NOT NULL,
  product_status int NOT NULL,
  discount decimal(5,2) NOT NULL,
  `description` text,
  PRIMARY KEY (product_id),
  KEY category_id (category_id),
  KEY supplier_id (supplier_id),
  CONSTRAINT products_ibfk_1 FOREIGN KEY (category_id) REFERENCES categories (category_id),
  CONSTRAINT products_ibfk_2 FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES products WRITE;
/*!40000 ALTER TABLE products DISABLE KEYS */;
INSERT INTO products VALUES (6,11,1,'Grapes',50,699.99,1,0.10,'Fresh and juicy grapes perfect for snacking and adding to salads.'),(7,12,2,'Raspberries',100,89.99,1,0.15,'Delicious and sweet raspberries, rich in antioxidants and vitamins.'),(8,13,3,'Apricots',200,19.99,1,0.05,'Sweet and tangy apricots, perfect for snacking or adding to desserts.'),(9,14,4,'Banana',150,14.99,1,0.20,'Fresh bananas, great source of potassium and energy.'),(10,11,1,'Oranges',75,29.99,1,0.00,'Juicy and refreshing oranges, packed with vitamin C.'),(11,13,3,'Apple',75,29.99,1,0.00,'Crisp and sweet apples, perfect for a healthy snack.'),(12,12,2,'Bell Pepper',75,29.99,1,0.00,'Fresh and crunchy bell peppers, great for salads and cooking.'),(13,14,4,'Potatoes',75,29.99,1,0.00,'Versatile and nutritious potatoes, essential for many dishes.'),(14,12,2,'Parsley',75,29.99,1,0.00,'Fresh parsley, perfect for garnishing and adding flavor to your dishes.');
/*!40000 ALTER TABLE products ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiving`
--

DROP TABLE IF EXISTS receiving;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE receiving (
  receiving_id int NOT NULL AUTO_INCREMENT,
  quantity int NOT NULL,
  supplier varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  lot_id int NOT NULL,
  product_id int NOT NULL,
  PRIMARY KEY (receiving_id),
  KEY lot_id (lot_id),
  KEY product_id (product_id),
  CONSTRAINT receiving_ibfk_1 FOREIGN KEY (lot_id) REFERENCES inventory_transactions (lot_id),
  CONSTRAINT receiving_ibfk_2 FOREIGN KEY (product_id) REFERENCES products (product_id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiving`
--

LOCK TABLES receiving WRITE;
/*!40000 ALTER TABLE receiving DISABLE KEYS */;
INSERT INTO receiving VALUES (11,100,'Supplier 1',11,6),(12,200,'Supplier 2',12,7),(13,300,'Supplier 3',13,8),(14,400,'Supplier 4',14,9),(15,500,'Supplier 5',15,10);
/*!40000 ALTER TABLE receiving ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS roles;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE roles (
  role_id varchar(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (role_id),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES roles WRITE;
/*!40000 ALTER TABLE roles DISABLE KEYS */;
INSERT INTO roles VALUES ('ROLE_ADMIN','Admin'),('ROLE_MANAGER','Manager'),('ROLE_USER','User');
/*!40000 ALTER TABLE roles ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipments`
--

DROP TABLE IF EXISTS shipments;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE shipments (
  shipment_id int NOT NULL AUTO_INCREMENT,
  order_id int NOT NULL,
  shipment_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  expected_delivery_date timestamp NULL DEFAULT NULL,
  delivery_status int NOT NULL,
  shipping_address text NOT NULL,
  shipping_name varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  shipping_phone varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  billing_address text NOT NULL,
  PRIMARY KEY (shipment_id),
  KEY order_id (order_id),
  CONSTRAINT shipments_ibfk_1 FOREIGN KEY (order_id) REFERENCES orders (order_id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipments`
--

LOCK TABLES shipments WRITE;
/*!40000 ALTER TABLE shipments DISABLE KEYS */;
INSERT INTO shipments VALUES (11,36,'2024-07-07 03:07:04','2024-06-25 05:00:00',1,'Ship Address 1','Alice Johnson','111-222-3333','Bill Address 1'),(12,37,'2024-07-07 03:07:04','2024-06-26 05:00:00',1,'Ship Address 2','Bob Smith','222-333-4444','Bill Address 2'),(13,38,'2024-07-07 03:07:04','2024-06-27 05:00:00',1,'Ship Address 3','Carol Williams','333-444-5555','Bill Address 3'),(14,39,'2024-07-07 03:07:04','2024-06-28 05:00:00',1,'Ship Address 4','David Brown','444-555-6666','Bill Address 4'),(15,40,'2024-07-07 03:07:04','2024-06-29 05:00:00',1,'Ship Address 5','Eve Davis','555-666-7777','Bill Address 5');
/*!40000 ALTER TABLE shipments ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_features`
--

DROP TABLE IF EXISTS site_features;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE site_features (
  id int NOT NULL AUTO_INCREMENT,
  feature_name varchar(50) NOT NULL,
  feature_description varchar(50) NOT NULL,
  icon_html text,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_features`
--

LOCK TABLES site_features WRITE;
/*!40000 ALTER TABLE site_features DISABLE KEYS */;
INSERT INTO site_features VALUES (1,'Free Shipping','Free on order over $300','fas fa-car-side fa-3x text-white'),(2,'Security Payment','100% security payment','fas fa-user-shield fa-3x text-white'),(3,'30 Day Return','30 day money guarantee','fas fa-exchange-alt fa-3x text-white'),(4,'24/7 Support','Support every time fast','fa fa-phone-alt fa-3x text-white');
/*!40000 ALTER TABLE site_features ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS suppliers;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE suppliers (
  supplier_id int NOT NULL AUTO_INCREMENT,
  company_name varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  contact_name varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  contact_title varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  address varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  city varchar(20) NOT NULL,
  country varchar(20) NOT NULL,
  phone varchar(20) NOT NULL,
  PRIMARY KEY (supplier_id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES suppliers WRITE;
/*!40000 ALTER TABLE suppliers DISABLE KEYS */;
INSERT INTO suppliers VALUES (1,'ABC Corporation','John Doe','Sales Manager','123 Main St','New York','USA','123-456-7890'),(2,'XYZ Enterprises','Jane Smith','Purchasing Manager','456 Elm St','Los Angeles','USA','234-567-8901'),(3,'Global Supplies Ltd','Michael Brown','Operations Manager','789 Maple St','Chicago','USA','345-678-9012'),(4,'Tech Solutions','Emily Davis','Tech Support','101 Oak St','San Francisco','USA','456-789-0123'),(5,'World Wide Traders','Daniel Johnson','CEO','202 Pine St','Miami','USA','567-890-1234');
/*!40000 ALTER TABLE suppliers ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-13 10:17:41
