package acorn.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import acorn.admin.dto.CoachDto;
import acorn.admin.entity.Coach;
import acorn.admin.repository.CoachRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private CoachRepository coachRepository;

    // 이미지 파일 저장 경로
    private final String IMAGE_DIR = "src/main/resources/static/admin/img/";

    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @GetMapping("/{id}")
    public Coach getCoachById(@PathVariable("id") int id) {
        return coachRepository.findById(id).orElse(null);
    }

    @PostMapping(consumes = "multipart/form-data")
    public Coach insertCoach(@RequestPart("coachImage") MultipartFile coachImage, @RequestPart("coachData") CoachDto coachDto) throws IOException {
        String imagePath = saveImage(coachImage);
        Coach coach = Coach.toEntity(coachDto);
        coach.setCoachImage(imagePath);
        return coachRepository.save(coach);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Coach updateCoach(@PathVariable("id") int id, @RequestPart("coachImage") MultipartFile coachImage, @RequestPart("coachData") CoachDto coachDto) throws IOException {
        return coachRepository.findById(id)
            .map(existingCoach -> {
                String imagePath = null;
                try {
                    if (coachImage != null && !coachImage.isEmpty()) {
                        imagePath = saveImage(coachImage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (imagePath != null) {
                    existingCoach.setCoachImage(imagePath);
                }
                existingCoach.updateFromDto(coachDto);
                return coachRepository.save(existingCoach);
            })
            .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCoach(@PathVariable("id") int id) {
        coachRepository.deleteById(id);
    }

    private String saveImage(MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            String filePath = Paths.get(IMAGE_DIR, fileName).toString();
            image.transferTo(new File(filePath));
            return "/images/" + fileName;
        }
        return null;
    }
}
