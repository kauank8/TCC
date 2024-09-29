package farias.paulino.kauan.SistemaAgendamento.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente extends Usuario {
	@CPF
	@Column(unique = true, nullable = false, length = 11 )
	private String cpf;
	
	@Column(nullable = false, length = 150)
	private String nome;
	
	@Column(nullable = false, length = 20)
	private String telefone;
	
	@Column(nullable = false)
	private String endereco;

	public Cliente(int id, String email, String senha, String nivelAcesso, String cpf, String nome, String telefone, String endereco) {
        super(id, email, senha, nivelAcesso);  // Chama o construtor da classe Usuario
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nome = nome;
    }
	
	
}
