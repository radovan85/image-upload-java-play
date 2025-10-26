package com.radovan.play.controllers;

import com.radovan.play.service.ImageService;
import jakarta.inject.Inject;
import play.libs.Files;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class ImageController extends Controller {

    private ImageService imageService;

    @Inject
    private void initialize(ImageService imageService) {
        this.imageService = imageService;
    }

    public Result getAllImages() {
        return ok(Json.toJson(imageService.listAll()));
    }

    public Result getImage(Long imageId) {
        return ok(Json.toJson(imageService.getImageById(imageId)));
    }

    public Result deleteImage(Long imageId) {
        imageService.deleteImage(imageId);
        return ok(Json.toJson("The image with id " + imageId + " has been removed!"));
    }

    public Result storeImage(Http.Request request) {
        Http.MultipartFormData<Files.TemporaryFile> body = request.body().asMultipartFormData();
        Http.MultipartFormData.FilePart<Files.TemporaryFile> filePart = body.getFile("file");

        if (filePart != null) {
            imageService.addImage(filePart);
            return created("The image has been stored!");

        } else {
            return badRequest("File is missing");
        }
    }

    public Result updateImage(Http.Request request, Long imageId) {
        Http.MultipartFormData<Files.TemporaryFile> body = request.body().asMultipartFormData();
        Http.MultipartFormData.FilePart<Files.TemporaryFile> filePart = body.getFile("file");

        if (filePart != null) {
            imageService.updateImage(filePart, imageId);
            return created("The image has been updated!");

        } else {
            return badRequest("File is missing");
        }
    }
}
