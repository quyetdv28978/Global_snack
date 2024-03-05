package com.example.demo.core.Admin.service.impl.SanPham;

import com.example.demo.core.Admin.model.request.AdminSanPhamRequest;
import com.example.demo.core.Admin.model.response.AdminExcelAddSanPhamBO;
import com.example.demo.core.Admin.model.response.AdminExcelAddSanPhamResponse;
import com.example.demo.core.Admin.model.response.AdminSanPhamResponse;
import com.example.demo.core.Admin.repository.*;
import com.example.demo.core.Admin.service.AdSanPhamService.AdExcelAddSanPhamService;
import com.example.demo.entity.*;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
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

    @Override
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
        this.savaData(adminExcelAddSanPhamBO);
        adminExcelAddSanPhamBO.setTotalError(importStatusCounts.getOrDefault(true, 0L));
        adminExcelAddSanPhamBO.setTotalSuccess(importStatusCounts.getOrDefault(false, 0L));

        return adminExcelAddSanPhamBO;
    }

    @Override
    public AdminExcelAddSanPhamResponse processRow(Row row) {
        AdminExcelAddSanPhamResponse userDTO = new AdminExcelAddSanPhamResponse();

        Long stt = ExcelUtils.getCellLong(row.getCell(0));
        String tenSanPham = ExcelUtils.getCellString(row.getCell(1));
        String TenVatLieu = ExcelUtils.getCellString(row.getCell(2));
        String valueTrongLuong = ExcelUtils.getCellString(row.getCell(3));
        List<String> idTrongLuongs = new ArrayList<>();
        String giaBan = ExcelUtils.getCellString(row.getCell(4));
       // String giaNhap = ExcelUtils.getCellString(row.getCell(5));
        String soLuongSize = String.valueOf(ExcelUtils.getCellString(row.getCell(7)));
        String anhMau1 = ExcelUtils.getCellString(row.getCell(8));
        String anhChinh = ExcelUtils.getCellString(row.getCell(9));
        String anh1 = ExcelUtils.getCellString(row.getCell(10));
        String moTa = ExcelUtils.getCellString(row.getCell(13));
        String tenLoai = ExcelUtils.getCellString(row.getCell(14));
        String tenThuongHieu = ExcelUtils.getCellString(row.getCell(15));

//        TrongLuong trongLuong = adTrongLuongRepository.findByTenTrongLuongExcel(Integer.valueOf(valueTrongLuong));
        VatLieu vatLieu = vatLieuReponsitory.findByTenVatLieuExcel(TenVatLieu);
        Loai loai = loaiReponsitory.findByTens(tenLoai);
        ThuongHieu thuongHieu = thuongHieuReponsitory.findByTen(tenThuongHieu);

        String[] soLuongSizeArray = soLuongSize.split(",");
        List<String> listSoLuongSize = new ArrayList<>();

        String[] arrayGiaBan = giaBan.split(",");
        List<String> lstGiaBan = new ArrayList<>();

        for (String giaBans : arrayGiaBan) {
            String trimmedMauSac = giaBans.trim(); // Loại bỏ khoảng trắng trước và sau phần tử
            lstGiaBan.add(trimmedMauSac);
        }

        for (String size : soLuongSizeArray) {
            String trimmedSize = size.trim(); // Loại bỏ khoảng trắng trước và sau phần tử
            listSoLuongSize.add(trimmedSize);
        }
        String[] arrayImgMau = anhMau1.split(",");
        List<String> listAnhMauSac = new ArrayList<>();

        for (String img : arrayImgMau) {
            String trimmedMauSac = img.trim(); // Loại bỏ khoảng trắng trước và sau phần tử
            listAnhMauSac.add(trimmedMauSac);
        }


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

        }
