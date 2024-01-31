create table danh_muc
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    name_danh_muc     varchar(255) null
);

create table hinh_thuc_thanh_toan
(
    id                   bigint auto_increment
        primary key,
    create_by            varchar(255) null,
    create_date          date         null,
    last_updated_by      varchar(255) null,
    last_updated_date    date         null,
    hinh_thuc_thanh_toan tinyint      null,
    check (`hinh_thuc_thanh_toan` between 0 and 1)
);

create table khuyen_mai
(
    id                 bigint auto_increment
        primary key,
    create_by          varchar(255) null,
    create_date        date         null,
    last_updated_by    varchar(255) null,
    last_updated_date  date         null,
    gia_tri_khuyen_mai double       null,
    ngay_bat_dau       date         null,
    ngay_ket_thuc      date         null,
    ten_khuyen_mai     varchar(255) null,
    trang_thai         int          not null
);

create table nha_cung_cap
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    dia_chi_nha_cung  varchar(255) null,
    email             varchar(255) null,
    ma_nha_cung_cap   varchar(255) null,
    sdt               varchar(255) null,
    ten_nha_cung_cap  varchar(255) null
);

create table permision
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255)                          null,
    create_date       date                                  null,
    last_updated_by   varchar(255)                          null,
    last_updated_date date                                  null,
    method            enum ('POST', 'GET', 'DELETE', 'PUT') null,
    name_permision    varchar(255)                          null,
    url               varchar(255)                          null
);

create table role
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    role              varchar(255) null,
    trang_thai        int          not null
);

create table permision_detail
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    id_permision      bigint       null,
    id_role           bigint       null,
    constraint FK466ih7srbop170b4epl61goj2
        foreign key (id_permision) references permision (id),
    constraint FKi64ufcwgjgqaqbtedwe6wrmw5
        foreign key (id_role) references role (id)
);

create table ship
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    chi_phi           double       null,
    ma_ship           varchar(255) null,
    ngay_ship         date         null,
    nha_van_chuyen    varchar(255) null,
    trang_thai        int          not null
);

create table tai_khoan
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255)                 null,
    create_date       date                         null,
    last_updated_by   varchar(255)                 null,
    last_updated_date date                         null,
    loaitk            enum ('FACEBOOK', 'GOOOGLE') null,
    mat_khau          varchar(255)                 null,
    tai_khoan         varchar(255)                 null
);

create table khach_hang
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255)               null,
    create_date       date                       null,
    last_updated_by   varchar(255)               null,
    last_updated_date date                       null,
    dob               date                       null,
    age               int                        not null,
    full_name         varchar(255)               null,
    gioi_tinh         enum ('NAM', 'NU', 'KHAC') null,
    name              varchar(255)               null,
    trang_thai        int                        not null,
    id_tk             bigint                     null,
    constraint UK_4ibu7juonhvse8p95m0hwnkj0
        unique (id_tk),
    constraint FKmex21s5149sy0glcr7sgor7h4
        foreign key (id_tk) references tai_khoan (id)
);

create table dia_chi
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    huyen             varchar(255) null,
    so_nha            varchar(255) null,
    tinh              varchar(255) null,
    trang_thai        int          not null,
    xa                varchar(255) null,
    id_kh             bigint       null,
    constraint FKb2ifn5dxm7wicmjjk8lc8rg86
        foreign key (id_kh) references khach_hang (id)
);

create table gio_hang
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    ngay_tao          date         null,
    trang_thai        int          not null,
    id_kh             bigint       null,
    constraint UK_t49f4rw6mwaygxpxmyjjvhd2e
        unique (id_kh),
    constraint FKpj2e0lps65w99apwi7uyewohg
        foreign key (id_kh) references khach_hang (id)
);

create table nhan_vien
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255)               null,
    create_date       date                       null,
    last_updated_by   varchar(255)               null,
    last_updated_date date                       null,
    dob               date                       null,
    age               int                        not null,
    full_name         varchar(255)               null,
    gioi_tinh         enum ('NAM', 'NU', 'KHAC') null,
    name              varchar(255)               null,
    trang_thai        int                        not null,
    id_tk             bigint                     null,
    constraint UK_epbl4ew8aso79og47tsdrooye
        unique (id_tk),
    constraint FKd4l4205tyjp72pl5tckg5ireu
        foreign key (id_tk) references tai_khoan (id)
);

create table don_hang
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    gia_giam          double       null,
    ngay_tao          date         null,
    ngay_thanh_toan   date         null,
    phi_ship          double       null,
    thoi_gian_du_kien date         null,
    tong_tien         double       null,
    trang_thai        int          not null,
    id_user           bigint       null,
    id_don_hang       bigint       null,
    id_ship           bigint       null,
    constraint UK_f0w7rfj8ol0ba3femubs1qhrj
        unique (id_user),
    constraint FK34tsgn27j9b3taayb4h747i0q
        foreign key (id_user) references khach_hang (id),
    constraint FK46vc7a697e4gxm5i9axgu6187
        foreign key (id_ship) references ship (id),
    constraint FK72xse95eh3b201m3avq9yumxd
        foreign key (id_don_hang) references nhan_vien (id)
);

