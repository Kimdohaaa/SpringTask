package SpringTask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Service
public class DoctorDto {
    private  int dno;
    private  String dname;
    private  String pname;
}
