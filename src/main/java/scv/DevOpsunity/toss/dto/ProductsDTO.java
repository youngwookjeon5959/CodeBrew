package scv.DevOpsunity.toss.dto;


public class ProductsDTO {

    private int orderNo;
    private int amount;
    private String orderName;
    private  String orderDate;
    private String customerName;


    public ProductsDTO(int amount, String orderName,String orderDate, String customerName) {

        this.amount = amount;
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    // getter 및 setter 생략



    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



}