create table phiep_nhap
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    gia_ban           double       null,
    ma_phieu_nhap     varchar(255) null,
    ngay_het_han      date         null,
    ngay_nhap         date         null,
    ngay_san_xuat     date         null,
    so_luong          int          not null,
    ten_phieu_nhap    varchar(255) null,
    id_ncc            bigint       null,
    id_phieu_nhap     bigint       null,
    constraint FKatid149553gmak7365b9990vu
        foreign key (id_ncc) references nha_cung_cap (id),
    constraint FKplb2ypryoqr2aoc3kkjuvvnyj
        foreign key (id_phieu_nhap) references nhan_vien (id)
);

create table phieu_giam_gia
(
    id                  bigint auto_increment
        primary key,
    create_by           varchar(255) null,
    create_date         date         null,
    last_updated_by     varchar(255) null,
    last_updated_date   date         null,
    dieu_kien_giam      double       null,
    gia_tri_phieu_giam  double       null,
    loai_phieu_giam_gia varchar(255) null,
    ngay_bat_dau        date         null,
    ngay_ket_thuc       date         null,
    ten_phieu_giam      varchar(255) null,
    id_dh               bigint       null,
    constraint UK_m3wo3f297cdy08yfi2wfrrca3
        unique (id_dh),
    constraint FKkmoabxjhbtgowit5vqpfte1gv
        foreign key (id_dh) references don_hang (id)
);

create table role_detail
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    id_role           bigint       null,
    id_taikhoan       bigint       null,
    constraint FK33yp9d3ei63di1qje6l0ix9jk
        foreign key (id_taikhoan) references tai_khoan (id),
    constraint FKcvrksfjnq2ovxa9qpe96o8gxc
        foreign key (id_role) references role (id)
);

create table san_pham
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    gia_ban           double       null,
    gia_nhap          double       null,
    mota              varchar(255) null,
    so_luong_ton      int          null,
    ten_san_pham      varchar(255) null,
    tieu_de           varchar(255) null,
    trang_thai        int          not null,
    id_phieu_nhap     bigint       null,
    constraint FKv8tkk56o9lvlapmlcifhw7nd
        foreign key (id_phieu_nhap) references phiep_nhap (id)
);

create table chi_tiet_khuyen_mai
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    id_km             bigint       null,
    id_sp             bigint       null,
    constraint FKl9j42xylgrm1leokxdivfian5
        foreign key (id_km) references khuyen_mai (id),
    constraint FKo0b7pflbtnrbc1jcr8pekldtq
        foreign key (id_sp) references san_pham (id)
);

create table danh_muc_chi_tiet
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    id_danh_muc       bigint       null,
    id_san_pham       bigint       null,
    constraint FK8h3m3aiq1x22m0tr8l8yji5q2
        foreign key (id_san_pham) references san_pham (id),
    constraint FKguadqvvllc05ay44359ynpdw3
        foreign key (id_danh_muc) references danh_muc (id)
);

create table don_hang_chi_tiet
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    so_luong          int          not null,
    tong_tien         double       null,
    id_donhang        bigint       null,
    id_sanpham        bigint       null,
    constraint FKjqeteauy9c376j98aaaxubqff
        foreign key (id_donhang) references don_hang (id),
    constraint FKr5s4as7cgxh41c2qdee31b5hg
        foreign key (id_sanpham) references san_pham (id)
);

create table gio_hang_chi_tiet
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    gia_tien          double       null,
    so_luong          int          not null,
    id_gio_hang       bigint       null,
    id_sp             bigint       null,
    constraint FKhkle2qtnnet5fq60x6tdhekql
        foreign key (id_gio_hang) references gio_hang (id),
    constraint FKtmvc1cpmk2uh5il1mn1t91nwn
        foreign key (id_sp) references san_pham (id)
);

create table images
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    image             varchar(255) null,
    id_sanpham        bigint       null,
    constraint FK2m3wunj9am5u85hw9reb4atmj
        foreign key (id_sanpham) references san_pham (id)
);

create table loai_san_pham
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    ten_loai_san_pham varchar(255) null,
    trang_thai        int          not null,
    id_sp             bigint       null,
    constraint UK_8fp6u7c4s833q52qwxb0ab02f
        unique (id_sp),
    constraint FKg02kielrge8an1qe9dt6h1483
        foreign key (id_sp) references san_pham (id)
);

create table thanh_toan
(
    id                bigint auto_increment
        primary key,
    create_by         varchar(255) null,
    create_date       date         null,
    last_updated_by   varchar(255) null,
    last_updated_date date         null,
    ngay_thanh_toan   datetime(6)  null,
    tong_tien         double       null,
    trang_thai        int          not null,
    id_httt           bigint       null,
    id_dh             bigint       null,
    constraint UK_nkqw3er6h0c98vvc0lxsq3oru
        unique (id_httt),
    constraint UK_rds0e3asxmbssyrcj3fan0qhb
        unique (id_dh),
    constraint FKj9aik00pboiihg2ekgf3ceusn
        foreign key (id_httt) references hinh_thuc_thanh_toan (id),
    constraint FKpm97rvch39n1bd15g5kl7wxo7
        foreign key (id_dh) references don_hang (id)
);

