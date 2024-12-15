-- Xóa cơ sở dữ liệu nếu đã tồn tại
DROP DATABASE IF EXISTS itss;

-- Tạo cơ sở dữ liệu mới
CREATE DATABASE IF NOT EXISTS itss;

USE itss;

-- Bảng Template
CREATE TABLE Template (
    TemplateID INT AUTO_INCREMENT PRIMARY KEY,  -- ID template, tự động tăng
    title VARCHAR(255) NOT NULL,  -- Tiêu đề template
    description TEXT NULL,  -- Mô tả template
    favorite BOOLEAN DEFAULT FALSE,  -- Trường yêu thích, mặc định là FALSE
    tag VARCHAR(255) NULL,  -- Các tag liên quan đến template
    view_count INT DEFAULT 0,  -- Số lượt xem template, mặc định là 0
    save_count INT DEFAULT 0,  -- Số lượt lưu template, mặc định là 0
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- Thời gian tạo template
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- Thời gian cập nhật template
);

-- Bảng User (Bao gồm cả admin và người dùng thông thường)
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,  -- ID người dùng, tự động tăng
    username VARCHAR(255) UNIQUE NOT NULL,  -- Tên đăng nhập, phải là duy nhất
    email VARCHAR(255) UNIQUE NOT NULL,  -- Địa chỉ email, phải là duy nhất
    password VARCHAR(255) NOT NULL,  -- Mật khẩu
    is_admin BOOLEAN DEFAULT FALSE,  -- Thêm trường is_admin để phân biệt người dùng và admin
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP  -- Thời gian tạo tài khoản
);

-- Bảng Manage (Quản lý các Admin)
CREATE TABLE Manage (
    AdminID INT NOT NULL,  -- ID của Admin
    TemplateID INT NOT NULL,  -- ID của Template
    ManageDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- Ngày quản lý template
    PRIMARY KEY (AdminID, TemplateID),  -- Khóa chính là sự kết hợp của AdminID và TemplateID
    FOREIGN KEY (AdminID) REFERENCES User(UserID) ON DELETE CASCADE,  -- Tham chiếu đến bảng User
    FOREIGN KEY (TemplateID) REFERENCES Template(TemplateID) ON DELETE CASCADE  -- Tham chiếu đến bảng Template
);

-- Bảng UserTemplate (Lưu trữ mối quan hệ giữa người dùng và template)
CREATE TABLE UserTemplate (
    UserID INT NOT NULL,  -- ID người dùng
    TemplateID INT NOT NULL,  -- ID template
    is_favorite BOOLEAN DEFAULT FALSE,  -- Trường yêu thích, mặc định là FALSE
    usage_count INT DEFAULT 0,  -- Số lần sử dụng template
    last_used DATETIME DEFAULT CURRENT_TIMESTAMP,  -- Thời gian sử dụng cuối cùng
    PRIMARY KEY (UserID, TemplateID),  -- Khóa chính là sự kết hợp của UserID và TemplateID
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,  -- Tham chiếu đến bảng User
    FOREIGN KEY (TemplateID) REFERENCES Template(TemplateID) ON DELETE CASCADE  -- Tham chiếu đến bảng Template
);

-- Bảng Language (Lưu trữ thông tin về ngôn ngữ)
CREATE TABLE Language (
    LanguageID INT AUTO_INCREMENT PRIMARY KEY,  -- ID ngôn ngữ, tự động tăng
    name VARCHAR(255) NOT NULL,  -- Tên ngôn ngữ (Ví dụ: "Tiếng Việt", "Tiếng Nhật")
    code VARCHAR(10) NOT NULL  -- Mã ngôn ngữ (Ví dụ: "vi", "ja")
);

-- Bảng TemplateLanguage (Liên kết giữa Template và Language)
CREATE TABLE TemplateLanguage (
    TemplateID INT NOT NULL,  -- ID của template
    LanguageID INT NOT NULL,  -- ID của ngôn ngữ
    translated_title VARCHAR(255) NOT NULL,  -- Tiêu đề của template theo ngôn ngữ
    translated_description TEXT NULL,  -- Mô tả của template theo ngôn ngữ
    PRIMARY KEY (TemplateID, LanguageID),  -- Khóa chính là sự kết hợp của TemplateID và LanguageID
    FOREIGN KEY (TemplateID) REFERENCES Template(TemplateID) ON DELETE CASCADE,  -- Tham chiếu đến bảng Template
    FOREIGN KEY (LanguageID) REFERENCES Language(LanguageID) ON DELETE CASCADE  -- Tham chiếu đến bảng Language
);


-- Tạo bảng TemplateDetail
CREATE TABLE TemplateDetail (
    TemplateDetailID INT AUTO_INCREMENT PRIMARY KEY,  -- ID tự động tăng của TemplateDetail
    TemplateID INT NOT NULL,  -- Tham chiếu đến bảng Template
    template_text TEXT NOT NULL,  -- Nội dung template chi tiết
    template_setsumei TEXT NOT NULL,  -- Giải thích về template
    FOREIGN KEY (TemplateID) REFERENCES Template(TemplateID) ON DELETE CASCADE  -- Liên kết với Template, tự động xóa khi Template bị xóa
);

