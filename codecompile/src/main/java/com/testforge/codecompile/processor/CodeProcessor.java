package com.testforge.codecompile.processor;

import com.testforge.codecompile.dto.CodeDTO;
import com.testforge.codecompile.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CodeProcessor {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;


    public ResponseDTO processCode(CodeDTO code){
        String uuid=UUID.randomUUID().toString().replace("-","");
        code.setToken(uuid);
        kafkaTemplate.send("sample-topic", "a", code);
        return new ResponseDTO(uuid);
    }



}
