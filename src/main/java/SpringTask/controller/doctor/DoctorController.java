package SpringTask.controller.doctor;

import SpringTask.model.dto.DoctorDto;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import SpringTask.service.doctor.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    // [1] 의사 등록
    @PostMapping
    public boolean addDoctor(@RequestBody DoctorDto doctorDto){
        boolean result = doctorService.addDoctor(doctorDto);
        return result;
    }

    // [2] 의사 조회
    @GetMapping
    public List<DoctorDto> findAll(){
        List<DoctorDto> result = doctorService.findAll();
        return result;
    }

    // [3] 의사 수정
    @PutMapping
    public boolean updateDoctor(@RequestBody DoctorDto doctorDto){
        boolean result = doctorService.updateDoctor(doctorDto);
        return result;
    }
    // [4] 의사 삭제
    @DeleteMapping
    public boolean deleteDoctor(@RequestParam int dno){
        boolean result = doctorService.deleteDoctor(dno);
        return result;
    }

    // [5] 의사 상세조회
    @GetMapping("/view")
    public DoctorDto find(@RequestParam int dno){
        DoctorDto result = doctorService.find(dno);
        return result;
    }
}
