package com.radovan.play.modules;

import com.google.inject.AbstractModule;
import com.radovan.play.converter.TempConverter;
import com.radovan.play.repository.ImageRepository;
import com.radovan.play.repository.impl.ImageRepositoryImpl;
import com.radovan.play.service.ImageService;
import com.radovan.play.service.impl.ImageServiceImpl;
import com.radovan.play.utils.FileValidator;
import com.radovan.play.utils.HibernateUtil;

public class AutoBindModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(ImageService.class).to(ImageServiceImpl.class).asEagerSingleton();
        bind(ImageRepository.class).to(ImageRepositoryImpl.class).asEagerSingleton();
        bind(TempConverter.class).asEagerSingleton();
        bind(FileValidator.class).asEagerSingleton();
        bind(HibernateUtil.class).asEagerSingleton();
    }
}
