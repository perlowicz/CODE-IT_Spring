package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.ClientDTOMapper;
import com.example.codeit_db_com.arch.course.CourseDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class TransactionDTOMapper {

    private final ClientDTOMapper clientDTOMapper;
    private final CourseDTOMapper courseDTOMapper;

    public TransactionDTOMapper(ClientDTOMapper clientDTOMapper, CourseDTOMapper courseDTOMapper) {
        this.clientDTOMapper = clientDTOMapper;
        this.courseDTOMapper = courseDTOMapper;
    }

    public TransactionDTO map(Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setCourseDTO(courseDTOMapper.map(transaction.getCourse()));
        transactionDTO.setClientDTO(clientDTOMapper.mapClientToDTO(transaction.getClient()));
        transactionDTO.setSignupDate(transaction.getSignupDate());
        transactionDTO.setExpirationDate(transaction.getExpirationDate());
        transactionDTO.setOpinion(transaction.getOpinion());

        return transactionDTO;
    }
}
