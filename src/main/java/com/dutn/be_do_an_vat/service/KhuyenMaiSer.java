package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTODieuKienKhuyenMai;
import com.dutn.be_do_an_vat.dto.DTOKhuyenMai;
import com.dutn.be_do_an_vat.entity.*;
import com.dutn.be_do_an_vat.repositoty.*;
import com.dutn.be_do_an_vat.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class KhuyenMaiSer {
    @Autowired
    private IKhuyenMai khuyenMaiRes;
    @Autowired
    private IChiTietKhuyenMai chiTietKhuyenMaiRes;
    @Autowired
    private IDieuKienApKhuyenMai dieuKienApKhuyenMaiRes;
    @Autowired
    private ISanPham sanPhamRes;
    @Autowired
    private ILoaiKhuyenMai loaiKhuyenMaiRes;

    /**
     * Thêm khuyến mãi theo % hoặc giảm tiền
     *
     * @param khuyenMaiDTO :tenKhuyenMai, ngayBatDau, ngayKetThuc, trangThai, giaTriKhuyenMai,loaiKhuyenMai,idsps;
     */
    @Transactional
    public void addKhuyenMaiGiamTheoTiLe(DTOKhuyenMai khuyenMaiDTO) {
        KhuyenMai khuyenMai1 = MapperUtils.dtoToEntity(khuyenMaiDTO, KhuyenMai.class);
        khuyenMai1.setLoaiKhuyenMai(loaiKhuyenMaiRes.findById(1l).get());
        khuyenMaiRes.save(khuyenMai1);
        chiTietKhuyenMaiRes.saveAll(khuyenMaiDTO.getIdsps().stream()
                .map(i -> ChiTietKhuyenMai
                        .builder()
                        .khuyenMai(khuyenMai1)
                        .sanPham(sanPhamRes.findById(i).get())
                        .build())
                .collect(Collectors.toSet()));
    }

    /**
     * Thêm khuyến mãi theo quà tặng
     *
     * @param dieuKienKhuyenMai :tenKhuyenMai, ngayBatDau, ngayKetThuc, trangThai, giaTriKhuyenMai,loaiKhuyenMai,idsps,
     *                          soLuongTang, dieuKienTang, loaiSanPhamTang, sanPhamLaSao;
     */
    @Transactional
    public void addkhuyenMaiTangKem(DTODieuKienKhuyenMai dieuKienKhuyenMai) {
        KhuyenMai khuyenMai = MapperUtils.dtoToEntity(dieuKienKhuyenMai, KhuyenMai.class);
        khuyenMai.setLoaiKhuyenMai(loaiKhuyenMaiRes.findById(1l).get());
        khuyenMaiRes.save(khuyenMai);

        DieuKienApDungKhuyenMai dieuKienApDungKhuyenMai = MapperUtils.entityToDTO(dieuKienKhuyenMai, DieuKienApDungKhuyenMai.class);
        dieuKienApDungKhuyenMai.setKhuyenMai(khuyenMai);
        dieuKienApKhuyenMaiRes.save(dieuKienApDungKhuyenMai);
    }

    public void updateKhuyenMai(DTOKhuyenMai khuyenMaiDTO, Long idKhuyenMai) {
        KhuyenMai khuyenMai = MapperUtils.dtoToEntity(khuyenMaiDTO, KhuyenMai.class);
        khuyenMai.setId(idKhuyenMai);
        khuyenMaiRes.save(khuyenMai);
    }

    @Transactional
    public void updateKhuyenMaiQuick(Long idkm, int trangThai) {
        KhuyenMai khuyenMai = khuyenMaiRes.findById(idkm).get();
        khuyenMai.setTrangThai(trangThai);
        khuyenMaiRes.save(khuyenMai);
    }

    @Transactional
    public void deleteKhuyenMai(Long idkm) {
        chiTietKhuyenMaiRes.deleteAll(chiTietKhuyenMaiRes.showKhuyenMaiDetailS(idkm));
        khuyenMaiRes.deleteById(idkm);
    }
}