-- Thêm dữ liệu mẫu vào bảng TemplateDetail


-- Thêm dữ liệu vào bảng Language
INSERT INTO Language (name, code) VALUES
('Tiếng Việt', 'vi'),
('Tiếng Nhật', 'ja'),
('Tiếng Anh', 'en');

-- Thêm dữ liệu vào bảng Template
INSERT INTO Template (title, description, favorite, tag) VALUES
('Template xin nghỉ học', 'Đây là template dành cho việc xin nghỉ học hoặc báo cáo nghỉ học tạm thời.', TRUE, 'hourensou, xin nghỉ học, báo cáo'),
('Template báo cáo kéo dài thời gian làm bài', 'Template này dùng để báo cáo kéo dài thời gian làm bài kiểm tra, bài tập hoặc dự án.', FALSE, 'hourensou, kéo dài thời gian, báo cáo'),
('Template báo cáo đi muộn', 'Đây là template dùng để báo cáo việc đi muộn, lý do đi muộn và thời gian dự kiến đến.', TRUE, 'hourensou, đi muộn, báo cáo'),
('Template xin phép vắng mặt trong buổi họp', 'Template dành cho việc xin phép vắng mặt trong các cuộc họp quan trọng.', FALSE, 'hourensou, vắng mặt, họp'),
('Template báo cáo tình trạng sức khỏe', 'Template này sử dụng để báo cáo tình trạng sức khỏe không tốt.', TRUE, 'hourensou, sức khỏe, báo cáo'),
('Template yêu cầu gia hạn deadline dự án', 'Dành cho việc yêu cầu gia hạn deadline do khối lượng công việc lớn hoặc lý do bất khả kháng.', TRUE, 'hourensou, gia hạn, deadline'),
('Template cập nhật tiến độ công việc', 'Sử dụng để cập nhật tiến độ của các công việc đang được thực hiện.', FALSE, 'hourensou, tiến độ, báo cáo'),
('Template thông báo thay đổi lịch trình', 'Template này sử dụng để thông báo thay đổi lịch trình công việc hoặc họp.', TRUE, 'hourensou, thay đổi, lịch trình'),
('Template xin phép làm việc từ xa', 'Dành cho việc xin phép làm việc từ xa vì lý do cá nhân hoặc khách quan.', FALSE, 'hourensou, làm việc từ xa, xin phép'),
('Template báo cáo kết quả công việc tuần', 'Template này tổng hợp và báo cáo kết quả công việc hàng tuần.', TRUE, 'hourensou, kết quả, báo cáo'),
('Template báo cáo mất đồ dùng cá nhân', 'Sử dụng để báo cáo trường hợp mất mát đồ dùng cá nhân trong công ty.', FALSE, 'hourensou, mất đồ, báo cáo'),
('Template xin phép nghỉ dưỡng bệnh', 'Template này dùng để xin nghỉ phép dưỡng bệnh trong thời gian ngắn.', TRUE, 'hourensou, nghỉ dưỡng, sức khỏe'),
('Template thông báo hoàn thành công việc', 'Dành cho việc thông báo đã hoàn thành công việc hoặc dự án.', TRUE, 'hourensou, hoàn thành, công việc'),
('Template báo cáo sự cố kỹ thuật', 'Sử dụng để báo cáo các sự cố kỹ thuật phát sinh trong quá trình làm việc.', FALSE, 'hourensou, sự cố, kỹ thuật'),
('Template xin phép ra ngoài trong giờ làm', 'Dành cho việc xin phép ra ngoài trong giờ làm việc với lý do chính đáng.', TRUE, 'hourensou, ra ngoài, xin phép'),
('Template thông báo chậm tiến độ', 'Template này sử dụng để thông báo việc chậm tiến độ công việc và đề xuất giải pháp.', FALSE, 'hourensou, chậm tiến độ, báo cáo'),
('Template báo cáo kết quả học tập', 'Template này dùng để báo cáo kết quả học tập định kỳ của nhân viên.', TRUE, 'hourensou, kết quả học tập, báo cáo'),
('Template xin phép tham gia khóa đào tạo', 'Dành cho việc xin phép tham gia các khóa đào tạo nội bộ hoặc bên ngoài.', TRUE, 'hourensou, khóa đào tạo, xin phép'),
('Template yêu cầu công cụ làm việc', 'Sử dụng để yêu cầu cấp phát công cụ làm việc mới hoặc thay thế.', FALSE, 'hourensou, công cụ, yêu cầu'),
('Template thông báo nghỉ việc', 'Template này dùng để thông báo quyết định nghỉ việc của nhân viên.', TRUE, 'hourensou, nghỉ việc, thông báo');

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

