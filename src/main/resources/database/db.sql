-- DROP DATABASE IF EXISTS itss;

-- Tạo cơ sở dữ liệu nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS itss;
USE itss;

-- Bảng Template
CREATE TABLE Template (
    TemplateID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NULL,
    favorite BOOLEAN DEFAULT FALSE,
    tag VARCHAR(255) NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng User (Thay vì phân biệt student và teacher, ta dùng is_admin)
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN DEFAULT FALSE,  -- Thêm trường is_admin để phân biệt người dùng và admin
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Manage (Quản lý các Admin)
CREATE TABLE Manage (
    AdminID INT NOT NULL,
    TemplateID INT NOT NULL,
    ManageDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (AdminID, TemplateID),
    FOREIGN KEY (AdminID) REFERENCES User(UserID) ON DELETE CASCADE,  -- Thay AdminID bằng UserID từ bảng User
    FOREIGN KEY (TemplateID) REFERENCES Template(TemplateID) ON DELETE CASCADE
);

-- Bảng UserTemplate
CREATE TABLE UserTemplate (
    UserID INT NOT NULL,
    TemplateID INT NOT NULL,
    is_favorite BOOLEAN DEFAULT FALSE,
    usage_count INT DEFAULT 0, -- Số lần sử dụng template
    last_used DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (UserID, TemplateID),
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (TemplateID) REFERENCES Template(TemplateID) ON DELETE CASCADE
);

-- Bảng Language
CREATE TABLE Language (
    LanguageID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL, -- Tên ngôn ngữ (ví dụ: "Tiếng Việt", "Tiếng Nhật")
    code VARCHAR(10) NOT NULL -- Mã ngôn ngữ (ví dụ: "vi", "ja")
);

-- Bảng TemplateLanguage (Liên kết giữa Template và Language)
CREATE TABLE TemplateLanguage (
    TemplateID INT NOT NULL,
    LanguageID INT NOT NULL,
    translated_title VARCHAR(255) NOT NULL, -- Tiêu đề của template theo ngôn ngữ
    translated_description TEXT NULL, -- Mô tả của template theo ngôn ngữ
    PRIMARY KEY (TemplateID, LanguageID),
    FOREIGN KEY (TemplateID) REFERENCES Template(TemplateID) ON DELETE CASCADE,
    FOREIGN KEY (LanguageID) REFERENCES Language(LanguageID) ON DELETE CASCADE
);

-- Thêm dữ liệu vào bảng Language
INSERT INTO Language (name, code) VALUES
('Tiếng Việt', 'vi'),
('Tiếng Nhật', 'ja'),
('Tiếng Anh', 'en');

-- Thêm dữ liệu vào bảng Template cho các tình huống Hourensou
INSERT INTO Template (title, description, favorite, tag) VALUES
('Template xin nghỉ học', 'Đây là template dành cho việc xin nghỉ học hoặc báo cáo nghỉ học tạm thời.', TRUE, 'hourensou, xin nghỉ học, báo cáo'),
('Template báo cáo kéo dài thời gian làm bài', 'Template này dùng để báo cáo kéo dài thời gian làm bài kiểm tra, bài tập hoặc dự án.', FALSE, 'hourensou, kéo dài thời gian, báo cáo'),
('Template báo cáo đi muộn', 'Đây là template dùng để báo cáo việc đi muộn, lý do đi muộn và thời gian dự kiến đến.', TRUE, 'hourensou, đi muộn, báo cáo');

-- Thêm 20 admin vào bảng User
INSERT INTO User (username, email, password, is_admin) VALUES
('admin_01', 'admin1@gmail.com', 'adminpassword123', TRUE),
('admin_02', 'admin2@gmail.com', 'adminpassword456', TRUE),
('admin_03', 'admin3@gmail.com', 'adminpassword789', TRUE),
('admin_04', 'admin4@gmail.com', 'adminpassword101112', TRUE),
('admin_05', 'admin5@gmail.com', 'adminpassword131415', TRUE),
('admin_06', 'admin6@gmail.com', 'adminpassword161718', TRUE),
('admin_07', 'admin7@gmail.com', 'adminpassword192021', TRUE),
('admin_08', 'admin8@gmail.com', 'adminpassword222324', TRUE),
('admin_09', 'admin9@gmail.com', 'adminpassword252627', TRUE),
('admin_10', 'admin10@gmail.com', 'adminpassword282930', TRUE),
('admin_11', 'admin11@gmail.com', 'adminpassword313233', TRUE),
('admin_12', 'admin12@gmail.com', 'adminpassword343536', TRUE),
('admin_13', 'admin13@gmail.com', 'adminpassword373839', TRUE),
('admin_14', 'admin14@gmail.com', 'adminpassword404142', TRUE),
('admin_15', 'admin15@gmail.com', 'adminpassword434445', TRUE),
('admin_16', 'admin16@gmail.com', 'adminpassword464748', TRUE),
('admin_17', 'admin17@gmail.com', 'adminpassword495051', TRUE),
('admin_18', 'admin18@gmail.com', 'adminpassword525354', TRUE),
('admin_19', 'admin19@gmail.com', 'adminpassword555657', TRUE),
('admin_20', 'admin20@gmail.com', 'adminpassword585960', TRUE);

-- Thêm 20 user vào bảng User
INSERT INTO User (username, email, password, is_admin) VALUES
('user_01', 'user1@gmail.com', 'userpassword123', FALSE),
('user_02', 'user2@gmail.com', 'userpassword456', FALSE),
('user_03', 'user3@gmail.com', 'userpassword789', FALSE),
('user_04', 'user4@gmail.com', 'userpassword101112', FALSE),
('user_05', 'user5@gmail.com', 'userpassword131415', FALSE),
('user_06', 'user6@gmail.com', 'userpassword161718', FALSE),
('user_07', 'user7@gmail.com', 'userpassword192021', FALSE),
('user_08', 'user8@gmail.com', 'userpassword222324', FALSE),
('user_09', 'user9@gmail.com', 'userpassword252627', FALSE),
('user_10', 'user10@gmail.com', 'userpassword282930', FALSE),
('user_11', 'user11@gmail.com', 'userpassword313233', FALSE),
('user_12', 'user12@gmail.com', 'userpassword343536', FALSE),
('user_13', 'user13@gmail.com', 'userpassword373839', FALSE),
('user_14', 'user14@gmail.com', 'userpassword404142', FALSE),
('user_15', 'user15@gmail.com', 'userpassword434445', FALSE),
('user_16', 'user16@gmail.com', 'userpassword464748', FALSE),
('user_17', 'user17@gmail.com', 'userpassword495051', FALSE),
('user_18', 'user18@gmail.com', 'userpassword525354', FALSE),
('user_19', 'user19@gmail.com', 'userpassword555657', FALSE),
('user_20', 'user20@gmail.com', 'userpassword585960', FALSE);


-- Thêm dữ liệu vào bảng Manage (tham chiếu đến bảng User thay vì Admin)
INSERT INTO Manage (AdminID, TemplateID, ManageDate) VALUES
(4, 1, '2024-11-27 10:00:00'),  -- admin_01
(5, 2, '2024-11-27 11:00:00'),  -- admin_02
(4, 3, '2024-11-27 12:00:00');  -- admin_01

-- Thêm dữ liệu vào bảng UserTemplate
INSERT INTO UserTemplate (UserID, TemplateID, is_favorite, usage_count, last_used) VALUES
(1, 1, TRUE, 3, '2024-11-27 10:30:00'),
(2, 2, FALSE, 1, '2024-11-27 11:30:00'),
(3, 3, TRUE, 2, '2024-11-27 12:30:00');

-- Thêm dữ liệu vào bảng TemplateLanguage để phản ánh nội dung của các template
INSERT INTO TemplateLanguage (TemplateID, LanguageID, translated_title, translated_description) VALUES
(1, 1, 'Template xin nghỉ học', 'Đây là mẫu template dùng để xin nghỉ học hoặc báo cáo nghỉ học tạm thời.'),
(1, 2, '授業欠席申請テンプレート', 'これは授業を欠席するための申請または一時的な欠席を報告するためのテンプレートです。'),
(2, 1, 'Template báo cáo kéo dài thời gian làm bài', 'Template này dành cho việc báo cáo kéo dài thời gian làm bài kiểm tra, bài tập hoặc dự án.'),
(2, 2, '試験の提出期限延長報告テンプレート', '試験、課題、またはプロジェクトの提出期限延長に関する報告のためのテンプレートです。'),
(3, 1, 'Template báo cáo đi muộn', 'Đây là mẫu template dùng để báo cáo đi muộn, lý do đi muộn và thời gian dự kiến đến lớp.'),
(3, 2, '遅刻報告テンプレート', '遅刻の報告、遅刻理由、および到着予定時間のためのテンプレートです。');


select * from Template;