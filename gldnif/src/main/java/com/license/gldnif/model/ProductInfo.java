package com.license.gldnif.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ProductInfo {

    private int productId;
    private Integer productNo;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receiptDate;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;
    private int orderId;
    private BigInteger orderNo;
    private String orderInc;
    private String cob;
    private int deliveryId;
    private String deliveryRequestNo;
    private String deliveryCharge;
    private String licenseNo;
    private String taxInvoice;
    private String deliveryInc;
    private String filepath;
    private String lockeyNo;
    private String model;
    private String phoneNo1;
    private String phoneNo2;
    private String phoneNo3;
    private String hp;
    private int qcId;
    private String qc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date qcDate;
    private String qcCharge;
    private String remarks;
    private Integer count;
    private int rowStart;
    private int rowEnd;
}
