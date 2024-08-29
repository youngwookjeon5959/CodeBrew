package scv.DevOpsunity.toss.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import scv.DevOpsunity.toss.dto.ProductsDTO;


import java.util.List;

@Mapper
@Repository
public interface TossDAO {

    public void insertProduct(ProductsDTO product);


    public List<ProductsDTO> selectOrder(String customerName);

}
