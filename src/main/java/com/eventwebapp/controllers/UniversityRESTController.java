package com.eventwebapp.controllers;

import com.eventwebapp.entities.other.Picture;
import com.eventwebapp.entities.other.PictureMapping;
import com.eventwebapp.entities.rso.University;
import com.eventwebapp.repositories.PictureMappingRepo;
import com.eventwebapp.repositories.PictureRepo;
import com.eventwebapp.repositories.UniversityRepo;
import org.apache.tomcat.jni.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Xavier on 11/4/2015.
 *
 * TODO: map the university creation page to a /sadmin/university/new
 * TODO:  map others to /university/
 */

@Controller
@RequestMapping("/university")
public class UniversityRESTController {

    @Autowired
    private
    UniversityRepo universityRepo;

    @Autowired
    PictureMappingRepo pictureMappingRepo;

    @Autowired
    private
    PictureRepo pictureRepo;

    // TODO: GET university creation page
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newUniversity(Model model, University university){
        return "test/placeholder";
    }

    // TODO: POST to university creation page
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewUniversity(Model model, @Valid University university, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("message", "there were errors creating this university");
            return "test/placeholder";
        }

        universityRepo.save(university);
        return "test/placeholder";
    }


    // TODO: GET single university profile
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUniversity(Model model, @PathVariable("id") Long id){
        if(!universityRepo.exists(id)){
            model.addAttribute("message", String.format("There is no university with id %d", id));
        }

        model.addAttribute(universityRepo.findOne(id));

        return "test/placeholder";
    }


    // TODO: 11/10/15 Put all of the methods pertaining to pictures into their own controller
    @RequestMapping(value = "/pictures", method = RequestMethod.GET)
    String pics(Model model){
        return "pictures";
    }

    @RequestMapping(value = "/api/pictures", method = RequestMethod.GET)
    ResponseEntity<List<Picture>> pictures(/*@PathVariable("id") Long id*/){
//        List <Picture> list =
//                pictureMappingRepo.findAll()
//                            .stream()
//                            .filter(pictureMapping -> pictureMapping.getMapped_to_id() == id)
//                            .map(pictureMapping -> pictureRepo.findOne(pictureMapping.getMapped_to_id()))
//                            .collect(Collectors.toList());

        List<Picture> list = pictureRepo.findAll();

        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/pictures", method = RequestMethod.POST)
    ResponseEntity<?> uploadPicture(@RequestParam("picture") MultipartFile picture){
        System.out.println("in pic post");
        try{

            String filename = picture.getOriginalFilename();
            String directory = "/images";

            String filepath = Paths.get(directory, filename).toString();

            System.out.println("NEW PICTURE: " + filepath);

            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(
                            new File(filepath)
                    ));

            stream.write(picture.getBytes());
            stream.close();

            pictureRepo.save(new Picture(filepath));
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
