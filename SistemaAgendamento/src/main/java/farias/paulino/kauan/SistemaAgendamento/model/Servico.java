package farias.paulino.kauan.SistemaAgendamento.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 50, unique = true)
    private String titulo;
    
    @Column(nullable = false)
    private int duracao;
    
    @Column(nullable = false)
    private double preco;
    
    @Column(nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(
        name = "servico_produto",
        joinColumns = @JoinColumn(name = "servico_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;
    
}
