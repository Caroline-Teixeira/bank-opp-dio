package br.com.bank_dio.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investiment {

    private InvestimentType investimentType;
    private double investedValue;
    private double bankReturn; // rendimento
    

    @Override
    public String toString() {
    return String.format("%s — Valor Investido: R$ %.2f | Rendimento: R$ %.2f",
            investimentType.getDescriptionType(), investedValue, bankReturn);
}


    
}
