package acorn.admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import acorn.admin.dto.CoachDto;
import acorn.admin.entity.Coach;
import acorn.admin.repository.CoachRepository;

@Service
public class CoachService {

    private final CoachRepository coachRepository;
    private final String IMAGE_DIR = Paths.get("src/main/resources/static/admin/img/").toAbsolutePath().toString();

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach getCoachById(int id) {
        return coachRepository.findById(id).orElse(null);
    }

    public Coach saveCoach(CoachDto coachDto, MultipartFile coachImage) throws IOException {
        String imagePath = saveImage(coachImage);
        Coach coach = Coach.toEntity(coachDto);
        coach.setCoachImage(imagePath);
        return coachRepository.save(coach);
    }

    public Coach updateCoach(int id, CoachDto coachDto, MultipartFile coachImage) throws IOException {
        return coachRepository.findById(id)
            .map(existingCoach -> {
                try {
                    if (coachImage != null && !coachImage.isEmpty()) {
                        String imagePath = saveImage(coachImage);
                        existingCoach.setCoachImage(imagePath);
                    }
                    existingCoach.updateFromDto(coachDto);
                    return coachRepository.save(existingCoach);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("코치 정보를 업데이트하는 동안 오류가 발생했습니다.");
                }
            })
            .orElseThrow(() -> new RuntimeException("코치를 찾을 수 없습니다."));
    }

    public void deleteCoach(int id) {
        coachRepository.deleteById(id);
    }

    private String saveImage(MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String newFileName = System.currentTimeMillis() + extension;
            Path filePath = Paths.get(IMAGE_DIR, newFileName);

            File directory = new File(IMAGE_DIR);
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IOException("디렉토리를 생성할 수 없습니다: " + directory.getAbsolutePath());
            }

            try {
                image.transferTo(filePath.toFile());
                return "/admin/img/" + newFileName;
            } catch (IOException e) {
                throw new IOException("이미지를 저장할 수 없습니다: " + filePath.toString(), e);
            }
        }
        return null;
    }

}
