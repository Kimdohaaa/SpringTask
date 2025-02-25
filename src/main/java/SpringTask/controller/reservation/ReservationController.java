package SpringTask.controller.reservation;

import SpringTask.model.dto.ReservationDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import SpringTask.service.reservation.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    // [1] 예약 현황 조회
    @GetMapping
    public List<ReservationDto> findAll(){
        List<ReservationDto> result = reservationService.findAll();
        return result;
    }

    // [2] 예약 등록
    @PostMapping
    public boolean addR(@RequestBody ReservationDto reservationDto){
        boolean result = reservationService.addR(reservationDto);
        return result;
    }

    // [3] 예약 수정
    @PutMapping
    public boolean updateR(@RequestBody ReservationDto reservationDto){
        boolean result = reservationService.updateR(reservationDto);
        return result;
    }
    // [4] 예약 삭제
    @DeleteMapping
    public boolean deleteR(@RequestParam("rno") int rno){
        boolean result = reservationService.deleteR(rno);
        return result;
    }
    // [5] 환자별 예약조회
    @GetMapping("/findM")
    public List<ReservationDto> findByMno(@RequestParam("mno") int mno){
        List<ReservationDto> result = reservationService.findByMno(mno);
        return result;
    }

    // [6] 의사별 예약조회
    @GetMapping("/findD")
    public List<ReservationDto> findByDno(@RequestParam("dno") int dno){
        List<ReservationDto> result = reservationService.findByDno(dno);
        return result;
    }
}
