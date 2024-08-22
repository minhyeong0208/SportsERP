package acorn.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import acorn.admin.dto.CoachDto;
import acorn.admin.entity.Coach;
import acorn.admin.service.CoachService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    @GetMapping("/{id}")
    public Coach getCoachById(@PathVariable("id") int id) {
        return coachService.getCoachById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public Coach insertCoach(@RequestPart("coachImage") MultipartFile coachImage, @RequestPart("coachData") CoachDto coachDto) {
        try {
            return coachService.saveCoach(coachDto, coachImage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("코치를 저장하는 동안 오류가 발생했습니다.");
        }
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Coach updateCoach(@PathVariable("id") int id, @RequestPart("coachImage") MultipartFile coachImage, @RequestPart("coachData") CoachDto coachDto) {
        try {
            return coachService.updateCoach(id, coachDto, coachImage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("코치를 업데이트하는 동안 오류가 발생했습니다.");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCoach(@PathVariable("id") int id) {
        coachService.deleteCoach(id);
    }
}
