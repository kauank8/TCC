package farias.paulino.kauan.SistemaAgendamento.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @Column(nullable = false)
    private String observacoes;
    
    /*
    @OneToMany(mappedBy = "ficha", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;
	*/
    @ManyToOne
    @JoinColumn(name = "agendamento_id")  // Adicionando um relacionamento com o Agendamento
    private Agendamento agendamento;
}