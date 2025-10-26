package com.radovan.play.service;

import com.radovan.play.dto.ImageDto;
import play.libs.Files;
import play.mvc.Http;

import java.util.List;

public interface ImageService {

    ImageDto addImage(Http.MultipartFormData.FilePart<Files.TemporaryFile> file);

    ImageDto getImageById(Long imageId);

    List<ImageDto> listAll();

    void deleteImage(Long imageId);

    ImageDto updateImage(Http.MultipartFormData.FilePart<Files.TemporaryFile> file,Long imageId);
}
