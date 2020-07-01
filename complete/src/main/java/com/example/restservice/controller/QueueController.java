package com.example.restservice.controller;

import com.example.restservice.controller.model.queue.CreateQueueRequest;
import com.example.restservice.controller.model.queue.CreateQueueResponse;
import com.example.restservice.controller.model.queue.MyQueuesResponse;
import com.example.restservice.controller.model.queue.QueueDetailsResponse;
import com.example.restservice.controller.model.queue.QueueStatusResponse;
import com.example.restservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class QueueController {

  @Autowired private QueueService queueService;

  @PostMapping(path = "/queue")
  public ResponseEntity<CreateQueueResponse> createQueue(
      @RequestBody CreateQueueRequest createQueueRequest) {
    return ResponseEntity.ok(queueService.createQueue(createQueueRequest));
  }

  @GetMapping(path = "/queues")
  public ResponseEntity<MyQueuesResponse> getMyQueues() {
    return ResponseEntity.ok(queueService.getMyQueues());
  }

  @GetMapping(path = "/queue/{queueId}")
  public ResponseEntity<QueueDetailsResponse> getQueueDetails(
      @PathVariable("queueId") String queueId) {
    return ResponseEntity.ok(queueService.getQueueDetails(queueId));
  }

  @GetMapping(path = "/queue/status")
  public ResponseEntity<QueueStatusResponse> getQueueStatus(
      @RequestParam(required = false) String queueId,
      @RequestParam(required = false) String queueName) {
    System.out.println(queueId);
    System.out.println(queueName);
    if (queueId != null) {
      return ResponseEntity.ok(queueService.getQueueStatus(queueId));
    } else if (queueName != null) {
      return ResponseEntity.ok(queueService.getQueueStatusByName(queueName));
    } else {
      return new ResponseEntity(HttpStatus.BAD_REQUEST); // Todo Give reason
    }
  }
}
