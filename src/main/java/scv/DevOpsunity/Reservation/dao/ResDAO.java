package scv.DevOpsunity.Reservation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scv.DevOpsunity.Reservation.dto.ResDTO;

import java.util.List;

@Mapper
@Repository("ResDAO")
public interface ResDAO {

    void insertReservation(ResDTO resDTO);
    void deleteReservationById(String user_id);

    List<ResDTO> selectAllReservations();

    List<ResDTO> selectReservationByUserId(@Param("user_id") String user_id);

    void deleteReservationByResNum(@Param("resNum") int resNum);
}
