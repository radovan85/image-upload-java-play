package com.radovan.play.service.impl;

import com.radovan.play.converter.TempConverter;
import com.radovan.play.dto.ImageDto;
import com.radovan.play.entity.ImageEntity;
import com.radovan.play.exceptions.FileUploadException;
import com.radovan.play.exceptions.InstanceUndefinedException;
import com.radovan.play.repository.ImageRepository;
import com.radovan.play.service.ImageService;
import com.radovan.play.utils.FileValidator;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import play.mvc.Http;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private TempConverter tempConverter;
    private FileValidator fileValidator;

    @Inject
    private void initialize(ImageRepository imageRepository, TempConverter tempConverter, FileValidator fileValidator) {
        this.imageRepository = imageRepository;
        this.tempConverter = tempConverter;
        this.fileValidator = fileValidator;
    }

    @Override
    public ImageDto addImage(Http.MultipartFormData.FilePart<play.libs.Files.TemporaryFile> file) {
       fileValidator.validateFile(file);
       try {
           ImageDto imageDto = new ImageDto();
           imageDto.setName(Objects.requireNonNull(file.getFilename()));
           imageDto.setContentType(file.getContentType());
           imageDto.setSize(file.getFileSize());
           Path path = file.getRef().path();
           byte[] fileData = Files.readAllBytes(path);
           imageDto.setData(fileData);

           ImageEntity storedImage = imageRepository.save(tempConverter.dtoToEntity(imageDto));
           return tempConverter.entityToDto(storedImage);
       }catch (Exception exc) {
           throw new FileUploadException(exc.getMessage());
        }
    }

    @Override
    public ImageDto getImageById(Long imageId) {
        ImageEntity imageEntity = imageRepository.findById(imageId)
                .orElseThrow(() -> new InstanceUndefinedException("The image has not been found!"));
        return tempConverter.entityToDto(imageEntity);
    }

    @Override
    public List<ImageDto> listAll() {
        return imageRepository.findAll()
                .stream().map(tempConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteImage(Long imageId) {
        getImageById(imageId);
        imageRepository.deleteById(imageId);
    }

    @Override
    public ImageDto updateImage(Http.MultipartFormData.FilePart<play.libs.Files.TemporaryFile> file, Long imageId) {
        getImageById(imageId);
        fileValidator.validateFile(file);
        try {
            ImageDto imageDto = new ImageDto();
            imageDto.setId(imageId);
            imageDto.setName(Objects.requireNonNull(file.getFilename()));
            imageDto.setContentType(file.getContentType());
            imageDto.setSize(file.getFileSize());
            Path path = file.getRef().path();
            byte[] fileData = Files.readAllBytes(path);
            imageDto.setData(fileData);

            ImageEntity updatedImage = imageRepository.save(tempConverter.dtoToEntity(imageDto));
            return tempConverter.entityToDto(updatedImage);
        }catch (Exception exc) {
            throw new FileUploadException(exc.getMessage());
        }
    }
}