//        else if (DataUltil.isNullObject(giaNhap)) {
//            userDTO.setImportMessageGiaNhap("Giá Nhập không được để trống tại ví trí: " + stt);
//            userDTO.setError(true);
//
//        }
else if (DataUltil.isNullObject(anhChinh)) {
            userDTO.setImportMessageAnhChinh("ảnh chính không được để trống vị trí: " + stt);
            userDTO.setError(true);

        } else if (DataUltil.isNullObject(tenLoai)) {
            userDTO.setImportMessageLoai("Tên loại không được để trống tại vị trí: " + stt);
            userDTO.setError(true);

        } else if (DataUltil.isNullObject(tenThuongHieu)) {
            userDTO.setImportMessageThuongHieu("Tên thương hiệu không được để trống tại vị trí: " + stt);
            userDTO.setError(true);

        }
        if (DataUltil.isNullObject(vatLieu)) {
            userDTO.setImportMessageVatLieu("Vật liệu không tồn tại vị trí: " + stt);
            userDTO.setError(true);

        }  else if (DataUltil.isNullObject(loai)) {
            userDTO.setImportMessageLoai("loại không tồn tại vị trí: " + stt);
            userDTO.setError(true);
        } else if (DataUltil.isNullObject(thuongHieu)) {
            userDTO.setImportMessageThuongHieu("Thương hiệu không tồn tại vị trí: " + stt);
            userDTO.setError(true);
        }  else if (!DataUltil.isNullObject(valueTrongLuong)) {
            String[] sizeArray = valueTrongLuong.split(",");
            List<String> listTenTrongLuong = new ArrayList<>();
            for (String size : sizeArray) {
                String trimmedSize = size.trim(); // Loại bỏ khoảng trắng trước và sau phần tử
                listTenTrongLuong.add(trimmedSize);
            }
           if (lstGiaBan.size() < listTenTrongLuong.size()) {
                userDTO.setImportMessageGiaBan("Bạn cần nhập thêm giá bán ,tổng sản phẩm là: " + listTenTrongLuong.size() + " tại vị trí: " + stt);
                userDTO.setError(true);

            } else if (lstGiaBan.size() > listTenTrongLuong.size()) {
                userDTO.setImportMessageGiaBan("Thừa giá bán, tổng sản phẩm là: " + listTenTrongLuong.size() + " tại vị trí: " + stt);
                userDTO.setError(true);

            }
            else if (listSoLuongSize.size() > listTenTrongLuong.size()) {
                userDTO.setSoLuongTrongLuongs("Thừa số lượng, tổng sản phẩm là: " + listTenTrongLuong.size() + " tại vị trí: " + stt);
                userDTO.setError(true);

            } else if (listSoLuongSize.size() < listTenTrongLuong.size()) {
                userDTO.setSoLuongTrongLuongs("Bạn cần nhập thêm số lượng , tổng sản phẩm là: " + listTenTrongLuong.size() + " tại vị trí: " + stt);
                userDTO.setError(true);

            } else if (listAnhMauSac.size() > listTenTrongLuong.size()) {
                userDTO.setImportMessageImageMau("Thừa số lượng ảnh của màu  sản phẩm là: " + listTenTrongLuong.size() + " tại vị trí: " + stt);
                userDTO.setError(true);

            } else if (listAnhMauSac.size() < listTenTrongLuong.size()) {
                userDTO.setImportMessageImageMau("Bạn cần nhập thêm ảnh, tổng sản phẩm là: " + listTenTrongLuong.size() + " tại vị trí: " + stt);
                userDTO.setError(true);

            } else {
                List<String> imgMauSacList = azureImgProduct(listAnhMauSac);
                userDTO.setIdVatLieu(vatLieu.getId());
                userDTO.setTenVatLieu(TenVatLieu);
                userDTO.setImportMessageVatLieu("SUCCESS");
                userDTO.setTenSanPham(tenSanPham);
                userDTO.setImportMessageSanPham("SUCCESS");
                userDTO.setGiaBan(lstGiaBan);
                userDTO.setImportMessageGiaBan("SUCCESS");
              //  userDTO.setGiaNhap(lstGiaNhap);
             //   userDTO.setImportMessageGiaNhap("SUCCESS");
                userDTO.setImportMessageImageMau("SUCCESS");


                try {
                    String linkAnh = getImageToAzureUtil.uploadImageToAzure(anhChinh);
                    userDTO.setAnhChinh(linkAnh);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                userDTO.setImportMessageAnhChinh("SUCCESS");
                List<String> listAnh = new ArrayList<>(Arrays.asList(anh1));
                List<String> imgList = azureImgProduct(listAnh);
                userDTO.setImagesProduct(imgList);
                userDTO.setMoTa(moTa);
                userDTO.setIdLoai(loai.getId());
                userDTO.setTenLoai(tenLoai);
                userDTO.setImportMessageLoai("SUCCESS");
                userDTO.setIdThuongHieu(thuongHieu.getId());
                userDTO.setTenThuongHieu(tenThuongHieu);
                userDTO.setImportMessageThuongHieu("SUCCESS");
                userDTO.setTenTrongLuongs(listTenTrongLuong);
                userDTO.setImportMessageTrongLuong("SUSSCESS");
                userDTO.setImportMessageSoLuongTrongLuong("SUSSCESS");
                userDTO.setError(false);
                userDTO.setError(false);
            }


        }

        return userDTO;
    }

    @Override
    public AdminExcelAddSanPhamBO savaData(AdminExcelAddSanPhamBO adminExcelAddSanPhamBO) {

        try {
            for (AdminExcelAddSanPhamResponse o : adminExcelAddSanPhamBO.getResponseList()) {
                if (o.getTenSanPham() == null || o.getGiaBan() == null
                        || o.getTenLoai() == null
                        || o.getTenThuongHieu() == null
                        || o.getIdLoai() == null || o.getIdThuongHieu() == null
                        || o.getIdTrongLuong() == null || o.getIdVatLieu() == null) {
                    return null;
                }
            }
            if (adminExcelAddSanPhamBO.getTotalError() != null && Integer.valueOf(adminExcelAddSanPhamBO.getTotalError().toString()) != 0)
                return null;
            List<SanPhamChiTiet> saveSanPhamChiTiet = this.saveAll(adminExcelAddSanPhamBO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminExcelAddSanPhamBO;
    }

    @Override
    public List<SanPhamChiTiet> saveAll(AdminExcelAddSanPhamBO adminExcelAddSanPhamBO) {
        List<SanPhamChiTiet> sanPhamChiTiets = new ArrayList<>();

        adminExcelAddSanPhamBO.getResponseList().forEach(BO -> {

            SanPham chiTiet = sanPhamReponsitory.findBySanPhamTenAndTrangThai(BO.getTenSanPham());
            if (chiTiet == null) {
                try {
                    this.save(BO);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StorageException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            } else {
                SanPham sanPham = sanPhamReponsitory.findBySanPhamTenAndTrangThai(BO.getTenSanPham());
                if (sanPham != null) {
                    sanPham.setAnh(BO.getAnhChinh());
                    sanPham.setNgaySua(DatetimeUtil.getCurrentDate());
                    sanPham.setTen(BO.getTenSanPham());
                    sanPham.setMoTa(BO.getMoTa());
                    sanPham.setLoai(Loai.builder().id(Integer.valueOf(BO.getIdLoai())).build());
                    sanPham.setThuongHieu(ThuongHieu.builder().id(Integer.valueOf(BO.getIdThuongHieu())).build());
                    sanPhamReponsitory.save(sanPham);
                }

                List<SanPhamChiTiet> lstChiTiet = chiTietSanPhamReponsitory.findByListSanPhamId(chiTiet.getId());
                if (lstChiTiet != null) {
                    for (int i = 0; i < lstChiTiet.size(); i++) {
                        SanPhamChiTiet sanPhamChiTiet = lstChiTiet.get(i);
                        BigDecimal giaBan = BigDecimal.valueOf(Long.valueOf(BO.getGiaBan().get(i)));
                 //       BigDecimal giaNhap = BigDecimal.valueOf(Long.valueOf(BO.getGiaNhap().get(i)));

                  //      sanPhamChiTiet.setGiaNhap(giaNhap);
                        sanPhamChiTiet.setGiaBan(giaBan);
                        sanPhamChiTiet.setTrangThai(1);
//                        sanPhamChiTiet.setSoLuongTon(Integer.valueOf(BO.getSoLuongSize().get(i)));
//                        sanPhamChiTiet.setAnh(imgMauSacValue);
                        chiTietSanPhamReponsitory.save(sanPhamChiTiet);
                    }
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
                .anh(request.getAnhChinh())
                .build();
        SanPham sanPham = this.saveSanPham(sanPhamRequest);
        if (request.getImagesProduct() == null || request.getImagesProduct().isEmpty()) {
            //  this.saveImage(sanPham, request.getImgMauSac());
        } else {
//            for (String img : request.getImagesProduct()) {
//                request.getImgMauSac().add(img);
//            }
//            this.saveImage(sanPham, request.getImgMauSac());
        }
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

                request.getImagesProduct().forEach(images -> {
                    Image image = new Image();
                    //    image.setSanPhamChiTiet(sanPhamChiTiet);
                    image.setTrangThai(1);
                    image.setNgayTao(DatetimeUtil.getCurrentDate());
                    image.setAnh(images);
                    imageList.add(image);
                });
            } else {
                // Thiết lập thông tin image mới
                request.getImagesProduct().forEach(images -> {
                    Image image = new Image();
                    //    image.setSanPhamChiTiet(sanPhamChiTiet);
                    image.setTrangThai(1);
                    image.setNgayTao(DatetimeUtil.getCurrentDate());
                    image.setAnh(images);
                    imageList.add(image);
                });
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
