package com.license.gldnif.service;

import com.license.gldnif.model.ProductInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

public interface InsertProductService {

    @Insert("INSERT INTO ORDERS ( ORDER_NO, ORDER_INC, DELIVERY_DATE, CODE_OF_BIZ, TAX_INVOICE ) VALUES ( #{orderNo}, #{orderInc}, #{deliveryDate},#{cob}, #{taxInvoice} )")
    void insertOrder(ProductInfo vo) throws Exception;

    @Insert("INSERT INTO DELIVERY ( DELIVERY_REQUEST_NO, DELIVERY_INC, CHARGE, HP, ORDER_ID) VALUES ( #{deliveryRequestNo}, #{deliveryInc}, #{deliveryCharge},#{hp}, #{orderId} )")
    @SelectKey(statement = "SELECT ORDER_ID FROM ORDERS WHERE ORDER_NO = #{orderNo}", keyProperty = "orderId", before = true, resultType = int.class)
    void insertDelivery(ProductInfo vo) throws Exception;

    @Insert("INSERT INTO PRODUCT (PRODUCT_NO, RECEIPT_DATE) VALUES ( #{productNo}, #{receiptDate} )")
    @SelectKey(statement = "SELECT MAX(PRODUCT_NO)+1 FROM PRODUCT", keyProperty = "productNo", before = true, resultType = int.class)
    void insertPrdouct(ProductInfo vo) throws Exception;

    @Insert("INSERT INTO QC ( QC, QC_DATE, QC_CHARGE, REMARKS, PRODUCT_ID) VALUES ( #{qc}, #{qcDate}, #{qcCharge}, #{remarks}, #{productId})")
    @SelectKey(statement= "SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NO = #{productNo}", keyProperty="productId", before = true, resultType = int.class)
    void insertQc(ProductInfo vo) throws Exception;
}
