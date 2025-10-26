package com.radovan.play.modules;

import com.google.inject.AbstractModule;
import com.radovan.play.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public class InstanceModule extends AbstractModule {

    protected void configure(){
        bind(SessionFactory.class).toInstance(getSessionFactory());
        bind(ModelMapper.class).toInstance(getMapper());
    }

    public SessionFactory getSessionFactory(){
        return new HibernateUtil().getSessionFactory();
    }

    public ModelMapper getMapper(){
        ModelMapper returnValue = new ModelMapper();
        returnValue.getConfiguration()
                .setAmbiguityIgnored(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return returnValue;
    }
}
