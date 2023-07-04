package org.example.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.dto.RegisterRequest;
import org.example.service.UserService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@NoArgsConstructor
public class Subscriber implements MessageListener {

    @Autowired
    UserService userService;

    public void onMessage(Message message) {
        try {

            log.debug("MESSAGE ---> {}", message.getMessageProperties());
            String messageBody = new String(message.getBody());
            RegisterRequest registerRequest = new ObjectMapper().readValue(messageBody, RegisterRequest.class);
            userService.saveUser(registerRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
