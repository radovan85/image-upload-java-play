package com.radovan.play.repository;

import com.radovan.play.entity.ImageEntity;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    ImageEntity save(ImageEntity imageEntity);

    Optional<ImageEntity> findById(Long imageId);

    List<ImageEntity> findAll();

    void deleteById(Long imageId);
}
