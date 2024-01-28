package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.entity.Images;
import com.dutn.be_do_an_vat.entity.KhachHang;
import com.dutn.be_do_an_vat.repositoty.Image;
import com.dutn.be_do_an_vat.service.base_service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageSer implements IService<Images> {
    private Image imageRes;

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Optional add(Images images) {
        return Optional.of(images);
    }

    @Override
    public Optional update(Long id, Images images) {
        Images image = imageRes.findById(id).get();
        image.setImage(images.getImage());
        return Optional.of(imageRes.save(image));
    }

    @Override
    public void delete(Long id) {
        imageRes.deleteById(id);
    }

    @Override
    public Images search(Long id) {
        return null;
    }

    @Override
    public List getAll_filter(int soLuong, int trang) {
        return null;
    }

    @Override
    public KhachHang add(KhachHang kh) {
        return null;
    }
}
