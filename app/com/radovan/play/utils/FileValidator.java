package com.radovan.play.utils;

import com.radovan.play.exceptions.DataNotValidatedException;
import jakarta.inject.Singleton;
import org.apache.commons.io.FilenameUtils;
import play.libs.Files;
import play.mvc.Http.MultipartFormData.FilePart;

import java.util.Optional;


@Singleton
public class FileValidator {

    public Boolean validateFile(FilePart<Files.TemporaryFile> file) {
        Boolean returnValue = false;
        String extension = FilenameUtils.getExtension(file.getFilename());
        if (isSupportedExtension(extension)) {
            returnValue = true;
        } else {
            throw new DataNotValidatedException("The file is not valid!");
        }

        return returnValue;
    }

    public Boolean isSupportedExtension(String extension) {
        Boolean returnValue = false;
        Optional<String> extensionOptional = Optional.ofNullable(extension);
        if (extensionOptional.isPresent()) {
            if (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")) {
                returnValue = true;
            }
        }

        return returnValue;
    }

}
