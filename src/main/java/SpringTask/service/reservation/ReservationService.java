package SpringTask.service.reservation;

import SpringTask.model.dto.ReservationDto;
import SpringTask.model.mapper.reservation.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationMapper reservationMapper;

    // 예약 현황
    public List<ReservationDto> findAll(){
        List<ReservationDto> result = reservationMapper.findAll();
        return result;
    }
    // 예약
    public boolean addR(ReservationDto reservationDto){
        boolean result = reservationMapper.addR(reservationDto);
        return result;
    }

    // [3] 예약 수정
    public boolean updateR(ReservationDto reservationDto){
        boolean result = reservationMapper.updateR(reservationDto);
        return result;
    }
    // [4] 예약 삭제
    public boolean deleteR(int rno){
        boolean result = reservationMapper.deleteR(rno);
        return result;
    }
    // [5] 환자별 예약조회
    public List<ReservationDto> findByMno(int mno){
        List<ReservationDto> result = reservationMapper.findRByMno(mno);
        return result;
    }
    // [6] 의사별 예약조회
    public List<ReservationDto> findByDno(int dno){
        List<ReservationDto> result = reservationMapper.findRByDno(dno);
        return result;
    }
}
