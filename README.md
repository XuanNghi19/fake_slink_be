# Ứng Dụng Quản Lý Sinh Viên PTIT

Bài tập lớn "Ứng dụng quản lý sinh viên PTIT" nhằm phát triển một hệ thống giúp sinh viên quản lý thông tin cá nhân, theo dõi kết quả học tập và nhận các thông báo quan trọng từ trường. Hướng tiếp cận của sinh viên là xây dựng một ứng dụng di động dễ sử dụng, giúp sinh viên nhanh chóng theo dõi thông tin học tập và nhận thông báo kịp thời.

## Giải Pháp

Ứng dụng di động giúp sinh viên:
- Xem thông tin cá nhân.
- Thay đổi mật khẩu.
- Theo dõi kết quả học tập.
- Theo dõi lịch học.
- Nhận thông báo về lịch thi, điểm môn học, yêu cầu phúc khảo.

Ngoài ra, ứng dụng còn hỗ trợ sinh viên gửi yêu cầu phúc khảo và theo dõi trạng thái yêu cầu.

## Đóng Góp Chính

Bài tập lớn này phát triển một ứng dụng di động đáp ứng nhu cầu cơ bản của sinh viên, giúp họ dễ dàng quản lý thông tin học tập và theo dõi thông báo từ trường. Kết quả đạt được là một ứng dụng hoàn chỉnh, với giao diện thân thiện và đầy đủ chức năng cần thiết.

## Công Nghệ Sử Dụng

- **Java Spring Boot**
- **Spring Security**
- **Oracle Cloud**
- **Oracle Database**
- **SQLite Database**
- **Firebase**
- **Render**

## Các Thực Thể Chính

- **Student**: Chứa thông tin sinh viên.
- **Teacher**: Chứa thông tin giáo viên.
- **StudentDevice**: Chứa thông tin FCM token của điện thoại sinh viên.
- **Subject**: Chứa thông tin môn học.
- **Semester**: Chứa thông tin về kỳ học.
- **ClassSubject**: Chứa thông tin về lớp học tín chỉ tương ứng với các học phần khác nhau.
- **SubjectRegistration**: Chứa thông tin học sinh đăng ký lớp tín chỉ.
- **TimeTable**: Chứa thông tin về lịch học của lớp tín chỉ.
- **ReviewForm**: Chứa thông tin về đơn phúc khảo của sinh viên.
- **Major**: Chứa thông tin về ngành học của sinh viên.
- **Grade**: Chứa thông tin về điểm số của sinh viên.
- **ExamSchedule**: Chứa thông tin về lịch thi của lớp học tín chỉ.
- **DraftTeacherNum**: Dùng để tạo mã giáo viên tự động.
- **DraftStudentNum**: Dùng để tạo mã sinh viên tự động.
- **Department**: Chứa thông tin về khoa.

## Kiến Trúc Sử Dụng

### Kiến Trúc Phía Backend (BE)
Sử dụng kiến trúc **MVC** (Model-View-Controller):
- **Controller Layer**: Xử lý yêu cầu HTTP từ client.
- **Service Layer**: Chứa logic nghiệp vụ (business logic).
- **Repository Layer**: Quản lý truy cập dữ liệu (database).
- **Model Layer (Entity)**: Định nghĩa các đối tượng dữ liệu.
- **DTOs (Data Transfer Objects)**: Truyền dữ liệu giữa các lớp (Request, Response).

### Kiến Trúc Phía Frontend (FE)
Sử dụng kiến trúc **MVVM** (Model-View-ViewModel):
- **Activities**: Chứa các màn hình hiển thị giao diện người dùng.
- **Adapters**: Chứa các adapter giúp chuyển dữ liệu từ model vào các view như RecyclerView và ListView.
- **API**: Các lớp dịch vụ giao tiếp với các API backend.
- **Enums**: Các kiểu dữ liệu enum dùng trong ứng dụng.
- **Helpers**: Các lớp trợ giúp như AppHelper và DatabaseHelper.
- **Models**: Chứa các lớp đại diện cho dữ liệu yêu cầu và phản hồi từ API.
- **Singletons**: Các lớp đơn (singleton) để lưu trữ trạng thái hoặc các đối tượng duy nhất.
- **Notification**: Các lớp quản lý thông báo trong ứng dụng, bao gồm thông báo cục bộ và từ Firebase.
- **Repository**: Các lớp quản lý dữ liệu từ nhiều nguồn.
- **Retrofit**: Các lớp sử dụng Retrofit để giao tiếp với các dịch vụ RESTful API.

## Các Chức Năng

### 1. Xem Thông Tin Cá Nhân và Thay Đổi
- **Chức năng**: Sinh viên có thể xem và chỉnh sửa thông tin cá nhân, bao gồm thay đổi ảnh đại diện và mật khẩu.
- **Mô tả**: Hệ thống cho phép sinh viên cập nhật thông tin cá nhân của mình, như tên, ảnh đại diện và mật khẩu để đảm bảo bảo mật tài khoản.

### 2. Theo Dõi Kết Quả Học Tập
- **Chức năng**: Sinh viên có thể theo dõi kết quả học tập, bao gồm điểm số các môn học.
- **Mô tả**: Hệ thống cung cấp giao diện cho sinh viên xem điểm số các môn học đã thi, giúp theo dõi tiến độ học tập và đánh giá kết quả.

### 3. Theo Dõi Lịch Học
- **Chức năng**: Sinh viên có thể theo dõi lịch học của mình.
- **Mô tả**: Hệ thống hiển thị lịch học cá nhân của sinh viên, bao gồm các môn học và thời gian học cụ thể theo thời khóa biểu.

### 4. Hệ Thống Thông Báo
- **Chức năng**: Hệ thống gửi thông báo cho sinh viên về các sự kiện quan trọng (Lịch thi, Cập nhật phiếu phúc khảo, Thông báo điểm môn học).
- **Mô tả**: Hệ thống tự động gửi thông báo đến sinh viên về các sự kiện quan trọng để họ không bỏ lỡ thông tin liên quan đến học tập.

### 5. Theo Dõi Các Thông Báo Đã Gửi
- **Chức năng**: Sinh viên có thể theo dõi các thông báo đã nhận.
- **Mô tả**: Sinh viên có thể xem lại các thông báo đã được gửi, từ lịch thi, thông báo điểm cho đến các thông báo cập nhật khác.

### 6. Theo Dõi Lớp Tín Chỉ
- **Chức năng**: Sinh viên có thể theo dõi các lớp tín chỉ của mình.
- **Mô tả**: Hệ thống giúp sinh viên theo dõi các lớp học, bao gồm các môn học đã đăng ký và số tín chỉ của từng môn học.

### 7. Hệ Thống Thông Báo Kết Quả Môn Học
- **Chức năng**: Hệ thống thông báo cho sinh viên khi có kết quả môn học.
- **Mô tả**: Sinh viên nhận thông báo qua hệ thống khi kết quả môn học được công bố.

### 8. Yêu Cầu Phúc Khảo và Theo Dõi Trạng Thái
- **Chức năng**: Sinh viên có thể gửi yêu cầu phúc khảo và theo dõi trạng thái yêu cầu.
- **Mô tả**: Sinh viên có thể gửi yêu cầu phúc khảo và theo dõi trạng thái yêu cầu (được chấp nhận, đang xử lý, hoặc đã hoàn thành).
