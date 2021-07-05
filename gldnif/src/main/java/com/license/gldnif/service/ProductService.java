package com.license.gldnif.service;

import com.license.gldnif.model.ProductInfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductService {

    @Select("SELECT P.PRODUCT_NO AS productNo, P.RECEIPT_DATE AS receiptDate, O.DELIVERY_DATE AS deliveryDate, O.ORDER_NO AS orderNo, O.ORDER_INC AS orderInc, D.DELIVERY_REQUEST_NO AS deliveryRequestNo, D.CHARGE AS deliveryCharge, P.LICENSE_NO AS licenseNo, O.TAX_INVOICE AS taxInvoice, O.CODE_OF_BIZ AS cob, D.DELIVERY_INC AS deliveryInc, P.LOCKEY_NO AS lockeyNo, P.MODEL AS model, Q.QC_ID AS qcId, Q.QC AS qc, Q.QC_DATE AS qcDate, Q.QC_CHARGE AS qcCharge, Q.REMARKS AS remarks FROM PRODUCT AS P INNER JOIN ORDERS O ON O.ORDER_ID = P.ORDER_ID INNER JOIN DELIVERY AS D ON D.DELIVERY_ID = P.DELIVERY_ID INNER JOIN QC AS Q ON Q.PRODUCT_ID = P.PRODUCT_ID WHERE Q.QC_ID IN (SELECT MAX(QC_ID) FROM QC GROUP BY PRODUCT_ID);")
    List<ProductInfo> selectAll() throws Exception;

//    @Select("SELECT P.PRODUCT_NO AS productNo, P.RECEIPT_DATE AS receiptDate, O.DELIVERY_DATE AS deliveryDate, O.ORDER_NO AS orderNo, O.ORDER_INC AS orderInc, D.DELIVERY_REQUEST_NO AS deliveryRequestNo, D.CHARGE AS  deliveryCharge, P.LICENSE_NO AS licenseNo, O.TAX_INVOICE AS taxInvoice, O.CODE_OF_BIZ AS cob, D.DELIVERY_INC AS deliveryInc, P.LOCKEY_NO AS lockeyNo, P.MODEL AS model, Q.QC_ID AS qcId, Q.QC AS qc, Q.QC_DATE AS qcDate, Q.QC_CHARGE AS qcCharge, Q.REMARKS AS remarks FROM PRODUCT AS P LEFT OUTER JOIN ORDERS O ON O.ORDER_ID = P.ORDER_ID LEFT OUTER JOIN DELIVERY AS D ON D.DELIVERY_ID = P.DELIVERY_ID LEFT OUTER JOIN QC AS Q ON Q.PRODUCT_ID = P.PRODUCT_ID WHERE Q.QC_ID IN (SELECT MAX(QC_ID) FROM QC GROUP BY PRODUCT_ID);")

    @Select("SELECT P.PRODUCT_NO AS productNo, P.RECEIPT_DATE AS receiptDate, O.DELIVERY_DATE AS deliveryDate,O.ORDER_NO AS orderNo, O.ORDER_INC AS orderInc,O.CODE_OF_BIZ AS cob, D.DELIVERY_REQUEST_NO AS deliveryRequestNo, D.CHARGE AS deliveryCharge, P.LICENSE_NO AS licenseNo, O.TAX_INVOICE AS taxInvoice, D.DELIVERY_INC AS deliveryInc, P.LOCKEY_NO AS lockeyNo, P.MODEL AS model, Q.QC_ID AS qcId, Q.QC AS qc, Q.QC_DATE AS qcDate, Q.QC_CHARGE AS qcCharge, Q.REMARKS AS remarks INTO OUTFILE '/Users/chang/Desktop/2021.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\n' FROM PRODUCT AS P LEFT OUTER JOIN ORDERS O ON O.ORDER_ID = P.ORDER_ID LEFT OUTER JOIN DELIVERY AS D ON D.DELIVERY_ID = P.DELIVERY_ID LEFT OUTER JOIN QC AS Q ON Q.PRODUCT_ID = P.PRODUCT_ID WHERE Q.QC_ID IN (SELECT MAX(QC_ID) FROM QC GROUP BY PRODUCT_ID);")
    List<ProductInfo> saveExel() throws Exception;

    @Select("SELECT O.ORDER_ID AS  orderId, O.ORDER_NO AS orderNo, O.DELIVERY_DATE AS deliveryDate, O.ORDER_INC AS orderInc, O.CODE_OF_BIZ AS cob, D.DELIVERY_REQUEST_NO AS deliveryRequestNo, D.CHARGE AS deliveryCharge, O.TAX_INVOICE AS taxInvoice, D.DELIVERY_INC AS deliveryInc FROM ORDERS AS O LEFT OUTER JOIN DELIVERY D ON O.ORDER_ID = D.ORDER_ID ")
    List<ProductInfo> selectOrder() throws Exception;

    @Select("SELECT P.PRODUCT_NO AS productNo, P.RECEIPT_DATE AS receiptDate, P.LICENSE_NO AS licenseNo, P.LOCKEY_NO AS lockeyNo, P.MODEL AS model, Q.QC_ID AS qcId, Q.QC AS qc, Q.QC_DATE AS qcDate,Q.QC_CHARGE AS qcCharge,Q.REMARKS AS remarks FROM PRODUCT P LEFT OUTER JOIN QC Q ON Q.PRODUCT_ID = P.PRODUCT_ID")
    List<ProductInfo> selectProduct() throws Exception;



    @Update("UPDATE DELIVERY SET FILE_PATH = #{filepath} WHERE DELIVERY_REQUEST_NO = #{deliveryRequestNo}")
    void upload(String filepath,String deliveryRequestNo) throws Exception;

    //    @Insert("INSERT INTO PRODUCT ( PRODUCT_NO, RECEIPT_DATE, LOCKEY_NO, MODEL, LICENSE_NO, ORDER_ID, DELIVERY_ID) VALUES ( #{productNo}, #{receiptDate}, #{lockeyNo}, #{model}, #{licenseNo}, #{orderId}, #{deliveryId})")
    @Update("UPDATE PRODUCT SET LOCKEY_NO = #{lockeyNo}, MODEL = #{model}, LICENSE_NO = #{licenseNo}, ORDER_ID = #{orderId}, DELIVERY_ID = #{deliveryId} WHERE PRODUCT_NO = #{productNo}")
    @SelectKey(statement = "SELECT ORDER_ID AS orderId, DELIVERY_ID as deliveryId FROM DELIVERY WHERE DELIVERY_REQUEST_NO = #{deliveryRequestNo}", keyColumn="orderId,deliveryId", keyProperty = "orderId,deliveryId", before = true, resultType = ProductInfo.class)
    void updateProduct(ProductInfo vo) throws Exception;





//    @Select("SELECT P.PRODUCT_NO AS productNo, P.RECEIPT_DATE AS receiptDate, O.DELIVERY_DATE AS deliveryDate,O.ORDER_NO AS orderNo, O.ORDER_INC AS orderInc,O.CODE_OF_BIZ AS cob, D.DELIVERY_REQUEST_NO AS deliveryRequestNo, D.CHARGE AS deliveryCharge, P.LICENSE_NO AS licenseNo, O.TAX_INVOICE AS taxInvoice, D.DELIVERY_INC AS deliveryInc, P.LOCKEY_NO AS lockeyNo, P.MODEL AS model, Q.QC_ID AS qcId, Q.QC AS qc, Q.QC_DATE AS qcDate, Q.QC_CHARGE AS qcCharge,Q.REMARKS AS remarks INTO OUTFILE '/Users/chang/Desktop/2021.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\n' FROM PRODUCT AS P LEFT OUTER JOIN ORDERS O ON O.ORDER_ID = P.ORDER_ID LEFT OUTER JOIN DELIVERY AS D ON D.DELIVERY_ID = P.DELIVERY_ID LEFT OUTER JOIN QC AS Q ON Q.PRODUCT_ID = P.PRODUCT_ID WHERE Q.QC_ID IN (SELECT MAX(QC_ID) FROM QC GROUP BY PRODUCT_ID);")


//    엑셀저장쿼리
//    @Select("selectOne")
//    ProductInfo selectOne() throws Exception;
}

