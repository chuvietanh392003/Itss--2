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

-- Bảng User
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('student', 'teacher') NOT NULL, -- Phân biệt vai trò giữa sinh viên và giáo viên
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Admin
CREATE TABLE Admin (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Manage
CREATE TABLE Manage (
    AdminID INT NOT NULL,
    TemplateID INT NOT NULL,
    ManageDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (AdminID, TemplateID),
    FOREIGN KEY (AdminID) REFERENCES Admin(AdminID) ON DELETE CASCADE,
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

-- Thêm dữ liệu vào bảng Template
INSERT INTO Template (title, description, favorite, tag) VALUES
('Template 1', 'Đây là template dành cho sinh viên học tiếng Nhật.', TRUE, 'education, japanese'),
('Template 2', 'Template dành cho giáo viên Nhật Bản dùng trong việc giảng dạy.', FALSE, 'teacher, lesson'),
('Template 3', 'Một mẫu template cho bài học ngôn ngữ', TRUE, 'language, teaching');

-- Thêm dữ liệu vào bảng User
INSERT INTO User (username, email, password, role) VALUES
('student_01', 'student01@gmail.com', 'password123', 'student'),
('teacher_01', 'teacher01@gmail.com', 'password456', 'teacher'),
('student_02', 'student02@gmail.com', 'password789', 'student');

-- Thêm dữ liệu vào bảng Admin
INSERT INTO Admin (username, password) VALUES
('admin_01', 'adminpassword123'),
('admin_02', 'adminpassword456');

-- Thêm dữ liệu vào bảng Manage
INSERT INTO Manage (AdminID, TemplateID, ManageDate) VALUES
(1, 1, '2024-11-27 10:00:00'),
(2, 2, '2024-11-27 11:00:00'),
(1, 3, '2024-11-27 12:00:00');

-- Thêm dữ liệu vào bảng UserTemplate
INSERT INTO UserTemplate (UserID, TemplateID, is_favorite, usage_count, last_used) VALUES
(1, 1, TRUE, 3, '2024-11-27 10:30:00'),
(2, 2, FALSE, 1, '2024-11-27 11:30:00'),
(3, 3, TRUE, 2, '2024-11-27 12:30:00');

-- Thêm dữ liệu vào bảng TemplateLanguage
INSERT INTO TemplateLanguage (TemplateID, LanguageID, translated_title, translated_description) VALUES
(1, 1, 'Template dành cho học sinh', 'Đây là mẫu template dành cho sinh viên học tiếng Nhật.'),
(1, 2, '日本語の学生向けテンプレート', '日本語を学ぶ学生のためのテンプレートです。'),
(2, 1, 'Template dành cho giáo viên', 'Template này dành cho giáo viên giảng dạy môn học cho học sinh.'),
(3, 2, '言語学習テンプレート', '言語学習のための素晴らしいテンプレートです。');
