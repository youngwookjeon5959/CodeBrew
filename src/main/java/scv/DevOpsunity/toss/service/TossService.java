package scv.DevOpsunity.toss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scv.DevOpsunity.toss.dao.TossDAO;
import scv.DevOpsunity.toss.dto.ProductsDTO;
import java.util.List;

@Service
public class TossService {


    @Autowired
    private TossDAO tossdao;

    public void insertProduct(ProductsDTO product) {
        tossdao.insertProduct(product);
    }


    public List<ProductsDTO> getOrderDetails(String customerName) {
        return tossdao.selectOrder(customerName);
    }

}
