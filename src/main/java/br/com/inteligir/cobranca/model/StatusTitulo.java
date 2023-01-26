package br.com.inteligir.cobranca.model;

public enum StatusTitulo {
    
    PENDENTE("Pendente"), 
    RECEBDIDO("Recebido"),
    VENCIDO("Vencido");


    private final String descricao;

    private StatusTitulo(String descricao) {
        this.descricao = descricao;
    }


    public String getDescricao(){
        return descricao;
    }

    

}
