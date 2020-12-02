package com.board.gldnif.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ProductInfo {

    private int productNo;
    private Date receiptDate;
    private Date deliveryDate;
    private int orderNo;
    private String orderInc;
    private String cob;
    private String deliveryRequestNo;
    private String deliveryCharge;
    private String licenseNo;
    private String taxInvoice;
    private String deliveryInc;
    private String lockeyNo;
    private String model;
    private int qcId;
    private String qc;
    private Date qcDate;
    private String qcCharge;
    private String remarks;
}
