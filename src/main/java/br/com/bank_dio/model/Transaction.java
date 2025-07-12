package br.com.bank_dio.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private TransactionType transactionType;
    private double value;
    private LocalDateTime date;


    @Override // Ex saída: DEPÓSITO de R$ 200.00 em 12-05-2025 -- 10:00:00
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy -- HH:mm:ss");
        return String.format("%s de R$ %.2f em %s",
                transactionType.getDescriptionType(), value, date.format(formatter));
    }

}
