# Car-RentalProject
Project mô phỏng website cho thuê xe máy và ô tô

## Contents
- [Giới thiệu](#Giới-thiệu)
- [Thành viên](#Thành-viên)
- [Mentor hướng dẫn](#Mentor)
- [Tính năng](#Tính-năng)
- [Cấu trúc Database](#Cấu-trúc-Database)
- [Công cụ](#Công-cụ)
- [Kiến trúc](#Kiến-trúc)
- [API Documentation](#API-Documentation)


## Giới thiệu
Server API của project mô phỏng website đặt xe online với các chức năng sau:
- Tìm kiếm xe theo địa điểm cho thuê, giá thuê, hãng xe,..
- Đăng ký thuê xe và đặt cọc.

Website chỉ đóng vai là bên cho thuê xe. Người thuê xe sẽ được liên hệ lại từ cửa hàng để xác nhận thông tin đặt xe. Website cũng tập trung hướng đến những đối tượng khách hàng có nhu cầu thuê xe trong thời gian ngắn để du lịch hoặc làm sự kiện.

## Thành viên
Thành viên của project bao gồm:
- Nguyễn Thị Thùy: Phụ trách FrontEnd, //Cần bổ sung
- Vũ Ngọc Hải: Phụ trách BackEnd, bao gồm thiết kế database, thiết kế các REST API để liên kết database với bên FrontEnd.

## Mentor
Mentor hướng dẫn
- //FrontEnd bổ sung
- Phạm Thanh Hải: BackEnd

## Tính Năng
User story của người dùng.

<img align="left|middle" src="https://i.imgur.com/isbtagv.png">

## Cấu trúc Database
Cấu trúc các bảng trong database của project

<img align="middle" src="https://i.imgur.com/0uOr82c.png">

## Công cụ
Cấu trúc và các công cụ được sử dụng của project:
- Version control: git
- Database: mysql
- BackEnd: Java
- IDE : intellij
- Testing: Postman: https://www.getpostman.com/
- FrontEnd:
- Api documentation: GoSwagger: https://car-rental-2020.herokuapp.com/swagger-ui.html

## Kiến trúc
- main: chạy ứng dụng
- application.properties: Kết nối database, thông tin server mysql, port(8080)
- controller: Xác định API tưng ứng cho mỗi chức năng chính của website
- entity: Tạo các thực thể đại diện cho mỗi bảng trong database.
- model: Gồm 4 phần:
        dto : object mà lớp dao trả về cho lớp controller
        mapper : chứa các function dùng cho việc cast object từ dao sang dto
        request : object được parse từ chuỗi json mà front-end gửi lên api
        response : object gồm thông tin trả về cho front gồm message,Htmlstatus,body.
- service: lớp thực thi các logic nghiệp vụ
- security: khởi tạo authencation, filter
- config: khởi tạo swagger
- util: verify, generate token
- pom: chứa các dependencies của các thư viện

## API Documentation
Swagger link: http://siteb.ap.loclx.io/swagger/index.html
