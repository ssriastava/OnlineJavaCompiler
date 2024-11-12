package com.testforge.worker.listener;

import com.testforge.worker.dto.CodeDTO;
import com.testforge.worker.service.CompileRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WorkerListener {

    @Autowired
    CompileRunService service;

    @KafkaListener(topics = "sample-topic", groupId = "group_id")
    void listener(CodeDTO data) {
        System.out.println("Data Received:"+data.getCode());
        service.processCode(data);
        System.out.println("Ending service: "+data.getCode());
    }
}
