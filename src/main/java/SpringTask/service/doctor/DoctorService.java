package SpringTask.service.doctor;

import SpringTask.model.dto.DoctorDto;
import SpringTask.model.mapper.doctor.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    // [1] 의사 등록
    public boolean addDoctor(DoctorDto doctorDto){
        boolean result = doctorMapper.addDoctor(doctorDto);
        return result;
    }

    // [2] 의사 조회
    public List<DoctorDto> findAll(){
        List<DoctorDto> result = doctorMapper.findAll();
        return result;
    }

    // [3] 의사 수정
    public boolean updateDoctor(DoctorDto doctorDto){
        boolean result = doctorMapper.updateDoctor(doctorDto);
        return result;
    }

    // [4] 의사 삭제
    public boolean deleteDoctor(int dno){
        boolean result = doctorMapper.deleteDoctor(dno);
        return result;
    }

    // [5] 의사 상세조회
    public DoctorDto find(int dno){
        DoctorDto result = doctorMapper.find(dno);
        return result;
    }
}
