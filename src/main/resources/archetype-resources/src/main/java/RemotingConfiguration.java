package com.beebank.payment;

import com.beebank.payment.business.ChargingWaitBatchBiz;

import com.beebank.payment.business.ChargingWaitBatchData;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.beebank.payment.business.AuthenticationBiz;
import com.beebank.payment.business.AuthenticationData;
import com.beebank.payment.business.ChargingBiz;
import com.beebank.payment.business.ChargingData;
import com.beebank.payment.business.ChargingQueryBatchBiz;
import com.beebank.payment.business.ChargingQueryBatchData;
import com.beebank.payment.business.ChargingQueryBiz;
import com.beebank.payment.business.ChargingQueryData;
import com.beebank.payment.business.SignatureBiz;
import com.beebank.payment.business.SignatureData;
import com.beebank.payment.business.SignatureQueryBatchBiz;
import com.beebank.payment.business.SignatureQueryBatchData;
import com.beebank.payment.business.SignatureQueryBiz;
import com.beebank.payment.business.SignatureQueryData;
import com.beebank.payment.business.SpdbResultRecv;
import com.beebank.payment.business.TransferingBiz;
import com.beebank.payment.business.TransferingData;
import com.beebank.remoting.Serializer;


@Configuration
public class RemotingConfiguration {
    
    @Bean
    public Map<String,Class<?>> bizCodeAndDataClassMap(){
        Map<String,Class<?>> map = new HashMap<String,Class<?>>();
        map.put("Signature", SignatureData.class);
        map.put("SignatureQuery", SignatureQueryData.class);
        map.put("Charging", ChargingData.class);
        map.put("ChargingQuery", ChargingQueryData.class);
        map.put("Authentication", AuthenticationData.class);
        map.put("Transfering", TransferingData.class);
        map.put("SignatureQueryBatch", SignatureQueryBatchData.class);
        map.put("ChargingQueryBatch", ChargingQueryBatchData.class);
        map.put("ChargingWaitBatch", ChargingWaitBatchData.class);
        return map;
    }
    
    
    @Bean
    public Map<String,Class<?>> bizCodeAndBizClassMap(){
        Map<String,Class<?>> map = new HashMap<String,Class<?>>();
        map.put("Signature", SignatureBiz.class);
        map.put("SignatureQuery", SignatureQueryBiz.class);
        map.put("Charging", ChargingBiz.class);
        map.put("ChargingQuery", ChargingQueryBiz.class);
        map.put("Authentication", AuthenticationBiz.class);
        map.put("Transfering", TransferingBiz.class);
        map.put("SignatureQueryBatch", SignatureQueryBatchBiz.class);
        map.put("ChargingQueryBatch", ChargingQueryBatchBiz.class);
        map.put("ChargingWaitBatch", ChargingWaitBatchBiz.class);
        return map;
    }
    
    @Bean
    public Map<String,Class<?>> channelAndReceiverClassMap(){
        Map<String,Class<?>> map = new HashMap<String,Class<?>>();
        map.put("spdb", SpdbResultRecv.class);
        return map;
    }
    
    @Bean
    public Map<String,Class<?>> channelAndReceiverDataClassMap(){
        Map<String,Class<?>> map = new HashMap<String,Class<?>>();
        map.put("spdb", SpdbResultRecv.class);
        return map;
    }
    
    @Bean
    public Serializer businessSerializer(){
        
        return null;
    }
    
    
    @Bean
    public Map<String,Serializer> channelAndSerializerMap(){
        Map<String,Serializer> map = new HashMap<String,Serializer>();
        map.put("spdb", null);
        return map;
    }

}
