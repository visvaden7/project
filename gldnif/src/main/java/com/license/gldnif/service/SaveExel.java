package com.license.gldnif.service;


import com.license.gldnif.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
@Slf4j
public class SaveExel {

    public static void downloadExel(PrintWriter writer, List<ProductInfo> productInfoList) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        writer.write("발주번호, 발주처, 세금계산서, 납품처, 납품요구번호, 납품담당자, 입고일, 출고일, 락키번호, 제품종류, 라이선스 채널, 제품번호, 검수일, 상태, 검수담당자, 비고 \n");
        for(ProductInfo productInfo : productInfoList) {
            writer.write(productInfo.getOrderNo() +","
                    + productInfo.getOrderInc() +","
                    + productInfo.getTaxInvoice() +","
                    + productInfo.getDeliveryInc() +","
                    + productInfo.getDeliveryRequestNo() +","
                    + productInfo.getDeliveryCharge() +","
                    + format.format(productInfo.getReceiptDate()) +","
                    + format.format(productInfo.getDeliveryDate()) +","
                    + productInfo.getLockeyNo() +","
                    + productInfo.getModel() +","
                    + productInfo.getLicenseNo() +","
                    + productInfo.getProductNo() +","
                    + format.format(productInfo.getQcDate()) +","
                    + productInfo.getQc() +","
                    + productInfo.getQcCharge() +",");
            if(productInfo.getRemarks() != null){
                writer.write(productInfo.getRemarks() +"\n");
            }else{
                writer.write("" +"\n");
            }
        }
    }
}
