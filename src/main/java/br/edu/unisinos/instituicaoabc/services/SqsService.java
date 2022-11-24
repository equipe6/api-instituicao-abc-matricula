package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.dtos.MatriculaDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.io.Serializable;

/**
 * Classe reponsavel pelas regras de negócio de envio de mensageria para AWS SQS.
 */
@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SqsService implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/362770054718/instituicao-abc-matriculado";

    //Injeta SsqClient
    private final SqsClient sqsClient;

    /**
     * Metodo assíncrono responsável pelo envia da mensagem para o message Broker AWS SQS.
     */
    @Async
    public void sendMessage(String messageBody) {
        SendMessageRequest messageRequest = SendMessageRequest.builder()
                .queueUrl(QUEUE_URL)
                .messageBody(messageBody)
                .build();
        SendMessageResponse messageResponse = sqsClient.sendMessage(messageRequest);
        log.info("messageResponse: " + messageResponse);
    }

    /**
     * Metodo assíncrono responsável pelo envia da mensagem para o message Broker AWS SQS.
     * Recebe MatriculaDto e converte para Json.
     */
    @Async
    public void sendMessage(MatriculaDto matriculaDto) {
        this.sendMessage(matriculaDto.toString());
    }

}
