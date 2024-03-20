package com.example.demo.core.Admin.service.impl.SanPham;

import com.example.demo.core.Admin.model.request.AdminSanPhamRequest;
import com.example.demo.core.Admin.model.response.AdminExcelAddSanPhamBO;
import com.example.demo.core.Admin.model.response.AdminExcelAddSanPhamResponse;
import com.example.demo.core.Admin.model.response.AdminSanPhamResponse;
import com.example.demo.core.Admin.repository.*;
import com.example.demo.core.Admin.service.AdSanPhamService.AdExcelAddSanPhamService;
import com.example.demo.entity.*;
import com.example.demo.reponsitory.ILoSanPhamRes;
import com.example.demo.util.DataUltil;
import com.example.demo.util.DatetimeUtil;
import com.example.demo.util.ExcelUtils;
import com.example.demo.util.ImageToAzureUtil;
import com.microsoft.azure.storage.StorageException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdminExcelAddSanPhamSerivecImpl implements AdExcelAddSanPhamService {

    @Autowired
    private AdSanPhamReponsitory sanPhamReponsitory;

    @Autowired
    private AdChiTietSanPhamReponsitory chiTietSanPhamReponsitory;

    @Autowired
    private AdVatLieuReponsitory vatLieuReponsitory;

    @Autowired
    private AdTrongLuongRepository adTrongLuongRepository;

    @Autowired
    private ImageToAzureUtil imageToAzureUtil;

    @Autowired
    private AdImageReponsitory imageReponsitory;

    @Autowired
    private AdLoaiReponsitory loaiReponsitory;

    @Autowired
    private AdThuongHieuReponsitory thuongHieuReponsitory;

    @Autowired
    ImageToAzureUtil getImageToAzureUtil;

    @Autowired
    private LoSanPhamSer loSanPhamSer;
    @Autowired
    private ILoSanPhamRes loSanPhamRes;

    @Override
    @Transactional
    public AdminExcelAddSanPhamBO previewDataImportExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<AdminExcelAddSanPhamResponse> list = StreamSupport.stream(sheet.spliterator(), false)
                .skip(1) // Bỏ qua 2 dòng đầu tiên
                .filter(row -> !ExcelUtils.checkNullLCells(row, 1))
                .map(row -> processRow(row))
                .collect(Collectors.toList());


        Map<Boolean, Long> importStatusCounts = list.stream()
                .collect(Collectors.groupingBy(AdminExcelAddSanPhamResponse::isError, Collectors.counting()));

        // set tổng số bản ghi lỗi, tổng số bản ghi thành công, tổng số bản ghi
        AdminExcelAddSanPhamBO adminExcelAddSanPhamBO = new AdminExcelAddSanPhamBO();
        adminExcelAddSanPhamBO.setResponseList(list);
        adminExcelAddSanPhamBO.setTotal(Long.parseLong(String.valueOf(list.size())));
        adminExcelAddSanPhamBO.setTotalError(importStatusCounts.getOrDefault(true, 0L));
        adminExcelAddSanPhamBO.setTotalSuccess(importStatusCounts.getOrDefault(false, 0L));
        if (adminExcelAddSanPhamBO.getTotalError() == 0) this.saveAll(adminExcelAddSanPhamBO);
        return adminExcelAddSanPhamBO;
    }

    @Override
    @Transactional
    public AdminExcelAddSanPhamResponse processRow(Row row) {
        AdminExcelAddSanPhamResponse userDTO = new AdminExcelAddSanPhamResponse();

        Long stt = ExcelUtils.getCellLong(row.getCell(0));
        String tenSanPham = ExcelUtils.getCellString(row.getCell(1));
        String TenVatLieu = ExcelUtils.getCellString(row.getCell(2));
        String valueTrongLuong = ExcelUtils.getCellString(row.getCell(3));
        String[] valueTrongLuongs = valueTrongLuong.split(",");
        String donVi = ExcelUtils.getCellString(row.getCell(4));
        String[] donVis = donVi.split(",");
        String giaBan = ExcelUtils.getCellString(row.getCell(5));
        String[] giaBans = giaBan.split(",");
        String soLuong = String.valueOf(ExcelUtils.getCellString(row.getCell(6)));
        String[] soLuongs = soLuong.split(",");
        String moTa = ExcelUtils.getCellString(row.getCell(7));
        String loaiSanPham = ExcelUtils.getCellString(row.getCell(8));
        String thuongHieu = ExcelUtils.getCellString(row.getCell(9));
        String tenLo = ExcelUtils.getCellString(row.getCell(10));
        String tenLos[] = tenLo.split(",");
        String maLo = ExcelUtils.getCellString(row.getCell(11));
        String maLos[] = maLo.split(",");
        String ngayHetHan = ExcelUtils.getCellString(row.getCell(12));
        List<LocalDate> ngayHetHans = Arrays.stream(ngayHetHan.split(","))
                .map(i -> LocalDate.parse(i, DateTimeFormatter.ofPattern("dd/MM/yyyy"))).collect(Collectors.toList());


//        Optional<LoSanPham> loSanPham = loSanPhamRes.findByTenLo(tenLo);
        Optional<SanPham> sanPham = sanPhamReponsitory.findByTenSanPhamExcel(tenSanPham);


        if (DataUltil.isNullObject(tenSanPham)) {
            userDTO.setImportMessageSanPham("Tên Sản phẩm không được để trống tại vị trí: " + stt);
            userDTO.setError(true);
        } else if (DataUltil.isNullObject(TenVatLieu)) {
            userDTO.setImportMessageVatLieu("Tên vật liệu không được để trống tại vị trí: " + stt);
            userDTO.setError(true);
        } else if (DataUltil.isNullObject(valueTrongLuong)) {
            userDTO.setImportMessageTrongLuong("Trọng lượng không được để trống tại vị trí: " + stt);
            userDTO.setError(true);
        } else if (DataUltil.isNullObject(giaBan)) {
            userDTO.setImportMessageGiaBan("Giá bán không được để trống tại vị trí: " + stt);
            userDTO.setError(true);
        } else if (DataUltil.isNullObject(loaiSanPham)) {
            userDTO.setImportMessageLoai("Tên loại không được để trống tại vị trí: " + stt);
            userDTO.setError(true);

        } else if (DataUltil.isNullObject(thuongHieu)) {
            userDTO.setImportMessageThuongHieu("Tên thương hiệu không được để trống tại vị trí: " + stt);
            userDTO.setError(true);

        } else if (sanPham.isPresent()) {
            userDTO.setImportMessageSanPham("Sản phẩm đã tồn tại");
            userDTO.setError(true);
        } else {
            userDTO.setTenVatLieu(TenVatLieu);
            userDTO.setImportMessageVatLieu("SUCCESS");
            userDTO.setTenSanPham(tenSanPham);
            userDTO.setImportMessageSanPham("SUCCESS");
            userDTO.setGiaBan(List.of(giaBans));
            userDTO.setImportMessageGiaBan("SUCCESS");
            userDTO.setSoLuong(List.of(soLuongs));
            userDTO.setMoTa(moTa);
            userDTO.setTenLoai(loaiSanPham);
            userDTO.setImportMessageLoai("SUCCESS");
            userDTO.setTenThuongHieu(thuongHieu);
            userDTO.setImportMessageThuongHieu("SUCCESS");
            userDTO.setTenTrongLuong(List.of(valueTrongLuongs));
            userDTO.setImportMessageTrongLuong("SUCCESS");
            userDTO.setDonVis(List.of(donVis));
            userDTO.setMaLo(List.of(maLos));
            userDTO.setTenLo(List.of(tenLos));
            userDTO.setNgayHetHan(ngayHetHans);
            userDTO.setError(false);
            userDTO.setError(false);
        }
        return userDTO;
    }

    private boolean checkData(Optional optional) {
        return !optional.isPresent();
    }

    @Override
    @Transactional
    public List<SanPhamChiTiet> saveAll(AdminExcelAddSanPhamBO adminExcelAddSanPhamBO) {
        adminExcelAddSanPhamBO.getResponseList().forEach(i -> {
            Optional<VatLieu> vatLieu = vatLieuReponsitory.findByTenVatLieuExcel(i.getTenVatLieu());
            Optional<Loai> loai = loaiReponsitory.findByTens(i.getTenLoai());
            Optional<ThuongHieu> thuongHieuReal = thuongHieuReponsitory.findByTen(i.getTenThuongHieu());
            Optional<SanPham> sanPham = sanPhamReponsitory.findByTenSanPhamExcel(i.getTenSanPham());
            for (int j = 0; j < i.getTenTrongLuong().size(); j++) {
                Optional<TrongLuong> trongLuong = adTrongLuongRepository.findByTenTrongLuongAndDonViExcel(Integer.valueOf(i.getTenTrongLuong().get(j)), i.getDonVis().get(j));
                Optional<LoSanPham> loSanPham = loSanPhamRes.findByTenLo(i.getTenLo().get(j));
                if (checkData(trongLuong)) {
                    trongLuong = trongLuong.of(adTrongLuongRepository.save(TrongLuong.builder()
                            .donVi(i.getDonVis().get(j))
                            .value(Integer.valueOf(i.getTenTrongLuong().get(j)))
                            .trangThai(1)
                            .build()));
                }
                if (checkData(vatLieu)) {
                    vatLieu = vatLieu.of(vatLieuReponsitory.save(VatLieu.builder()
                            .ten(i.getTenVatLieu())
                            .ma(new Random().nextInt(10000) + "")
                            .trangThai(1)
                            .build()));
                }
                if (checkData(loai)) {
                    loai = loai.of(loaiReponsitory.save(Loai.builder()
                            .ten(i.getTenLoai())
                            .trangThai(1)
                            .build()));
                }
                if (checkData(thuongHieuReal)) {
                    thuongHieuReal = thuongHieuReal.of(thuongHieuReponsitory.save(ThuongHieu.builder()
                            .ten(i.getTenThuongHieu())
                            .trangThai(1)
                            .build()));
                }
                if (checkData(sanPham)) {
                    sanPham = sanPham.of(sanPhamReponsitory.save(SanPham.builder()
                            .thuongHieu(thuongHieuReal.get())
                            .loai(loai.get())
                            .vatLieu(vatLieu.get())
                            .moTa(i.getMoTa())
                            .ten(i.getTenSanPham())
                            .ma(new Random().nextInt(100000) + "")
                            .trangThai(1)
                            .build()));
                }
                SanPhamChiTiet sanPhamChiTiet = chiTietSanPhamReponsitory.save(SanPhamChiTiet.builder()
                        .ma(new Random().nextInt(10000) + "")
                        .sanPham(sanPham.get())
                        .trongLuong(trongLuong.get())
                        .giaBan(new BigDecimal(i.getGiaBan().get(j)))
                        .trangThai(1)
                        .soLuongTon(Integer.valueOf(i.getSoLuong().get(j)))
                        .build());

                if (checkData(loSanPham)) {
                    loSanPham = loSanPham.of(loSanPhamRes.save(LoSanPham.builder()
                            .tenLo(i.getTenLo().get(j))
                            .maLo(i.getMaLo().get(j))
                            .ngayHetHan(i.getNgayHetHan().get(j))
                            .soLuong(Integer.parseInt(i.getSoLuong().get(j)))
                            .ngayNhap(LocalDateTime.now())
                            .trangThai(1)
                            .sanPhamChiTiet(sanPhamChiTiet)
                            .build()));
                }
            }
        });
        return null;
    }

    public AdminSanPhamResponse save(AdminExcelAddSanPhamResponse request) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        //  String linkAnh = getImageToAzureUtil.uploadImageToAzure(request.getAnhChinh());
        AdminSanPhamRequest sanPhamRequest = AdminSanPhamRequest.builder()
                .loai(request.getIdLoai())
                .thuongHieu(request.getIdThuongHieu())
                .moTa(request.getMoTa())
                .ten(request.getTenSanPham())
                .vatLieu(request.getIdVatLieu())
                .build();
        SanPham sanPham = this.saveSanPham(sanPhamRequest);
        List<SanPhamChiTiet> saveSanPhamChiTiet = this.saveSanPhamChiTiet(request, sanPham);
        return this.findByIdSP(sanPham.getId());
    }

    public AdminSanPhamResponse findByIdSP(Integer id) {
        return sanPhamReponsitory.findByIdSP(id);
    }

    public SanPham saveSanPham(AdminSanPhamRequest request) {
        SanPham sanPham = request.dtoToEntity(new SanPham());
        SanPham sanPhamSave = sanPhamReponsitory.save(sanPham);
        // lưu ma theo dạng SP + id vừa tương ứng
        sanPhamSave.setMa("SP" + sanPhamSave.getId());
        return sanPhamReponsitory.save(sanPhamSave);
    }

    public List<SanPhamChiTiet> saveSanPhamChiTiet(AdminExcelAddSanPhamResponse repuest2, SanPham sanPham) throws URISyntaxException, StorageException, InvalidKeyException, IOException {

        List<SanPhamChiTiet> lstsanPhamChiTiet = new ArrayList<>();
//        if (repuest2.getIdSize() == null || repuest2.getIdSize().isEmpty()) {
//            lstsanPhamChiTiet = this.saveSanPhamIfIdSizeNull(lstsanPhamChiTiet, repuest2, sanPham);
//        } else {
//            lstsanPhamChiTiet = this.saveSanPhamIfIdSizenotNull(lstsanPhamChiTiet, repuest2, sanPham);
//        }

        //   List<SanPhamChiTiet> lstChiTiet = chiTietSanPhamReponsitory.saveAll(lstsanPhamChiTiet);
        return lstsanPhamChiTiet;
    }


    public List<Image> saveImage(SanPham sanPham, List<String> imgSanPham) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        HashSet<String> uniqueImgLinks = new HashSet<>(imgSanPham);
        List<Image> imageList = new ArrayList<>();
        for (String img : uniqueImgLinks) {
            Image image = new Image();
            //      String linkAnh = getImageToAzureUtil.uploadImageToAzure(img);
            image.setAnh(img);
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


    @Override
    public void saveAllImage(AdminExcelAddSanPhamBO adminExcelAddSanPhamBO, List<SanPhamChiTiet> savedSanPhamChiTiets) {
        List<Image> imageList = new ArrayList<>();

        adminExcelAddSanPhamBO.getResponseList().forEach(request -> {
            int index = adminExcelAddSanPhamBO.getResponseList().indexOf(request);
            SanPhamChiTiet sanPhamChiTiet = savedSanPhamChiTiets.get(index);
            SanPhamChiTiet existingChiTiet = chiTietSanPhamReponsitory.findBySanPhamTen(request.getTenSanPham());
            List<Image> list = imageReponsitory.findBySanPhamId(existingChiTiet.getId());

            if (!list.isEmpty()) {
                // Cập nhật thông tin image
                list.forEach(image -> {
                    //   image.setSanPhamChiTiet(sanPhamChiTiet);
                    image.setTrangThai(1);
                    image.setNgaySua(DatetimeUtil.getCurrentDate());
                });

                // Xóa toàn bộ ảnh và thêm các ảnh mới
                imageReponsitory.deleteAll(list);

            }
        });

        List<Image> savedImages = imageReponsitory.saveAll(imageList);
        savedImages.forEach(image -> {
            image.setMa("IM" + image.getId());
        });
        imageReponsitory.saveAll(savedImages);
    }

    @Override
    public List<String> azureImgProduct(List<String> url) {
        ExecutorService executor = Executors.newFixedThreadPool(20); // Số lượng luồng tối đa là 10
        List<CompletableFuture<String>> futures = url.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> {
                    String azureImageUrl = null;
                    try {
                        azureImageUrl = imageToAzureUtil.uploadImageToAzure(s);
                    } catch (URISyntaxException | StorageException | IOException | InvalidKeyException e) {
                        e.printStackTrace();
                    }
                    return azureImageUrl;
                }, executor))
                .collect(Collectors.toList());

        List<String> imgList = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        executor.shutdown();
        return imgList;
    }
}
