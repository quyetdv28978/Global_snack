package com.dutn.be_do_an_vat.utility;

import com.dutn.be_do_an_vat.entity.LoSanPham;
import com.dutn.be_do_an_vat.entity.NhaCungCap;
import com.dutn.be_do_an_vat.entity.SanPham;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ImportAndExportExcel {
    public Map<String, List> processExcelFile(MultipartFile file) {
        Map<String, List> products = new HashMap<>();

        List sanphams = new ArrayList();
        List nhaCungCaps = new ArrayList();
        List los = new ArrayList();
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() == 0) {
                    continue;
                }
                try {
                    SanPham sanPham = SanPham.builder()
                            .tenSanPham(currentRow.getCell(4) == null ? " " : currentRow.getCell(4).getStringCellValue())
                            .giaBan(currentRow.getCell(4) == null ? 0 : currentRow.getCell(8).getNumericCellValue())
                            .soLuongTon(currentRow.getCell(4) == null ? 0 : (int) currentRow.getCell(7).getNumericCellValue())
                            .trangThai(0)
                            .build();

                    LoSanPham loSanPham = LoSanPham.builder()
                            .giaBan(currentRow.getCell(4) == null ? 0 : currentRow.getCell(8).getNumericCellValue())
                            .giaNhap(currentRow.getCell(4) == null ? 0 : currentRow.getCell(9).getNumericCellValue())
                            .maLo(currentRow.getCell(4) == null ? " " : currentRow.getCell(1).getStringCellValue())
                            .ngayNhap(FormatDate.getDate(currentRow.getCell(4) == null ? " " : currentRow.getCell(3).getStringCellValue()).atStartOfDay())
                            .ngaySanXuat(FormatDate.getDate(currentRow.getCell(4) == null ? " " : currentRow.getCell(5).getStringCellValue()))
                            .ngayHetHan(FormatDate.getDate(currentRow.getCell(4) == null ? " " : currentRow.getCell(6).getStringCellValue()))
                            .soLuongTon(currentRow.getCell(4) == null ? 0 : (int) currentRow.getCell(7).getNumericCellValue())
                            .tenLo(currentRow.getCell(4) == null ? " " : currentRow.getCell(4).getStringCellValue())
                            .tongTien((currentRow.getCell(4) == null ? 0 : currentRow.getCell(10).getNumericCellValue()))
                            .build();

                    NhaCungCap nhaCungCap = NhaCungCap.builder()
                            .tenNhaCungCap(currentRow.getCell(4) == null ? " " : currentRow.getCell(11).getStringCellValue())
                            .sdt(currentRow.getCell(4) == null ? " " : currentRow.getCell(12).getStringCellValue())
                            .email(currentRow.getCell(4) == null ? " " : currentRow.getCell(13).getStringCellValue())
                            .diaChiNhaCung(currentRow.getCell(4) == null ? " " : currentRow.getCell(14).getStringCellValue())
                            .build();

                    sanphams.add(sanPham);
                    los.add(loSanPham);
                    nhaCungCaps.add(nhaCungCap);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            products.put("sanpham", sanphams);
            products.put("lo", los);
            products.put("ncc", nhaCungCaps);
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

}
