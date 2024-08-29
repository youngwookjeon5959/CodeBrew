package scv.DevOpsunity.Reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scv.DevOpsunity.Reservation.dao.ResDAO;
import scv.DevOpsunity.Reservation.dto.ResDTO;

import java.util.List;

@Service("ReservationService")
public class ReservationService {

    @Autowired
    private ResDAO resDAO;

    public List<ResDTO> getAllReservations() {
        return resDAO.selectAllReservations();
    }

    public List<ResDTO> getReservationsByUserId(String user_id) {
        return resDAO.selectReservationByUserId(user_id);
    }

    public void addReservation(ResDTO resDTO) {
        resDAO.insertReservation(resDTO);
    }

    public void deleteReservationByResNum(int resNum) {
        resDAO.deleteReservationByResNum(resNum);
    }


}
