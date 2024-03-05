package com.example.demo.core.Admin.service.impl.SanPham;

import com.example.demo.core.Admin.model.request.AdminSanPhamRepuest2;
import com.example.demo.core.Admin.model.request.AdminSanPhamRequest;
import com.example.demo.core.Admin.model.response.AdminImageResponse;
import com.example.demo.core.Admin.model.response.AdminSanPhamChiTiet2Response;
import com.example.demo.core.Admin.model.response.AdminSanPhamResponse;
import com.example.demo.core.Admin.model.response.SanPhamDOT;
import com.example.demo.core.Admin.repository.AdChiTietSanPhamReponsitory;
import com.example.demo.core.Admin.repository.AdImageReponsitory;
import com.example.demo.core.Admin.repository.AdSanPhamReponsitory;
import com.example.demo.core.Admin.service.AdSanPhamService.AdSanPhamService;
import com.example.demo.entity.*;
import com.example.demo.infrastructure.status.ChiTietSanPhamStatus;
import com.example.demo.util.Const;
import com.example.demo.util.ConstFile;
import com.example.demo.util.DatetimeUtil;
import com.example.demo.util.ImageToAzureUtil;
import com.microsoft.azure.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.*;


@Service
public class SanPhamServiceImpl implements AdSanPhamService {
    @Autowired
    private AdSanPhamReponsitory sanPhamReponsitory;

    @Autowired
    private AdChiTietSanPhamReponsitory chiTietSanPhamReponsitory;

    @Autowired
    ImageToAzureUtil getImageToAzureUtil;

    @Autowired
    private AdImageReponsitory imageReponsitory;

    @Autowired
    private LoSanPhamSer loSanPhamSer;

    @Override
    public List<AdminSanPhamResponse> getAll() {
        return sanPhamReponsitory.getAll();
    }

    public List<SanPhamDOT> getAlls() {
        List<SanPhamDOT> sanPhamDOTS = new ArrayList<>();
        for (AdminSanPhamResponse o : sanPhamReponsitory.getAll()) {
            List<AdminImageResponse> img = this.getProductImages(o.getId());
            List<AdminSanPhamChiTiet2Response> spct = this.findBySanPhamCT(o.getId());
            sanPhamDOTS.add(new SanPhamDOT(img, spct,
                    o.getId(), o.getTen(), o.getMoTa(), o.getMa(), o.getTrangThai()
                    , o.getNgayTao(), o.getSoLuongTon()
                    , o.getVatLieu(), o.getLoai(), o.getThuongHieu(), o.getAnh(), o.getNgaySua()));
        }
        return sanPhamDOTS;
    }

    @Override
    public SanPhamDOT findByIdSP(Integer id) {
        AdminSanPhamResponse o = sanPhamReponsitory.findByIdSP(id);
        List<AdminImageResponse> img = this.getProductImages(o.getId());
        List<AdminSanPhamChiTiet2Response> spct = this.findBySanPhamCT(o.getId());
        SanPhamDOT sanPhamDOT = new SanPhamDOT(img, spct,
                o.getId(), o.getTen(), o.getMoTa(), o.getMa(), o.getTrangThai()
                , o.getNgayTao(), o.getSoLuongTon()
                , o.getVatLieu(), o.getLoai(), o.getThuongHieu(), o.getAnh(), o.getNgaySua());

        return sanPhamDOT;
    }

    @Override
    public List<AdminSanPhamChiTiet2Response> findBySanPhamCT(Integer id) {
        return sanPhamReponsitory.get(id);
    }

    @Override
    public List<AdminImageResponse> getProductImages(Integer idProduct) {
        return imageReponsitory.findBySanPhamIds(idProduct);
    }

