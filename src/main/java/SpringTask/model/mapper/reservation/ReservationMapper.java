package SpringTask.model.mapper.reservation;

import SpringTask.model.dto.ReservationDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationMapper {
    // 예약현황조회
    @Select("SELECT r.*, m.mname, d.dname FROM reservation r JOIN member m ON m.mno = r.mno JOIN doctor d ON r.dno = d.dno order by r.rno asc")
    public List<ReservationDto> findAll();

    // 예약 등록
    @Insert("INSERT INTO reservation (mno, dno, rdate, rtime) VALUES (#{mno}, #{dno}, #{rdate}, #{rtime})")
    public boolean addR(ReservationDto reservationDto);
    // [3] 예약 수정
    @Update("UPDATE reservation SET rdate=#{rdate}, rtime = #{rtime} WHERE rno = #{rno}")
    public boolean updateR(ReservationDto reservationDto);
    // [4] 예약 삭제
    @Delete("DELETE FROM reservation WHERE rno = #{rno}")
    public boolean deleteR(int rno);
    // [5] 환자별 예약조회
    @Select("SELECT r.*, m.mname, d.dname , d.pname" +
            " FROM reservation r" +
            " JOIN member m ON m.mno = r.mno" +
            " JOIN doctor d ON r.dno = d.dno where r.mno = #{mno} order by r.rdate asc")
    public List<ReservationDto> findRByMno(int mno);

    // [6] 의사별 예약조회
    @Select("SELECT r.*, m.mname, d.dname , d.pname" +
            " FROM reservation r" +
            " JOIN member m ON m.mno = r.mno" +
            " JOIN doctor d ON r.dno = d.dno where r.dno = #{dno} order by r.rdate asc")
    public List<ReservationDto> findRByDno(int dno);
}