INSERT INTO TemplateDetail (TemplateID, template_text, template_setsumei) VALUES
(1, 
'\r \n
[上司の名前] 様\r\n
お疲れ様です。\r\n
[日付] にお休みをいただきたく、お願い申し上げます。\r\n
[理由]（例: 私的な用事、家族のイベント、休養など）により、お休みを希望しています。\r\n
ご確認いただき、問題があればお知らせいただけると幸いです。\r\n
必要な手続きがあれば、お知らせください。\r\n
お手数をおかけしますが、どうぞよろしくお願いいたします。\r\n
敬具\r\n
[あなたの名前]\r\n
[あなたの役職]\r\n', 
'名前を書くときは、マークを付ける必要があります。姓と名の部分の間'),
(2, 
'\r \n
[プロジェクト名] プロジェクトに関する進捗報告です。\r\n
現在の進捗率は [進捗率] で、[完了予定日] に完了を予定しています。\r\n
進行中の課題：[課題内容]\r\n
次のステップ：[次のタスク]\r\n
問題や提案があればお知らせください。\r\n
よろしくお願いいたします。\r\n', 
'進捗率を記入する際に、現在の状況を正確に記述してください。');

INSERT INTO TemplateDetail (TemplateID, template_text, template_setsumei) VALUES
(1, 
'\r\n
[上司の名前] 様\r\n
お疲れ様です。\r\n
[日付] にお休みをいただきたく、お願い申し上げます。\r\n
[理由]（例: 私的な用事、家族のイベント、休養など）により、お休みを希望しています。\r\n
ご確認いただき、問題があればお知らせいただけると幸いです。\r\n
必要な手続きがあれば、お知らせください。\r\n
お手数をおかけしますが、どうぞよろしくお願いいたします。\r\n
敬具\r\n
[あなたの名前]\r\n
[あなたの役職]\r\n', 
'名前を書くときは、マークを付ける必要があります。姓と名の部分の間'),

(2, 
'\r\n
[プロジェクト名] プロジェクトに関する進捗報告です。\r\n
現在の進捗率は [進捗率] で、[完了予定日] に完了を予定しています。\r\n
進行中の課題：[課題内容]\r\n
次のステップ：[次のタスク]\r\n
問題や提案があればお知らせください。\r\n
よろしくお願いいたします。\r\n', 
'進捗率を記入する際に、現在の状況を正確に記述してください。'),

(3, 
'\r\n
[遅刻理由] 様\r\n
お疲れ様です。\r\n
[遅刻理由] により、[遅刻時間] に到着予定です。\r\n
ご迷惑をおかけしますが、よろしくお願いいたします。\r\n
敬具\r\n
[あなたの名前]\r\n
[あなたの役職]\r\n', 
'遅刻理由や予定到着時刻を具体的に記入してください。'),

(4, 
'\r\n
[会議の名前] 会議に関するお知らせです。\r\n
[会議日時] に[会議内容] が行われます。\r\n
参加できない場合、必ず事前にお知らせください。\r\n
ご確認いただき、よろしくお願いいたします。\r\n
敬具\r\n
[あなたの名前]\r\n
[あなたの役職]\r\n', 
'会議に参加できない場合、理由を記入してください。'),

(5, 
'\r\n
[名前] 様\r\n
お世話になっております。\r\n
[健康状態] により、[期間] での休養をお願い申し上げます。\r\n
[必要な手続き] があればお知らせいただければと思います。\r\n
何卒よろしくお願いいたします。\r\n
敬具\r\n
[あなたの名前]\r\n
[あなたの役職]\r\n', 
'健康状態について具体的に記述し、期間を明確に記載してください。'),

(6, 
'\r\n
[プロジェクト名] プロジェクトに関する進捗報告です。\r\n
現在の進捗率は [進捗率] で、[完了予定日] に完了を予定しています。\r\n
進行中の課題：[課題内容]\r\n
次のステップ：[次のタスク]\r\n
問題や提案があればお知らせください。\r\n
よろしくお願いいたします。\r\n', 
'進捗状況や課題内容について詳細に記述してください。'),

(7, 
'\r\n
[仕事名] 仕事に関するお知らせです。\r\n
[提出期限] に関して、提出期限を延長したいと思います。\r\n
必要な手続きがあればお知らせいただけますか。\r\n
お手数ですがよろしくお願いいたします。\r\n
敬具\r\n
[あなたの名前]\r\n
[あなたの役職]\r\n', 
'提出期限の変更理由を明確に記述してください。'),

(8, 
'\r\n
[会議名] 会議に関するお知らせです。\r\n
[変更内容] 会議の時間または場所が変更になりました。\r\n
詳細な情報は下記をご確認ください。\r\n
ご確認いただき、問題があればお知らせください。\r\n
よろしくお願いいたします。\r\n', 
'会議の変更内容を明確に伝えることが重要です。');

select * from user;
-- Kiểm tra dữ liệu
SELECT * FROM TemplateDetail;
select * from Template;
