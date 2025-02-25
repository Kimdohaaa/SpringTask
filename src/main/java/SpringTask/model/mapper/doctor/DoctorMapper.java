package SpringTask.model.mapper.doctor;

import SpringTask.model.dto.DoctorDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorMapper {
    // [1] 의사 등록
    @Insert("insert into doctor (dname, pname) values (#{dname}, #{pname})")
    public boolean addDoctor(DoctorDto doctorDto);
    // [2] 의사 조회
    @Select("select * from doctor")
    public List<DoctorDto> findAll();
    // [3] 의사 수정
    @Update("update doctor set dname = #{dname}, pname = #{pname} where dno = #{dno}"  )
    public boolean updateDoctor(DoctorDto doctorDto);
    // [4] 의사 삭제
    @Delete("delete from doctor where dno = #{dno}"  )
    public boolean deleteDoctor(int dno);
    // [5] 의사 상세조회
    @Select("select * from doctor where dno = #{dno}"  )
    public DoctorDto find(int dno);
}
