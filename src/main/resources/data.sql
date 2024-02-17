insert into intidb(check_init)values (false);

insert into tai_khoan(id, create_by, create_date, last_updated_by, last_updated_date, mat_khau, tai_khoan) values (1, "quyet", "2002-07-02", "quyet", "2002-07-02","$2a$10$Sm09wblli4opHvQD.3siReou9O1b8pGVQVdHFfNfFshKoN9faiYHG", "user");
insert into tai_khoan(id, create_by, create_date, last_updated_by, last_updated_date, mat_khau, tai_khoan) values (2, "quyet", "2002-07-02", "quyet", "2002-07-02","$2a$10$Sm09wblli4opHvQD.3siReou9O1b8pGVQVdHFfNfFshKoN9faiYHG", "admin");

insert into khach_hang(id, create_by, create_date, last_updated_by, last_updated_date, dob, age, full_name, gioi_tinh, name, trang_thai, id_tk) values (1, "quyet", "2002-07-02", "quyet", "2002-07-02", "2002-07-02", 22, "do van quyet", "NAM", "quyet", 0, 1);

insert into role(id,create_by, create_date, last_updated_by, last_updated_date, role, trang_thai)values (1, "quyet", "2002-07-02", "quyet", "2002-07-02", "ROLE_USER", 0),(2, "quyet", "2002-07-02", "quyet", "2002-07-02", "ROLE_ADMIN", 0);

insert into role_detail(create_by, create_date, last_updated_by, last_updated_date, id_role, id_taikhoan) values("quyet", "2002-07-02", "quyet", "2002-07-02",1, 1);
insert into role_detail(create_by, create_date, last_updated_by, last_updated_date, id_role, id_taikhoan) values("quyet", "2002-07-02", "quyet", "2002-07-02",2, 2);

insert into permision(id,create_by, create_date, last_updated_by, last_updated_date, method, name_permision, url)values (1, "quyet", "2002-07-02", "quyet", "2002-07-02", "POST", "ADD_CART", "/gio-hang/**"),(2,"quyet", "2002-07-02", "quyet", "2002-07-02", "GET", "SHOW_CART", "/gio-hang/**"),(3,"quyet", "2002-07-02", "quyet", "2002-07-02", "PUT", "UPDATE_CART", "/gio-hang/**"),(4,"quyet", "2002-07-02", "quyet", "2002-07-02", "POST", "ADD_BILL", "/hoa-don/**"),(5,"quyet", "2002-07-02", "quyet", "2002-07-02", "GET", "SHOW_BILL", "/hoa-don/**"),(6,"quyet", "2002-07-02", "quyet", "2002-07-02", "PUT", "UPDATE_BILL", "/hoa-don/**");

insert into permision_detail(id, create_by, create_date, last_updated_by, last_updated_date, id_permision, id_role) values (1, "quyet", "2002-07-02", "quyet", "2002-07-02", 1, 1);
insert into permision_detail(id, create_by, create_date, last_updated_by, last_updated_date, id_permision, id_role) values (2, "quyet", "2002-07-02", "quyet", "2002-07-02", 2, 1);
insert into permision_detail(id, create_by, create_date, last_updated_by, last_updated_date, id_permision, id_role) values (3, "quyet", "2002-07-02", "quyet", "2002-07-02", 3, 1);
insert into permision_detail(id, create_by, create_date, last_updated_by, last_updated_date, id_permision, id_role) values (4, "quyet", "2002-07-02", "quyet", "2002-07-02", 4, 2);
insert into permision_detail(id, create_by, create_date, last_updated_by, last_updated_date, id_permision, id_role) values (5, "quyet", "2002-07-02", "quyet", "2002-07-02", 5, 2);
insert into permision_detail(id, create_by, create_date, last_updated_by, last_updated_date, id_permision, id_role) values (6, "quyet", "2002-07-02", "quyet", "2002-07-02", 6, 2);

insert into san_pham(id, create_by, create_date, last_updated_by, last_updated_date, gia_ban, mota, so_luong_ton, ten_san_pham, tieu_de, trang_thai) values (1, "quyet", "2002-07-02", "quyet", "2002-07-02",100,  "san pham tot", 100, "san pham a", "tieu de a", 0);
insert into san_pham(id, create_by, create_date, last_updated_by, last_updated_date, gia_ban, mota, so_luong_ton, ten_san_pham, tieu_de, trang_thai) values (2, "quyet", "2002-07-02", "quyet", "2002-07-02",1200,  "san pham tot", 100, "san pham b", "tieu de b", 0);
insert into san_pham(id, create_by, create_date, last_updated_by, last_updated_date, gia_ban, mota, so_luong_ton, ten_san_pham, tieu_de, trang_thai) values (3, "quyet", "2002-07-02", "quyet", "2002-07-02",1300,  "san pham tot", 100, "san pham c", "tieu de c", 0);
insert into san_pham(id, create_by, create_date, last_updated_by, last_updated_date, gia_ban, mota, so_luong_ton, ten_san_pham, tieu_de, trang_thai) values (4, "quyet", "2002-07-02", "quyet", "2002-07-02",1400, "san pham tot", 100, "san pham d", "tieu de d", 0);
insert into san_pham(id, create_by, create_date, last_updated_by, last_updated_date, gia_ban, mota, so_luong_ton, ten_san_pham, tieu_de, trang_thai) values (5, "quyet", "2002-07-02", "quyet", "2002-07-02",1500, "san pham tot", 100, "san pham e", "tieu de e", 0);

insert into danh_muc(id, create_by, create_date, last_updated_by, last_updated_date,name_danh_muc) values (1, "quyet", "2002-07-02", "quyet", "2002-07-02", "chết tiệt thật")

insert into images(create_by, create_date, last_updated_by, last_updated_date,image, id_sanpham) values ("quyet", "2002-07-02", "quyet", "2002-07-02","1.jpg", 1);
insert into images(create_by, create_date, last_updated_by, last_updated_date,image, id_sanpham) values ("quyet", "2002-07-02", "quyet", "2002-07-02","3.jpg", 2);
insert into images(create_by, create_date, last_updated_by, last_updated_date,image, id_sanpham) values ("quyet", "2002-07-02", "quyet", "2002-07-02","4.jpg", 3);
insert into images(create_by, create_date, last_updated_by, last_updated_date,image, id_sanpham) values ("quyet", "2002-07-02", "quyet", "2002-07-02","1.jpg", 4);
insert into images(create_by, create_date, last_updated_by, last_updated_date,image, id_sanpham) values ("quyet", "2002-07-02", "quyet", "2002-07-02","3.jpg", 5);