package com.example.demo.core.Admin.model.response;

import com.example.demo.infrastructure.status.ImportStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class AdminExcelAddSanPhamResponse {

    private String tenSanPham;

    private List<String> tenTrongLuong;
    private List<String> donVis;

    private Integer idVatLieu;

    private String tenVatLieu;

    private List<String> giaBan;

    private List<String> soLuong;

    private String moTa;

    private String tenThuongHieu;

    private String tenLoai;

    private Integer idLoai;

    private Integer idThuongHieu;

    private String soLuongTrongLuongs;

    private List<String> tenLo, maLo;
    private List<LocalDate> ngayHetHan;

    private List<String> idTrongLuongs;

    private String importMessageSanPham;

    private String importMessageVatLieu;

    private String importMessageTrongLuong;

    private String importMessageSoLuongTrongLuong;

    private String importMessageGiaBan;

    private String importMessageLoai;

    private String importMessageThuongHieu;

    private boolean isError;

    private ImportStatus importStatus;

    public ImportStatus getImportStatus() {
        if (isError) {
            importStatus = ImportStatus.FAIL;
        } else {
            importStatus = ImportStatus.SUCCESS;
        }
        return importStatus;
    }
}
