package com.radovan.play.converter;

import com.radovan.play.dto.ImageDto;
import com.radovan.play.entity.ImageEntity;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

// converts entity to dto and vice versa
@Singleton
public class TempConverter {

    private ModelMapper mapper;

    @Inject
    private void initialize(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ImageDto entityToDto(ImageEntity imageEntity) {
        return mapper.map(imageEntity, ImageDto.class);
    }

    public ImageEntity dtoToEntity(ImageDto imageDto) {
        return mapper.map(imageDto, ImageEntity.class);
    }
}
