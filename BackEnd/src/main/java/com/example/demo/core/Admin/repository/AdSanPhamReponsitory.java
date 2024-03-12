package com.example.demo.core.Admin.repository;

import com.example.demo.core.Admin.model.response.AdminSanPhamChiTiet2Response;
import com.example.demo.core.Admin.model.response.AdminSanPhamChiTietResponse;
import com.example.demo.core.Admin.model.response.AdminSanPhamResponse;
import com.example.demo.core.Admin.model.response.SanPhamDOT;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.SanPhamChiTiet;
import com.example.demo.reponsitory.SanPhamReponsitory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdSanPhamReponsitory extends SanPhamReponsitory {

    @Query(value = """
                     SELECT sp.id as id,sp.anh as anh, sp.ma as ma, sp.ten as ten,
                            sp.mo_ta as moTa, sp.trang_thai as trangThai, sp.ngay_tao as ngayTao,
                            sp.ngay_sua as ngaySua,th.ten as thuongHieu, vl.ten as vatLieu, l.ten as loai, SUM(lo.so_luong) as SoLuongTon
                     FROM datn.san_pham sp
                              JOIN datn.thuong_hieu th ON sp.id_thuong_hieu = th.id
                              JOIN datn.vat_lieu vl ON sp.id_vat_lieu = vl.id
                              JOIN datn.loai l ON sp.id_loai = l.id
                              LEFT JOIN datn.san_pham_chi_tiet spct ON spct.id_san_pham = sp.id
                              LEFT JOIN datn.lo_san_pham lo ON lo.id_ct_san_pham = spct.id
                     WHERE sp.trang_thai IN (1, 0, 3) and lo.trang_thai = 1 or lo.trang_thai is null
                     GROUP BY sp.id, sp.anh, sp.ma, sp.ten, sp.mo_ta, sp.trang_thai, sp.ngay_tao, sp.ngay_sua,  th.ten, vl.ten, l.ten
                     ORDER BY sp.id DESC;
            """, nativeQuery = true)
    List<AdminSanPhamResponse> getAll();

    @Query(value = """
                     SELECT sp.id as id,sp.anh as anh, sp.ma as ma, sp.ten as ten,
                            sp.mo_ta as moTa, sp.trang_thai as trangThai, sp.ngay_tao as ngayTao,
                            sp.ngay_sua as ngaySua, 
                            th.ten as thuongHieu, vl.ten as vatLieu, l.ten as loai,SUM(spct.so_luong_ton) as SoLuongTon
                         from san_pham sp
                     JOIN datn.thuong_hieu th ON sp.id_thuong_hieu = th.id
                     JOIN datn.vat_lieu vl ON sp.id_vat_lieu = vl.id
                     JOIN datn.loai l ON sp.id_loai = l.id
                     JOIN datn.san_pham_chi_tiet spct ON spct.id_san_pham = sp.id
                     WHERE sp.trang_thai IN (1, 0, 3) and sp.id=:id 
                     GROUP BY sp.id, sp.anh, sp.ma, sp.ten, sp.mo_ta, sp.trang_thai,
                      sp.ngay_tao, sp.ngay_sua,  th.ten, vl.ten, l.ten
                     ORDER BY sp.id DESC
            """, nativeQuery = true)
    AdminSanPhamResponse findByIdSP(Integer id);

    @Query(value = """
                     SELECT sp.id as id,sp.anh as anh, sp.ma as ma, sp.ten as ten,
                            sp.mo_ta as moTa, sp.trang_thai as trangThai, sp.ngay_tao as ngayTao,
                            sp.ngay_sua as ngaySua, 
                            th.ten as thuongHieu, vl.ten as vatLieu, l.ten as loai,0 as SoLuongTon
                         from san_pham sp
                     JOIN datn.thuong_hieu th ON sp.id_thuong_hieu = th.id
                     JOIN datn.vat_lieu vl ON sp.id_vat_lieu = vl.id
                     JOIN datn.loai l ON sp.id_loai = l.id
                     WHERE sp.trang_thai IN (1, 0, 3) and sp.id=:id 
                     GROUP BY sp.id, sp.anh, sp.ma, sp.ten, sp.mo_ta, sp.trang_thai,
                      sp.ngay_tao, sp.ngay_sua,  th.ten, vl.ten, l.ten
                     ORDER BY sp.id DESC
            """, nativeQuery = true)
    AdminSanPhamResponse findByIdSPOne(Integer id);


    @Query(value = """ 
                        SELECT
                            spct.id,sp.ma,sp.ten,spct.gia_ban as giaBan,spct.gia_nhap as giaNhap,
                            lo.so_luong as soLuongTon,spct.trang_thai as trangThai,
                            sp.mo_ta as moTa,l.ten AS loai,spct.anh as anh,
                            th.ten as thuongHieu, km.ten as tenKM,
                            km.thoi_gian_bat_dau as thoiGianBatDau, km.thoi_gian_ket_thuc as thoiGianKetThuc,
                            spct.gia_sau_giam as giaSauGiam, km.gia_tri_giam as giaTriGiam,
                            tl.value as trongLuong,
                            lo.ma_lo as maLo, lo.ten_lo as tenLo, lo.ngay_het_han as ngayHetHan
                        FROM datn.san_pham_chi_tiet spct join datn.san_pham sp on spct.id_san_pham = sp.id
                                                         join datn.loai l  on sp.id_loai = l.id
                                                         join datn.thuong_hieu th on sp.id_thuong_hieu = th.id
                                                         join datn.trong_luong tl on spct.id_trong_luong = tl.id
                                                         left join datn.khuyen_mai km on spct.id_khuyen_mai = km.id
                                                         JOIN datn.lo_san_pham lo ON lo.id_ct_san_pham = spct.id
                        WHERE sp.id  =:id
                        and lo.trang_thai in (1, 3)
            """, nativeQuery = true)
    List<AdminSanPhamChiTiet2Response> get(@Param("id") Integer id);


    @Query(value = """ 
                        SELECT
                            spct.id,sp.ma,sp.ten,spct.gia_ban as giaBan,spct.gia_nhap as giaNhap,
                            lo.so_luong as soLuongTon,spct.trang_thai as trangThai,
                            sp.mo_ta as moTa,l.ten AS loai,spct.anh as anh,
                            th.ten as thuongHieu, km.ten as tenKM,
                            km.thoi_gian_bat_dau as thoiGianBatDau, km.thoi_gian_ket_thuc as thoiGianKetThuc,
                            spct.gia_sau_giam as giaSauGiam, km.gia_tri_giam as giaTriGiam,
                            tl.value as trongLuong,
                            lo.ma_lo as maLo, lo.ten_lo as tenLo, lo.ngay_het_han as ngayHetHan
                        FROM datn.san_pham_chi_tiet spct join datn.san_pham sp on spct.id_san_pham = sp.id
                                                         join datn.loai l  on sp.id_loai = l.id
                                                         join datn.thuong_hieu th on sp.id_thuong_hieu = th.id
                                                         join datn.trong_luong tl on spct.id_trong_luong = tl.id
                                                         left join datn.khuyen_mai km on spct.id_khuyen_mai = km.id
                                                         left JOIN datn.lo_san_pham lo ON lo.id_ct_san_pham = spct.id
                        WHERE sp.id =:id and
                         lo.trang_thai is null 
            """, nativeQuery = true)
    List<AdminSanPhamChiTiet2Response> getALlLoSanPhamNot(@Param("id") Integer id);

    @Query(value = """ 
                           SELECT 
                                  spct.id,sp.ma,sp.ten,spct.gia_ban as giaBan,spct.gia_nhap as giaNhap,
                                  lo.so_luong as soLuongTon,spct.trang_thai as trangThai,
                                  sp.mo_ta as moTa,l.ten AS loai,spct.anh as anh,
                                  th.ten as thuongHieu, km.ten as tenKM,
                                  km.thoi_gian_bat_dau as thoiGianBatDau, km.thoi_gian_ket_thuc as thoiGianKetThuc,
                                  spct.gia_sau_giam as giaSauGiam, km.gia_tri_giam as giaTriGiam,
                                  tl.value as trongLuong, 
                                  lo.ma_lo as maLo, lo.ten_lo as tenLo, lo.ngay_het_han as ngayHetHan
                           FROM datn.san_pham_chi_tiet spct join datn.san_pham sp on spct.id_san_pham = sp.id
                                   join datn.loai l  on sp.id_loai = l.id
                                   join datn.thuong_hieu th on sp.id_thuong_hieu = th.id
                                    join datn.trong_luong tl on spct.id_trong_luong = tl.id
                                     left join datn.khuyen_mai km on spct.id_khuyen_mai = km.id
                                     left JOIN datn.lo_san_pham lo ON lo.id_ct_san_pham = spct.id
                           WHERE spct.id  =:id and lo.trang_thai in (1,3)
            """, nativeQuery = true)
    AdminSanPhamChiTiet2Response getByid(@Param("id") Integer id);

    @Query(value = """ 
                           SELECT
                               spct.id,sp.ma,sp.ten,spct.gia_ban as giaBan,spct.gia_nhap as giaNhap,
                               lo.so_luong as soLuongTon,spct.trang_thai as trangThai,
                               sp.mo_ta as moTa,l.ten AS loai,spct.anh as anh,
                               th.ten as thuongHieu, km.ten as tenKM,
                               km.thoi_gian_bat_dau as thoiGianBatDau, km.thoi_gian_ket_thuc as thoiGianKetThuc,
                               spct.gia_sau_giam as giaSauGiam, km.gia_tri_giam as giaTriGiam,
                               tl.value as trongLuong,
                               lo.ma_lo as maLo, lo.ten_lo as tenLo, lo.ngay_het_han as ngayHetHan
                           FROM datn.san_pham_chi_tiet spct join datn.san_pham sp on spct.id_san_pham = sp.id
                                                            join datn.loai l  on sp.id_loai = l.id
                                                            join datn.thuong_hieu th on sp.id_thuong_hieu = th.id
                                                            join datn.trong_luong tl on spct.id_trong_luong = tl.id
                                                            left join datn.khuyen_mai km on spct.id_khuyen_mai = km.id
                                                            LEFT JOIN datn.lo_san_pham lo ON lo.id_ct_san_pham = spct.id
                           WHERE spct.id  =:id and lo.trang_thai is null
            """, nativeQuery = true)
    AdminSanPhamChiTiet2Response getByidLoSanPham(@Param("id") Integer id);

    @Query(value = """ 
            SELECT sp.id as id,sp.anh as anh, sp.ma as ma, sp.ten as ten,
                                   sp.mo_ta as moTa, sp.trang_thai as trangThai, sp.ngay_tao as ngayTao,
                                   sp.ngay_sua as ngaySua, 
                                   th.ten as thuongHieu, vl.ten as vatLieu, l.ten as loai, SUM(lo.so_luong_ton) as SoLuongTon
                               FROM datn.san_pham sp
                               JOIN datn.thuong_hieu th ON sp.id_thuong_hieu = th.id
                               JOIN datn.vat_lieu vl ON sp.id_vat_lieu = vl.id
                               JOIN datn.loai l ON sp.id_loai = l.id
                               JOIN datn.san_pham_chi_tiet spct ON spct.id_san_pham = sp.id
                                                             JOIN datn.lo_san_pham lo ON lo.id_ct_san_pham = spct.id
                            where (CASE
                                                      WHEN :comboBoxValue = 'conHang' THEN  sp.trang_thai = 1
                                                      WHEN :comboBoxValue = 'hetHang' THEN  sp.trang_thai = 0
                                                      WHEN :comboBoxValue = 'tonKho' THEN sp.trang_thai = 3
                                                      WHEN :comboBoxValue = 'dangKhuyenMai' THEN spct.id_khuyen_mai IS NOT NULL
                                                      END) and lo.trang_thai in (1,3)
                      GROUP BY sp.id,sp.anh, sp.ma, sp.ten, sp.mo_ta, sp.trang_thai,
                       sp.ngay_tao, sp.ngay_sua,  th.ten, vl.ten, l.ten
                               
            """, nativeQuery = true)
    List<AdminSanPhamResponse> loc(@Param("comboBoxValue") String comboBoxValue);


    @Query("select  pot from  SanPham  pot where pot.ten like :keyword or pot.ma like :keyword or pot.loai.ten like :keyword or pot.thuongHieu.ten like :keyword")
    Page<SanPham> search(String keyword, Pageable pageable);

    @Query("select  pot from  SanPham  pot " +
            "where pot.ten like :keyword ")
    SanPham findByTenSanPhamExcel(String keyword);

    @Query(value = "select  pt  from  SanPham  pt where pt.ten like :ten and (pt.trangThai=1 or pt.trangThai=2 or pt.trangThai=3)")
    SanPham findBySanPhamTenAndTrangThai(String ten);


    @Query(value = """ 
                             SELECT
                                  spct.id,sp.ma,sp.ten,spct.gia_ban as giaBan,spct.gia_nhap as giaNhap,
                                  spct.so_luong_ton as soLuongTon,spct.trang_thai as trangThai,
                                  
                                  sp.mo_ta as moTa,l.ten AS loai,spct.anh as anh,
                                  th.ten as thuongHieu, km.ten as tenKM,
                                  km.thoi_gian_bat_dau as thoiGianBatDau, km.thoi_gian_ket_thuc as thoiGianKetThuc,
                                  spct.gia_sau_giam as giaSauGiam, km.gia_tri_giam as giaTriGiam,
                                  tl.value as trongLuong
                           FROM datn.san_pham_chi_tiet spct join datn.san_pham sp on spct.id_san_pham = sp.id
                                   join datn.loai l  on sp.id_loai = l.id
                                   join datn.thuong_hieu th on sp.id_thuong_hieu = th.id
                                    join datn.trong_luong tl on spct.id_trong_luong = tl.id
                                    left join datn.khuyen_mai km on spct.id_khuyen_mai = km.id
                                    
                            WHERE (CASE
                                   WHEN :comboBoxValue = 'conHang' THEN spct.trang_thai = 1
                                   WHEN :comboBoxValue = 'hetHang' THEN spct.trang_thai = 2
                                   WHEN :comboBoxValue = 'tonKho' THEN spct.trang_thai = 3
                                   WHEN :comboBoxValue = 'dangKhuyenMai' THEN spct.id_khuyen_mai IS NOT NULL
                                   END)
            """, nativeQuery = true)
    List<AdminSanPhamChiTiet2Response> locSPCT(@Param("comboBoxValue") String comboBoxValue);


    @Query(value = """
                     SELECT sp.id as id,sp.anh as anh, sp.ma as ma, sp.ten as ten,
                            sp.mo_ta as moTa, sp.trang_thai as trangThai, sp.ngay_tao as ngayTao,
                            sp.ngay_sua as ngaySua, 
                            th.ten as thuongHieu, vl.ten as vatLieu, l.ten as loai, SUM(lo.so_luong_ton) as SoLuongTon
                     FROM datn.san_pham sp
                     JOIN datn.thuong_hieu th ON sp.id_thuong_hieu = th.id
                     JOIN datn.vat_lieu vl ON sp.id_vat_lieu = vl.id
                     JOIN datn.loai l ON sp.id_loai = l.id
                     JOIN datn.san_pham_chi_tiet spct ON spct.id_san_pham = sp.id
                                                   JOIN datn.lo_san_pham lo ON lo.id_ct_san_pham = spct.id
                     WHERE sp.trang_thai IN (1, 0, 3) and l.id =:idloai and lo.trang_thai in (1,3)
                     GROUP BY sp.id, sp.anh, sp.ma, sp.ten, sp.mo_ta, sp.trang_thai, sp.ngay_tao,
                      sp.ngay_sua,  th.ten, vl.ten, l.ten
                     ORDER BY sp.id DESC
            """, nativeQuery = true)
    List<AdminSanPhamResponse> getSanPhamByIdLoai(@Param("idloai") Integer idloai);

    @Query(value = """
                     SELECT sp.id as id,sp.anh as anh, sp.ma as ma, sp.ten as ten,
                            sp.mo_ta as moTa, sp.trang_thai as trangThai, sp.ngay_tao as ngayTao,
                            sp.ngay_sua as ngaySua, 
                            th.ten as thuongHieu, vl.ten as vatLieu, l.ten as loai, SUM(lo.so_luong_ton) as SoLuongTon
                     FROM datn.san_pham sp
                     JOIN datn.thuong_hieu th ON sp.id_thuong_hieu = th.id
                     JOIN datn.vat_lieu vl ON sp.id_vat_lieu = vl.id
                     JOIN datn.loai l ON sp.id_loai = l.id
                     JOIN datn.san_pham_chi_tiet spct ON spct.id_san_pham = sp.id
                                                   JOIN datn.lo_san_pham lo ON lo.id_ct_san_pham = spct.id
                     WHERE sp.trang_thai IN (1, 0, 3) and th.id =:idthuonghieu and lo.trang_thai in (1,3)
                     GROUP BY sp.id, sp.anh, sp.ma, sp.ten, sp.mo_ta, sp.trang_thai,
                      sp.ngay_tao, sp.ngay_sua,  th.ten, vl.ten, l.ten
                     ORDER BY sp.id DESC
            """, nativeQuery = true)
    List<AdminSanPhamResponse> getSanPhamByIdThuongHieu(@Param("idthuonghieu") Integer idthuonghieu);
}