    @Override
    public Boolean findBySanPhamTen(AdminSanPhamRepuest2 request) {
        SanPham chiTiet = sanPhamReponsitory.findBySanPhamTenAndTrangThai(request.getTen());
        if (chiTiet != null) {
            if (chiTiet.getThuongHieu().getId() == request.getThuongHieu() && chiTiet.getLoai().getId() == request.getLoai()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<SanPhamDOT> loc(String comboBoxValue) {
        List<SanPhamDOT> sanPhamDOTS = new ArrayList<>();
        for (AdminSanPhamResponse o : sanPhamReponsitory.loc(comboBoxValue)) {
            List<AdminImageResponse> img = this.getProductImages(o.getId());
            List<AdminSanPhamChiTiet2Response> spct = this.findBySanPhamCT(o.getId());

            sanPhamDOTS.add(new SanPhamDOT(img, spct,
                    o.getId(), o.getTen(), o.getMoTa(), o.getMa(), o.getTrangThai()
                    , o.getNgayTao(), o.getSoLuongTon()
                    , o.getVatLieu(), o.getLoai(), o.getThuongHieu(), o.getAnh(), o.getNgaySua()));
        }

        return sanPhamDOTS;
    }


    public List<AdminSanPhamChiTiet2Response> locCTSP(String comboBoxValue) {
        return sanPhamReponsitory.locSPCT(comboBoxValue);
    }

    @Override
    public List<AdminSanPhamResponse> getSanPhamByIdLoai(Integer idloai) {
        return sanPhamReponsitory.getSanPhamByIdLoai(idloai);
    }

    @Override
    public List<AdminSanPhamResponse> getSanPhamByIdThuongHieu(Integer idthuonghieu) {
        return sanPhamReponsitory.getSanPhamByIdThuongHieu(idthuonghieu);
    }

    @Override
    public SanPhamDOT save(AdminSanPhamRepuest2 request) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
//        String linkAnh = getImageToAzureUtil.uploadImageToAzure(request.getAnh());
        String linkAnh = ConstFile.updoadLoadFile(request.getAnh());
        System.out.println(request.getAnh() + " " + "quyet anh");
        request.getImgMauSac().forEach(System.out::println);
        AdminSanPhamRequest sanPhamRequest = AdminSanPhamRequest.builder()
                .loai(request.getLoai())
                .thuongHieu(request.getThuongHieu())
                .moTa(request.getMoTa())
                .ten(request.getTen())
                .vatLieu(request.getVatLieu())
                .anh(Const.DOMAIN + linkAnh)
                .build();
        SanPham sanPham = this.saveSanPham(sanPhamRequest);
        this.saveSanPhamChiTiet(request, sanPham);
//        SanPham sanPham = null;
        return this.findByIdSP(sanPham.getId());
    }

    public SanPham saveSanPham(AdminSanPhamRequest request) {
        SanPham sanPham = request.dtoToEntity(new SanPham());
        SanPham sanPhamSave = sanPhamReponsitory.save(sanPham);
        // lưu ma theo dạng SP + id vừa tương ứng
        sanPhamSave.setMa("SP" + sanPhamSave.getId());
        return sanPhamReponsitory.save(sanPhamSave);
    }

    @Override
    public List<SanPhamChiTiet> saveSanPhamChiTiet(AdminSanPhamRepuest2 repuest2, SanPham sanPham) throws URISyntaxException, StorageException, InvalidKeyException, IOException {
        List<SanPhamChiTiet> lstsanPhamChiTiet = new ArrayList<>();
        lstsanPhamChiTiet = this.saveSanPhamIfIdSizenotNull(lstsanPhamChiTiet, repuest2, sanPham);
        List<SanPhamChiTiet> lstChiTiet = chiTietSanPhamReponsitory.saveAll(lstsanPhamChiTiet);
        return lstsanPhamChiTiet;
    }

    public List<SanPhamChiTiet> saveSanPhamIfIdSizenotNull(List<SanPhamChiTiet> lstsanPhamChiTiet, AdminSanPhamRepuest2 repuest2, SanPham sanPham) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        for (String idTrongLuong : repuest2.getIdMauSac()) {
            SanPhamChiTiet chiTiet = new SanPhamChiTiet();
            chiTiet.setSanPham(sanPham);
            chiTiet.setNgayTao(DatetimeUtil.getCurrentDate());
            chiTiet.setTrongLuong(TrongLuong.builder().id(Integer.valueOf(idTrongLuong)).build());
            lstsanPhamChiTiet.add(chiTiet);
        }
        List<SanPhamChiTiet> lstChiTiet = chiTietSanPhamReponsitory.saveAll(lstsanPhamChiTiet);
        for (int i = 0; i < lstsanPhamChiTiet.size(); i++) {
            SanPhamChiTiet sanPhamChiTiet = lstsanPhamChiTiet.get(i);
            String imgMauSacValue = repuest2.getImgMauSac().get(i);
            BigDecimal giaBan = BigDecimal.valueOf(Long.valueOf(repuest2.getGiaBan().get(i)));
            sanPhamChiTiet.setTrangThai(ChiTietSanPhamStatus.CON_HANG);
            sanPhamChiTiet.setMa("CTSP" + sanPhamChiTiet.getId());
            sanPhamChiTiet.setGiaBan(giaBan);
            sanPhamChiTiet.setSoLuongTon(Integer.valueOf(repuest2.getSoLuongSize().get(i)));
//            String linkAnh = getImageToAzureUtil.uploadImageToAzure(imgMauSacValue);
            String linkAnh = ConstFile.updoadLoadFile(imgMauSacValue);
            sanPhamChiTiet.setAnh(Const.DOMAIN + linkAnh);
            SanPhamChiTiet sanPhamChiTiet2 = chiTietSanPhamReponsitory.save(sanPhamChiTiet);
        }

        return lstsanPhamChiTiet;
    }


    @Override
    public SanPhamDOT delete(Integer id) {
        SanPham sanPham = sanPhamReponsitory.findById(id).get();
        if (sanPham != null) {
            sanPham.setTrangThai(0);
            sanPhamReponsitory.save(sanPham);
        }
        return this.findByIdSP(id);
    }

    @Override
    public SanPhamDOT khoiPhuc(Integer id) {
        SanPham sanPham = sanPhamReponsitory.findById(id).get();
        if (sanPham != null) {
            sanPham.setTrangThai(3);
            sanPhamReponsitory.save(sanPham);
        }
        return this.findByIdSP(sanPham.getId());
    }

    public List<Image> saveImage(SanPham sanPham, List<String> imgSanPham) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        HashSet<String> uniqueImgLinks = new HashSet<>(imgSanPham);
        List<Image> imageList = new ArrayList<>();
        for (String img : uniqueImgLinks) {
            Image image = new Image();
            String linkAnh = getImageToAzureUtil.uploadImageToAzure(img);
            image.setAnh(linkAnh);
            image.setSanPham(sanPham);
            image.setTrangThai(1);
            image.setNgayTao(DatetimeUtil.getCurrentDate());
            imageList.add(image);
        }
        List<Image> images = this.imageReponsitory.saveAll(imageList);
        for (int i = 0; i < images.size(); i++) {
            Image image = images.get(i);
            image.setMa("IM" + images.get(i).getId());
        }
        return this.imageReponsitory.saveAll(images);
    }
}
