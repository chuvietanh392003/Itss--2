-- Xóa cơ sở dữ liệu nếu đã tồn tại
DROP DATABASE IF EXISTS itss;

-- Tạo cơ sở dữ liệu mới
CREATE DATABASE IF NOT EXISTS itss;

USE itss;

-- Bảng User (Bao gồm cả admin và người dùng thông thường)
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,  -- ID người dùng, tự động tăng
    username VARCHAR(255) UNIQUE NOT NULL,  -- Tên đăng nhập, phải là duy nhất
    email VARCHAR(255) UNIQUE NOT NULL,     -- Địa chỉ email, phải là duy nhất
    password VARCHAR(255) NOT NULL,         -- Mật khẩu
    is_admin BOOLEAN DEFAULT FALSE,         -- Phân biệt người dùng và admin
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP -- Thời gian tạo tài khoản
);

-- Bảng Template
CREATE TABLE Template (
    TemplateID INT AUTO_INCREMENT PRIMARY KEY,  -- ID template, tự động tăng
    UserID INT NOT NULL,
    title VARCHAR(255) NOT NULL,                -- Tiêu đề template
    description TEXT NULL,                      -- Mô tả template
    favorite BOOLEAN DEFAULT FALSE,             -- Trường yêu thích, mặc định là FALSE
    tag VARCHAR(255) NULL,                      -- Các tag liên quan đến template
    view_count INT DEFAULT 0,                   -- Số lượt xem template, mặc định là 0
    save_count INT DEFAULT 0,                   -- Số lượt lưu template, mặc định là 0
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,                      -- Thời gian tạo template
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- Thời gian cập nhật template
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Bảng Language (Lưu trữ thông tin về ngôn ngữ)
CREATE TABLE Language (
    LanguageID INT AUTO_INCREMENT PRIMARY KEY, -- ID ngôn ngữ, tự động tăng
    name VARCHAR(255) NOT NULL,                -- Tên ngôn ngữ (Ví dụ: "Tiếng Việt", "Tiếng Nhật")
    code VARCHAR(10) NOT NULL                  -- Mã ngôn ngữ (Ví dụ: "vi", "ja")
);

-- Tạo bảng TemplateDetail
CREATE TABLE TemplateDetail (
    TemplateDetailID INT AUTO_INCREMENT PRIMARY KEY, -- ID tự động tăng của TemplateDetail
    TemplateID INT NOT NULL,                          -- Tham chiếu bảng Template
    template_text TEXT NOT NULL,                      -- Nội dung template chi tiết
    template_setsumei TEXT NOT NULL,                  -- Giải thích về template
    FOREIGN KEY (TemplateID) REFERENCES Template(TemplateID) ON DELETE CASCADE -- Liên kết với Template
);

-- Thêm dữ liệu vào bảng Language
INSERT INTO Language (name, code) VALUES
('Tiếng Việt', 'vi'),
('Tiếng Nhật', 'ja'),
('Tiếng Anh', 'en');

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

-- テンプレートテーブルにデータを追加
INSERT INTO Template (UserID, title, description, favorite, tag) VALUES
(1, '休学申請テンプレート', 'これは休学または一時的な休学報告用のテンプレートです。', TRUE, 'hourensou, 休学, 報告'),
(1, '試験延長報告テンプレート', 'これは試験、課題、またはプロジェクトの期限延長を報告するためのテンプレートです。', FALSE, 'hourensou, 延長, 報告'),
(1, '遅刻報告テンプレート', 'これは遅刻の報告用テンプレートで、遅刻の理由と到着予定時間を記入します。', TRUE, 'hourensou, 遅刻, 報告'),
(1, '会議欠席申請テンプレート', '重要な会議に欠席するための申請テンプレートです。', FALSE, 'hourensou, 欠席, 会議'),
(1, '健康状態報告テンプレート', 'これは健康状態が良くないことを報告するためのテンプレートです。', TRUE, 'hourensou, 健康, 報告'),
(1, 'プロジェクト期限延長要求テンプレート', '作業負荷が大きい場合や不可抗力による期限延長を要求するためのテンプレートです。', TRUE, 'hourensou, 延長, 期限'),
(1, '進捗報告テンプレート', '進行中の作業の進捗状況を報告するためのテンプレートです。', FALSE, 'hourensou, 進捗, 報告'),
(1, 'スケジュール変更通知テンプレート', '作業や会議のスケジュール変更を通知するためのテンプレートです。', TRUE, 'hourensou, 変更, スケジュール'),
(1, '在宅勤務申請テンプレート', '個人的または客観的な理由で在宅勤務を申請するためのテンプレートです。', FALSE, 'hourensou, 在宅勤務, 申請'),
(1, '週次業務結果報告テンプレート', '週次で業務結果を報告するためのテンプレートです。', TRUE, 'hourensou, 結果, 報告'),
(2, '私物紛失報告テンプレート', '会社内で私物が紛失した場合の報告用テンプレートです。', FALSE, 'hourensou, 紛失, 報告'),
(1, '病気休暇申請テンプレート', '短期間の病気休暇を申請するためのテンプレートです。', TRUE, 'hourensou, 休暇, 健康'),
(2, '業務完了通知テンプレート', '業務またはプロジェクトの完了を通知するためのテンプレートです。', TRUE, 'hourensou, 完了, 業務'),
(1, '技術的問題報告テンプレート', '作業中に発生した技術的な問題を報告するためのテンプレートです。', FALSE, 'hourensou, 問題, 技術'),
(1, '勤務時間中の外出申請テンプレート', '勤務時間中に正当な理由で外出するための申請テンプレートです。', TRUE, 'hourensou, 外出, 申請'),
(1, '遅延報告テンプレート', '業務の遅延を報告し、解決策を提案するためのテンプレートです。', FALSE, 'hourensou, 遅延, 報告'),
(1, '学業成果報告テンプレート', '従業員の定期的な学業成果を報告するためのテンプレートです。', TRUE, 'hourensou, 成果, 報告'),
(1, '研修参加申請テンプレート', '社内外の研修に参加するための申請テンプレートです。', TRUE, 'hourensou, 研修, 申請'),
(1, '作業ツール要求テンプレート', '新しい作業ツールの提供や交換を要求するためのテンプレートです。', FALSE, 'hourensou, ツール, 要求'),
(1, '退職通知テンプレート', '従業員の退職を通知するためのテンプレートです。', TRUE, 'hourensou, 退職, 通知');

INSERT INTO TemplateDetail (TemplateID, template_text, template_setsumei) VALUES
(1,
'\r\n [先生の名前] 先生\n
お世話になっております。\n
私は[あなたの名前] です。 [理由] により、[期間] の間、休学を申請させていただきます。\n
休学期間：[開始日] から [終了日]\n
詳細な理由：[具体的な理由]\n
必要な手続き等があればお知らせいただけますと幸いです。\n
どうぞよろしくお願いいたします。\n
敬具\n
[あなたの名前]', 
'休学期間や理由を具体的に記述し、必要な手続きを確認してください。'),

(2, 
'\r\n [担当者の名前] 様\n
お世話になっております。\n
[試験名] または [課題名] の提出期限について延長をお願いしたく存じます。\n
理由：[延長を希望する理由]\n
新しい提出希望日：[新しい期限の日付]\n
ご確認のほど、よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'試験や課題の提出期限延長の理由と新しい希望日を具体的に記載してください。'),

(3, 
'\r\n [上司の名前] 様\n
お疲れ様です。\n
[遅刻理由] により、[到着予定時間] に到着いたします。\n
ご迷惑をおかけして申し訳ありません。\n
何卒ご理解のほど、よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'遅刻理由と到着予定時間を明確に記載してください。'),

(4, 
'\r\n [上司の名前] 様\n
お世話になっております。\n
[会議名] に関して、欠席のお願いを申し上げます。\n
理由：[欠席理由]\n
ご迷惑をおかけしますが、何卒ご理解のほどよろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'欠席理由を簡潔に説明し、理解を求める表現を使用してください。'),

(5, 
'\r\n [上司の名前] 様\n
お疲れ様です。\n
私、[あなたの名前] は [健康状態の理由] により、[期間] の間、休養を申請させていただきます。\n
必要な手続きがあればお知らせいただけますと幸いです。\n
どうぞよろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'健康状態に関する詳細を明確に記載し、休養期間を具体的に伝えてください。'),

(6, 
'\r\n [プロジェクト名] プロジェクトに関する進捗報告です。\n
現在の進捗率：[進捗率]\n
完了予定日：[完了予定日]\n
現在の課題：[課題内容]\n
次のステップ：[次のタスク]\n
ご確認いただき、問題や提案があればお知らせください。\n
よろしくお願いいたします。', 
'プロジェクト進捗の詳細を具体的に記載してください。'),

(7, 
'\r\n [上司の名前] 様\n
お世話になっております。\n
[タスク名] に関して、提出期限の延長をお願い申し上げます。\n
理由：[延長理由]\n
新しい期限：[新しい期限の希望日]\n
ご確認のほどよろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'提出期限延長の理由と希望日を明確に記載してください。'),

(8, 
'\r\n [関係者の名前] 様\n
お疲れ様です。\n
[会議名] 会議のスケジュールが以下の通り変更されました。\n
新しい日時：[変更後の日時]\n
新しい場所：[変更後の場所]\n
詳細：[その他の詳細]\n
ご確認いただき、問題があればお知らせください。\n
よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'会議スケジュールの変更点を正確に記述してください。'),

(9, 
'\r\n [上司の名前] 様\n
お疲れ様です。\n
個人的な理由により、在宅勤務を申請いたします。\n
期間：[在宅勤務を希望する期間]\n
理由：[具体的な理由]\n
ご確認いただき、問題がなければご承認をお願い申し上げます。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'在宅勤務を希望する理由と期間を明確に記載してください。'),

(10, 
'\r\n [関係者の名前] 様\n
お世話になっております。\n
以下は今週の業務報告です。\n
完了したタスク：[タスク内容]\n
進行中のタスク：[進行中の内容]\n
次のステップ：[次の予定]\n
何かご質問がありましたら、遠慮なくお知らせください。\n
よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'週次業務報告の構成を簡潔に整理し、詳細を記述してください。'),

(11, 
'\r\n [上司の名前] 様\n
お疲れ様です。\n
本日、会社内で私物が紛失しました。\n
紛失した物：[紛失した物の詳細]\n
場所：[紛失が発生した場所]\n
時刻：[紛失した時刻]\n
状況：[詳細な状況説明]\n
何か情報があればお知らせいただけると幸いです。\n
よろしくお願いいたします。\n
敬具\n
[あなたの名前]', 
'紛失した物や状況を具体的に記載し、詳細に報告してください。'),

(12, 
'\r\n [関係者の名前] 様\n
お疲れ様です。\n
以下の業務/プロジェクトが完了しました。\n
件名：[完了した業務またはプロジェクト名]\n
概要：[完了内容の概要]\n
詳細：[完了の詳細情報]\n
次のステップ：[関連する次のアクション]\n
ご確認いただけますと幸いです。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'業務完了内容を簡潔かつ具体的に記載してください。'),

(13, 
'\r\n [担当者の名前] 様\n
お疲れ様です。\n
以下の技術的な問題が発生しました。\n
問題の詳細：[具体的な問題点]\n
影響：[業務やプロジェクトへの影響]\n
対策案：[提案する対策案]\n
ご確認いただき、アドバイスをいただけますと幸いです。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'技術的な問題と対策案を簡潔に記載してください。'),

(14, 
'\r\n [担当者名] 様\n
お疲れ様です。\n
現在進行中の [プロジェクト名] に関して、追加リソースが必要です。\n
必要なリソース：[具体的なリソースの内容]\n
理由：[追加リソースが必要な理由]\n
ご確認いただき、問題がなければご承認いただけますと幸いです。\n
よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'追加リソースが必要な理由を具体的に記載し、詳細を明確に提示してください。'),

(15, 
'\r\n [上司の名前] 様\n
お疲れ様です。\n
以下の内容で新しい提案をご提示いたします。\n
提案内容：[提案の概要]\n
目的：[提案の目的]\n
メリット：[提案による利点]\n
ご確認いただき、ご意見をいただけると幸いです。\n
どうぞよろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'提案の目的、内容、メリットを明確かつ簡潔に記述してください。'),

(16, 
'\r\n [会議の担当者名] 様\n
お疲れ様です。\n
以下の内容について、ミーティングをリクエストいたします。\n
議題：[議題の内容]\n
希望日時：[希望する日時]\n
目的：[ミーティングの目的]\n
ご都合が合わない場合は、他の候補日時をご提案いただけますと幸いです。\n
よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'議題、目的、希望日時を具体的に記載し、柔軟な対応を提案してください。'),

(17, 
'\r\n [上司の名前] 様\n
お疲れ様です。\n
以下のプロセスを改善するためのご提案を申し上げます。\n
現在の課題：[現在の問題点]\n
提案内容：[具体的な改善案]\n
期待される効果：[提案の利点や効果]\n
ご確認いただき、ご意見をいただけますと幸いです。\n
よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'現在の課題を明確にし、提案内容と期待される効果を具体的に記載してください。'),

(18, 
'\r\n [担当者名] 様\n
お疲れ様です。\n
以下の内容でお知らせ申し上げます。\n
件名：[件名の概要]\n
内容：[お知らせ内容の詳細]\n
関連する資料：[関連資料の有無やリンク]\n
ご不明な点がございましたら、ご連絡ください。\n
どうぞよろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'件名と内容を簡潔に記述し、必要な場合は関連資料を添付してください。'),

(19, 
'\r\n [上司の名前] 様\n
お世話になっております。\n
以下の件について、承認をお願い申し上げます。\n
件名：[承認を求める内容]\n
目的：[承認を必要とする理由や背景]\n
詳細：[具体的な内容]\n
ご確認いただき、ご承認いただけると幸いです。\n
よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'承認を必要とする内容を具体的に記載し、背景や目的を説明してください。'),

(20, 
'\r\n [関係者全員] 様\n
お疲れ様です。\n
以下の内容について、進捗をお知らせ申し上げます。\n
進捗状況：[進捗内容]\n
課題：[現在の課題]\n
次のステップ：[次の予定]\n
何かご質問があればお知らせください。\n
よろしくお願いいたします。\n
敬具\n
[あなたの名前]\n
[あなたの役職]', 
'進捗状況、課題、次のステップを簡潔に記載してください。');
